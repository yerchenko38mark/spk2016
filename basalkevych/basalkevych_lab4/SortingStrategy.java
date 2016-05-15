import java.util.Comparator;

public class SortingStrategy {
	public static Comparator<Individual> comparatorIncrease = new Comparator<Individual>() {
		
		@Override
		public int compare(Individual o1, Individual o2) {
			
			float v1 = FloatConverter.byteArrayToFloat(o1.getGenes());
			float v2 = FloatConverter.byteArrayToFloat(o2.getGenes());
			
			if(InputFunction.calc(v1) > InputFunction.calc(v2)) 
				return 1;
			else if(InputFunction.calc(v1) == InputFunction.calc(v2))
				return 0;
			else 
				return -1;
		}
	};
}
