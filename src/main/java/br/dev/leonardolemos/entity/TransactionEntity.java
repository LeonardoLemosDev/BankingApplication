package br.dev.leonardolemos.entity;

import br.dev.leonardolemos.enums.TransactionTypeEnum;
import br.dev.leonardolemos.util.SystemUtil;

import java.time.LocalDate;
import java.util.Objects;

public class TransactionEntity {
    private TransactionTypeEnum transactionType;
    private LocalDate date;
    private double amount;

    public TransactionEntity() {
    }

    public TransactionEntity(TransactionTypeEnum transactionType, LocalDate date, double amount) {
        this.transactionType = transactionType;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionEntity that)) return false;
        return Double.compare(that.amount, amount) == 0 && transactionType == that.transactionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionType, amount);
    }

    @Override
    public String toString() {
        return "Data: " + getFormatedDate() + "\n" +
                "Tipo: " + transactionType + "\n" +
                "Valor: " + getFormatedAmount();
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getFormatedAmount() {
        return String.format("%.2f", amount);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFormatedDate(){
       return SystemUtil.formatToBrazilianDate(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
