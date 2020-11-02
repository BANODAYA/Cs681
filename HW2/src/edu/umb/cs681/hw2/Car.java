package edu.umb.cs681.hw2;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class Car {
	private String model, make;
	private int mileage, year;
	private float price;
	private int dominationCount;
	
	public Car(String make, String model, int mileage, int year, float price) {
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}
	
	public String getModel() {
		return model;
	}

	public String getMake() {
		return make;
	}

	public int getMileage() {
		return mileage;
	}

	public int getYear() {
		return year;
	}

	public float getPrice() {
		return price;
	}

	public int getDominationCount() {
		return this.dominationCount;
	}
	
public void setDominationCount(List<Car> cars) {
		
		for (Car car : cars) {
			if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
					&& (car.getYear() <= this.getYear())) {
				this.dominationCount++;
			}
		}
		this.dominationCount--; 	
	}
public static void main(String args[]) {
	List<Car> carsList = new ArrayList<>();
	carsList.add(new Car("Maserati", "Ghibli", 19, 2017, 70000));
	carsList.add(new Car("BMW", "M5", 17, 2019, 50000));
	carsList.add(new Car("Lamborghini", "Aventador", 14, 2020, 100000));
	carsList.add(new Car("Porsche", "Taycan", 16, 2018, 150000));

	carsList.forEach((Car car) -> car.setDominationCount(carsList));

	List<Car> sortByPrice = carsList.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toList());
	System.out.println("List of cars sorted by Price");
	
	sortByPrice.forEach((Car car) -> System.out.println(car.getMake() + "= " + car.getPrice()));
	System.out.println("---------------------------------------------------");
	
	List<Car> sortByMileage = carsList.stream().sorted(Comparator.comparing(Car::getMileage)).collect(Collectors.toList());
	System.out.println("List of cars sorted by Mileage");
	
	sortByMileage.forEach((Car car) -> System.out.println(car.getMake() + "= " + car.getMileage()));
	System.out.println("---------------------------------------------------");


	List<Car> sortByYear = carsList.stream().sorted(Comparator.comparing(Car::getYear)).collect(Collectors.toList());
	System.out.println("List of cars sorted by Year");
	
	sortByYear.forEach((Car car) -> System.out.println(car.getMake() + "= " + car.getYear()));
	System.out.println("---------------------------------------------------");

	
//	carsList.forEach((Car car) -> car.setDominationCount(carsList));

	List<Car> sortByDominationCount = carsList.stream().sorted(Comparator.comparing(Car::getDominationCount)).collect(Collectors.toList());
	System.out.println("List of cars sorted by Domination count");
	
	sortByDominationCount.forEach((Car car) -> System.out.println(car.getMake() + "=" + car.getDominationCount()));


	

	

	
}

}
