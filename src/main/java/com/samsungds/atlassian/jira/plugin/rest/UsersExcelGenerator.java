package com.samsungds.atlassian.jira.plugin.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UsersExcelGenerator {
  private static final short TITLE_ROW = 1;
  private static final short CONFIDENTIALITY_ROW = 3;
  private static final short HEADER_RWO = 5;
  private static final short BODY_RWO = 6;
  private static final short START_COL = 1;

  public static ByteArrayInputStream usersToExcel(List<MysqlUser> mysqlUsers) throws IOException {
    String[] columns = { "Host", "User" };
    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet("Mysql Users");
      Map<String, CellStyle> stylesMap = createStylesMap(workbook);

      Row titleRow = sheet.createRow(1);
      Cell titleCell = titleRow.createCell(TITLE_ROW);
      titleCell.setCellValue("Mysql Users");
      titleCell.setCellStyle(stylesMap.get("title"));
      sheet.addMergedRegion(new CellRangeAddress(TITLE_ROW, TITLE_ROW, START_COL, columns.length));
      // sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));

      Row confidentialityRow = sheet.createRow(CONFIDENTIALITY_ROW);
      Cell confidentialityCell = confidentialityRow.createCell(columns.length);
      confidentialityCell.setCellValue("Confidentiality");
      confidentialityCell.setCellStyle(stylesMap.get("confidentiality"));

      Row headerRow = sheet.createRow(HEADER_RWO);
      for (int col = 0; col < columns.length; col++) {
        Cell cell = headerRow.createCell(START_COL + col);
        cell.setCellValue(columns[col]);
        cell.setCellStyle(stylesMap.get("header"));
      }

      int rowIdx = BODY_RWO;
      for (MysqlUser mysqlUser : mysqlUsers) {
        Row row = sheet.createRow(rowIdx++);
        Cell cell;

        cell = row.createCell(START_COL);
        cell.setCellValue(mysqlUser.getHost());
        cell.setCellStyle(stylesMap.get("body"));

        cell = row.createCell(START_COL + 1);
        cell.setCellValue(mysqlUser.getUser());
        cell.setCellStyle(stylesMap.get("body"));
      }

      for (int col = 0; col < columns.length; col++) {
        sheet.autoSizeColumn(col + 1);
      }

      workbook.write(out);

      return new ByteArrayInputStream(out.toByteArray());
    }
  }

  private static Map<String, CellStyle> createStylesMap(Workbook wb) {
    Map<String, CellStyle> styles = new HashMap<>();
    CellStyle style;

    Font titleFont = wb.createFont();
    titleFont.setFontHeightInPoints((short) 18);
    titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
    titleFont.setBold(true);
    style = wb.createCellStyle();
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setFont(titleFont);
    styles.put("title", style);

    Font confidentialityFont = wb.createFont();
    confidentialityFont.setFontHeightInPoints((short) 14);
    confidentialityFont.setColor(IndexedColors.RED.getIndex());
    confidentialityFont.setBold(true);
    style = createStyleWithColor(wb, IndexedColors.RED.getIndex());
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setFont(confidentialityFont);
    styles.put("confidentiality", style);

    Font headerFont = wb.createFont();
    headerFont.setFontHeightInPoints((short) 14);
    headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());
    headerFont.setBold(true);
    style = createStyleWithColor(wb, IndexedColors.BLACK.getIndex());
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style.setFont(headerFont);
    styles.put("header", style);

    Font bodyFont = wb.createFont();
    bodyFont.setFontHeightInPoints((short) 12);
    bodyFont.setColor(IndexedColors.BLACK.getIndex());
    style = createStyleWithColor(wb, IndexedColors.BLACK.getIndex());
    style.setAlignment(HorizontalAlignment.LEFT);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setFont(bodyFont);
    styles.put("body", style);

    return styles;
  }

  private static CellStyle createStyleWithColor(Workbook wb, short color) {
    BorderStyle thin = BorderStyle.THIN;
    CellStyle style = wb.createCellStyle();

    style.setBorderRight(thin);
    style.setRightBorderColor(color);
    style.setBorderBottom(thin);
    style.setBottomBorderColor(color);
    style.setBorderLeft(thin);
    style.setLeftBorderColor(color);
    style.setBorderTop(thin);
    style.setTopBorderColor(color);

    return style;
  }

}
