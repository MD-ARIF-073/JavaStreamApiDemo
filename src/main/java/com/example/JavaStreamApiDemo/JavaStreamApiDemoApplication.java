package com.example.JavaStreamApiDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class JavaStreamApiDemoApplication {

	public static void main(String[] args) {
		List<Hotel> hotelList = getHotels();

		hotelList.stream().filter(hotel ->
				hotel.getCity().equals("Toronto")).forEach(hotel ->
				System.out.println(hotel));

		hotelList.stream().filter(hotel ->
				hotel.getCity().equals("London")).forEach(hotel ->
				System.out.println(hotel));

		hotelList.stream().sorted(Comparator.comparing(Hotel::getPrice).thenComparing(Hotel::getNumberOfRooms))
//				.sorted(Comparator.comparing(Hotel::getNumberOfRooms))
				.forEach(hotel -> System.out.println(hotel));

		hotelList.stream().sorted(Comparator.comparing(Hotel::getNumberOfRooms).reversed())
				.forEach(hotel -> System.out.println(hotel));

		boolean isPriceMoreThan20 = hotelList.stream()
				.allMatch(hotel -> hotel.getPrice() > 50.00);
		System.out.println(isPriceMoreThan20);

	}

	private static List<Hotel> getHotels() {

		return Arrays.asList(
				new Hotel("Park Grand","London",10,59.99),
				new Hotel("The Chilworth","London",15,65.00),
				new Hotel("Yotel","London",8,75.99),
				new Hotel("The Bailey's","London",5,225.99),
				new Hotel("Rubens At The Palace","London",7,125.00),
				new Hotel("Ameritania","New York",2,175.00),
				new Hotel("Freehand New York","New York",20,185.00),
				new Hotel("Royalton","New York",6,325.00),
				new Hotel("Riu Plaza","New York",25,105.00),
				new Hotel("Holiday Inn","Toronto",12,95.00),
				new Hotel("DoubleTree","Toronto",11,99.99),
				new Hotel("Delta Hotels","Toronto",16,199.99),
				new Hotel("The Westin Harbour Castle","Toronto",35,329.99),
				new Hotel("The Clarence Park","Toronto",22,259.99),
				new Hotel("Kandy City Stay","Kandy",21,75.00),
				new Hotel("Lakewood","Kandy",15,109.99),
				new Hotel("Ceyloni Panorama Resort","Kandy",2,49.99)
		);
	}

}
