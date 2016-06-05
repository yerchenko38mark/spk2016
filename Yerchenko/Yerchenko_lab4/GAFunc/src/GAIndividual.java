import java.util.Random;

public class GAIndividual {
	private static Random randg = new Random(); // random gen.
	public int genome_size;
	public float[] genome;
	public float fitness;

	public GAIndividual(int gsize, float[] min_range, float[] max_range) {
		// random individual with length gsize
		// i-gen must be in range between min_range[i] and max_range[i]
		genome_size = gsize;
		genome = new float[genome_size];
		for (int i = 0; i < genome_size; i++) {
			genome[i] = randg.nextFloat() * (max_range[i] - min_range[i])
					+ min_range[i];
		}
		///////////
					//System.out.println(genome[0]);
		evalFitness();//evaluate the new individual
	}

	public GAIndividual(float d[]) {
		// create individual with same gene as d[]
		// min_range[i] <= d[i] <= max_range[i]
		genome_size = d.length;
		genome = new float[genome_size];
		for (int i = 0; i < genome_size; i++) {
			genome[i] = d[i];
		}
		evalFitness();//evaluate the new individual
	}

	public GAIndividual mutate(float[] min_range, float[] max_range) {
		float rate = 1.0f / (float) genome_size;
		float[] result = new float[genome_size];
		for (int i = 0; i < genome_size; i++)
			result[i] = genome[i];
		// point mutation
		for (int i = 0; i < genome_size; i++)
			if ((float) Math.random() < rate)
				result[i] = randg.nextFloat() * (max_range[i] - min_range[i])
						+ min_range[i];

		return new GAIndividual(result);
	}

	public static GAIndividual xover1p(GAIndividual f, GAIndividual m) {
		// single point crossover
		Random rng = new Random();
		int xpoint = 1 + rng.nextInt(1);
		float[] child = new float[f.genome_size];
		for (int i = 0; i < xpoint; i++) {
			child[i] = f.genome[i];
		}
		for (int i = xpoint; i < f.genome_size; i++) {
			child[i] = m.genome[i];
		}
		return new GAIndividual(child);
	}

	public String toString() {
		String s = "[";
		s += genome[0] + "]";
		s += " fitness = " + fitness;
		return s;
	}

	private void evalFitness() {

		fitness = (39 - 96 * genome[0] - 67 * genome[0] * genome[0] + 4 * genome[0]
						* genome[0] * genome[0]);

	}

}
