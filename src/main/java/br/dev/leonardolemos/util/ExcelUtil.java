package br.dev.leonardolemos.util;

import br.dev.leonardolemos.entity.BankAccountEntity;
import br.dev.leonardolemos.entity.TransactionEntity;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static br.dev.leonardolemos.enums.TitlesEnum.EXPORT_TRANSACTIONS_SUCCESSFULLY;
import static br.dev.leonardolemos.service.MenuService.showDivider;

public class ExcelUtil {
    static final String downloadPath = FileSystemView.getFileSystemView().getDefaultDirectory().toString();
    static final String homePath = FileSystemView.getFileSystemView().getHomeDirectory().toString();

    public static void generateExcel(BankAccountEntity bankAccount) {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("Sheet1");
            HSSFRow headerRow = sheet.createRow(0);

            CellStyle style = workbook.createCellStyle();

            Font font = workbook.createFont();

            font.setBold(true);
            style.setFont(font);

            headerRow.createCell(0).setCellValue("DATA");
            headerRow.getCell(0).setCellStyle(style);

            headerRow.createCell(1).setCellValue("TIPO DE TRANSAÇÃO");
            headerRow.getCell(1).setCellStyle(style);

            headerRow.createCell(2).setCellValue("VALOR");
            headerRow.getCell(2).setCellStyle(style);

            int dataRowIndex = 1;

            for (TransactionEntity transaction : bankAccount.getTransactions()) {

                HSSFRow dataRow = sheet.createRow(dataRowIndex);

                dataRow.createCell(0).setCellValue(transaction.getFormatedDate());
                dataRow.createCell(1).setCellValue(transaction.getTransactionType().toString());
                dataRow.createCell(2).setCellValue(transaction.getFormatedAmount());

                dataRowIndex++;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
            String timestamp = dateFormat.format(new Date());

            String filePath = homePath + File.separator + "Transações - " + bankAccount.getCustomer().getName() + " - " + timestamp + ".csv";

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
                workbook.close();

                System.out.println("Local do arquivo:");
                System.out.println(filePath);
                showDivider();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
