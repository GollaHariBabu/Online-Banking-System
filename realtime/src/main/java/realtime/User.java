package realtime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            return rs.next(); // Returns true if user exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registerUser(String username, String password, String fullName, String email, String phone) {
        String query = "INSERT INTO users (username, password, full_name, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, fullName);
            pst.setString(4, email);
            pst.setString(5, phone);

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
