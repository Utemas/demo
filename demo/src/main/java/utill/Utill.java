package utill;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import com.demo.demo.po.Customer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
    
    public static String getAddress(Customer customer){
        String address = customer.getAddr_provinve() + " " + customer.getAddr_city() + " " + customer.getAddr_community() + " " + customer.getAddr_street() + " " +customer.getAddr_block() + " " + customer.getAddr_unit() + " " + customer.getAddr_floor() +" " + customer.getAddr_room();
        return address;
    }

    public static boolean nameIsNull(HashMap<String,Object> map,String identify, String id){
        if(identify == null || id==null){
            map.put("name","请登录");
            return true;
        }
        return false;
    }
    public void createPDF(String filename) throws IOException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("example of PDF");
            document.open();
            document.add(new Paragraph("Hello World!"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}