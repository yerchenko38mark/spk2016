package com.lab5;

import java.util.Date;
import java.util.Random;

public class TSP_GA {
	private TourManager tourManager = new TourManager();
	private GA ga = new GA(tourManager);

	public TSP_GA(int x[], int y[], int populationSize) {
		// Створення і додавання міста
		for (int i = 0; i < x.length; i++) {
			tourManager.add(new City(x[i], y[i]));
		}

		// Ініціалізація популяції
		Population pop = new Population(populationSize, true, tourManager);
		System.out.println("Initial distance: " + pop.getFittest().getDistance());
		
		Date currentTimeBefore = new Date();
		long timeBefore = currentTimeBefore.getTime();
		// System.out.println("Time: " + timeBefore);
		// Розвинення населеня на 100 поколінь
		
		pop = ga.evolvePopulation(pop);
		for (int i = 0; i < 100; i++) {
			pop = ga.evolvePopulation(pop);
		}
		Date currentTimeAfter = new Date();
		long timeAfter = currentTimeAfter.getTime();
		
		// System.out.println("Time: " + timeAfter);
		long time = timeAfter - timeBefore;

		// Вивід результатів
		System.out.println("Finished");
		System.out.println("Final distance: " + pop.getFittest().getDistance());
		System.out.println("Time: " + time + "мс");
		System.out.println("Solution:");
		System.out.println(pop.getFittest());
	}

	public static void main(String[] args) {
		int citySize = 10;
		
		int[] x = new int[citySize];
		int[] y = new int[citySize];
		
		Random random = new Random();
		
		System.out.println("/* ----------------------- */");
		System.out.println("City size: " + citySize);
		System.out.print("[");
		for (int i = 0; i < citySize; i++) {
			x[i] = random.nextInt(citySize);
			y[i] = random.nextInt(citySize);
			
			System.out.print(x[i] + "," + y[i] + ";");
		}
		System.out.println("]");

		System.out.println("/* ----------------------- */");
		new TSP_GA(x, y, 20);
		System.out.println("/* ----------------------- */");
		new TSP_GA(x, y, 100);
		System.out.println("/* ----------------------- */");
		new TSP_GA(x, y, 200);
		
		citySize = 20;
		
		x = new int[citySize];
		y = new int[citySize];
		
		System.out.println("/* ----------------------- */");
		System.out.println("City size: " + citySize);
		System.out.print("[");
		for (int i = 0; i < citySize; i++) {
			x[i] = random.nextInt(citySize);
			y[i] = random.nextInt(citySize);
			
			System.out.print(x[i] + "," + y[i] + ";");
		}
		System.out.println("]");

		System.out.println("/* ----------------------- */");
		new TSP_GA(x, y, 20);
		System.out.println("/* ----------------------- */");
		new TSP_GA(x, y, 100);
		System.out.println("/* ----------------------- */");
		new TSP_GA(x, y, 200);
		System.out.println("/* ----------------------- */");
	}
}