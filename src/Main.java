import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    private static boolean firstRun = true;

    public static void main(String[] args) {
        if (firstRun) {
            firstRun = false;
            try (Connection conn = DBConnection.getConnection();
                 Statement st = conn.createStatement()) {
                System.out.println("Clearing previous database data...");
                st.executeUpdate("TRUNCATE TABLE property, realtor RESTART IDENTITY");
                System.out.println("Database cleared. IDs will start from 1.\n");
            } catch (Exception e) {
                System.out.println("Could not clear database: " + e.getMessage());
            }
        }

        Scanner scanner = new Scanner(System.in);
        RealEstateAgency agency = new RealEstateAgency("Astana Agency", "Astana", 10, 5);

        Property p1 = new Apartment("Qabanbay batyr 23", 120, 200000);
        Property p2 = new House("Turan 55", 200, 350000);
        Property p3 = new Apartment("Syganak 16/1", 80, 120000);
        Realtor r1 = new Realtor(1, "Shangerey Akerke", "7 777 123 4567", 5, 4.0);
        Realtor r2 = new Realtor(2, "Kairat Nurtas", "7 705 105 1234", 10, 4.8);

        agency.addProperty(p1);
        agency.addProperty(p2);
        agency.addProperty(p3);
        agency.addRealtor(r1);
        agency.addRealtor(r2);

        System.out.println("\nADD A NEW APARTMENT");
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Square footage: ");
        int square = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Property userApartment = new Apartment(address, square, price);
        agency.addProperty(userApartment);

        System.out.println("\nAGENCY INFO");
        agency.printInfo();

        System.out.println("\nALL PROPERTIES");
        for (Property p : agency.getProperties()) {
            if (p != null) System.out.println(p);
        }

        System.out.println("\nSORTED BY PRICE");
        agency.sortPropertiesByPrice();
        for (Property p : agency.getProperties()) {
            if (p != null) System.out.println(p);
        }

        System.out.println("\nAVAILABLE PROPERTIES");
        for (Property p : agency.getAvailableProperties()) {
            System.out.println(p);
        }

        System.out.println("\nTAX CALCULATION");
        System.out.println(p1.getAddress() + " tax: $" + p1.calculateTax());
        System.out.println(p2.getAddress() + " tax: $" + p2.calculateTax());

        System.out.println("\nMOST EXPERIENCED REALTOR");
        Realtor best = agency.findMostExperiencedRealtor();
        System.out.println(best);
        System.out.println("Commission for $" + p2.getPrice() + " property: $" + best.calculateCost(p2.getPrice()));

        System.out.println("\nAdding properties to database");
        PropertyDAO.addProperty(p1, "APARTMENT");
        PropertyDAO.addProperty(p2, "HOUSE");
        PropertyDAO.addProperty(p3, "APARTMENT");
        PropertyDAO.addProperty(userApartment, "APARTMENT");

        System.out.println("\nProperties from database:");
        PropertyDAO.getAllProperties();

        System.out.println("\nUpdating property price (ID 1)...");
        PropertyDAO.updatePrice(1, 210000);

        System.out.println("\nDeleting a property (ID 3)...");
        PropertyDAO.deleteProperty(3);

        System.out.println("\nProperties after update and delete:");
        PropertyDAO.getAllProperties();

        System.out.println("\nAdding realtors to the database...");
        RealtorDAO.addRealtor(r1);
        RealtorDAO.addRealtor(r2);
        System.out.println("\nRealtors from database: ");
        RealtorDAO.getAllRealtors();

        System.out.println("\nUpdating realtor commission (ID 1)...");
        RealtorDAO.updateRealtorCommission(1, 5.0);

        System.out.println("\n DATABASE FINAL STATE");
        System.out.println("\nPROPERTIES:");
        PropertyDAO.getAllProperties();
        System.out.println("\nREALTORS:");
        RealtorDAO.getAllRealtors();
        scanner.close();
    }
}