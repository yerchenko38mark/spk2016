import java.util.Arrays;

public class Selector {
	public static Individual[] naturalSelection(Individual[] individuals){
		Individual[] res = new Individual[Constants.POPULATION_SIZE];
		
		Arrays.sort(individuals, SortingStrategy.comparatorIncrease);
		for(int i = 0; i < Constants.POPULATION_SIZE; i++){
			res[i] = individuals[i];
		}
		return res;
	}
}
