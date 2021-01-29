package test.unit;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CommenUnit {
    private static Random random = new Random();

    private static char[] cc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 't'};

    public static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();

    private static Calendar current = Calendar.getInstance();

    private static volatile AtomicInteger ai = new AtomicInteger(10000);

    /**
     * 生成任意的中文字符
     *
     * @return
     */
    public static char getRandomChineseChar() {
        String str = "";
        int hightPos; //
        int lowPos;
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }
        return str.charAt(0);
    }

    /**
     * 根据传入想要的长度生成中文名字
     *
     * @param len
     * @return
     */
    public static String getChineseName(int len) {
        if (len == 0) {
            len = random.nextInt(2) + 2;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(getRandomChineseChar());
        }
        return sb.toString();
    }

    /**
     * 获得18位数的随机ID
     *
     * @return
     */
    public static String getRandomUUID() {
        int nowInt = ai.getAndIncrement();
        if (nowInt >= 99990) {
            ai = new AtomicInteger(10000);
        }

        long current = System.currentTimeMillis();
        //2、创建随机对象
        Random rd = new Random();

        //4、返回唯一码
        return String.valueOf(current) + nowInt;
    }


    public static String getRandomAccount() {
        int nextInt;
        do {
            nextInt = random.nextInt(20);
        } while (nextInt <= 5);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nextInt; i++) {
            sb.append(getRandomChar());
        }

        return sb.toString();
    }

    /**
     * 获得随机的英文小写字符
     *
     * @return
     */
    public static char getRandomChar() {
        int order = random.nextInt(26);
        return cc[order];
    }

    // 18位身份证号码各位的含义:
    // 1-2位省、自治区、直辖市代码；
    // 3-4位地级市、盟、自治州代码；
    // 5-6位县、县级市、区代码；
    // 7-14位出生年月日，比如19670401代表1967年4月1日；
    // 15-17位为顺序号，其中17位（倒数第二位）男为单数，女为双数；
    // 18位为校验码，0-9和X。
    // 作为尾号的校验码，是由把前十七位数字带入统一的公式计算出来的，
    // 计算的结果是0-10，如果某人的尾号是0－9，都不会出现X，但如果尾号是10，那么就得用X来代替，
    // 因为如果用10做尾号，那么此人的身份证就变成了19位。X是罗马数字的10，用X来代替10

    public static String getRandomCardID() {
        String id = "";
        // 随机生成省、自治区、直辖市代码 1-2
        String provinces[] = {"11", "12", "13", "14", "15", "21", "22", "23",
                "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
                "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
                "63", "64", "65", "71", "81", "82"};
        String province = provinces[new Random().nextInt(provinces.length - 1)];
        // 随机生成地级市、盟、自治州代码 3-4
        String citys[] = {"01", "02", "03", "04", "05", "06", "07", "08",
                "09", "10", "21", "22", "23", "24", "25", "26", "27", "28"};
        String city = citys[new Random().nextInt(citys.length - 1)];
        // 随机生成县、县级市、区代码 5-6
        String countys[] = {"01", "02", "03", "04", "05", "06", "07", "08",
                "09", "10", "21", "22", "23", "24", "25", "26", "27", "28",
                "29", "30", "31", "32", "33", "34", "35", "36", "37", "38"};
        String county = countys[new Random().nextInt(countys.length - 1)];
        // 随机生成出生年月 7-14
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE,
                date.get(Calendar.DATE) - new Random().nextInt(365 * 100));
        String birth = dft.format(date.getTime());
        // 随机生成顺序号 15-17
        String no = random.nextInt(10) + "" + random.nextInt(10) + random.nextInt(10);
        // 随机生成校验码 18
        String checks[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "X"};
        String check = checks[new Random().nextInt(checks.length - 1)];
        // 拼接身份证号码
        id = province + city + county + birth + no + check;

        return id;
    }

    /**
     * 通过身份证号码验证是否成年？
     *
     * @param cardId
     * @return
     */
    public static boolean isAdult(String cardId) {
        String birthday = cardId.substring(6, 14);
        try {
            Date parse = threadLocal.get().parse(birthday);    //yyyyMMdd
            Calendar birthDay = Calendar.getInstance();
            birthDay.setTime(parse);
            int year = current.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
            if (year > 18) {
                return true;
            } else if (year < 18) {
                return false;
            }
            // 如果年相等，就比较月份
            int month = current.get(Calendar.MONTH) - birthDay.get(Calendar.MONTH);
            if (month > 0) {
                return true;
            } else if (month < 0) {
                return false;
            }
            // 如果月也相等，就比较天
            int day = current.get(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH);
            return day >= 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddHHmmssSSSSSS");
        String dateNowStr = dateFormat.format(dateNow);
        System.out.println(Thread.currentThread().getName());
    }
}
