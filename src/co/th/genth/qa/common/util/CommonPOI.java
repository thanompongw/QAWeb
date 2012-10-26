/******************************************************
 * Program History
 * 
 * Project Name	            :  CRMReport
 * Client Name				:  
 * Package Name             :  co.th.genth.crm.common.util
 * Program ID 	            :  CommonPOI.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  9 มี.ค. 2555
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.common.util;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import co.th.genth.qa.QAConstant;
import co.th.genth.qa.exception.CommonException;

/**
 * @author Thanompong.W
 */
public class CommonPOI {
	
	@Resource(name = "configService")
	private ConfigurationResolver resolver = new ConfigurationResolver();
	
	private Workbook wb = null;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss", Locale.US);
	/**
	 * Buffer size for transferring process
	 */
	private static final int BUFFER_SIZE = 4096;
	
	private static long tmpCount = 0;
	
	public CommonPOI(Workbook wb) {
		this.wb = wb;
	}
	
	public CommonPOI(String templateName) throws CommonException {
		// management.startWeb(templateName);
		wb = prepareWorkbook(templateName);
	}
	
	/**
	 * used to prepare workbook for Online Report
	 * 
	 * @param templateName
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws Exception
	 */
	private Workbook prepareWorkbook(String templateName) throws CommonException {
		String templateDirectory = this.resolver.getProperty(QAConstant.REPORT_TEMPLATE_PATH);
		// templateDirectory = "D:\\xxx\\Templates\\";
		String filePath = templateDirectory + templateName + "_Template"
		                  + QAConstant.XLS_REPORT_EXTENTION;
		if (!isExistFile(filePath)) {
			// If not exists then look for .xlsx type
			filePath = templateDirectory + templateName + "_Template"
			           + QAConstant.XLSX_REPORT_EXTENTION;
			if (!isExistFile(filePath)) {
				throw ErrorUtil.generateError("MSTD0013AERR", filePath);
			}
		}
		
		InputStream is = null;
		File template = new File(filePath);
		try {
			is = new FileInputStream(template);
			wb = WorkbookFactory.create(is);
		} catch (IOException e) {
			throw ErrorUtil.generateError("MSTD0038AERR", filePath);
		} catch (InvalidFormatException e) {
			throw ErrorUtil.generateError("MSTD0038AERR", filePath);
		} catch (Exception e) {
			throw ErrorUtil.generateError("MSTD0038AERR", filePath);
		} finally {
			template = null;
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		
		return wb;
	}
	
	/**
	 * Function to create new cell base on parameter. (in default POI will
	 * return null to unformatted cell.)
	 * 
	 * @param sheet
	 * @param intRow
	 * @param intCol
	 * @return
	 */
	public Cell createCell(Sheet sheet, int intRow, int intCol) {
		
		Row row = sheet.getRow(intRow);
		if (row == null)
			row = sheet.createRow(intRow);
		
		Cell cell = row.getCell(intCol);
		if (cell == null)
			cell = row.createCell(intCol);
		return cell;
	}
	
	/**
	 * Function to create new cell with format base on cellToCopy.
	 * 
	 * @param sheet
	 * @param cellToCopy
	 * @param intRow
	 * @param intCol
	 * @return
	 */
	public Cell createCell(Sheet sheet, Cell cellToCopy, int intRow, int intCol) {
		
		Cell newCell = createCell(sheet, intRow, intCol);
		if (cellToCopy != null) {
			try {
				newCell.setCellStyle(cellToCopy.getCellStyle());
				newCell.setCellType(cellToCopy.getCellType());
			} catch (Exception e) {
			}
		}
		return newCell;
	}
	
	public String toString(Cell cell) throws CommonException {
		
		String value = null;
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			value = StringUtils.trimToNull(cell.getStringCellValue());
		}
		
		return value;
	}
	
	public Double toNumberic(Cell cell) throws CommonException {
		Double value = 0.0D;
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			value = cell.getNumericCellValue();
		}
		
		return value;
	}
	
	/**
	 * Function to assign current cell with object value.
	 * 
	 * @param cell
	 * @param obj
	 * @return
	 * @throws CommonException
	 */
	public Cell setObject(Cell cell, Object obj) throws CommonException {
		if (obj != null) {
			if (obj instanceof String)
				cell.setCellValue((String) obj);
			else if (obj instanceof Number)
				cell.setCellValue(((Number) obj).doubleValue());
			else if (obj instanceof Date)
				cell.setCellValue((Date) obj);
		}
		return cell;
	}
	
	public Cell setObject(Sheet sheet, 
	                      int row, 
	                      int col, 
	                      Object obj) throws CommonException {
		Cell cell = createCell(sheet, row, col);
		
		cell = setObject(cell, obj);
		
		return cell;
		
	}
	
	public Cell setObject(Sheet sheet, 
	                      int row, 
	                      int col, 
	                      Object obj, 
	                      CellStyle cellStyle, 
	                      int width) throws CommonException {
		Cell cell = createCell(sheet, row, col);
		cell = setObject(cell, obj);
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
		if (width != 0) {
			cell.getSheet().setColumnWidth(col, width);
		}
		return cell;
		
	}
	
	public Sheet writeRow(Sheet sheet,
	                      Sheet sheetCopy,
	                      int row,
	                      int rowCopy,
	                      int col,
	                      int copyCol,
	                      Object[] objData) throws CommonException {
		Row rowToCopy = sheetCopy.getRow(rowCopy);
		
		for (int i = 0; i < objData.length; i++) {
			Cell cellToCopy = rowToCopy.getCell((copyCol + i));
			
			Cell newCell = createCell(sheet, cellToCopy, row, (col + i));
			newCell = setObject(newCell, objData[i]);
		}
		
		return sheet;
	}
	
	/**
	 * Method to insert new row to destination. Method to copy format from
	 * source to destination. this method used especially to insert total/
	 * summary in tabular data because the position is depend on row size.
	 * 
	 * @param intSheet sheet destination
	 * @param intSheetCopy sheet source
	 * @param row row destination
	 * @param rowCopy row copy
	 * @param colStart col start source copy
	 * @param colEnd col End Source copy
	 * @param insertRowFlag insert new row before copy row
	 * @throws CommonException
	 */
	public void copyRow(int intSheet,
	                    int intSheetCopy,
	                    int row,
	                    int rowCopy,
	                    int colStart,
	                    int colEnd,
	                    boolean insertRowFlag) throws CommonException {
		if (insertRowFlag) {
			Sheet sheet = wb.getSheetAt(intSheet);
			sheet.shiftRows(row, sheet.getLastRowNum(), 1);
		}
		this.copyRow(intSheet, intSheetCopy, row, rowCopy, colStart, colEnd);
	}
	
	/**
	 * Method to copy format from source to destination. this method used
	 * especially to insert total/ summary in tabular data because the
	 * posisition is depend on row size.
	 * 
	 * @param intSheet sheet destination
	 * @param intSheetCopy sheet source
	 * @param row row destination
	 * @param rowCopy row copy
	 * @param colStart col start source copy
	 * @param colEnd col End Source copy
	 * @throws CommonException
	 */
	public void copyRow(int intSheet,
	                    int intSheetCopy,
	                    int row,
	                    int rowCopy,
	                    int colStart,
	                    int colEnd) throws CommonException {
		Sheet sheetCopy = wb.getSheetAt(intSheetCopy);
		Sheet sheet = wb.getSheetAt(intSheet);
		Row rowToCopy = sheetCopy.getRow(rowCopy);
		
		for (int i = colStart; i <= colEnd; i++) {
			Cell cellToCopy = rowToCopy.getCell(i);
			Cell newCell = createCell(sheet, cellToCopy, row, i);
			
			
			if (cellToCopy.getCellType() != 3) {
				Object obj = null;
				if (cellToCopy.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					obj = Integer.valueOf((int) cellToCopy.getNumericCellValue());
				} else if (cellToCopy.getCellType() == Cell.CELL_TYPE_STRING) {
					obj = cellToCopy.getStringCellValue();
				}
				newCell = setObject(newCell, obj);
			}
		}
	}
	
	public	void copyRow(Sheet sheet, 
	      	             Sheet sheetCopy, 
	      	             int row, 
	      	             int rowCopy, 
	      	             int colStart, 
	      	             int colEnd) throws CommonException {
		Row rowToCopy = sheetCopy.getRow(rowCopy);
		for (int i = colStart; i <= colEnd; i++) {
			Cell cellToCopy = rowToCopy.getCell((i));
			Cell newCell = createCell(sheet, cellToCopy, row, i);
			if (cellToCopy.getCellType() != 3) {
				Object obj = null;
				if (cellToCopy.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					obj = Integer.valueOf((int) cellToCopy.getNumericCellValue());
				} else if (cellToCopy.getCellType() == Cell.CELL_TYPE_STRING) {
					obj = cellToCopy.getStringCellValue();
				}
				newCell = setObject(newCell, obj);
			}
		}
	}
	
	public void copyRowSpec(int intSheet,
	                        int intSheetCopy,
	                        int row,
	                        int rowCopy,
	                        int colStartCopy,
	                        int colEndCopy,
	                        int colStartDest) throws CommonException {
		Sheet sheetCopy = wb.getSheetAt(intSheetCopy);
		Sheet sheet = wb.getSheetAt(intSheet);
		Row rowToCopy = sheetCopy.getRow(rowCopy);
		for (int i = colStartCopy; i <= colEndCopy; i++) {
			int j = 0;
			Cell cellToCopy = rowToCopy.getCell(i);
			
			Cell newCell = createCell(sheet, cellToCopy, row, colStartDest + j);
			if (cellToCopy.getCellType() != 3) {
				Object obj = null;
				if (cellToCopy.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					obj = Integer.valueOf((int) cellToCopy.getNumericCellValue());
				} else if (cellToCopy.getCellType() == Cell.CELL_TYPE_STRING) {
					obj = cellToCopy.getStringCellValue();
				}
				newCell = setObject(newCell, obj);
			}
			j++;
		}
	}
	
	public	String writeFile(Workbook report, 
	      	                 String stFileName, 
	      	                 String dirPath) throws CommonException {
		String reportFilePath = this.writeFile(report, stFileName, dirPath, true);
		// management.endWeb(stFileName);
		return reportFilePath;
	}
	
	/**
	 * Method to create excel file to destination.
	 * 
	 * @param report
	 *            workbook of data
	 * @param stFileName
	 *            report file name
	 * @param dirPath
	 *            path for create report file
	 * @param timestampFlag
	 *            true : system will append timestamp value to end of report
	 *            file name false : system will skip step for append timestamp
	 *            value
	 * @throws CommonException
	 */
	public String writeFile(Workbook report,
	                        String stFileName,
	                        String dirPath,
	                        boolean timestampFlag) throws CommonException {
		StringBuffer reportFileName = new StringBuffer();
		String templateExtention = QAConstant.XLS_REPORT_EXTENTION;
		if (report instanceof XSSFWorkbook) {
			templateExtention = QAConstant.XLSX_REPORT_EXTENTION;
		}
		
		Date now = new Date(System.currentTimeMillis());
		
		if (timestampFlag) {
			
			reportFileName.append(stFileName).append("_").append(formatter.format(now))
			        .append(templateExtention);
		} else {
			reportFileName.append(stFileName).append(templateExtention);
		}
		
		String reportFilePath = new File(dirPath, reportFileName.toString()).getAbsolutePath();
		
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(reportFilePath);
			report.write(fos);
		} catch (Exception io) {
			
			// CSTD0050CommonLogger.logStackTrace(io);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
			}
		}
		
		// management.endWeb(stFileName);
		return reportFilePath;
	}
	
	public static void writeToResponse(Workbook report,
	                                   HttpServletResponse response,
	                                   String stFileName) throws CommonException {
		writeToResponse(report, response, stFileName, true);
	}
	
	/**
	 * Method to create excel file to destination.
	 * 
	 * @param report
	 *            workbook of data
	 * @param response
	 *            HttpServletResponse
	 * @param stFileName
	 *            report file name
	 * @param timestampFlag
	 *            true : system will append timestamp value to end of report
	 *            file name false : system will skip step for append timestamp
	 *            value
	 * @throws CommonException
	 */
	public static void writeToResponse(Workbook report,
	                                   HttpServletResponse response,
	                                   String stFileName,
	                                   boolean timestampFlag) throws CommonException {
		InputStream is = null;
		OutputStream os = null;
		
		try {
			String mimetype = "application/vnd.ms-excel";
			
			Date now = new Date(System.currentTimeMillis());
			
			String currentDate = formatter.format(now);
			String templateExtention = QAConstant.XLS_REPORT_EXTENTION;
			if (report instanceof XSSFWorkbook) {
				templateExtention = QAConstant.XLSX_REPORT_EXTENTION;
			}
			
			if (timestampFlag) {
				stFileName = stFileName + "_" + currentDate + templateExtention;
			} else {
				stFileName = stFileName + templateExtention;
			}
			
			File tempFile = File.createTempFile(new StringBuffer("tmp").append(tmpCount++)
			                                            .append(System.currentTimeMillis())
			                                            .toString(),
			                                    templateExtention);
			
			FileOutputStream xls = new FileOutputStream(tempFile);
			report.write(xls);
			xls.flush();
			xls.close();
			
			is = new FileInputStream(tempFile);
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=" + stFileName);
			response.setContentType(mimetype);
			response.setContentLength(is.available());
		    response.flushBuffer();
			os = response.getOutputStream();
			
			IOUtils.copy(is, os);
			
			tempFile.delete();
		} catch (IOException e) {
			throw ErrorUtil.generateError("MSTD0038AERR",
			                              new String[] { stFileName, e.getMessage() },
			                              0);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				} finally {
					os = null;
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				} finally {
					is = null;
				}
			}
		}
	}
	
	public void copyFormat(int sheetNum,
	                       int startRow,
	                       int startCol,
	                       int sheetCopyNum,
	                       int rowCopy,
	                       int startColCopy,
	                       int rowColumn,
	                       int numColumn,
	                       boolean copyValue) {
		Sheet sheet = wb.getSheetAt(sheetNum);
		Sheet sheetCopy = wb.getSheetAt(sheetCopyNum);
		copyFormat(sheet,
		           startRow,
		           startCol,
		           sheetCopy,
		           rowCopy,
		           startColCopy,
		           rowColumn,
		           numColumn,
		           copyValue);
	}
	
	public void copyFormat(Sheet sheet,
	                       int startRow,
	                       int startCol,
	                       Sheet sheetCopy,
	                       int rowCopy,
	                       int startColCopy,
	                       int rowColumn,
	                       int numColumn,
	                       boolean copyValue) {
		Row rowToCopy = null;
		for (int j = 0; j < rowColumn; j++) {
			rowToCopy = sheetCopy.getRow(rowCopy + j);
			if (rowToCopy == null) {
				continue;
			}
			for (int i = 0; i < numColumn; i++) {
				Cell cellToCopy = rowToCopy.getCell((startColCopy + i));
				Cell newCell = createCell(sheet, cellToCopy, startRow + j, (startCol + i));
				
				if (copyValue) {
					copyValue(newCell, cellToCopy);
				}
			}
		}
	}
	
	private void copyValue(Cell cell, Cell cellToCopy) {
		
		if (cellToCopy != null) {
			switch (cellToCopy.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					cell.setCellValue(cellToCopy.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						cell.setCellValue(cellToCopy.getDateCellValue());
					} else
						cell.setCellValue(cellToCopy.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					cell.setCellValue(cell.getNumericCellValue());
			}
		}
	}
	
	/**
	 * Method to set style into row and column.
	 * 
	 * @param intSheet
	 * @param rowStart
	 * @param rowEnd
	 * @param colStart
	 * @param colEnd
	 * @param style
	 */
	public void setStyleRow(int intSheet,
	                        int rowStart,
	                        int rowEnd,
	                        int colStart,
	                        int colEnd,
	                        CellStyle style) {
		Sheet sheet = wb.getSheetAt(intSheet);
		setStyleRow(sheet, rowStart, rowEnd, colStart, colEnd, style);
	}
	
	/**
	 * Method to set style into row and column.
	 * 
	 * @param intSheet
	 * @param rowStart
	 * @param rowEnd
	 * @param colStart
	 * @param colEnd
	 * @param style
	 */
	public void setStyleRow(Sheet sheet,
	                        int rowStart,
	                        int rowEnd,
	                        int colStart,
	                        int colEnd,
	                        CellStyle style) {
		Cell cell;
		Row row;
		if (style != null) {
			for (int i = rowStart; i <= rowEnd; i++) {
				for (int j = colStart; j <= colEnd; j++) {
					row = sheet.getRow(i);
					if (row == null) {
						row = sheet.createRow(i);
					}
					cell = row.getCell(j);
					if (cell == null) {
						cell = row.createCell(j);
					}
					cell.setCellStyle(style);
				}
			}
		}
	}
	
	public void copyFormatWithMerge(Sheet sheet,
	                                int startRow,
	                                int startCol,
	                                Sheet sheetCopy,
	                                int rowCopy,
	                                int startColCopy,
	                                int rowColumn,
	                                int numColumn,
	                                boolean copyValue) {
		copyFormat(sheet,
		           startRow,
		           startCol,
		           sheetCopy,
		           rowCopy,
		           startColCopy,
		           rowColumn,
		           numColumn,
		           copyValue);
		if (rowColumn != 0) {
			rowColumn = rowColumn - 1;
		}
		if (numColumn != 0) {
			numColumn = numColumn - 1;
		}
		copyMerge(sheet, startRow, startCol, sheetCopy, rowCopy, startColCopy, rowColumn, numColumn);
	}
	
	public void copyMerge(Sheet sheet,
	                      int startRow,
	                      int startCol,
	                      Sheet sheetCopy,
	                      int rowCopy,
	                      int startColCopy,
	                      int rowColumn,
	                      int numColumn) {
		int regionSize = sheetCopy.getNumMergedRegions();
		CellRangeAddress[] regions = null;
		if (regionSize > 0) {
			regions = new CellRangeAddress[regionSize];
			for (int i = 0; i < regions.length; i++) {
				regions[i] = sheet.getMergedRegion(i);
			}
			int rowTo = rowCopy + rowColumn;
			int colTo = startColCopy + numColumn;
			for (int k = 0; k < regions.length; k++) {
				if (rowCopy <= regions[k].getFirstRow() && regions[k].getFirstRow() <= rowTo
				    && startColCopy <= regions[k].getFirstColumn()
				    && regions[k].getFirstColumn() <= colTo) {
					int firstRow = (regions[k].getFirstRow() - rowCopy) + startRow;
					int lastRow = (regions[k].getLastRow() - rowCopy) + startRow;
					int firstCol = (regions[k].getFirstColumn() - startColCopy) + startCol;
					int lastCol = (regions[k].getLastColumn() - startColCopy) + startCol;
					CellRangeAddress region = new CellRangeAddress(firstRow,
					                                               lastRow,
					                                               firstCol,
					                                               lastCol);
					sheet.addMergedRegion(region);
				}
			}
		}
	}
	
	public void mergeCell(Sheet sheet,
	                      CellRangeAddress region,
	                      Integer firstRow,
	                      Integer lastRow,
	                      Integer firstCol,
	                      Integer lastCol) {
		boolean isMerge = false;
		if (region == null) {
			if (firstRow != null && lastRow != null && firstCol != null && lastCol != null) {
				region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
				isMerge = true;
			}
		} else {
			if (firstRow != null && lastRow != null && firstCol != null && lastCol != null) {
				region.setFirstRow(firstRow);
				region.setLastRow(lastRow);
				region.setFirstColumn(firstCol);
				region.setLastColumn(lastCol);
				isMerge = true;
			}
		}
		if (isMerge) {
			sheet.addMergedRegion(region);
		}
	}
	
	public void removeRow(Sheet sheet, int rowStart, int rowEnd) throws CommonException {
		Row removeRow = null;
		for (int i = rowStart; i <= rowEnd; i++) {
			removeRow = sheet.getRow(i);
			if (removeRow != null) {
				sheet.removeRow(removeRow);
			}
		}
	}
	
	/**
	 * @return Returns the workbook.
	 */
	public Workbook getWorkBook() {
		return wb;
	}
	
	/**
	 * Method to Shift row / insert row by move the rowFrom until rowTo to the
	 * bottom by size of rowSize.
	 * 
	 * @param sheet
	 * @param rowFrom
	 * @param rowTo
	 * @param rowSize
	 */
	public void shiftRow(int sheet, int rowFrom, int rowTo, int rowSize) {
		wb.getSheetAt(sheet).shiftRows(rowFrom, rowTo, rowSize);
	}
	
	/**
	 * Method used to transfer Input Stream into Output Stream. used to write
	 * excel file To HTTPResponse.
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	public static void transfer(InputStream is, OutputStream os) throws IOException {
		byte buffer[] = new byte[BUFFER_SIZE];
		int l = 0;
		while ((l = is.read(buffer)) > -1) {
			if (l > 0) {
				os.write(buffer, 0, l);
			}
		}
		os.flush();
		buffer = null;
	}
	
	public boolean isExistFile(String filePath) {
		boolean result;
		File file = new File(filePath);
		if (file.isFile()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
}
