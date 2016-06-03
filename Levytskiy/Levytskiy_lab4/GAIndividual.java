import java.util.Random;

public class GAIndividual {
	private static Random randg = new Random(); 
	public int genome_size;
	public float[] genome;
	public float fitness;

	public GAIndividual(int gsize, float[] min_range, float[] max_range) {
		genome_size = gsize;
		genome = new float[genome_size];
		for (int i = 0; i < genome_size; i++) {
			genome[i] = randg.nextFloat() * (max_range[i] - min_range[i])
					+ min_range[i];
		}
		evalFitness();
	}

	public GAIndividual(float d[]) {
		genome_size = d.length;
		genome = new float[genome_size];
		for (int i = 0; i < genome_size; i++) {
			genome[i] = d[i];
		}
		evalFitness();
	}

	public GAIndividual mutate(float[] min_range, float[] max_range) {
		float rate = 1.0f / (float) genome_size;
		float[] result = new float[genome_size];
		for (int i = 0; i < genome_size; i++)
			result[i] = genome[i];
		for (int i = 0; i < genome_size; i++)
			if ((float) Math.random() < rate)
				result[i] = randg.nextFloat() * (max_range[i] - min_range[i])
						+ min_range[i];

		return new GAIndividual(result);
	}

	public static GAIndividual xover1p(GAIndividual f, GAIndividual m) {
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
		int a = 12;
		int b = -8;
		int c = -40;
		int d = 3;
		fitness = -(a + b * genome[0] + c * genome[0] * genome[0] + d
				* genome[0] * genome[0] * genome[0]);
	}

}