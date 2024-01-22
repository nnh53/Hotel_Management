package views;

import controllers.Controller;

public abstract class AbstractMenu extends Menu {

    //====================CONSTRUCTOR====================
    public AbstractMenu(String title) {
        super(title);
    }

    //====================METHOD====================
    /*
    Mỗi một Menu sẽ có switch case khác nhau, nhận vào controller để từ controller gọi xuống
     */
    //true thì còn tiếp tục, false là ngừng
    public abstract boolean eventHandler(int choise, Controller controller);

    /*
        Tạo vòng lặp để chạy hàm eventHandler
     */
    public void trigger(Controller controller) {
        boolean continues;
        do {
            this.printAllOption();
            int userChoise = this.getChoise();
            continues = eventHandler(userChoise, controller);
        } while (continues);
    }

}
