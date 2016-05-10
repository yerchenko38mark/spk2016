import java.util.Comparator;

public class SortingStrategy {
	public static Comparator<Individual> comparatorIncrease = new Comparator<Individual>() {
		
		@Override
		public int compare(Individual o1, Individual o2) {
			float v1 = o1.getPath();
			float v2 = o2.getPath();
			
			if(v1 > v2)
				return 1;
			else 
				if(v1 == v2)
					return 0;
			else 
				return -1;
		}
	};
}
