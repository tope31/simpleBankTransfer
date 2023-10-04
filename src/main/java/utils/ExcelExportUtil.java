package utils;

import dbConnection.DBConnection;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExcelExportUtil {
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public void exportToExcel(Integer userId) throws SQLException, IOException {

        String sql = "SELECT * FROM transaction_history WHERE sender_id = ?";
        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        resultSet = preparedStatement.executeQuery();
//        blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
//        blank sheet
        XSSFSheet sheet = workbook.createSheet("Transfer History");

        writeHeaderLine(sheet);

        writeDataLines(resultSet, workbook, sheet);

        String excelFilePath = "Transfer History.xlsx";
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        workbook.close();
        preparedStatement.close();
    }

    private void writeHeaderLine(XSSFSheet sheet) {
        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Receiver ID");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Amount");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Date");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Time");
    }

    private void writeDataLines(ResultSet resultSet, XSSFWorkbook workbook, XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (resultSet.next()) {
            int receiverId = resultSet.getInt("receiver_id");
            int amount = resultSet.getInt("amount");
            String date = resultSet.getString("date");
            String time = resultSet.getString("time");

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(receiverId);

            cell = row.createCell(columnCount++);
            cell.setCellValue(amount);

            cell = row.createCell(columnCount++);
            cell.setCellValue(date);

            cell = row.createCell(columnCount++);
            cell.setCellValue(time);
        }
    }
}
