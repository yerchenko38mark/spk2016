import java.util.Arrays;
import java.util.Random;

public class PopulationGenerator {
	
	private static Random rand = new Random();
	
	public static Individual[] generatePopulation(int size){
		Individual[] res = new Individual[size];
		
		for(int i = 0; i < size; i++){
			byte mas[] = new byte[Constants.CITY_COUNT];
			
			for(int j = 0; j < Constants.CITY_COUNT; j++){
				mas[j] = (byte)j;
			}
			
			for(int j = 0; j < Constants.CITY_COUNT; j++){
				int pos1 = rand.nextInt(Constants.CITY_COUNT);
				int pos2 = rand.nextInt(Constants.CITY_COUNT);
				byte temp = mas[pos1];
				mas[pos1] = mas[pos2];
				mas[pos2] = temp;
			}
			
			res[i] = new Individual(mas);
			
			System.out.println("generate item #" + i +
					" arr:" + Arrays.toString(mas));
		}
		return res;
	}
}
