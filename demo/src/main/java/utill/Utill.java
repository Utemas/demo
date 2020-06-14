package utill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.Person;

public final class Utill {

    public static String[] getBirth(String id_number){
        String[] birth = new String[3];
        birth[0] = id_number.substring(6,10);
        birth[1] = id_number.substring(10, 12);
        birth[2] = id_number.substring(12, 14);
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
        int result = Integer.parseInt(month) + 1;
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
    public static String caculateAge(String id_number){
        int result;
        String[] birth = getBirth(id_number);
        int birthYear = Integer.parseInt(birth[0]);
        int birthMonth = Integer.parseInt(birth[1]);
        int birthDay = Integer.parseInt(birth[2]);
        int birthFake = getSysYearToInt() - birthYear;
        result = birthFake;
        if(getSysMonthToInt() < birthMonth){
            result = birthFake - 1;
            return String.valueOf(result);
        }
        if(getSysDayToInt() < birthDay){
            result = birthFake - 1;
            return String.valueOf(result);
        }
        return String.valueOf(result);
    }
    
    public static String getAddress(Person customer){
        String address = customer.getAddr_province() +" "+ customer.getAddr_city() + " " + customer.getAddr_community()
                + " " +customer.getAddr_street() + " " + customer.getAddr_block() +"栋 "+ customer.getAddr_unit()+"单元 "+
                customer.getAddr_floor()+"楼 " + customer.getAddr_room()+"号";
        return address;
    }

    public static boolean nameIsNull(HashMap<String,Object> map,String identify, String id){
        if(identify == null || id==null){
            map.put("name","请登录");
            return true;
        }
        return false;
    }

    //获取学生在学校的年份
    public static List<String> yearsInSchool(int enterYear){
        String[] arr = new String[5];
        arr[0] = String.valueOf(enterYear);
        arr[1] = String.valueOf(enterYear + 1);
        arr[2] = String.valueOf(enterYear + 2);
        arr[3] = String.valueOf(enterYear + 3);
        arr[4] = String.valueOf(enterYear + 4);
        List<String> enterYears =new ArrayList<>();
        enterYears.add(arr[0]);
        enterYears.add(arr[1]);
        enterYears.add(arr[2]);
        enterYears.add(arr[3]);
        enterYears.add(arr[4]);
        return enterYears;
    }

    public static String formatTime(String date) {
        String result = date.substring(0, 11);
        return result;
    }

    public static String getNowDateShort(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
       return dateString;
    }

    public static boolean isInteger(String str) {  
        try {
            Double num2 = Double.valueOf(str);    
            return true;
        } catch (Exception e) {  
            return false;
        }  
    }


    public static List<ClassInfo> tempGPA(List<ClassInfo> clist){
        List<ClassInfo> new_clist = new ArrayList<>();
        for(ClassInfo c : clist){
            boolean q = isInteger(c.getClass_score());
            if(q){
                double t = Double.valueOf(c.getClass_score());
                if(t >= 95 && t <= 100){
                    c.setClass_jidian(4.5);
                }else if(t >= 90 && t <= 94){
                    c.setClass_jidian(4.0);
                }else if(t >= 85 && t <= 89){
                    c.setClass_jidian(3.5);
                }else if(t >= 80 && t <= 84){
                    c.setClass_jidian(3.0);
                }else if(t >= 75 && t <= 79){
                    c.setClass_jidian(2.5);
                }else if(t >= 70 && t <= 74){
                    c.setClass_jidian(2.0);
                }else if(t >= 65 && t <= 69){
                    c.setClass_jidian(1.5);
                }else if(t >= 60 && t <= 64){
                    c.setClass_jidian(1.0);
                }else if(t <= 59){
                    c.setClass_jidian(0.0);
                }
                new_clist.add(c);
            }else{
                //不是数字用五分制
                switch (c.getClass_score()) {
                    case "优秀":
                        c.setClass_jidian(4.0);
                        break;
                    case "良好":
                        c.setClass_jidian(3.0);
                        break;
                    case "中等":
                        c.setClass_jidian(2.0);
                        break;
                    case "及格":
                        c.setClass_jidian(1.0);
                        break;
                    case "不及格":
                        c.setClass_jidian(0.0);
                        break;
                    default:
                        break;
                }
                new_clist.add(c);
            }
        }
        
        return new_clist;
    }

    public static double caluterGPA(List<ClassInfo> cList,double xuefenTotal){
        double result =0.0;
        double xuefenji = 0.0;
        for (ClassInfo c : cList) {
            xuefenji += c.getClass_jidian() * c.getClass_xuefen();
        }
        result = xuefenji / xuefenTotal;

        return result;
    }
   
}