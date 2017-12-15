package com.marie.bank;

import com.marie.bank.exception.NotAllowedOperationException;
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

    private static final String LINE_DECORATION = "-----------------------------------------------";
    private static List<Account> accounts;
    private static Map<Integer, String> mapOperations;
    private static AccountService accountService;
    private static Scanner SC;
    private static String response;
    private static boolean operationChoice;
    private static boolean accountChoice;
    private static boolean exitBank;

    public static void main(String[] args) {
        initializeBank();
        while (!exitBank) {
            bankOperation();
        }
    }

    private static void bankOperation() {
        accounts.stream().map(Account::toString).forEach(System.out::println);
        System.out.print(" Please choice your account id : ");
        int accountId = SC.nextInt();
        accountChoice = accounts.stream().map(Account::getId).anyMatch(a -> Objects.equals(a, accountId));
        boolean exitAccount = false;
        if (accountChoice) {
            while (!exitAccount) {
                exitAccount = accountOperation(accountId);
            }
        } else {
            System.out.println("Your accountId don't exist, please choice the right one");
        }
        response = choiceMessage("bank");
        exitBank = response.equalsIgnoreCase("YES");

    }

    private static boolean accountOperation(int accountId) {
        mapOperations.forEach((k, v) -> System.out.println("Choice : " + k + " for operation : " + v));
        System.out.print("Choice the operation that you want to do :");
        int operationId = SC.nextInt();
        operationChoice = mapOperations.containsKey(operationId);
        if (operationChoice) {
            doOperation(operationId, accountId, operationId, SC);
        } else {
            System.out.println(" Your choice the wrong choice, Legal choice is 1,2,3");
        }
        response = choiceMessage("account");
        return response.equalsIgnoreCase("YES");
    }

    private static String choiceMessage(String level) {
        System.out.printf("Do you want to leave the %s, choice (yes or no):", level);
        return SC.next();

    }

    private static void doOperation(int choice, int accountId, int operationId, Scanner sc) {
        Account account = accounts.get(accountId);
        Operation operation = null;
        if (operationId == 1 || operationId == 2) {
            System.out.print("Give the amount of operation " + mapOperations.get(operationId) + "  :");
            double amount = sc.nextDouble();
            operation = new Operation(LocalDate.now(), amount);

        }
        try {
            switch (choice) {
                case 1:
                    System.out.println("---------the deposit on your account-----------");
                    accountService.deposit(account, operation);
                    System.out.printf("your operation %s has successfully Done \n", operation.getType().toString());
                    System.out.println(operation);
                    System.out.println(LINE_DECORATION);
                    break;
                case 2:
                    System.out.println("---------the withdrawal on your account----------");
                    accountService.withdrawal(account, operation);
                    System.out.printf("your operation %s has successfully Done \n", operation.getType().toString());
                    System.out.println(operation);
                    System.out.println(LINE_DECORATION);
                    break;
                case 3:
                    System.out.println("--------The historical of your account --------");
                    accountService.historical(account);
                    System.out.println(LINE_DECORATION);
                    break;
            }
        } catch (IllegalArgumentException | NotAllowedOperationException | NullPointerException e) {
            System.out.println("Correct this " + e.getMessage());
        }

    }

    private static void initializeBank() {
        createAccounts();
        createOperations();
        accountService = new AccountService();
        SC = new Scanner(System.in);
        response = "";
        operationChoice = true;
        accountChoice = true;
        exitBank = false;
        System.out.println(LINE_DECORATION);
        System.out.println("        WelCome To Bank        ");
        System.out.println(LINE_DECORATION);

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
