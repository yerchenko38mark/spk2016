import java.util.Random;

public class GAPopulation {
	private static Random randg = new Random();// random gen.
	public int pop_size;
	public GAIndividual[] ind;
	public int best_index; // index of best individual in array ind

	// best_fitness = ind[best_index].fitness
	public GAPopulation(int psize, int gsize, float[] min_range,
			float[] max_range) {
		// random population with size pop_size
		// psize: length of population
		// gsize: chromosome length
		pop_size = psize;
		ind = new GAIndividual[pop_size];
		System.out.println("Initial population");
		for (int i = 0; i < pop_size; i++)
			{ind[i] = new GAIndividual(gsize, min_range, max_range);
		System.out.println(i+ " "+ ind[i]+" ");}
		//System.out.println();
		
		evaluate();
	}

	public GAPopulation(GAIndividual[] p) {
		// create population with same individuals as p
		pop_size = p.length;
		ind = new GAIndividual[pop_size];
		System.out.println("New population");
		for (int i = 0; i < pop_size; i++){
			ind[i] = p[i];
			System.out.println(i+ " "+ ind[i]+" ");}
		evaluate();
	}

	public GAPopulation generate(GAPopulation p, int xrate, int mrate,
			float[] min_range, float[] max_range) {
		//creating new population with p, xrate - % of population is mating
		// mrate - % of population mutated, rest - recreation
		if (xrate < 0 || xrate > 100 || mrate < 0 || mrate > 100
				|| xrate + mrate > 100)
			System.err.println("error: xrate and/or mrate set incorrectly");
		GAIndividual[] newg = new GAIndividual[p.pop_size];
		
		
		
		int newg_index = 0;
		int xn = xrate * p.pop_size / 100;
		//xn: amount of sons to mate
		int mn = mrate * p.pop_size / 100;
		// mn: amount of sons to mutate
		for (int i = 0; i < xn; i++) {
			// select to parents for cross-over:
			int p1 = p.tr_select();
			int p2 = p.tr_select();
			//System.out.println(p1+ " "+ p2);
			newg[newg_index++] = GAIndividual.xover1p(p.ind[p1], p.ind[p2]);
		}
		// mutation
		for (int i = 0; i < mn; i++)
			newg[newg_index++] = p.ind[p.tr_select()].mutate(min_range,
					max_range);
		// recreation:
		for (int i = newg_index; i < p.pop_size; i++)
			newg[i] = p.ind[p.tr_select()];
		
		//for (int i = 0; i < p.pop_size; i++) {
		//	System.out.println(i + " " + p.ind[i]);}
		
		return new GAPopulation(newg);
	}

	public int tr_select() {
		//tournament selection of size pop_size/10
		int s_index = randg.nextInt(pop_size);
		float s_fitness = ind[s_index].fitness;
		int tr_size = Math.min(10, pop_size);
		for (int i = 1; i < tr_size; i++) {
			int tmp = randg.nextInt(pop_size);
			if (ind[tmp].fitness > s_fitness) {//< for min//>for max
				s_index = tmp;
				s_fitness = ind[tmp].fitness;
			}
		}
		return s_index;
	}

	private void evaluate() {
		//evaluation
		int best = 0;
		// index of best individual
		float best_fitness = ind[0].fitness;
		float sum = ind[0].fitness;
		for (int i = 1; i < pop_size; i++) {
			sum += ind[i].fitness;
			if (ind[i].fitness > best_fitness) {//< for min//>for max
				best = i;
				best_fitness = ind[i].fitness;
			}
		}
		best_index = best;
	}

	public String toString() {
		String s = "best individual = " + ind[best_index];

		return s;
	}

}
