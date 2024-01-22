package views;

import java.util.Collections;
import java.util.Comparator;
import models.Hotel;
import models.HotelList;
import ultils.Inputter;
import static views.Menu.printNotification;

/**
 package chứa các method IN dữ liệu từ hotelList hoặc trả về controller
 @author hoangnn
 */
public class HotelListView {

    //====================PROP====================
    public static final String PHONE_REGEX = "^0[3-9][0-9]{8}$"; //hằng số
    public static final String HOTEL_ID_REGEX = "^H\\d{2}$"; //hằng số

    //====================CONSTRUCTOR====================
    public HotelListView() {
    }

    //====================METHOD====================
    //hàm trả về một Hotel đã hoàn thiện
    public Hotel getNewHotelInfomation(HotelList hotelList) {
        if (hotelList == null) {
            return null;
        }
        // 1. thu thập dữ liệu
        String inputtedId;
        do {
            inputtedId = Inputter.getString("Input Hotel Id: ", "That Field is Required and must be HXX Format", HOTEL_ID_REGEX);
        } while (hotelList.isExistedById(inputtedId));

        String inputtedName = Inputter.getString("Input Hotel Name: ", "That Field is Required", false);

        int inputtedNumRoomsAvailable = Inputter.getInteger("Input Number of Rooms Available: ", "That Field is Required and cannot be Negative", 0, 1000);

        //xử lý input thành menu
        String inputtedAddress = Inputter.getString("Input Hotel Address: ", "That Field is Required", false);

        String inputtedPhone = Inputter.getString("Input Hotel Phone: ", "That Field is Required and must be 10 character Vietnam PhoneNumber", PHONE_REGEX);

        int inputtedRating = Inputter.getInteger("Input Hotel Rating: ", "That Field is Required and must be bettween 1 to 6", 1, 6);

        // 2. tạo hotel
        Hotel inputtedHotel = new Hotel(inputtedId, inputtedName, inputtedNumRoomsAvailable, inputtedAddress, inputtedPhone, inputtedRating);

        return inputtedHotel;
    }

    //hàm trả về thông tin muốn search
    public String getSearchHotelInfomation() {
        Menu menuSearch = new Menu("Search Hotel Menu");
        menuSearch.addNewOption("Search by hotelId");
        menuSearch.addNewOption("Search by hotelName");
        int choise;
        while (true) {
            menuSearch.printAllOption();
            choise = menuSearch.getChoise();
            switch (choise) {
                case 1: {
                    String targetId = Inputter.getString("Input hotelId", "That field is required", HOTEL_ID_REGEX);
                    return targetId;
                }
                case 2: {
                    String targetId = Inputter.getString("Input hotelName", "That field is required", false);
                    return targetId;
                }
            }
        }
    }

    public void printAll(HotelList hotelList) {
        if (hotelList == null || hotelList.isEmpty()) {
            Menu.printNotification("Had nothing to print");
            return;
        }

        String msg = "";
        for (Hotel item : hotelList) { //item là 1 thằng Hotel
            msg = msg.concat(item.toString());
        }
        printNotification(msg, "Hotel List");

    }

    public void printAllHotelByNameDESC(HotelList hotelList) {
        //SORT
        Collections.sort(hotelList, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel o1, Hotel o2) {
                return o2.getHotelName().compareTo(o1.getHotelName()); //reserver o2 o1
            }
        });

        this.printAll(hotelList);
    }

}
