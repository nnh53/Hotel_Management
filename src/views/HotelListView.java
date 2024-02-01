package views;

import java.util.Collections;
import java.util.Comparator;
import models.Hotel;
import models.HotelList;
import utils.Inputter;

/**
 * package chứa các method IN dữ liệu từ hotelList hoặc trả về controller
 *
 * @author hoangnn
 */
public class HotelListView {

    // ====================PROP====================
    public static final String PHONE_REGEX = "^0[3-9][0-9]{8}$"; // hằng số
    public static final String HOTEL_ID_REGEX = "^H\\d{2}$"; // hằng số
    public static final String HOTEL_ID_MSG = "That Field is Required and must be HXX Format";

    // ====================CONSTRUCTOR====================
    public HotelListView() {
    }

    // ====================METHOD====================
    /**
     * hàm trả về Hotel người dùng nhập, nhận vào hotelList từ model để check trùng
     *
     * @param hotelList
     * @return Hotel
     */
    public Hotel getNewHotelInformation(HotelList hotelList) {
        if (hotelList == null) {
            return null;
        }
        // 1. thu thập dữ liệu
        // id
        String inputtedId;
        do {
            inputtedId = Inputter.getString("Input Hotel Id: ", "That Field is Required and must be HXX Format", HOTEL_ID_REGEX);
        } while (hotelList.isExistedById(inputtedId));
        // name
        String inputtedName = Inputter.getString("Input Hotel Name: ", "That Field is Required", false);
        // num of room
        int inputtedNumRoomsAvailable = Inputter.getInteger("Input Number of Rooms Available: ", "That Field is Required and cannot be Negative", 0, 1000);
        // address
        String inputtedAddress;
        do {
            inputtedAddress = getNewAddressInformation();
        } while (hotelList.isExistedByAddress(inputtedAddress));
        // phone number
        String inputtedPhone = Inputter.getString("Input Hotel Phone: ", "That Field is Required and must be 10 character Vietnam PhoneNumber", PHONE_REGEX);
        // rating
        int inputtedRating = Inputter.getInteger("Input Hotel Rating: ", "That Field is Required and must be between 1 to 6", 1, 6);
        // 2. tạo hotel
        Hotel inputtedHotel = new Hotel(inputtedId, inputtedName, inputtedNumRoomsAvailable, inputtedAddress, inputtedPhone, inputtedRating);
        return inputtedHotel;
    }

    /**
     * hàm trả về Hotel người dùng ĐÃ update, nhận vào hotel muốn update
     *
     * @param hotel
     * @return Hotel
     */
    public Hotel getUpdateHotelInformation(Hotel hotel) {
        // 1. tạo clone hotel từ hotel cũ
        // keep (cannot be update)
        String hotelId = hotel.getHotelId();
        String hotelName = hotel.getHotelName();
        // update
        int numRoomsAvailable = hotel.getNumRoomsAvailable();
        String hotelAddress = hotel.getHotelAddress();
        String hotelPhone = hotel.getHotelPhone();
        int hotelRating = hotel.getHotelRating();
        // tạo menu
        Menu menuSearch = new Menu("Update Hotel Menu");
        menuSearch.addNewOption("Update numRoomsAvailable");
        menuSearch.addNewOption("Update hotelAddress");
        menuSearch.addNewOption("Update hotelPhone");
        menuSearch.addNewOption("Update hotelRating");
        menuSearch.addNewOption("View Current State ");
        menuSearch.addNewOption("Update Changed");
        // update
        int choice;
        while (true) {
            menuSearch.printAllOption();
            choice = menuSearch.getChoice();
            switch (choice) {
                case 1: {
                    numRoomsAvailable = Inputter.getInteger("Input Number of Rooms Available: ", "That Field is Required and cannot be Negative", 0, 1000);
                    break;
                }
                case 2: {
                    hotelAddress = getNewAddressInformation();
                    break;
                }
                case 3: {
                    hotelPhone = Inputter.getString("Input Hotel Phone: ", "That Field is Required and must be 10 character Vietnam PhoneNumber", PHONE_REGEX);
                    break;
                }
                case 4: {
                    hotelRating = Inputter.getInteger("Input Hotel Rating: ", "That Field is Required and must be between 1 to 6", 1, 6);
                    break;
                }
                case 5: {
                    Hotel inputtedHotel = new Hotel(hotelId, hotelName, numRoomsAvailable,
                            hotelAddress, hotelPhone, hotelRating);
                    System.out.println(inputtedHotel.toString());
                    break;
                }
                case 6: {
                    Hotel inputtedHotel = new Hotel(hotelId, hotelName, numRoomsAvailable, hotelAddress, hotelPhone, hotelRating);
                    Menu.printNotification("Update was made \n" + (inputtedHotel.toString()));
                    return inputtedHotel;
                }
            }
        }
    }

    /**
     * hàm người dùng nhập thông tin address chi tiết
     *
     * @return String
     */
    private String getNewAddressInformation() {
        String houseNumber = Inputter.getString("Input House Number: ", "That Field is Required", false).concat(" ");
        String street = Inputter.getString("Input Street: ", "That Field is Required", false);
        String ward = "Ward " + Inputter.getString("Input Ward: ", "That Field is Required", false);
        String district = Inputter.getString("Input District: ", "That Field is Required", false).concat(" District");
        String city = Inputter.getString("Input City: ", "That Field is Required", false).concat(" City");
        String address = String.join(", ", houseNumber.concat(street), ward, district, city);
        return address;
    }

    /**
     * hàm trả về hotelId hoặc hotelName muốn search
     *
     * @return String format hotelId hoặc hotelName
     */
    public String getSearchHotelInformation() {
        Menu menuSearch = new Menu("Search Hotel Menu");
        menuSearch.addNewOption("Search by hotelId");
        menuSearch.addNewOption("Search by hotelName");
        int choice;
        while (true) {
            menuSearch.printAllOption();
            choice = menuSearch.getChoice();
            switch (choice) {
                case 1: {
                    String targetId = Inputter.getString("Input hotelId", "That Field is Required and must be HXX Format", HOTEL_ID_REGEX);
                    return targetId;
                }
                case 2: {
                    String targetId = Inputter.getString("Input hotelName", "That field is required", false);
                    return targetId;
                }
            }
        }
    }

    /**
     * print tất cả phần tử trong list, nhận vào list muốn print
     *
     * @param hotelList
     */
    public void printAllHotel(HotelList hotelList) {
        if (hotelList == null || hotelList.isEmpty()) {
            Menu.printNotification("Had nothing to print");
            return;
        }
        String msg = "";
        for (Hotel item : hotelList) { // item là 1 thằng Hotel
            msg = msg.concat(item.toString());
        }
        Menu.printNotification(msg, "Hotel List");
    }

    /**
     * print tất cả phần tử trong list theo chiều DESC, nhận vào list muốn print
     *
     * @param hotelList
     */
    public void printAllHotelByNameDESC(HotelList hotelList) {
        if (hotelList == null || hotelList.isEmpty()) {
            Menu.printNotification("Had nothing to print");
            return;
        }
        // 1. SORT DESC name
        Collections.sort(hotelList, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel o1, Hotel o2) {
                return o2.getHotelName().compareTo(o1.getHotelName()); // reserver o2 o1
            }
        });
        // 2. Print
        this.printAllHotel(hotelList);
    }
}
