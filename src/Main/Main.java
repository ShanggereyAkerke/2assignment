package Main;

import model.Property;
import model.Apartment;
import model.House;
import model.Realtor;
import factory.PropertyFactory;
import repository.DBConnection;
import repository.PropertyDAO;
import repository.RealtorDAO;
import java.sql.*;
import java.util.*;

public class Main {
    static boolean firstRun = true;

    public static void main(String[] args) {
        if (firstRun) clearDB();
        Scanner scanner = new Scanner(System.in);
        RealEstateAgency agency = new RealEstateAgency("Astana RealEstate Agency", "Astana", 10, 5);
//2 POLYMORPH
        Property p1 = new Apartment("Qabanbay 23", 120, 200000);
        Property p2 = new House("Turan 55", 200, 350000);
        Property p3 = new Apartment("Syganak 16/1", 80, 120000);
        Realtor r1 = new Realtor(1, "Akerke", "77771234567", 5, 4.0);
        Realtor r2 = new Realtor(2, "Kairat", "77051051234", 10, 4.8);

        agency.addProperty(p1);
        agency.addProperty(p2);
        agency.addProperty(p3);
        agency.addRealtor(r1);
        agency.addRealtor(r2);

        System.out.println("ADD NEW PROPERTY ");
        System.out.print("Address: "); String addr = scanner.nextLine();
        System.out.print("Area (m²): "); int area = scanner.nextInt();
        System.out.print("Price: $"); double price = scanner.nextDouble();
        scanner.nextLine();

        Property userProp = new Apartment(addr, area, price);
        agency.addProperty(userProp);

        showDemo(agency, p1, p2, r1, r2, userProp);

        dbOperations(p1, p2, p3, userProp, r1, r2);

        showPatternsAndFeatures(agency);

        scanner.close();
    }

    static void clearDB() {
        firstRun = false;
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement()) {
            st.executeUpdate("TRUNCATE TABLE property, realtor RESTART IDENTITY");
        } catch (Exception e) { System.out.println("DB clear error: " + e.getMessage()); }
    }

    static void showDemo(RealEstateAgency agency, Property p1, Property p2, Realtor r1, Realtor r2, Property userProp) {
        System.out.println("\n AGENCY INFORMATION ");
        agency.printInfo();

        System.out.println("\nALL PROPERTIES");
        for (Property p : agency.getProperties()) if (p != null) System.out.println(p);

        System.out.println("\n SORTED BY PRICE");
        agency.sortPropertiesByPrice();
        for (Property p : agency.getProperties()) if (p != null) System.out.println(p);

        //2 POLYMORPHISM
        System.out.println("\n TAXES ");
        System.out.println(p1.getAddress() + " tax: $" + p1.calculateTax());
        System.out.println(p2.getAddress() + " tax: $" + p2.calculateTax());

        System.out.println("\nMOST EXPERIENCED REALTOR");
        Realtor best = agency.findMostExperiencedRealtor();
        System.out.println(best + "\nCommission: $" + best.calculateCost(p2.getPrice()));
    }

    static void dbOperations(Property p1, Property p2, Property p3, Property userProp, Realtor r1, Realtor r2) {
        System.out.println("\nDATABASE OPERATIONS");
        PropertyDAO.addProperty(p1, "APARTMENT"); PropertyDAO.addProperty(p2, "HOUSE");
        PropertyDAO.addProperty(p3, "APARTMENT"); PropertyDAO.addProperty(userProp, "APARTMENT");

        System.out.println("\nProperties from DB:");
        PropertyDAO.getAllProperties();

        System.out.println("\nUpdating price (ID 1)...");
        PropertyDAO.updatePrice(1, 210000);

        System.out.println("\nDeleting property (ID 3)...");
        PropertyDAO.deleteProperty(3);

        System.out.println("\nAdding realtors...");
        RealtorDAO.addRealtor(r1); RealtorDAO.addRealtor(r2);

        System.out.println("\nRealtors from DB:");
        RealtorDAO.getAllRealtors();

        System.out.println("\nUpdating commission...");
        RealtorDAO.updateRealtorCommission(1, 5.0);
    }

    static void showPatternsAndFeatures(RealEstateAgency agency) {
        System.out.println("\n=== DESIGN PATTERNS ===");
        System.out.println(" Builder Pattern:");
        Realtor built = new Realtor.Builder(100, "Billie Eilish")
                .phoneNumber("77779998888").yearsOfExperience(3).commissionRate(3.8).build();
        System.out.println("   Created: " + built);

        Property factoryProp = PropertyFactory.createProperty("APARTMENT", "Factory St", 75, 140000);
        System.out.println("   Created: " + factoryProp);

        //LAMBDA
        List<Property> affordable = agency.searchProperties(p -> p.getPrice() < 250000);
        System.out.println("   Affordable (<$250K): " + affordable.size() + " properties");

        System.out.println("\nREFLECTION");
        Property expensive = agency.findMostExpensiveProperty();
        if (expensive != null) agency.debugProperty(expensive);

        System.out.println("\nREST API");
        System.out.println("   API running: http://localhost:8080/api/properties");
    }
}