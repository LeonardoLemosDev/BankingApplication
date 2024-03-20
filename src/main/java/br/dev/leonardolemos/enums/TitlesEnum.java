package br.dev.leonardolemos.enums;

public enum TitlesEnum {
    APP_NAME("MENU - APLICAÇÃO BANCÁRIA"),

    CHOOSE_AN_OPTION("Escolha uma opção: "),
    INVALID_OPTION("Opção inválida. Por favor, escolha novamente."),
    INVALID_NEGATIVE_VALUE("Valor não pode ser negativo. Por favor, informe-o novamente."),
    INVALID_VALUE("Valor inválido. Por favor, informe-o novamente."),
    INSERTION_VALIDATION("Os dados estão corretos? "),
    YES("1. Sim"),
    NO("2. Não"),
    PRESS_ENTER_TO_CONTINUE("Pressione ENTER para continuar..."),
    EXPORT_TRANSACTIONS_SUCCESSFULLY("Transações exportadas com sucesso!"),
    CUSTOMER_SUCCESSFULLY_ADDED("Cliente criado com sucesso!"),
    BANK_ACCOUNT_LIMIT_SUCCESSFULLY_UPDATED("Limite da conta bancária atualizado sucesso!"),
    BANK_ACCOUNT_LIMIT_ERROR("O limite não pode ser inferior ao saldo negativo!"),
    BANK_ACCOUNT_SUCCESSFULLY_ADDED("Conta bancária criada com sucesso!"),
    BANK_DEPOSIT_SUCCESSFULLY("Depósito bancário efetuado com sucesso!"),
    BANK_TRANSFER_SUCCESSFULLY("Transferência bancária efetuada com sucesso!"),
    BANK_TRANSFER_ERROR("Saldo insuficiente para realizar transferência!"),
    BANK_WITHDRAW_SUCCESSFULLY("Saque efetuado com sucesso!"),
    BANK_WITHDRAW_ERROR("Saldo insuficiente para realizar saque!"),
    BANK_ACCOUNT_NOT_FOUND("Conta bancária não encontrada!"),
    BANK_ACCOUNT_FOUND("Conta bancária encontrada "),
    INFO("Informações"),
    CUSTOMER_INFO("Informações do cliente"),
    BANK_ACCOUNT_INFO("Informações bancárias"),
    EXIT("Sair"),
    NO_ACCOUNT_REGISTERED("Nenhuma conta cadastrada!"),
    NO_TRANSACTION_REGISTERED("Nenhuma transação registrada!"),
    ACCOUNT_ORIGIN("Conta Origem"),
    ACCOUNT_DESTINY("Conta Destino"),

    ENTER_ACCOUNT_BANK_INFORMATION("Informe os dados bancários: "),
    ENTER_ACCOUNT_ID("Informe o número da conta: "),
    ENTER_ACCOUNT_AGENCY_ID("Informe o número da agência: "),
    ACCOUNT_BANK_ID("Número da conta: "),
    ACCOUNT_BANK_AGENCY("Número da agência: "),
    ACCOUNT_BANK_TYPE("Tipo de conta: "),
    ENTER_DEPOSIT_AMOUNT("Informe o valor do depósito: "),
    ENTER_TRANSFER_AMOUNT("Informe o valor de transferência: "),
    ENTER_WITHDRAW_AMOUNT("Informe o valor de saque: "),
    ENTER_NEW_LIMIT_AMOUNT("Informe o valor do novo limite: "),

    ENTER_CUSTOMERS_INFORMATION("Informe os dados do Cliente: "),
    CUSTOMERS_NAME("Nome: "),
    CUSTOMERS_EMAIL("E-mail: "),
    CUSTOMERS_DOCUMENT("CPF: "),

    ENDING_PROGRAM("Encerrando o programa..."),
    MAIN_MENU_OPTION_1("1. Cadastro de conta"),
    MAIN_MENU_OPTION_2("2. Realizar depósito"),
    MAIN_MENU_OPTION_3("3. Realizar transferência"),
    MAIN_MENU_OPTION_4("4. Saque"),
    MAIN_MENU_OPTION_5("5. Alterar limite"),
    MAIN_MENU_OPTION_6("6. Exportar histórico de transações"),
    MAIN_MENU_OPTION_7("7. Visualizar histórico de transações"),
    MAIN_MENU_OPTION_8("8. Visualizar contas existentes"),
    MAIN_MENU_OPTION_9("9. Popular sistema automaticamente"),
    MAIN_MENU_OPTION_0("0. " + EXIT);
    private final String title;

    TitlesEnum(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
