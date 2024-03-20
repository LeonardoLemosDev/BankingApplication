package br.dev.leonardolemos;

import br.dev.leonardolemos.entity.BankAccountEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.dev.leonardolemos.enums.TitlesEnum.*;
import static br.dev.leonardolemos.service.BankAccountService.*;
import static br.dev.leonardolemos.service.MenuService.showMainMenu;
import static br.dev.leonardolemos.service.MenuService.showMenuTitle;

public class Main {
    public static void main(String[] args) {
        List<BankAccountEntity> bankAccountList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            showMainMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    showMenuTitle(MAIN_MENU_OPTION_1.toString());
                    bankAccountList.add(createBankAccount());
                    scanner.nextLine();
                }
                case 2 -> {
                    showMenuTitle(MAIN_MENU_OPTION_2.toString());
                    makeBankDeposit(bankAccountList);
                    scanner.nextLine();
                }
                case 3 -> {
                    showMenuTitle(MAIN_MENU_OPTION_3.toString());
                    makeBankTransfer(bankAccountList);
                    scanner.nextLine();
                }
                case 4 -> {
                    showMenuTitle(MAIN_MENU_OPTION_4.toString());
                    makeWithdraw(bankAccountList);
                    scanner.nextLine();
                }
                case 5 -> {
                    showMenuTitle(MAIN_MENU_OPTION_5.toString());
                    updateLimit(bankAccountList);
                    scanner.nextLine();
                }
                case 6 -> {
                    showMenuTitle(MAIN_MENU_OPTION_6.toString());
                    exportAccountsTransactions(bankAccountList);
                    scanner.nextLine();
                }
                case 7 -> {
                    showMenuTitle(MAIN_MENU_OPTION_7.toString());
                    showAccountsTransactions(bankAccountList);
                    scanner.nextLine();
                }
                case 8 -> {
                    showMenuTitle(MAIN_MENU_OPTION_8.toString());
                    showAllBankAccounts(bankAccountList);
                    scanner.nextLine();
                }
                case 9 -> {
                    showMenuTitle(MAIN_MENU_OPTION_9.toString());
                    bankAccountList.addAll(DefaultData.populateCustomersAndAccounts());
                    showAllBankAccounts(bankAccountList);
                    scanner.nextLine();
                }
                case 0 -> {
                    System.out.println(ENDING_PROGRAM);
                    exit = true;
                }
                default -> System.out.println(INVALID_OPTION);
            }
        }
        scanner.close();
    }
}