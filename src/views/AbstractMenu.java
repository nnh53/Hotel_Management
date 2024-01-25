package views;

import controllers.Controller;

public abstract class AbstractMenu extends Menu {

    // ====================CONSTRUCTOR====================
    public AbstractMenu(String title) {
        super(title);
    }

    // ====================METHOD====================
    /*
     * Mỗi một Menu sẽ có switch case khác nhau, nhận vào controller để từ controller gọi xuống
     */
    // true thì còn tiếp tục, false là ngừng
    public abstract boolean eventHandler(int choice, Controller controller);

    // Tạo một môi trường vòng lặp để
    public void trigger(Controller controller) {
        boolean continues;
        do {
            this.printAllOption();
            int userChoice = this.getChoice();
            continues = eventHandler(userChoice, controller);
        } while (continues);
    }

}
