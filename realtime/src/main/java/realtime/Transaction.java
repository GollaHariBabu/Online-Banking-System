package realtime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    public void recordTransaction(int accountId, double amount, String transactionType) {
        String query = "INSERT INTO transactions (account_id, amount, transaction_type) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, accountId);
            pst.setDouble(2, amount);
            pst.setString(3, transactionType);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
