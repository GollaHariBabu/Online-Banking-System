package realtime;

import java.util.Scanner;

public class BankingApp {

	private static DatabaseConnection db = new DatabaseConnection();
	private static User user = new User();
	private static Account account = new Account();
	private static Transaction transaction = new Transaction();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to the Online Banking System");

		System.out.print("1. Register\n2. Login\nEnter option: ");
		int option = sc.nextInt();
		sc.nextLine();

		if (option == 1) {
			System.out.print("Enter username: ");
			String username = sc.nextLine();
			System.out.print("Enter password: ");
			String password = sc.nextLine();
			System.out.print("Enter full name: ");
			String fullName = sc.nextLine();
			System.out.print("Enter email: ");
			String email = sc.nextLine();
			System.out.print("Enter phone: ");
			String phone = sc.nextLine();

			user.registerUser(username, password, fullName, email, phone);
			System.out.println("Registration successful!");

		} else if (option == 2) {
			System.out.print("Enter username: ");
			String username = sc.nextLine();
			System.out.print("Enter password: ");
			String password = sc.nextLine();

			if (user.authenticateUser(username, password)) {
				System.out.println("Login successful!");

				System.out.print("Enter account type (Savings/Checking): ");
				String accountType = sc.nextLine();
				int userId = 1; // You should retrieve the user ID based on the login credentials
				account.createAccount(userId, accountType);

				System.out.print("Enter amount to deposit: ");
				double amount = sc.nextDouble();
				int accountId = 1; // Retrieve account ID
				account.updateBalance(accountId, amount);
				transaction.recordTransaction(accountId, amount, "Deposit");

				System.out.println("Transaction successful! Your new balance is: " + account.getBalance(accountId));
			} else {
				System.out.println("Invalid credentials");
			}
		}
	}
}
