import java.util.Arrays;

public class GeneticSolver {
	Individual[] initialPopulation;
	Individual[] providedPopulation;
	Individual[] selectedPopulation;
	
	public void solve(){
		Point[] points = CityNet.generateMap();
		CityNet.Create(points);
		Individual[] initialPopulation = PopulationGenerator.generatePopulation(Constants.POPULATION_SIZE);
		Arrays.sort(initialPopulation, SortingStrategy.comparatorIncrease);
		printPopulation("Base population",initialPopulation);
		selectedPopulation = initialPopulation;
		System.out.println("\nBase population AVG gistance:" + calculateAvgDistance(selectedPopulation)
				+ "\nBase population MIN gistance:" + calculateMinDistance(selectedPopulation) + "\n");
		
		for(int i = 0; i < Constants.ITERATIONS; i++){
			providedPopulation = Provider.provide(selectedPopulation);
			Arrays.sort(providedPopulation, SortingStrategy.comparatorIncrease);
			selectedPopulation = Selector.naturalSelection(providedPopulation);
			Arrays.sort(selectedPopulation, SortingStrategy.comparatorIncrease);
			float mediumY = calculateAvgDistance(selectedPopulation);
			float minY = calculateMinDistance(selectedPopulation);
			System.out.println("iteration:" + i + 
					" AVG distance:" + mediumY + "; MIN distance:" + minY);
		}
		
		printPopulation("Final population", selectedPopulation);
		
		System.out.println("\nFound way:");
		byte[] arr = selectedPopulation[Constants.POPULATION_SIZE - 1].getGenes();
		Point[] cities = CityNet.getInstance().getPoints();
		Point[] pathToVisualize = new Point[Constants.CITY_COUNT];
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println(cities[arr[i]].x + " " + cities[arr[i]].y);
			pathToVisualize[i] = new Point(cities[arr[i]].x, cities[arr[i]].y);
		}
		
		Visualizer visualizer = new Visualizer(Constants.MAX_X, Constants.MAX_Y);
		visualizer.setPoints(pathToVisualize);
		visualizer.display();
	}
	
	private float calculateAvgDistance(Individual[] population) {
		float res = 0.0f;
		for(int i = 0; i < population.length; i++){
			res += population[i].getPath();
		}
		res /= population.length;
		return res;
	}

	private float calculateMinDistance(Individual[] population) {
		float res = Float.MAX_VALUE;
		for(int i = 0; i < population.length; i++){
			if(res > population[i].getPath())
				res = population[i].getPath();
		}
		return res;
	}

	private void printPopulation(String title, Individual[] population){
		System.out.println(title);
		for(int i = 0; i < population.length; i++){
			byte[] arr = population[i].getGenes();
			float value = population[i].getPath();
			System.out.println("Individual:"+ i +"; distance:" + value +
					"; genes:" + Arrays.toString(arr));
		}
	}
}
