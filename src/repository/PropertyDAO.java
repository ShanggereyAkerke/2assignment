package repository;

import java.sql.*;
import model.Property;

public class PropertyDAO {
    public static void addProperty(Property p, String type) {
        String sql = "INSERT INTO property(address, square_footage, price, is_available, type) VALUES (?, ?, ?, ?, ?)";

     //TRY WITH RESOURCES
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getAddress());
            ps.setInt(2, p.getSquareFootage());
            ps.setDouble(3, p.getPrice());
            ps.setBoolean(4, p.isAvailable());
            ps.setString(5, type);
            ps.executeUpdate(); System.out.println("Property added to DB");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void getAllProperties() {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM property ORDER BY id")) {
            System.out.println("\n=== PROPERTIES IN DB ===");
            while (rs.next()) System.out.println(rs.getInt("id") + " | " + rs.getString("type") +
                    " | " + rs.getString("address") + " | $" + rs.getDouble("price"));
        } catch (Exception e) { System.out.println("DB error: " + e.getMessage()); }
    }

    public static void updatePrice(int id, double newPrice) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE property SET price = ? WHERE id = ?")) {
            ps.setDouble(1, newPrice); ps.setInt(2, id);
            ps.executeUpdate(); System.out.println("Price updated");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void deleteProperty(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM property WHERE id = ?")) {
            ps.setInt(1, id); ps.executeUpdate(); System.out.println("Property deleted");
        } catch (Exception e) { e.printStackTrace(); }
    }

}