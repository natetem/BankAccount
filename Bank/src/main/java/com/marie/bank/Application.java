package com.marie.bank;

import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import com.marie.bank.model.Operation;
import com.marie.bank.service.AccountService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;
import java.util.stream.IntStream;

public class Application {

    private static List<Account> accounts;
    private static Map<Integer, String> mapOperations;
    private static AccountService accountService;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeBank();
        System.out.println("--------WelCome To Bank--------");
        boolean accountChoice = false;
        boolean operationChoice = false;

        while (!accountChoice) {

            accounts.stream().map(Account::toString).forEach(System.out::println);
            System.out.print(" Please choice your account id : ");

            int accountId = sc.nextInt();

            accountChoice = accounts.stream().map(Account::getId).anyMatch(a -> Objects.equals(a, accountId));
            if (accountChoice) {
                while (!operationChoice) {

                    mapOperations.forEach((k, v) -> System.out.println("Choice : " + k + " for operation : " + v));

                    System.out.print("Choice the operation that you want to do :");
                    int operationId = sc.nextInt();

                    operationChoice = mapOperations.containsKey(operationId);

                    if (operationChoice) {
                        Account account = accounts.get(accountId);
                        Operation operation = null;
                        if (operationId == 1 || operationId == 2) {
                            System.out.print("Give the amount of operation: ");
                            double amount = sc.nextDouble();
                            operation = new Operation(LocalDate.now(), amount);

                        }
                        doOperation(operationId, account, operation);
                        System.out.print("Do you want to do another operation, choice (yes or no):");
                        String response = sc.next();
                        operationChoice = !response.equalsIgnoreCase("YES");
                    } else {
                        System.out.println(" Your choice the wrong choice, Legal choice is 1,2,3");
                        System.out.print("Do you want to do leave your account, choice (yes or no):");
                        String response = sc.next();
                        operationChoice = response.equalsIgnoreCase("YES");
                    }

                }

            } else {
                System.out.println("Your account id don't exist, please choice the right one");

            }

            System.out.print("Do you want to do leave the bank, choice (yes or no):");
            String response = sc.next();
            accountChoice = response.equalsIgnoreCase("YES");

        }

    }

    private static void doOperation(int choice, Account account, Operation operation) {
        try {
            switch (choice) {
                case 1:
                    accountService.deposit(account, operation);
                    break;
                case 2:
                    accountService.withdrawal(account, operation);
                    break;
                case 3:
                    accountService.historical(account);
                    break;
                default:
                    throw new IllegalArgumentException("The choice must be : 1,2,3");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Exception" + e);
        }

    }

    private static void initializeBank() {
        createAccounts();
        createOperations();
        accountService = new AccountService();
    }

    private static void createOperations() {
        List<String> listOperations = Arrays.asList("Deposit", "Withdrawal", "Historical");
        mapOperations = IntStream.range(0, listOperations.size())
                .boxed()
                .collect(toMap(i -> i + 1, listOperations::get));
    }

    private static void createAccounts() {
        List<Client> clients = Arrays.asList(new Client("Jack", "Bauer"),
                new Client("Chloe", "O'Brian"),
                new Client("Kim", "Bauer"),
                new Client("David", "Palmer"),
                new Client("Michelle", "Dessler"));
        accounts = clients.stream().map(c -> new Account(c, 1000)).collect(Collectors.toList());
    }
}
