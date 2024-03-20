package br.dev.leonardolemos.enums;

public enum TransactionTypeEnum {
    ENTRY("Entrada"),
    WITHDRAW("Saída");
    private final String accountType;

    TransactionTypeEnum(String type) {
        this.accountType = type;
    }

    @Override
    public String toString() {
        return accountType;
    }
}
