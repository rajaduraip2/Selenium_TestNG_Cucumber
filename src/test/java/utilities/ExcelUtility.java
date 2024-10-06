package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Path;

public class ExcelUtility {
    public FileInputStream fis;
    public FileOutputStream fos;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String SheetName) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(SheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return rowCount;
    }

    public int getCellCount(String SheetName, int rowNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(SheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        return cellCount;
    }

    public String getCellData(String SheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(SheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        DataFormatter df = new DataFormatter();
        return df.formatCellValue(cell);

    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists())    // If file not exists then create new file
        {
            workbook = new XSSFWorkbook();
            fos = new FileOutputStream(path);
            workbook.write(fos);
        }

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);

        if (workbook.getSheetIndex(sheetName) == -1) // If sheet not exists then create new Sheet
            workbook.createSheet(sheetName);
        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rownum) == null)   // If row not exists then create new Row
            sheet.createRow(rownum);
        row = sheet.getRow(rownum);

        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fos = new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
    }


    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
    }


    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
    }

}



