package views;

import java.util.ArrayList;
import utils.Inputter;

/**
 * lớp tạo ra một Menu và các method liên quan đến Menu
 *
 * @author hoangnn
 */
public class Menu {

    // ====================PROP====================
    public ArrayList<String> optionList = new ArrayList<>(); // mảng lưu các chuỗi lựa chọn
    public String title;

    // ====================CONSTRUCTOR====================
    public Menu(String title) {
        this.title = title;
    }

    // ====================METHOD====================
    public void addNewOption(String newOption) {
        optionList.add(newOption);
    }

    // print: in danh sách menu(các option)
    public void printAllOption() {
        int count = 1;
        String msg = "";
        for (String item : optionList) {
            msg = msg.concat(count + "." + item + "\n");
            count++;
        }
        printNotification(msg, title);
    }

    public int getChoice() {
        int choice = Inputter.getInteger("Input Your Choice", "Your choice must between 1 and " + optionList.size(), 1, optionList.size());
        return choice;
    }

    public static void printNotification(String msg) {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(msg);
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public static void printNotification(String msg, String title) {
        System.out.println("");
        System.out.println("----------------------------------------" + title + "----------------------------------");
        System.out.println(msg);
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
