package utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.demo.demo.po.Person;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

public class ReadExcel {

    private XSSFWorkbook getExcel(String filePath) {
        // 定义一个数据格式化对象
        XSSFWorkbook wb = null;
        try {
            //excel模板路径
            File cfgFile = ResourceUtils.getFile(filePath);
            InputStream in = new FileInputStream(cfgFile);
            //读取excel模板
            wb = new XSSFWorkbook(in);
            return wb;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Person Read(String filePath) {
        //获取sheet表格，及读取单元格内容
        Person person = new Person();
        XSSFSheet sheet = null;
        try{
            sheet = this.getExcel(filePath).getSheetAt(0);
            //先将获取的单元格设置为String类型，下面使用getStringCellValue获取单元格内容
            //如果不设置为String类型，如果单元格是数字，则报如下异常
            //java.lang.IllegalStateException: Cannot get a STRING value from a NUMERIC cell
            sheet.getRow(2).getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
            //读取单元格内容
            String cellValue = sheet.getRow(0).getCell(0).getStringCellValue();
            person.setId_number(cellValue);
            return person;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}