import java.util.Random;

public class GAPopulation {
	private static Random randg = new Random();
	public int pop_size;
	public GAIndividual[] ind;
	public int best_index; 

	public GAPopulation(int psize, int gsize, float[] min_range,
			float[] max_range) {
		pop_size = psize;
		ind = new GAIndividual[pop_size];
		System.out.println("Початкова популяція");
		for (int i = 0; i < pop_size; i++)
			{ind[i] = new GAIndividual(gsize, min_range, max_range);
		System.out.println(i+ " "+ ind[i]+" ");}
		
		evaluate();
	}

	private void evaluate() {
		int best = 0;
		float best_fitness = ind[0].fitness;
		for (int i = 1; i < pop_size; i++) {	
			if (ind[i].fitness > best_fitness) {
				best = i;
				best_fitness = ind[i].fitness;
			}
		}
		best_index = best;
	}
	
	public GAPopulation(GAIndividual[] p) {
		pop_size = p.length;
		ind = new GAIndividual[pop_size];
		System.out.println("Нова популяція");
		for (int i = 0; i < pop_size; i++){
			ind[i] = p[i];
			System.out.println(i+ " "+ ind[i]+" ");}
		evaluate();
	}

	public GAPopulation generate(GAPopulation p, int xrate, int mrate,
			float[] min_range, float[] max_range) {
		if (xrate < 0 || xrate > 100 || mrate < 0 || mrate > 100
				|| xrate + mrate > 100)
			System.err.println("error: xrate і/чи mrate неправилно встановлені");
		GAIndividual[] newg = new GAIndividual[p.pop_size];
		
		
		
		int newg_index = 0;
		int xn = xrate * p.pop_size / 100;
		int mn = mrate * p.pop_size / 100;
		for (int i = 0; i < xn; i++) {
			int p1 = p.tr_select();
			int p2 = p.tr_select();
			newg[newg_index++] = GAIndividual.xover1p(p.ind[p1], p.ind[p2]);
		}
		for (int i = 0; i < mn; i++)
			newg[newg_index++] = p.ind[p.tr_select()].mutate(min_range,
					max_range);
		for (int i = newg_index; i < p.pop_size; i++)
			newg[i] = p.ind[p.tr_select()];
		
		return new GAPopulation(newg);
	}

	public int tr_select() {
		int s_index = randg.nextInt(pop_size);
		float s_fitness = ind[s_index].fitness;
		int tr_size = Math.min(10, pop_size);
		for (int i = 1; i < tr_size; i++) {
			int tmp = randg.nextInt(pop_size);
			if (ind[tmp].fitness > s_fitness) {
				s_index = tmp;
				s_fitness = ind[tmp].fitness;
			}
		}
		return s_index;
	}

	public String toString() {
		String s = "best individual = " + ind[best_index];

		return s;
	}
}