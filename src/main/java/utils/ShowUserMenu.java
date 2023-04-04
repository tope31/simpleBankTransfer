package utils;

import model.Users;

import java.util.Scanner;

public class ShowUserMenu {

    public static void userMenu(Users users) {
        BankUtils bankUtils = new BankUtils();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("Hi " + users.getUsername());
        System.out.println("Welcome to Ivory Bank");


        while (choice <= 4) {
            System.out.println("Your current balance is: ");
            System.out.println("Enter 1 to Deposit\nEnter 2 to Withdraw\nEnter 3 to Transfer Money\nEnter 4 to View Transaction History");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter amount to deposit");
                    Integer deposit = scanner.nextInt();
                    bankUtils.depositBalance(deposit);
                    break;
                case 2:
                    System.out.println("TBD");
                    break;
                case 3:
                    System.out.println("TBA");
                    break;
                case 4:
                    System.out.println("TBC");
                    break;
            }
        }
    }
}
