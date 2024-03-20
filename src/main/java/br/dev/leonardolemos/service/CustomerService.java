package br.dev.leonardolemos.service;

import br.dev.leonardolemos.entity.CustomerEntity;
import br.dev.leonardolemos.util.SystemUtil;

import java.util.Scanner;

import static br.dev.leonardolemos.enums.TitlesEnum.*;
import static br.dev.leonardolemos.service.MenuService.showDivider;

public class CustomerService {

    public static CustomerEntity createCustomer() {
        CustomerEntity customer = new CustomerEntity();
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            System.out.println(ENTER_CUSTOMERS_INFORMATION);

            System.out.print(CUSTOMERS_NAME);
            customer.setName(scanner.next());
            scanner.nextLine();

            System.out.print(CUSTOMERS_DOCUMENT);
            customer.setDocument(scanner.next());
            scanner.nextLine();

            System.out.print(CUSTOMERS_EMAIL);
            customer.setEmail(scanner.next());
            scanner.nextLine();

            showDivider();
            System.out.println(customer);
            showDivider();
            done = SystemUtil.isValid();
        }
        System.out.println(CUSTOMER_SUCCESSFULLY_ADDED);
        return customer;
    }

}
