package com.lab4;

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

	public GAIndividual mutate(GAIndividual gaIndividual, float[] max_reg) {

		float[] result = new float[genome_size];
		for (int i = 0; i < genome_size; i++) {
			result[i] = genome[i];
		}
		
		for (int i = 0; i < genome_size; i++) {

			String string = floatToBinary(gaIndividual.genome[i]);
			int n = string.indexOf(".");
			int pp1 = (int) (Math.random() * n);
			int pp2 = (int) (Math.random() * n);
			if (pp1 > pp2) {
				int q = pp1;
				pp1 = pp2;
				pp2 = q;
			}
			char[] charmas = string.toCharArray();
			char c = charmas[pp1];
			charmas[pp1] = charmas[pp2];
			charmas[pp2] = c;
			String end = "";
			for (int j = 0; j < charmas.length; j++) {
				end += charmas[j];
			}

			result[i] = binaryToFloat(end);
			if (result[i] > max_reg[i])
				result[i] = max_reg[i];

		}
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
		fitness = (4 - 5 * genome[0] - 26 * genome[0] * genome[0] + 2
				* genome[0] * genome[0] * genome[0]);
	}

	public static String floatToBinary(float n) {
		String str = "";
		int b;
		float n1 = n;
		while (n1 != 0) {
			b = (int) n1 % 2;
			str = b + str;
			n1 = (int) n1 / 2;
		}

		while (str.length() != 8)
			str = "0" + str;

		str += ".";
		float drob = n - (int) n;
		for (int i = 0; i < 25; i++) {
			b = (int) (drob * 2);
			str += "" + b;
			drob = (drob * 2) - (int) (drob * 2);
		}

		return str;
	}

	public static float binaryToFloat(String string) {
		float number = 0f;
		int npoint = string.indexOf(".");

		String string1 = string.substring(0, npoint);

		for (int i = string1.length() - 1, step = 0; i >= 0; i--, step++) {
			if (string1.charAt(i) == '1')
				number += Math.pow(2, step);
		}

		String string2 = string.substring(npoint + 1, string.length());
		for (int i = 0; i < string2.length() - 1; i++) {
			if (string2.charAt(i) == '1')
				number += Math.pow(2, -(i + 1));
		}

		return number;
	}
}
