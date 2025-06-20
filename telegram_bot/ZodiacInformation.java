package org.example.telegram_bot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.MonthDay;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ZodiacInformation {
    private static String FILEPATH = "ZodiacSigns.xlsx";

    public static String getSign(MonthDay monthDay){
        MonthDay startDate = MonthDay.of(1, 20);
        MonthDay endDate = MonthDay.of(2, 19);
        if (isInRange(monthDay, startDate, endDate)){
            return "Водолей";
        }
        startDate = MonthDay.of(2, 20);
        endDate = MonthDay.of(3, 20);
        if (isInRange(monthDay, startDate, endDate)){
            return "Рыбы";
        }
        startDate = MonthDay.of(3, 21);
        endDate = MonthDay.of(4, 19);
        if (isInRange(monthDay, startDate, endDate)){
            return "Овен";
        }
        startDate = MonthDay.of(4, 20);
        endDate = MonthDay.of(5, 20);
        if (isInRange(monthDay, startDate, endDate)){
            return "Телец";
        }
        startDate = MonthDay.of(5, 21);
        endDate = MonthDay.of(6, 20);
        if (isInRange(monthDay, startDate, endDate)){
            return "Близнецы";
        }
        startDate = MonthDay.of(6, 21);
        endDate = MonthDay.of(7, 22);
        if (isInRange(monthDay, startDate, endDate)){
            return "Рак";
        }
        startDate = MonthDay.of(7, 23);
        endDate = MonthDay.of(8, 22);
        if (isInRange(monthDay, startDate, endDate)){
            return "Лев";
        }
        startDate = MonthDay.of(8, 23);
        endDate = MonthDay.of(9, 22);
        if (isInRange(monthDay, startDate, endDate)){
            return "Дева";
        }
        startDate = MonthDay.of(9, 23);
        endDate = MonthDay.of(10, 23);
        if (isInRange(monthDay, startDate, endDate)){
            return "Весы";
        }
        startDate = MonthDay.of(10, 24);
        endDate = MonthDay.of(11, 22);
        if (isInRange(monthDay, startDate, endDate)){
            return "Скорпион";
        }
        startDate = MonthDay.of(11, 23);
        endDate = MonthDay.of(12, 21);
        if (isInRange(monthDay, startDate, endDate)){
            return "Стрелец";
        }
        startDate = MonthDay.of(12, 22);
        endDate = MonthDay.of(1, 19);
        if (isInRange(monthDay, startDate, endDate)){
            return "Козерог";
        }
        return null;
    }

    private static boolean isInRange(MonthDay monthDay, MonthDay start, MonthDay end){
        if (start.getMonthValue() > end.getMonthValue()) {
            return !monthDay.isBefore(start) || !monthDay.isAfter(end);
        }
        return !monthDay.isBefore(start) && !monthDay.isAfter(end);
    }

    public static String getSignInformation(String zodiacSign) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(new File(FILEPATH));
        try {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(zodiacSign);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            return cell.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
