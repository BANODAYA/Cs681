package edu.umb.cs681.hw3;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class Car {
	private String model, make;
	private int mileage, year;
	private int price;
	private int dominationCount;
	
	public Car(String make, String model, int mileage, int year, int price) {
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
	carsList.add(new Car("Audi", "A8", 11, 2018, 90000));

	System.out.println("Cars price List");
	
	carsList.forEach( (Car car)->System.out.println(car.getMake()+"="+car.getPrice()));

	Integer min_price = carsList.stream().map( (Car car)-> car.getPrice()).reduce(0,(result, PriceOfcar)->{
		
						if(result==0)
							return PriceOfcar;
						else if(PriceOfcar < result) 
							return PriceOfcar;
						else 
							return result;});
	
	System.out.println("Lowest car price in this list is : "+min_price);
	

	Integer max_price = carsList.stream().map( (Car car)-> car.getPrice()).reduce(0,(result, PriceOfcar)->{
		
						if(result==0)
							return PriceOfcar;
						else if(PriceOfcar < result) 
							return result;
						else 
							return PriceOfcar;});
	
	System.out.println("Expensive car price in this list is : "+max_price);
	

	Integer min_mileage = carsList.stream().map( (Car car)-> car.getMileage() ).reduce(0, (result, MileageOfcar)->{
		
						if(result==0)
							return MileageOfcar;
						else if(MileageOfcar < result) 
							return MileageOfcar;
						else 
							return result;});
	
	System.out.println("Lowest car Mileage in this list is : "+min_mileage);
	
	Integer car_count = carsList.stream().map( (Car car)-> car.getMileage() ).reduce(0, (result, MileageOfcar)->{
		
		return ++result;
	});

    System.out.println("No. of cars in this list : "+car_count);

	}

}
