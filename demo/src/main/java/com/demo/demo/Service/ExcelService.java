package com.demo.demo.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.demo.demo.po.Person;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utill.ExcelImportUtils;

public class ExcelService {
    
    public List<Person> importData(File file)
    {
        Workbook wb = null;
        List<Person> PersonList = new ArrayList();
        try
        {
            if (ExcelImportUtils.isExcel2007(file.getPath())) {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } else {
                wb = new HSSFWorkbook(new FileInputStream(file));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return null;
        }

        Sheet sheet = wb.getSheetAt(0);//获取第一张表
        for (int i = 0; i < sheet.getLastRowNum(); i++)
        {
            Row row = sheet.getRow(i);//获取索引为i的行，以0开始
            String name= row.getCell(0).getStringCellValue();//获取第i行的索引为0的单元格数据
            int age = (int) row.getCell(1).getNumericCellValue();
            if (age==0 && name==null)
            {
                break;
            }
            Person hero=new Person();
            hero.setName(name);
            hero.setAddr_city("addr_city");
            PersonList.add(hero);
        }
        try
        {
            ((FileInputStream) wb).close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return PersonList;
    }


}