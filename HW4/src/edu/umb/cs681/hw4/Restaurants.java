package edu.umb.cs681.hw4;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class Restaurants {
	private String name, location;
	private double rating;
	private int avg_price;
	private int capacity;
	
	
public Restaurants(String name, String location, double rating, int avg_price, int capacity) {
		
		this.name = name;
		this.location = location;
		this.rating = rating;
		this.avg_price = avg_price;
		this.capacity = capacity;
	}


public String getName() {
	return name;
}


public String getLocation() {
	return location;
}


public double getRating() {
	return rating;
}


public int getAvg_price() {
	return avg_price;
}


public int getCapacity() {
	return capacity;
}


public static void main(String args[]) {
	List<Restaurants> list = new ArrayList<>();
	list.add(new Restaurants("Yard House", "Brookline", 4.5, 40, 100));
	list.add(new Restaurants("Greco", "NewBury", 4.1, 20, 20));
	list.add(new Restaurants("Row 34", "Congrees Street", 4.6, 80, 200));
	list.add(new Restaurants("Lincoln Traven", "South Boston", 4.4, 55, 140));
	list.add(new Restaurants("Mirchi Nation", "Marlborough", 4.3, 27, 50));



	Restaurants rating = list.stream()
			.max(Comparator.comparing(Restaurants::getRating)).get();
	System.out.println("Restaurant with highest rating : " + rating.getRating());

	Restaurants avgPrice = list.stream()
			.min(Comparator.comparing(Restaurants::getAvg_price)).get();
	System.out.println("Restaurant with Lowest Avg price: " + avgPrice.getAvg_price());

	List<Restaurants> sortedByRating = list.stream()
			.sorted(Comparator.comparing(Restaurants::getRating))
			.collect(Collectors.toList());
	System.out.println("Restaurants overall Rating : ");
	sortedByRating.forEach((Restaurants r) -> System.out.println(r.getName() + ": " + r.getRating()));
	
	List<Restaurants> sortedByCost = list.stream()
			.sorted(Comparator.comparing(Restaurants::getAvg_price))
			.collect(Collectors.toList());
	System.out.println("Restaurants Average cost : ");
	sortedByCost.forEach((Restaurants r) -> System.out.println(r.getName() + ": " + r.getAvg_price()));

	}

}

