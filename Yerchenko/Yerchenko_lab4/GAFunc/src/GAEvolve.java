

public class GAEvolve { 
	public float[] best_fitness;//best_fitness[i] best result for i - generation
	public GAIndividual best_ind; //best individual(x) in current evolution
	public GAEvolve(int generations, int pop_size, int genome_size, int xrate,int mrate, float[] min_range, float[] max_range){ 
		// xrate: mating frequency	
		// mrate: mutation frequency
		best_fitness = new float[generations];  
		GAPopulation gap = new GAPopulation(pop_size,genome_size,min_range,max_range); 
		best_fitness[0] = gap.ind[gap.best_index].fitness; 

		for(int i=1; i < generations; i++){ 
			gap = gap.generate(gap,xrate,mrate,min_range,max_range); 
			best_fitness[i] = gap.ind[gap.best_index].fitness; 
			System.out.println("Best in generation " + best_fitness[i]);
			} 
		best_ind = gap.ind[gap.best_index];
		} 
	
	public static void main(String[] args) {
			 //set range
			 float[] min = new float[]{-10f}; //min ranage
			 float[] max = new float[]{10f}; // max range
			 GAEvolve gae = new GAEvolve(10,50,1,70,20,min,max);
			 System.out.println("Results:");
			 System.out.println(gae.best_ind);
			
			} 
		}
	

