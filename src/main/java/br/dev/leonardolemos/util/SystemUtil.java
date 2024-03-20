package br.dev.leonardolemos.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static br.dev.leonardolemos.enums.TitlesEnum.*;

public class SystemUtil {

    public static String formatToBrazilianDate(LocalDate date) {
        DateTimeFormatter brazilianFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return brazilianFormat.format(date);
    }

    public static void space() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }

    public static boolean isValid() {
        boolean isValid = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println(INSERTION_VALIDATION);
        System.out.println(YES);
        System.out.println(NO);

        int validation = scanner.nextInt();
        scanner.nextLine();

        if (validation == 1) {
            isValid = true;
        } else if (validation == 2) {
            return isValid;
        } else {
            System.out.println(INVALID_OPTION);
        }
        return isValid;
    }

}
