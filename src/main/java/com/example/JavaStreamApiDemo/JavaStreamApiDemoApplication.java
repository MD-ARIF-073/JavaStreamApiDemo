package com.example.JavaStreamApiDemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JavaStreamApiDemoApplication {

	public static void main(String[] args) {

		List<Hotel> hotelList = getHotels();

                                  /*** Using Filter Method ***/

		hotelList.stream().filter(hotel ->
				hotel.getCity().equals("Toronto")).forEach(hotel ->         // In Java, forEach is a method provided by the Stream API for iterating over the elements of a stream
				System.out.println(hotel));

		hotelList.stream().filter(hotel ->
				hotel.getCity().equals("London")).forEach(hotel ->
				System.out.println(hotel));

                                /*** Using Sort Method ***/

		hotelList.stream().sorted(Comparator.comparing(Hotel::getPrice).thenComparing(Hotel::getNumberOfRooms))
				.sorted(Comparator.comparing(Hotel::getNumberOfRooms))
				.forEach(hotel -> System.out.println(hotel));

		hotelList.stream().sorted(Comparator.comparing(Hotel::getNumberOfRooms).reversed())
				.forEach(hotel -> System.out.println(hotel));

                                 /*** Using All Match Method ***/

		boolean isPriceMoreThan20 = hotelList.stream()
				.allMatch(hotel -> hotel.getPrice() > 50.00);
		System.out.println(isPriceMoreThan20);

		                         /*** Using Any Match Method ***/

		boolean anyHotelsInLosAngeles = hotelList.stream()
				.anyMatch(hotel -> hotel.getCity().equals("Los Angles"));
		System.out.println(anyHotelsInLosAngeles);

		boolean anyHotelPriceMoreThan500 = hotelList.stream()
				.anyMatch(hotel -> hotel.getPrice() > 500);
		System.out.println(anyHotelPriceMoreThan500);

                                 /*** Using Max Function ***/

		hotelList.stream()
				.max(Comparator.comparing(Hotel::getPrice))
				.ifPresent(System.out::println);

		                         /*** Using Min Function ***/

		hotelList.stream()
				.min(Comparator.comparing(Hotel::getNumberOfRooms))
				.ifPresent(System.out::println);


		                           /**** Using GroupBy Function ****/

		Map<String, List<Hotel>> hotelGroupByCity =                                     // This line declares a map called hotelGroupByCity of type Map<String, List<Hotel>>.
				hotelList.stream().collect(Collectors.groupingBy(Hotel::getCity));                                                                       // It then uses the stream method on the hotelList to create a stream of hotels.
				                                                                        // The collect method is then called on the stream to group the hotels by city.
				                                                                        // The Collectors.groupingBy(Hotel::getCity) method is used to specify that the hotels should be grouped by their city property.
				                                                                        // The result of the collect method is assigned to the hotelGroupByCity map.


		hotelGroupByCity.forEach(((city, hotel)->{              // This line iterates over the hotelGroupByCity map and prints the city and name of each hotel in the map. The forEach method is called on the hotelGroupByCity map.
			System.out.println(city);                           // The lambda expression (city, hotel) is passed to the forEach method. The lambda expression takes two arguments: city and hotel.
			hotel.forEach(System.out::println);
		}));

		                        /*** Using Map Operation ***/

		List<String> infos = hotelList.stream()
				//.map(hotel -> hotel.getHotelName().toUpperCase())  // another way is .map(Hotel::getCity) // Extracting city names using the getCity method
				.map(Hotel::getHotelName)
				.collect(Collectors.toList());

		System.out.println("Hotel Names: ");
		infos.forEach(System.out::println);

                                   /*** Using Peek Operation ***/

		List<String> names = Stream.of("bob", "peter", "mickey", "james")
				.filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e))  // Prints each filtered value to the console, but it doesn't modify the stream. It's useful for debugging purposes.
				.map(String::toUpperCase)                              // Transforms each name to uppercase, creating a stream of uppercase names.
				.peek(e -> System.out.println("Mapped value: " + e))
				.collect(Collectors.toList());

		System.out.println("Filtered Mapped Values: ");
		names.forEach(System.out::println);

                                   /*** Using Distinct operation ***/

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 1, 2, 3, 4, 10);

//      Displaying the distinct elements in the list
		list.stream().distinct().forEach(System.out::println);     // removing the duplicate items

		                           /*** Using findFirst operation ***/

		Stream.of(94, 87, 12).findFirst().ifPresent(hotels -> System.out.println(hotels));

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
