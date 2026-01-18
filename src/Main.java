import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

        System.out.println("\n=== ADD A NEW APARTMENT ===");
        System.out.print("Address: ");
        scanner.nextLine();
        String address = scanner.nextLine();
        System.out.print("Square footage: ");
        int square = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Property userApartment = new Apartment(address, square, price);
        agency.addProperty(userApartment);

        System.out.println("\n=== AGENCY INFO ===");
        agency.printInfo();

        System.out.println("\n=== ALL PROPERTIES ===");
        for (Property p : agency.getProperties()) {
            if (p != null) System.out.println(p);
        }

        System.out.println("\n=== SORTED BY PRICE ===");
        agency.sortPropertiesByPrice();
        for (Property p : agency.getProperties()) {
            if (p != null) System.out.println(p);
        }

        System.out.println("\n=== AVAILABLE PROPERTIES ===");
        for (Property p : agency.getAvailableProperties()) {
            System.out.println(p);
        }

        System.out.println("\n=== TAX CALCULATION ===");
        System.out.println(p1.getAddress() + " tax: $" + p1.calculateTax());
        System.out.println(p2.getAddress() + " tax: $" + p2.calculateTax());

        System.out.println("\n=== MOST EXPERIENCED REALTOR ===");
        Realtor best = agency.findMostExperiencedRealtor();
        System.out.println(best);
        System.out.println("Commission for $" + p2.getPrice() + " property: $" + best.calculateCost(p2.getPrice()));


        System.out.println("\n=== DATABASE ===");



        System.out.println("\n Adding properties to database ");
        PropertyDAO.addProperty(p1, "APARTMENT");
        PropertyDAO.addProperty(p2, "HOUSE");
        PropertyDAO.addProperty(p3, "APARTMENT");
        PropertyDAO.addProperty(userApartment, "APARTMENT");

        System.out.println("\n Reading all properties from database ");
        PropertyDAO.getAllProperties();

        System.out.println("\n Updating property price (ID 1) ");
        PropertyDAO.updatePrice(1, 210000);

        System.out.println("\n Deleting a property (ID 3) ");
        PropertyDAO.deleteProperty(3);

        System.out.println("\n Properties after update/delete ");
        PropertyDAO.getAllProperties();

        System.out.println("\n Adding realtors to database ");
        RealtorDAO.addRealtor(r1);
        RealtorDAO.addRealtor(r2);

        System.out.println("\n Reading all realtors from database ");
        RealtorDAO.getAllRealtors();

        System.out.println("\n Updating realtor commission (ID 1)");
        RealtorDAO.updateRealtorCommission(1, 5.0);

        System.out.println("\n Final database state:");
        System.out.println("\n--- PROPERTIES ---");
        PropertyDAO.getAllProperties();
        System.out.println("\n--- REALTORS ---");
        RealtorDAO.getAllRealtors();

        scanner.close();
    }
}