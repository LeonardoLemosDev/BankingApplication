package br.dev.leonardolemos.service;

import br.dev.leonardolemos.entity.BankAccountEntity;
import br.dev.leonardolemos.entity.CustomerEntity;
import br.dev.leonardolemos.entity.TransactionEntity;
import br.dev.leonardolemos.enums.BankAccountTypeEnum;
import br.dev.leonardolemos.enums.TransactionTypeEnum;
import br.dev.leonardolemos.util.ExcelUtil;
import br.dev.leonardolemos.util.SystemUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static br.dev.leonardolemos.enums.TitlesEnum.*;
import static br.dev.leonardolemos.service.CustomerService.createCustomer;
import static br.dev.leonardolemos.service.MenuService.showDivider;

public class BankAccountService {
    public static BankAccountEntity createBankAccount() {
        CustomerEntity customer = createCustomer();

        BankAccountEntity bankAccount = new BankAccountEntity();
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        bankAccount.setCustomer(customer);

        while (!done) {
            showDivider();
            System.out.print(ENTER_ACCOUNT_BANK_INFORMATION);

            System.out.print(ACCOUNT_BANK_ID);
            bankAccount.setAccountId(scanner.next());
            scanner.nextLine();

            System.out.print(ACCOUNT_BANK_AGENCY);
            bankAccount.setAgencyId(scanner.next());
            scanner.nextLine();

            boolean validateAccountType = false;

            while (!validateAccountType) {

                System.out.println(ACCOUNT_BANK_TYPE);
                System.out.println("1. " + BankAccountTypeEnum.CHECKING_ACCOUNT);
                System.out.println("2. " + BankAccountTypeEnum.SAVINGS_ACCOUNT);

                int option = scanner.nextInt();

                switch (option) {
                    case 1 -> {
                        bankAccount.setAccountType(BankAccountTypeEnum.CHECKING_ACCOUNT);
                        validateAccountType = true;
                    }
                    case 2 -> {
                        bankAccount.setAccountType(BankAccountTypeEnum.SAVINGS_ACCOUNT);
                        validateAccountType = true;
                    }
                    default -> System.out.println(INVALID_OPTION);
                }
            }

            showDivider();
            System.out.println(bankAccount);
            showDivider();
            done = SystemUtil.isValid();
        }

        System.out.println(BANK_ACCOUNT_SUCCESSFULLY_ADDED);
        System.out.println(PRESS_ENTER_TO_CONTINUE);
        showDivider();

        return bankAccount;
    }

    public static void makeBankDeposit(List<BankAccountEntity> bankAccountList) {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {

            System.out.print(ENTER_ACCOUNT_ID);
            String accountId = scanner.next();
            scanner.nextLine();

            System.out.print(ENTER_ACCOUNT_AGENCY_ID);
            String agencyId = scanner.next();
            scanner.nextLine();

            BankAccountEntity bankAccount = bankAccountList.stream()
                    .filter(account -> account.getAccountId().equals(accountId) && account.getAgencyId().equals(agencyId))
                    .findFirst().orElse(null);

            if (bankAccount == null) {
                System.out.println(BANK_ACCOUNT_NOT_FOUND);
                System.out.println(PRESS_ENTER_TO_CONTINUE);
                scanner.nextLine();
                return;
            }

            showDivider();
            System.out.println(BANK_ACCOUNT_FOUND);
            showDivider();
            System.out.println(bankAccount);
            showDivider();

            double amount = -1;

            while (amount < 0) {
                System.out.print(ENTER_DEPOSIT_AMOUNT);
                amount = scanner.nextDouble();
                scanner.nextLine();

                if (amount < 0) {
                    System.out.println(INVALID_NEGATIVE_VALUE);
                }
            }

            registerTransaction(bankAccount, TransactionTypeEnum.ENTRY, amount);

            showDivider();
            done = SystemUtil.isValid();
        }

        System.out.println(BANK_DEPOSIT_SUCCESSFULLY);
        System.out.println(PRESS_ENTER_TO_CONTINUE);

        showDivider();
    }

    public static void makeBankTransfer(List<BankAccountEntity> bankAccountList) {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            BankAccountEntity bankAccountOrigin = null;
            BankAccountEntity bankAccountDestiny = null;

            while (bankAccountOrigin == null) {
                System.out.println(ACCOUNT_ORIGIN);
                System.out.print(ENTER_ACCOUNT_ID);
                String accountId = scanner.next();
                scanner.nextLine();

                System.out.print(ENTER_ACCOUNT_AGENCY_ID);
                String agencyId = scanner.next();

                BankAccountEntity bankAccountFound = bankAccountList.stream()
                        .filter(account -> account.getAccountId().equals(accountId) && account.getAgencyId().equals(agencyId))
                        .findFirst().orElse(null);

                if (bankAccountFound == null) {
                    System.out.println(BANK_ACCOUNT_NOT_FOUND);
                    System.out.println(PRESS_ENTER_TO_CONTINUE);
                    return;
                } else {
                    bankAccountOrigin = bankAccountFound;
                }
            }

            while (bankAccountDestiny == null) {
                System.out.println(ACCOUNT_DESTINY);
                System.out.print(ENTER_ACCOUNT_ID);
                String accountIdOrigin = scanner.next();
                scanner.nextLine();

                System.out.print(ENTER_ACCOUNT_AGENCY_ID);
                String agencyIdOrigin = scanner.next();

                BankAccountEntity bankAccountFound = bankAccountList.stream()
                        .filter(account -> account.getAccountId().equals(accountIdOrigin) && account.getAgencyId().equals(agencyIdOrigin))
                        .findFirst().orElse(null);

                if (bankAccountFound == null) {
                    System.out.println(BANK_ACCOUNT_NOT_FOUND);
                    System.out.println(PRESS_ENTER_TO_CONTINUE);
                    return;
                } else {
                    bankAccountDestiny = bankAccountFound;
                }
            }

            showDivider();
            System.out.println(ACCOUNT_ORIGIN);
            System.out.println(bankAccountOrigin);
            showDivider();
            System.out.println(ACCOUNT_DESTINY);
            System.out.println(bankAccountDestiny);
            showDivider();

            double amount = -1;

            while (amount < 0) {
                System.out.print(ENTER_TRANSFER_AMOUNT);
                amount = scanner.nextDouble();
                scanner.nextLine();

                if (amount < 0) {
                    System.out.println(INVALID_NEGATIVE_VALUE);
                }
            }
            if (transactionIsValid(bankAccountOrigin, amount)) {
                registerTransaction(bankAccountOrigin, TransactionTypeEnum.WITHDRAW, amount);
                registerTransaction(bankAccountDestiny, TransactionTypeEnum.ENTRY, amount);
                System.out.println(BANK_TRANSFER_SUCCESSFULLY);
                System.out.println(PRESS_ENTER_TO_CONTINUE);
                done = true;
            } else
                System.out.println(BANK_TRANSFER_ERROR);
        }
    }

    public static void makeWithdraw(List<BankAccountEntity> bankAccountList) {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            BankAccountEntity bankAccount = null;

            while (bankAccount == null) {
                System.out.print(ENTER_ACCOUNT_ID);
                String accountId = scanner.next();
                scanner.nextLine();

                System.out.print(ENTER_ACCOUNT_AGENCY_ID);
                String agencyId = scanner.next();

                BankAccountEntity bankAccountFound = bankAccountList.stream()
                        .filter(account -> account.getAccountId().equals(accountId) && account.getAgencyId().equals(agencyId))
                        .findFirst().orElse(null);

                if (bankAccountFound == null) {
                    showDivider();
                    System.out.println(BANK_ACCOUNT_NOT_FOUND);
                    System.out.println(PRESS_ENTER_TO_CONTINUE);
                    return;
                } else {
                    bankAccount = bankAccountFound;
                }
            }

            showDivider();
            System.out.println(BANK_ACCOUNT_FOUND);
            System.out.println(bankAccount);
            showDivider();

            double amount = -1;

            while (amount < 0) {
                System.out.print(ENTER_WITHDRAW_AMOUNT);
                amount = scanner.nextDouble();
                scanner.nextLine();

                if (amount < 0) {
                    System.out.println(INVALID_NEGATIVE_VALUE);
                }
            }
            if (transactionIsValid(bankAccount, amount)) {
                registerTransaction(bankAccount, TransactionTypeEnum.WITHDRAW, amount);
                System.out.println(BANK_WITHDRAW_SUCCESSFULLY);
                System.out.println(PRESS_ENTER_TO_CONTINUE);

                done = true;
            } else
                System.out.println(BANK_WITHDRAW_ERROR);
        }
    }

    public static void updateLimit(List<BankAccountEntity> bankAccountList) {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            BankAccountEntity bankAccount = null;

            while (bankAccount == null) {
                System.out.print(ENTER_ACCOUNT_ID);
                String accountId = scanner.next();
                scanner.nextLine();

                System.out.print(ENTER_ACCOUNT_AGENCY_ID);
                String agencyId = scanner.next();

                BankAccountEntity bankAccountFound = bankAccountList.stream()
                        .filter(account -> account.getAccountId().equals(accountId) && account.getAgencyId().equals(agencyId))
                        .findFirst().orElse(null);

                if (bankAccountFound == null) {
                    System.out.println(BANK_ACCOUNT_NOT_FOUND);
                    System.out.println(PRESS_ENTER_TO_CONTINUE);
                    return;
                } else {
                    bankAccount = bankAccountFound;
                }
            }

            showDivider();
            System.out.println(BANK_ACCOUNT_FOUND);
            System.out.println(bankAccount);
            showDivider();

            double newLimit = -1;

            while (newLimit < 0) {
                System.out.print(ENTER_NEW_LIMIT_AMOUNT);
                newLimit = scanner.nextDouble();
                scanner.nextLine();

                if (newLimit < 0) {
                    System.out.println(INVALID_NEGATIVE_VALUE);
                }
            }
            if (updateLimitIsValid(bankAccount, newLimit)) {
                bankAccount.setLimit(newLimit);
                System.out.println(BANK_ACCOUNT_LIMIT_SUCCESSFULLY_UPDATED);
                System.out.println(PRESS_ENTER_TO_CONTINUE);
                done = true;
            } else {
                System.out.println(BANK_ACCOUNT_LIMIT_ERROR);
                System.out.println(PRESS_ENTER_TO_CONTINUE);
                return;
            }
        }
    }


    public static void registerTransaction(BankAccountEntity bankAccount, TransactionTypeEnum type, double value) {
        bankAccount.addTransaction(new TransactionEntity(type, LocalDate.now(), value));

        if (type.equals(TransactionTypeEnum.ENTRY))
            bankAccount.setBalance(bankAccount.getBalance() + value);

        if (type.equals(TransactionTypeEnum.WITHDRAW))
            bankAccount.setBalance(bankAccount.getBalance() - value);
    }

    public static boolean transactionIsValid(BankAccountEntity bankAccount, double value) {
        double balance = bankAccount.getBalance();
        double limit = bankAccount.getLimit();

        return value <= balance + limit;
    }

    public static boolean updateLimitIsValid(BankAccountEntity bankAccount, double newLimit) {
        double balance = bankAccount.getBalance();

        return !(balance < 0 && -(newLimit) > balance);
    }

    public static void showAllBankAccounts(List<BankAccountEntity> bankAccountList) {

        if (!bankAccountList.isEmpty()) {
            bankAccountList.forEach(bankAccount -> {
                System.out.println(bankAccount);
                showDivider();
            });
            System.out.println(PRESS_ENTER_TO_CONTINUE);
        } else {
            System.out.println(NO_ACCOUNT_REGISTERED);
            System.out.println(PRESS_ENTER_TO_CONTINUE);
        }
    }

    public static void showAccountsTransactions(List<BankAccountEntity> bankAccountList) {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {

            System.out.print(ENTER_ACCOUNT_ID);
            String accountId = scanner.next();
            scanner.nextLine();

            System.out.print(ENTER_ACCOUNT_AGENCY_ID);
            String agencyId = scanner.next();

            BankAccountEntity bankAccount = bankAccountList.stream()
                    .filter(account -> account.getAccountId().equals(accountId) && account.getAgencyId().equals(agencyId))
                    .findFirst().orElse(null);

            if (bankAccount == null) {
                System.out.println(BANK_ACCOUNT_NOT_FOUND);
                System.out.println(PRESS_ENTER_TO_CONTINUE);
                return;
            } else {
                showDivider();
                System.out.println(BANK_ACCOUNT_FOUND);
                showDivider();
                System.out.println(bankAccount);
                showDivider();

                if (!bankAccount.getTransactions().isEmpty()) {
                    bankAccount.getTransactions().forEach(transaction -> {
                        System.out.println(transaction);
                        showDivider();
                    });
                    System.out.println(PRESS_ENTER_TO_CONTINUE);
                    done = true;
                } else {
                    System.out.println(NO_TRANSACTION_REGISTERED);
                    System.out.println(PRESS_ENTER_TO_CONTINUE);
                    return;
                }
            }
        }
    }
    public static void exportAccountsTransactions(List<BankAccountEntity> bankAccountList) {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {

            System.out.print(ENTER_ACCOUNT_ID);
            String accountId = scanner.next();
            scanner.nextLine();

            System.out.print(ENTER_ACCOUNT_AGENCY_ID);
            String agencyId = scanner.next();

            BankAccountEntity bankAccount = bankAccountList.stream()
                    .filter(account -> account.getAccountId().equals(accountId) && account.getAgencyId().equals(agencyId))
                    .findFirst().orElse(null);

            if (bankAccount == null) {
                System.out.println(BANK_ACCOUNT_NOT_FOUND);
                System.out.println(PRESS_ENTER_TO_CONTINUE);
                return;
            } else {
                showDivider();
                System.out.println(BANK_ACCOUNT_FOUND);
                showDivider();
                System.out.println(bankAccount);
                showDivider();

                if (!bankAccount.getTransactions().isEmpty()) {
                    ExcelUtil.generateExcel(bankAccount);
                    System.out.println(EXPORT_TRANSACTIONS_SUCCESSFULLY);
                    System.out.println(PRESS_ENTER_TO_CONTINUE);
                    done = true;
                } else {
                    System.out.println(NO_TRANSACTION_REGISTERED);
                    System.out.println(PRESS_ENTER_TO_CONTINUE);
                    return;
                }
            }
        }
    }

}
