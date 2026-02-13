package repository;

import java.sql.*;
import model.Realtor;

public class RealtorDAO {
    public static void addRealtor(Realtor r) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement check = conn.prepareStatement("SELECT id FROM realtor WHERE id = ?");
            check.setInt(1, r.getId()); ResultSet rs = check.executeQuery();

            if (rs.next()) {
                PreparedStatement update = conn.prepareStatement(
                        "UPDATE realtor SET name=?, phone_number=?, years_of_experience=?, commission_rate=? WHERE id=?");
                update.setString(1, r.getName()); update.setString(2, r.getPhoneNumber());
                update.setInt(3, r.getYearsOfExperience()); update.setDouble(4, r.getCommissionRate());
                update.setInt(5, r.getId()); update.executeUpdate();
                System.out.println("✓ Realtor updated: " + r.getName());
            } else {
                PreparedStatement insert = conn.prepareStatement(
                        "INSERT INTO realtor(id, name, phone_number, years_of_experience, commission_rate) VALUES (?, ?, ?, ?, ?)");
                insert.setInt(1, r.getId()); insert.setString(2, r.getName());
                insert.setString(3, r.getPhoneNumber()); insert.setInt(4, r.getYearsOfExperience());
                insert.setDouble(5, r.getCommissionRate()); insert.executeUpdate();
                System.out.println("✓ Realtor added: " + r.getName());
            }
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    public static void getAllRealtors() {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM realtor ORDER BY id")) {
            System.out.println("\n REALTORS IN DB ");
            while (rs.next()) System.out.println("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name") +
                    " | Phone: " + rs.getString("phone_number") + " | Exp: " + rs.getInt("years_of_experience") +
                    " years | Rate: " + rs.getDouble("commission_rate") + "%");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void updateRealtorCommission(int id, double newRate) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE realtor SET commission_rate = ? WHERE id = ?")) {
            ps.setDouble(1, newRate);
            ps.setInt(2, id);
            if (ps.executeUpdate() > 0) System.out.println("Commission updated for ID: " + id);
            else System.out.println(" No realtor with ID: " + id);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void deleteRealtor(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM realtor WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) System.out.println("Realtor deleted ID: " + id);
            else System.out.println("No realtor with ID: " + id);
        } catch (Exception e) { e.printStackTrace(); }
    }
}