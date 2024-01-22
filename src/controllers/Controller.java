/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.Hotel;
import models.HotelList;
import ultils.Inputter;
import views.HotelListView;
import views.Menu;

/**
 *
 * @author hoangnn
 */
public class Controller {
    //====================PROP====================

    private HotelListView view;
    private HotelList hotelList;

    //====================CONSTRUCTOR====================
    public Controller() {
    }

    public Controller(HotelListView view, HotelList hotelList) {
        this.view = view;
        this.hotelList = hotelList;
    }

    public Controller(HotelListView view) {
        this.view = view;
    }

    //====================METHOD====================
    public void addHotel() {
        try {
            int choise;
            do {
                Hotel inputtedObj = this.view.getNewHotelInfomation(this.hotelList); //VIEW đưa CONTROLLER data

                this.hotelList.add(inputtedObj); //CONTROLLER đập xuống MODEL

                Menu.printNotification("Add Success");
                choise = Inputter.getYesNo("Do you want to continue (Yes/No)", "This is Required");
            } while (choise != 0);
            this.hotelList.saveToFile(); //SAVE xuống file

            Menu.printNotification("Save to File Success");

        } catch (Exception e) {
            Menu.printNotification("Save to File Failed");
            //e.printStackTrace();
        }
    }

    public void updateHotel() {

    }

    public void searchHotel() {
        int choise;
        do {
            String inputtedString = this.view.getSearchHotelInfomation(); //VIEW đưa CONTROLLER data

            ArrayList<Hotel> result = new ArrayList<>();
            if (inputtedString.matches(HotelListView.HOTEL_ID_REGEX)) {
                Hotel tmp = hotelList.getHotelById(inputtedString); //hotel tạm check null
                if (tmp != null) {
                    result.add(tmp);
                }
            } else {
                result = hotelList.getHotelByName(inputtedString);
            }

            String msg;
            if (result != null && !result.isEmpty()) {
                msg = "Found! \n";
                for (Hotel item : result) {
                    msg = msg.concat(item.toString());
                }
            } else {
                msg = "No Hotel Found!";
            }
            Menu.printNotification(msg);
            choise = Inputter.getYesNo("Do you want to continue (Yes/No)", "This is required");
        } while (choise != 0);
    }

    public void checkHotelExisted() {
        int choise;
        do {
            String targetId = Inputter.getString("Input hotelId", "That field is required", HotelListView.HOTEL_ID_REGEX);
            Hotel result = this.hotelList.getHotelById(targetId);
            String msg;
            if (result != null) {
                msg = "Found! \n".concat(result.toString());
            } else {
                msg = "No Hotel Found!";
            }
            Menu.printNotification(msg);
            choise = Inputter.getYesNo("Do you want to continue (Yes/No)", "This is required");
        } while (choise != 0);
    }

    public void displayAllHotel() {
        this.view.printAllHotelByNameDESC(hotelList);
    }

    public void deleteHotel() {
        try {
            String targetId = Inputter.getString("Input hotelId", "That field is required", HotelListView.HOTEL_ID_REGEX);
            Hotel result = this.hotelList.getHotelById(targetId);
            String msg;
            if (result != null) {
                msg = "Found! \n".concat(result.toString());
                Menu.printNotification(msg);
                int confirm = Inputter.getYesNo("Do you really want to Delete this Hotel (Yes/No)", "This is required");
                if (confirm == 1) {
                    msg = "Delete Success";
                    this.hotelList.remove(result);
                    Menu.printNotification(msg);

                    this.hotelList.saveToFile(); //SAVE xuống file
                    Menu.printNotification("Save to File Success");
                } else {
                    msg = "Canceled Delete";
                    Menu.printNotification(msg);
                }
            } else {
                msg = "No Hotel Found! Delete Failed";
                Menu.printNotification(msg);
            }
        } catch (Exception e) {
            Menu.printNotification("Save to File Failed");
            //e.printStackTrace();
        }
    }

    /*
        có thể chuyển mảng url để add đc nhiều file khác
     */
    public boolean connectDB(String urlDatabase) {
        try {
            this.hotelList = new HotelList(urlDatabase);

            Menu.printNotification("Load from File Success");
            return true;
        } catch (Exception e) {
            System.out.println("Cannot Load File");
            e.printStackTrace();
        }
        return false;
    }
}
