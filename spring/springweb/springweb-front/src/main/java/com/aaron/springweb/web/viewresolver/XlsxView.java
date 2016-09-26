package com.aaron.springweb.web.viewresolver;

import com.aaron.springweb.bean.Pizza;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/26.
 */
public class XlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
                  HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Pizza pizza = (Pizza) model.get("pizza");

        Sheet sheet = workbook.createSheet("sheet 1");
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        Row row = null;
        Cell cell = null;
        int rowCount = 0;
        int colCount = 0;

        // Create header cells
        row = sheet.createRow(rowCount++);

        cell = row.createCell(colCount++);
        cell.setCellStyle(style);
        cell.setCellValue("Name");

        cell = row.createCell(colCount++);
        cell.setCellStyle(style);
        cell.setCellValue("Flavor");

        cell = row.createCell(colCount++);
        cell.setCellStyle(style);
        cell.setCellValue("Toppings");

        // Create data cells
        row = sheet.createRow(rowCount++);
        colCount = 0;
        row.createCell(colCount++).setCellValue(pizza.getName());
        row.createCell(colCount++).setCellValue(pizza.getFlavor());

        StringBuffer toppings = new StringBuffer("");
        for (String topping : pizza.getToppings()) {
            toppings.append(topping);
            toppings.append(" ");
        }
        row.createCell(colCount++).setCellValue(toppings.toString());

        for (int i = 0; i < 3; i++)
            sheet.autoSizeColumn(i, true);
    }
}
