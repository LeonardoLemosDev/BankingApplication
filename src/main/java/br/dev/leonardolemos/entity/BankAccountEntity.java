package br.dev.leonardolemos.entity;

import br.dev.leonardolemos.enums.BankAccountTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccountEntity {
    private String accountId;
    private String agencyId;
    private BankAccountTypeEnum accountType;
    private CustomerEntity customer;
    private double balance;
    private double limit;

    private List<TransactionEntity> transactions = new ArrayList<>();

    public BankAccountEntity() {
    }

    public BankAccountEntity(String accountId, String agencyId, BankAccountTypeEnum accountType, CustomerEntity customer, double balance, double limit) {
        this.accountId = accountId;
        this.agencyId = agencyId;
        this.accountType = accountType;
        this.customer = customer;
        this.balance = balance;
        this.limit = limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccountEntity that)) return false;
        return getAccountId() == that.getAccountId() && getAgencyId() == that.getAgencyId() && getAccountType() == that.getAccountType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountId(), getAgencyId(), getAccountType());
    }

    @Override
    public String toString() {

        return "Cliente: " + customer.getName() + "\n" +
                "Número da conta: " + accountId + "\n" +
                "Número da agência: " + agencyId + "\n" +
                "Tipo de conta: " + accountType + "\n" +
                "Limite: " + getFormatedLimit() + "\n" +
                "Saldo: " + getFormatedBalance();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public BankAccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(BankAccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public double getBalance() {
        return balance;
    }

    public String getFormatedBalance() {
        return String.format("%.2f", balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLimit() {
        return limit;
    }

    public String getFormatedLimit(){
        return String.format("%.2f", limit);
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void addTransaction(TransactionEntity transaction) {
        this.transactions.add(transaction);
    }
}
