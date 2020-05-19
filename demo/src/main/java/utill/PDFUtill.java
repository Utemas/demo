package utill;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.demo.demo.po.ClassInfo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public final class PDFUtill {
    public static void createPDF(String filename) throws IOException {
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

    public static void createPDFtext(String filename, PdfWriter writer,List<ClassInfo> clist) throws IOException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("example of PDF");
            document.open();
            PdfPTable table = createTable(writer,clist);
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static PdfPTable createTable(PdfWriter writer,List<ClassInfo> clist) throws IOException, DocumentException { 
        PdfPTable table = new PdfPTable(3);//生成一个两列的表格
        PdfPCell cell;
        int size = 20;
        Font font = new Font(BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
      BaseFont.NOT_EMBEDDED));
        for(int i = 0;i<clist.size();i++) {
          cell = new PdfPCell(new Phrase(clist.get(i).getClass_name(),font));//产品编号
          cell.setFixedHeight(size);
          table.addCell(cell);
          cell = new PdfPCell(new Phrase(clist.get(i).getClass_teacher(),font));//产品名称
          cell.setFixedHeight(size);
          table.addCell(cell);
          cell = new PdfPCell(new Phrase(clist.get(i).getClass_score() + "", font));// 产品价格
          cell.setFixedHeight(size);
          table.addCell(cell);
        }
        return table;
      }
    
}