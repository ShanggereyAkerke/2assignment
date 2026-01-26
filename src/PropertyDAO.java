import java.sql.*;

public class PropertyDAO {
    public static void addProperty(Property p, String type) {
        String sql = "INSERT INTO property(address, square_footage, price, is_available, type) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getAddress());
            ps.setInt(2, p.getSquareFootage());
            ps.setDouble(3, p.getPrice());
            ps.setBoolean(4, p.isAvailable());
            ps.setString(5, type);

            ps.executeUpdate();
            System.out.println("Property added to Database");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getAllProperties() {
        String sql = "SELECT * FROM property ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n=== PROPERTIES IN DATABASE ===");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("type") + " | " +
                                rs.getString("address") + " | $" +
                                rs.getDouble("price")
                );
            }

        } catch (Exception e) {
            System.out.println("Error with realtor operation: " + e.getMessage());
        }
    }

    public static void updatePrice(int id, double newPrice) {
        String sql = "UPDATE property SET price = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newPrice);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Price updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteProperty(int id) {
        String sql = "DELETE FROM property WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Property deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}