/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author hoangnn
 */
public class Hotel {
    private String hotelId; 
    private String hotelName;
    private int numRoomsAvailable;
    private String hotelAddress;
    private String hotelPhone;
    private int hotelRating;

    //constructor
    public Hotel() {
    }
    
    public Hotel(String hotelId, String hotelName, int numRoomsAvailable, String hotelAddress, String hotelPhone, int hotelRating) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.numRoomsAvailable = numRoomsAvailable;
        this.hotelAddress = hotelAddress;
        this.hotelPhone = hotelPhone;
        this.hotelRating = hotelRating;
    }

    //getter
    public String getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getNumRoomsAvailable() {
        return numRoomsAvailable;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public int getHotelRating() {
        return hotelRating;
    }

    //setter
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setNumRoomsAvailable(int numRoomsAvailable) {
        this.numRoomsAvailable = numRoomsAvailable;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }

}
