package realtime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
	public double getBalance(int accountId) {
		String query = "SELECT balance FROM accounts WHERE account_id = ?";
		try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setInt(1, accountId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getDouble("balance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	}

	public void createAccount(int userId, String accountType) {
		String query = "INSERT INTO accounts (user_id, account_type) VALUES (?, ?)";
		try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setInt(1, userId);
			pst.setString(2, accountType);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBalance(int accountId, double amount) {
		String query = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
		try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setDouble(1, amount);
			pst.setInt(2, accountId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
