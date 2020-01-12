package utill;

import java.util.Calendar;

public final class Utill {

    public static String[] getBirth(String id_number){
        String[] birth = new String[3];
        birth[0] = id_number.substring(8,12);
        birth[1] = id_number.substring(12, 14);
        birth[2] = id_number.substring(14, 15);
        return birth;
    }

    //
    //get the system year
    //return int
    //
    public static int getSysYearToInt(){
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        int result = Integer.parseInt(year);
        return result;
    }
    //
    //get the system year
    //return String
    //
    public static String getSysYearToString(){
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }
    //
    //get the system Month
    //return int
    //
    public static int getSysMonthToInt(){
        Calendar date = Calendar.getInstance();
        String month = String.valueOf(date.get(Calendar.MONTH));
        int result = Integer.parseInt(month);
        return result;
    }
    //
    //get the system Month
    //return String
    //
    public static String getSysMonthToString(){
        Calendar date = Calendar.getInstance();
        String month = String.valueOf(date.get(Calendar.MONTH));
        return month;
    }
    //
    //get the system Day
    //return int
    //
    public static int getSysDayToInt(){
        Calendar date = Calendar.getInstance();
        String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
        int result = Integer.parseInt(day);
        return result;
    }
    //
    //get the system Day
    //return String
    //
    public static String getSysDayToString(){
        Calendar date = Calendar.getInstance();
        String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
        return day;
    }
    //
    //caculate the age
    //return String
    //
    public static String caculateAge(String Birth,int year, int month, int day){
        return null;
    }
}