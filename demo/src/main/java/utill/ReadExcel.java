package utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.Person;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

public class ReadExcel {

    @Autowired
    CustomerMapper customerMapper;

    private XSSFWorkbook getExcel(String filePath) {
        // 定义一个数据格式化对象
        XSSFWorkbook wb = null;
        try {
            // excel模板路径
            File cfgFile = ResourceUtils.getFile(filePath);
            InputStream in = new FileInputStream(cfgFile);
            // 读取excel模板
            wb = new XSSFWorkbook(in);
            return wb;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Person> Read(String filePath) {
        // 获取sheet表格，及读取单元格内容
        List<Person> plist = new ArrayList<>();
        XSSFSheet sheet = null;
        try{
            sheet = this.getExcel(filePath).getSheetAt(0);
            //先将获取的单元格设置为String类型，下面使用getStringCellValue获取单元格内容
            //如果不设置为String类型，如果单元格是数字，则报如下异常
            //java.lang.IllegalStateException: Cannot get a STRING value from a NUMERIC cell
            int rowNum = sheet.getLastRowNum();
            for(int i = 1; i <= rowNum; i++){
                Person person = new Person();
                sheet.getRow(i).getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(4).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(5).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(6).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(7).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(8).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(9).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(10).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(11).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(12).setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.getRow(i).getCell(13).setCellType(HSSFCell.CELL_TYPE_STRING);
                person.setId_number(sheet.getRow(i).getCell(0).getStringCellValue());
                person.setName(sheet.getRow(i).getCell(1).getStringCellValue());
                person.setAddr_province(sheet.getRow(i).getCell(2).getStringCellValue());
                person.setAddr_city(sheet.getRow(i).getCell(3).getStringCellValue());
                person.setAddr_community(sheet.getRow(i).getCell(4).getStringCellValue());
                person.setAddr_street(sheet.getRow(i).getCell(5).getStringCellValue());
                person.setAddr_block(sheet.getRow(i).getCell(6).getStringCellValue());
                person.setAddr_unit(sheet.getRow(i).getCell(7).getStringCellValue());
                person.setAddr_floor(sheet.getRow(i).getCell(8).getStringCellValue());
                person.setAddr_room(sheet.getRow(i).getCell(9).getStringCellValue());
                person.setSex(sheet.getRow(i).getCell(10).getStringCellValue());
                person.setCountry(sheet.getRow(i).getCell(11).getStringCellValue());
                person.setNation(sheet.getRow(i).getCell(12).getStringCellValue());
                person.setCard_type(sheet.getRow(i).getCell(13).getStringCellValue());
                System.out.println(person);
                plist.add(person);
            }
            return plist;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}