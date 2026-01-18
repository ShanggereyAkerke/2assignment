import java.sql.*;

public class RealtorDAO {
    public static void addRealtor(Realtor r) {
        String checkSql = "SELECT id FROM realtor WHERE id = ?";
        String insertSql = "INSERT INTO realtor(id, name, phone_number, years_of_experience, commission_rate) VALUES (?, ?, ?, ?, ?)";
        String updateSql = "UPDATE realtor SET name=?, phone_number=?, years_of_experience=?, commission_rate=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, r.getId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, r.getName());
                updateStmt.setString(2, r.getPhoneNumber());
                updateStmt.setInt(3, r.getYearsOfExperience());
                updateStmt.setDouble(4, r.getCommissionRate());
                updateStmt.setInt(5, r.getId());
                updateStmt.executeUpdate();
                System.out.println("Realtor updated in Database: " + r.getName());
            } else {
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, r.getId());
                insertStmt.setString(2, r.getName());
                insertStmt.setString(3, r.getPhoneNumber());
                insertStmt.setInt(4, r.getYearsOfExperience());
                insertStmt.setDouble(5, r.getCommissionRate());
                insertStmt.executeUpdate();
                System.out.println("Realtor added to Database: " + r.getName());
            }

        } catch (Exception e) {
            System.out.println("Error with realtor operation: " + e.getMessage());
        }
    }

    public static void getAllRealtors() {
        String sql = "SELECT * FROM realtor ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n=== REALTORS IN DATABASE ===");
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("name") +
                                " | Phone: " + rs.getString("phone_number") +
                                " | Experience: " + rs.getInt("years_of_experience") + " years" +
                                " | Rate: " + rs.getDouble("commission_rate") + "%"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateRealtorCommission(int id, double newRate) {
        String sql = "UPDATE realtor SET commission_rate = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newRate);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Realtor commission updated for ID: " + id);
            } else {
                System.out.println("No realtor found with ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteRealtor(int id) {
        String sql = "DELETE FROM realtor WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Realtor deleted with ID: " + id);
            } else {
                System.out.println("No realtor found with ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}