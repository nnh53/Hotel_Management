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

    // constructor
    public Hotel() {
    }

    public Hotel(String hotelId, String hotelName, int numRoomsAvailable, String hotelAddress,
            String hotelPhone, int hotelRating) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.numRoomsAvailable = numRoomsAvailable;
        this.hotelAddress = hotelAddress;
        this.hotelPhone = hotelPhone;
        this.hotelRating = hotelRating;
    }

    // getter
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

    // setter - cố tình để default để chỉ mỗi package models sử dụng đc
    void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    void setNumRoomsAvailable(int numRoomsAvailable) {
        this.numRoomsAvailable = numRoomsAvailable;
    }

    void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }

    @Override
    public boolean equals(Object that) {
        //check null
        if (that == null) {
            return false;
        }
        //check class
        if (that.getClass() != this.getClass()) {
            return false;
        }

        //compare by hotelId
        return this.getHotelId().equals(((Hotel) that).getHotelId());
    }

    @Override
    public String toString() {
        return String.format("%4s|%10s|%3d|%70s|%10s|%1d star\n", hotelId, hotelName, numRoomsAvailable, hotelAddress, hotelPhone, hotelRating);

    }
}
