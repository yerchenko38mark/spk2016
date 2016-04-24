import java.util.Arrays;

public class GeneticSolver {
	Individual[] initialPopulation;
	Individual[] newPopulation;
	Individual[] selectedPopulation;
	
	public void solve(){
		Individual[] initialPopulation = PopulationGenerator.generatePopulation(Constants.POPULATION_SIZE);
		Arrays.sort(initialPopulation, SortingStrategy.comparatorIncrease);
		printPopulation("Base population",initialPopulation);
		
		selectedPopulation = initialPopulation;
		
		for(int i=0;i<Constants.NUMBER_OF_ITERATIONS; i++){
			newPopulation = Provider.provide(selectedPopulation);
			Arrays.sort(newPopulation, SortingStrategy.comparatorIncrease);

			selectedPopulation = Selector.naturalSelection(newPopulation);
			Arrays.sort(selectedPopulation, SortingStrategy.comparatorIncrease);

			printPopulation("ITERATION #" + i,selectedPopulation);
			
			float avgX = calcAvgX(selectedPopulation);
			float avgY = calcAvgY(selectedPopulation);
			System.out.println("ITERATION # "+ i +" avarage X=" + avgX + 
					" avarage Y:" + avgY + "\n");
		}
		
		printPopulation("----------------------\nFinal population",selectedPopulation);
	}
	
	private float calcAvgY(Individual[] population) {
		float res = 0.0f;
		for(int i = 0; i < population.length; i++){
			res+=InputFunction.calc(population[i].getFloatValue());
		}
		res/=population.length;
		return res;
	}

	private float calcAvgX(Individual[] population) {
		float res = 0.0f;
		for(int i = 0; i < population.length; i++){
			res+=population[i].getFloatValue();
		}
		res/=population.length;
		return res;
	}

	private void printPopulation(String title, Individual[] genes){
		System.out.println(title);
		for(int i = 0; i < genes.length; i++){
			byte[] byteArray = genes[i].getGenes();
			float v = genes[i].getFloatValue();
			float value = InputFunction.calc(v);
			System.out.println("Individual #" + i + "\n x=" + v + "\n f(x)=" + value + 
					"\n As byte array:" + Arrays.toString(byteArray) + "\n");
		}
	}
}
