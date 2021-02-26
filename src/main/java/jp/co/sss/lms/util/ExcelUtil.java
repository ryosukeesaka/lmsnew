package jp.co.sss.lms.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import jp.co.sss.lms.dto.WorkbookDto;

@Component
public class ExcelUtil {
	private Workbook wb = null;

	public ExcelUtil() {}
	
	public ExcelUtil(String filePath) {
		try (FileInputStream in = new FileInputStream(filePath)) {
			wb = WorkbookFactory.create(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setVal(String sheetName, int rowNum, int clmNum, String value) {
		Cell cell = getCell(sheetName, rowNum, clmNum);
		if (value != null) {
			cell.setCellValue(value);
		}
	}

	public void setVal(String sheetName, int rowNum, int clmNum, Integer value) {
		Cell cell = getCell(sheetName, rowNum, clmNum);
		if (value != null) {
			cell.setCellValue(value);
		}
	}

	public void setVal(String sheetName, int rowNum, int clmNum, double value) {
		Cell cell = getCell(sheetName, rowNum, clmNum);
		cell.setCellValue(value);
	}

	public void setVal(String sheetName, int rowNum, int clmNum, Date value) {
		Cell cell = getCell(sheetName, rowNum, clmNum);
		cell.setCellValue(value);
	}

	private Cell getCell(String sheetName, int rowNum, int clmNum) {
		Sheet sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}
		Cell cell = row.getCell(clmNum);
		if (cell == null) {
			cell = row.createCell(clmNum);
		}
		return cell;
	}

	public Workbook getWb() {
		return wb;
	}

	/**
	 * Workbookのダウンロードを行う
	 * 
	 * @param workbookDto
	 * @param response
	 * @throws IOException
	 */
	public static void downloadBook(WorkbookDto workbookDto, HttpServletResponse response) throws IOException {

	        String fileNameSjis = new String(workbookDto.wbName.getBytes("Shift_JIS"), "ISO-8859-1").replace(" ", "%20");
	        String fileNameUtf8 = URLEncoder.encode(workbookDto.wbName, "UTF-8").replace("+", "%20");
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment;filename="+ fileNameUtf8);

	        try (ServletOutputStream out = response.getOutputStream()) {
	            workbookDto.wb.write(out);
	        }
	}
}