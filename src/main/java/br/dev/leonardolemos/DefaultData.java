package br.dev.leonardolemos;

import br.dev.leonardolemos.entity.BankAccountEntity;
import br.dev.leonardolemos.entity.CustomerEntity;
import br.dev.leonardolemos.enums.BankAccountTypeEnum;
import br.dev.leonardolemos.enums.TransactionTypeEnum;
import br.dev.leonardolemos.service.BankAccountService;

import java.util.ArrayList;
import java.util.List;

public class DefaultData {

    public static List<BankAccountEntity> populateCustomersAndAccounts() {

        List<BankAccountEntity> bankAccountList = new ArrayList<>();

        CustomerEntity customer1 = new CustomerEntity("Leonardo L Oliveira", "123.123.123-12", "leonardo@email.com");
        CustomerEntity customer2 = new CustomerEntity("Maria Silva", "456.456.456-45", "maria@email.com");
        CustomerEntity customer3 = new CustomerEntity("João Souza", "789.789.789-78", "joao@email.com");
        CustomerEntity customer4 = new CustomerEntity("Ana Santos", "987.987.987-98", "ana@email.com");
        CustomerEntity customer5 = new CustomerEntity("Pedro Lima", "654.654.654-65", "pedro@email.com");

        BankAccountEntity bankBankAccount1 = new BankAccountEntity("9001", "002", BankAccountTypeEnum.CHECKING_ACCOUNT, customer1, 0.00, 0.00);
        BankAccountEntity bankBankAccount2 = new BankAccountEntity("9011", "001", BankAccountTypeEnum.CHECKING_ACCOUNT, customer2, 0.00, 0.00);
        BankAccountEntity bankBankAccount3 = new BankAccountEntity("9093", "024", BankAccountTypeEnum.SAVINGS_ACCOUNT, customer3, 0.00, 0.00);
        BankAccountEntity bankBankAccount4 = new BankAccountEntity("9019", "001", BankAccountTypeEnum.CHECKING_ACCOUNT, customer4, 0.00, 0.00);
        BankAccountEntity bankBankAccount5 = new BankAccountEntity("9124", "003", BankAccountTypeEnum.CHECKING_ACCOUNT, customer5, 0.00, 0.00);

        bankAccountList.add(bankBankAccount1);
        bankAccountList.add(bankBankAccount2);
        bankAccountList.add(bankBankAccount3);
        bankAccountList.add(bankBankAccount4);
        bankAccountList.add(bankBankAccount5);

        createRandomTransactions(bankAccountList);

        return bankAccountList;
    }

    public static void createRandomTransactions(List<BankAccountEntity> bankAccountList) {
        bankAccountList.forEach(bankAccount -> {

            for (int i = 1; i <= 10; i++) {
                double randomAmount = Math.random() * 1000;
                if (randomAmount > 500)
                    BankAccountService.registerTransaction(bankAccount, TransactionTypeEnum.ENTRY, randomAmount);
                else if (randomAmount < 200) {
                    if (BankAccountService.transactionIsValid(bankAccount, randomAmount))
                        BankAccountService.registerTransaction(bankAccount, TransactionTypeEnum.WITHDRAW, randomAmount);
                }
            }
        });
    }
}
