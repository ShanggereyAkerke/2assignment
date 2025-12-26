import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RealEstateAgency agency = new RealEstateAgency("Astana Agency", "Astana", 10, 5);
        Property p1 = new Apartment("Qabanbay batyr 23", 120, 200000);
        Property p2 = new House("Turan 55", 200, 350000);
        Property p3 = new Apartment("Syganaq 16/1", 80, 120000);
        Realtor r1 = new Realtor(1, "Shangerey Akerke", "7 777 123 4567", 5, 4.0);
        Realtor r2 = new Realtor(2, "Kairat Nurtas", "7 705 105 1234", 10, 4.8);

        agency.addProperty(p1);
        agency.addProperty(p2);
        agency.addProperty(p3);
        agency.addRealtor(r1);
        agency.addRealtor(r2);
        System.out.println("\nAdd a new apartment ");

        System.out.print("Address: ");
        scanner.nextLine();
        String address = scanner.nextLine();

        System.out.print("Square footage: ");
        int square = scanner.nextInt();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        Property userApartment = new Apartment(address, square, price);
        agency.addProperty(userApartment);

        System.out.println("\n=== Agency Info ===");
        agency.printInfo();
        System.out.println("\n=== All Properties ===");
        for (Property p : agency.getProperties()) {
            if (p != null)
                System.out.println(p);
        }
        System.out.println("\n=== Sorted by Price ===");
        agency.sortPropertiesByPrice();
        for (Property p : agency.getProperties()) {
            if (p != null)
                System.out.println(p);
        }
        System.out.println("\n=== Available Properties ===");
        for (Property p : agency.getAvailableProperties()) {
            System.out.println(p);
        }
        System.out.println("\n=== Tax ===");
        System.out.println(p1.getAddress() + " tax: $" + p1.calculateTax());
        System.out.println(p2.getAddress() + " tax: $" + p2.calculateTax());
        System.out.println("\n=== Most Experienced Realtor ===");
        Realtor best = agency.findMostExperiencedRealtor();
        System.out.println(best);
        System.out.println("\nCommission: " + best.calculateCost(p2.getPrice()));
        scanner.close();
    }
}
