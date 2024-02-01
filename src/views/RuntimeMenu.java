package views;

import controllers.Controller;

/**
 * lớp tạo ra một Menu trong quá trình chạy chương trình và các method liên quan đến Menu
 */
public abstract class RuntimeMenu extends Menu {

    // ====================CONSTRUCTOR====================
    public RuntimeMenu(String title) {
        super(title);
    }

    // ====================METHOD====================

    /**
     * hàm handle sự kiện của từng Option trong Menu. hàm trả về boolean quyết định có tiếp tục Menu
     * hay không
     * 
     * @param choice
     * @param controller
     * @return boolean quyết định có tiếp tục Menu hay không
     */
    public abstract boolean eventHandler(int choice, Controller controller);

    /**
     * hàm trigger-tạo một môi trường vòng lặp để chạy Menu. nhận vào controller để controller xử lý
     * sự kiện
     * 
     * @param controller
     */
    public void trigger(Controller controller) {
        boolean continues;
        do {
            this.printAllOption();
            int userChoice = this.getChoice();
            continues = eventHandler(userChoice, controller);
        } while (continues);
    }

}
