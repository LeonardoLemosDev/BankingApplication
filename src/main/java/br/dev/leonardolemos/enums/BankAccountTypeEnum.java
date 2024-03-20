package br.dev.leonardolemos.enums;

public enum BankAccountTypeEnum {
    CHECKING_ACCOUNT("Conta corrente"),

    SAVINGS_ACCOUNT("Conta Poupança");

    private final String accountType;

    BankAccountTypeEnum(String type) {
        this.accountType = type;
    }

    @Override
    public String toString() {
        return accountType;
    }
}
