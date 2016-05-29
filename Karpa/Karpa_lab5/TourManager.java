package com.lab5;

import java.util.ArrayList;
import java.util.List;

public class TourManager {

	// Зберігання відстаней міст
	private List<City> destinationCities = new ArrayList<City>();

	// Додавання відстанні міста
	public void add(City city) {
		destinationCities.add(city);
	}

	// Отримання міста
	public City get(int index) {
		return (City) destinationCities.get(index);
	}

	// Отримання к-сті міст та населених пунктів
	public int size() {
		return destinationCities.size();
	}
}