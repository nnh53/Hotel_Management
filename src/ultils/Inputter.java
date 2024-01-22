package ultils;

/*
    còn có thể nâng cấp
    - hoàn thiện date
    - throw 1 dạng Error cụ thể
    - sanitize input của từng lúc sử dụng hàm vd getString(null, null, null)
 */
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.Scanner;

public class Inputter {

    public static Scanner sc = new Scanner(System.in);

    //ép nhập số nguyên
    public static int getInteger(String inpMsg, String errMsg) {
        System.out.println(inpMsg);
        while (true) {
            try {
                int number = Integer.parseInt(sc.nextLine().trim());
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    //ép nhập số nguyên trong khoảng
    public static int getInteger(String inpMsg, String errMsg, int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        System.out.println(inpMsg);
        while (true) {
            try {
                int number = Integer.parseInt(sc.nextLine().trim());
                if (number < lowerBound || number > upperBound) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }

    }

    //ép nhập số thực
    public static double getDouble(String inpMsg, String errMsg) {
        System.out.println(inpMsg);
        while (true) {
            try {
                double number = Double.parseDouble(sc.nextLine().trim());
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    //ép nhập số thực trong khoảng
    public static double getDouble(String inpMsg, String errMsg, double lowerBound, double upperBound) {
        if (lowerBound > upperBound) {
            double tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        System.out.println(inpMsg);
        while (true) {
            try {
                double number = Double.parseDouble(sc.nextLine().trim());
                if (number < lowerBound || number > upperBound) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    //ép nhập chuỗi
    public static String getString(String inpMsg, String errMsg, boolean allowEmpty) {
        System.out.println(inpMsg);
        String str;
        do {
            str = sc.nextLine().trim();
        } while (!allowEmpty && str.isEmpty());
        return str.trim();
    }

    //overload ép nhập chuỗi kèm regex
    public static String getString(String inpMsg, String errMsg, String regex) {
        System.out.println(inpMsg);
        while (true) {
            try {
                String str = str = sc.nextLine();
                if (!str.matches(regex)) {
                    throw new Exception();
                }
                return str.trim();
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    public static boolean getBoolean(String inpMsg, String errMsg) {
        //System.out.println("Please enter True or False");
        final String booleanRegex = "^(?i)(true|false)$";
        while (true) {
            try {
                String str = Inputter.getString(inpMsg, errMsg, false).trim(); //ko cho empty
                if (!str.matches(booleanRegex)) {
                    throw new Exception();
                }
                return Boolean.valueOf(str);
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    /**
    @param inpMsg
    @param errMsg
    @return 1 if yes, 0 if no
     */
    public static int getYesNo(String inpMsg, String errMsg) {
        //System.out.println("Please enter Yes or No");
        final String yesnoRegex = "^(?i)(yes|no)$";
        while (true) {
            try {
                String str = Inputter.getString(inpMsg, errMsg, false).trim(); //ko cho empty
                if (!str.matches(yesnoRegex)) {
                    throw new Exception();
                }
                if (str.contains("yes")) {
                    return 1;
                } else {
                    return 0;
                }

            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    public static String getDate(String inpMsg, String errMsg) {
        //==========chú ý rằng nếu tháng có 30 ngày mà nhập 31 ngày thì tự động +1 lên ==========//
        String str;

        //nếu mà thay đổi format THÌ thay đổi dấu '/' trong regex
        String inpRegex = "dd/MM/yyyy";
        String outRegex = "dd/MM/yyyy";
        String regex = "^(0?[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/\\d{4}$"; //tui đang làm là dấu '/'

        SimpleDateFormat inpFormatter = new SimpleDateFormat(inpRegex);
        SimpleDateFormat outFormatter = new SimpleDateFormat(outRegex);

        while (true) {
            try {
                str = getString(inpMsg, errMsg, false);
                if (!str.matches(regex) || !checkFebValid(str)) //2001-2-31 ?? //2000-2-31 ??
                {
                    throw new Exception();
                }
                //biến chuỗi nhập vào thành date chuẩn RFC 822: Mon, 22 Sep 2023 14:30:00 +0000
                Date dateRFC = inpFormatter.parse(str);

                //đổi chuẩn RFC 822 outRegex: dd/MM/yyyy
                String dateFormat = outFormatter.format(dateRFC);

                //******* giờ nếu không muốn tự động +1 thì check if với ngta nhập
                //test
                //System.out.println("str:" + str + ",dateFormat:" + dateFormat);
                if (str.equals(dateFormat)) {
                    return dateFormat;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Date is invalid");
            }
        }
    }

    //cách lấy data từ thư viện date
    //private Date expiredDate;
    //public String getExpiredDate() {
    //    String str = String.format("%2d/%2d/%4d", date.getDate(), date.getMonth()+1, date.getYear()+1900);
    //    return str;
    //}
    //hàm này chỉ dùng để check riêng trường hợp tháng 2 nhuận hay ko
    public static boolean checkFebValid(String dateStr) {
        String[] strArr = dateStr.split("/");
        String month = strArr[1];
        int year = Integer.valueOf(strArr[2]);
        int day = Integer.valueOf(strArr[0]);
        boolean isLeap = Year.isLeap(year);
        if ((month.equals("2") | month.equals("02") && isLeap)) {
            if (day > 29) //năm nhuận thì đc 29
            {
                return false;
            }
        } else if ((month.equals("2") | month.equals("02") && !isLeap)) {
            if (day > 28) //năm bth chỉ có 28
            {
                return false;
            }
        }
        return true;
    }

    public static boolean check2DayValid(Date d1, Date d2) {
        //hàm kiểm tra d1 có xảy ra trước d2
        return d1.before(d2);
    }

}
