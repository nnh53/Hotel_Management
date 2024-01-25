package models;
// phòng thủ para các hàm

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author hoangnn
 */
public class HotelList extends ArrayList<Hotel> implements IDatabaseManagement {

    // ====================PROP====================
    private String databaseURL = "";

    // ====================CONSTRUCTOR====================
    public HotelList(String urlDatabase) {
        super();
        this.databaseURL = urlDatabase;
        this.loadFromFile();
    }

    // ====================METHOD====================
    public void createNewFile() {
        try {
            PrintWriter pw = new PrintWriter(this.databaseURL);
            pw.close(); // (để dọn rác)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveToFile() {
        File f = new File(this.databaseURL);
        if (!f.exists()) {
            this.createNewFile();
        }
        try {
            // xử lý file
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));
            for (Hotel item : this) {
                writer.write(item.toString().trim());
                writer.write("\n");
            }
            writer.flush();
            writer.close(); // (để dọn rác)
            return true;
        } catch (Exception e) {
            System.out.println("Save File Error");
            // e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean loadFromFile() {
        File f = new File(this.databaseURL);
        if (!f.exists()) {
            this.createNewFile();
        }
        try {
            // đọc
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null) {
                // xử lý dòng
                StringTokenizer st = new StringTokenizer(line, "|");

                String hotelId = st.nextToken().trim();
                String hotelName = st.nextToken().trim();
                int numRoomsAvailable = Integer.parseInt(st.nextToken().trim());
                String hotelAddress = st.nextToken().trim();
                String hotelPhone = st.nextToken().trim();
                int hotelRating = Integer.parseInt(st.nextToken().substring(0, 1));
                // tạo
                Hotel newHotel = new Hotel(hotelId, hotelName, numRoomsAvailable, hotelAddress, hotelPhone, hotelRating);
                this.add(newHotel);
                line = reader.readLine();
            }

            reader.close(); // (để dọn rác)
            reader = null;
            f = null;
            return true;

        } catch (Exception e) {
            System.out.println("Parse Data Error" + e);
            // e.printStackTrace();
            return false;
        }
    }

    // check is target existed in list base on id
    public boolean isExistedById(String targetId) {
        boolean isExisted = false;
        for (Hotel item : this) { // item là 1 hotel trong list
            if (item.getHotelId().equals(targetId)) {
                isExisted = true;
                break;
            }
        }
        if (isExisted) {
            System.out.println("This Id is already Existed");
            return true;
        }

        return false;
    }

    public boolean isExistedByAddress(String targetAddress) {
        boolean isExisted = false;
        for (Hotel item : this) { // item là 1 hotel trong list
            if (item.getHotelAddress().equals(targetAddress)) {
                isExisted = true;
                break;
            }
        }
        if (isExisted) {
            System.out.println("This Address is already Existed");
            return true;
        }

        return false;
    }

    public Hotel getHotelById(String targetId) {
        for (Hotel item : this) {
            if (item.getHotelId().equals(targetId)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Hotel> getHotelByName(String targetName) {
        ArrayList<Hotel> tmpList = new ArrayList<>();

        for (Hotel item : this) {
            if (item.getHotelName().toLowerCase().contains(targetName)) {
                tmpList.add(item);
            }
        }

        return (tmpList.isEmpty()) ? null : tmpList;
    }
}
