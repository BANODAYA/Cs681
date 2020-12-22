package edu.umb.cs681.hw19;

import java.util.ArrayList;
import java.util.List;

public class Car {
	
	private int colorCode;
	
	private String model, make;
	private int mileage, year;
	private float price;
	private int dominationCount;

	public Car(int colorCode, String make, String model, int mileage, int year, float price) {
		this.colorCode = colorCode;
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}
	
	public int getColorCode() {
		return this.colorCode;
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
		
		cars.add(new Car(6767, "Benx", "GLE", 800, 2018, 700000));
		cars.add(new Car(4646, "Maruti", "nano", 500, 2013, 12000));
		cars.add(new Car(2929, "Audi", "A8", 600, 2016, 90000));
		cars.add(new Car(1246, "Subaru", "f3", 100, 2012, 18000));
		cars.add(new Car(8695, "Skoda", "Rapid", 600, 2019, 45000));
		cars.add(new Car(5463, "Bmw", "M5", 200, 2020, 100000));

		cars.forEach((Car car) -> car.setDominationCount(cars));
		
		Integer colorcode = cars
							 .stream()
			                 .parallel()
			                 .map((Car car) -> car.getColorCode())
			                 .reduce(0, (result, carColorCode) -> result += carColorCode,
			                         (finalResult, intermediateResult) -> finalResult + intermediateResult);

        System.out.println("Sum Of Car Color Code ---> " + colorcode);

		Integer Mileage = cars
							 .stream()
							 .parallel()
							 .map((Car car) -> car.getMileage())
							 .reduce(0, (result, carMileage) -> {
								 if (result == 0)				return carMileage;
								 else if (carMileage < result)	return carMileage;
								 else							return result;}	);

		System.out.println("Minimum Car Mileage ---> " + Mileage);

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

		System.out.println("Count of Car Make ---> " + Make);
		
	}
}
