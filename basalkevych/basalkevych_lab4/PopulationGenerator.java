import java.util.Random;

public class PopulationGenerator {
	private static Random rand = new Random();
	
	public static Individual[] generatePopulation(int size){
		Individual[] res = new Individual[size];
		for(int i = 0; i < size; i++){
			float value = (float)rand.nextInt(64) + 
					(float)rand.nextInt(100)/100.0f - 
					Constants.SHIFT;
			byte[] byteArray = FloatConverter.floatToByteArray(value);
			res[i] = new Individual(byteArray);
			
		}
		return res;
	}
}
