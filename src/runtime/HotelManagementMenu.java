package runtime;

import controllers.Controller;
import views.AbstractMenu;
import views.HotelListView;

/**
 * @author hoangnn
 */
public class HotelManagementMenu extends AbstractMenu {

    public HotelManagementMenu() {
        super("Hotel Management Menu");
        this.addNewOption("Add Hotel");
        this.addNewOption("Check Hotel Existed");
        this.addNewOption("Update Hotel");
        this.addNewOption("Delete Hotel");
        this.addNewOption("Search Hotel");
        this.addNewOption("Display All Hotel");
        this.addNewOption("Quit");
    }

    @Override
    public boolean eventHandler(int choice, Controller controller) {
        switch (choice) {
            case 1: {
                controller.addHotel();
                return true;
            }
            case 2: {
                controller.checkHotelExisted();
                return true;
            }
            case 3: {
                controller.updateHotel();
                return true;
            }
            case 4: {
                controller.deleteHotel();
                return true;
            }
            case 5: {
                controller.searchHotel();
                return true;
            }
            case 6: {
                controller.displayAllHotel();
                return true;
            }
            case 7: {
                System.out.println("Bye Bye");
                return false;
            }
            default: {
                return true;
            }
        }
    }

    public static void main(String[] args) {
        final String urlDatabase = "F:\\FPT\\Ki 3\\LAB211\\Hotel_Management-hoangnnSE183190\\database\\Hotel.dat";
        // =========INITIALIZE=========
        // view
        HotelListView hotelListView = new HotelListView();

        // controller
        Controller controller = new Controller(hotelListView); // về sau có thể còn view khác nữa

        // model
        controller.connectDB(urlDatabase);
        // =========USE=========
        HotelManagementMenu application = new HotelManagementMenu();
        application.trigger(controller);
    }

}
