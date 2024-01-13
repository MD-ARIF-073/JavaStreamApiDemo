package com.example.JavaStreamApiDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel {

    private String hotelName;
    private String city;
    private int numberOfRooms;
    private double price;

}
