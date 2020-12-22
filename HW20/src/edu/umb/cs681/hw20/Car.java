package edu.umb.cs681.hw20;

import java.util.ArrayList;
import java.util.List;



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

	public int getPrice() {
		return (int) price;
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

	public static void main(String[] args) {
		
		List<Car> cars = new ArrayList<>();
		
		cars.add(new Car( "Benx", "GLE", 800, 2018, 700000));
		cars.add(new Car( "Maruti", "nano", 500, 2013, 12000));
		cars.add(new Car( "Audi", "A8", 600, 2016, 90000));
		cars.add(new Car( "Subaru", "f3", 100, 2012, 18000));
		cars.add(new Car( "Skoda", "Rapid", 600, 2019, 45000));
		cars.add(new Car( "Bmw", "M5", 200, 2020, 100000));
		
		cars.forEach((Car car) -> car.setDominationCount(cars));

		Integer Mileage = cars
							 .stream()
							 .parallel()
							 .map((Car car) -> car.getMileage())
							 .reduce(0, (result, carMileage) -> {
								 if (result == 0)				return carMileage;
								 else if (carMileage < result)	return carMileage;
								 else							return result;}	);

		System.out.println("Minimum Car Mileage --->" + Mileage);

		Integer Price = cars
						   .stream()	
						   .parallel()
						   .map((Car car) -> car.getPrice())
						   .reduce(0, (result, carPrice) -> {
							   	if (result == 0)			return carPrice;
								else if (carPrice > result)	return carPrice;
								else						return result;}	);

		System.out.println("Maximum Car Price ---> " + Price);

		Integer Make = cars
						  .stream()
						  .parallel()
						  .map((Car car) -> car.getMake())
						  .reduce(0, (result, carMake) -> 	++result,
							      (finalResult, intermediateResult) -> finalResult);

		System.out.println("Count of Car Make---> " + Make);
		
	}
}
