package Main;
import java.util.*;
import model.Property;
import model.Realtor;
import model.House;
import model.Apartment;

public class RealEstateAgency {
    private String name, location;
    private Property[] properties;
    private Realtor[] realtors;
    private int propertyCount = 0, realtorCount = 0;

    public RealEstateAgency(String name, String location, int maxProperties, int maxRealtors) {
        this.name = name; this.location = location;
        this.properties = new Property[maxProperties];
        this.realtors = new Realtor[maxRealtors];
    }

    //DIP
    public interface PropertyStorage {
        void addProperty(Property p);
        Property[] getAllProperties();
        Property findPropertyByAddress(String address);
    }

    public void addProperty(Property p) { if (propertyCount < properties.length) properties[propertyCount++] = p; }
    public void addRealtor(Realtor r) { if (realtorCount < realtors.length) realtors[realtorCount++] = r; }

    public Property[] getProperties() {
        Property[] result = new Property[propertyCount];
        System.arraycopy(properties, 0, result, 0, propertyCount);
        return result;
    }

    public Property findMostExpensiveProperty() {
        if (propertyCount == 0) return null;
        Property max = properties[0];
        for (int i = 1; i < propertyCount; i++)
            if (properties[i].getPrice() > max.getPrice()) max = properties[i];
        return max;
    }

    public Realtor findMostExperiencedRealtor() {
        if (realtorCount == 0) return null;
        Realtor best = realtors[0];
        for (int i = 1; i < realtorCount; i++)
            if (realtors[i].getYearsOfExperience() > best.getYearsOfExperience()) best = realtors[i];
        return best;
    }

    public Property findPropertyByAddress(String address) {
        for (int i = 0; i < propertyCount; i++)
            if (properties[i].getAddress().equalsIgnoreCase(address)) return properties[i];
        return null;
    }

    public Property[] getAvailableProperties() {
        List<Property> available = new ArrayList<>();
        for (int i = 0; i < propertyCount; i++)
            if (properties[i].isAvailable()) available.add(properties[i]);
        return available.toArray(new Property[0]);
    }

    public void sortPropertiesByPrice() {
        Arrays.sort(properties, 0, propertyCount,
                Comparator.comparingDouble(Property::getPrice));
    }

    public void printInfo() {
        System.out.println("Agency: " + name + "\nLocation: " + location +
                "\nProperties: " + propertyCount + "\nRealtors: " + realtorCount);
    }
    public List<Property> searchProperties(java.util.function.Predicate<Property> condition) {
        List<Property> result = new ArrayList<>();
        for (int i = 0; i < propertyCount; i++)
            if (condition.test(properties[i])) result.add(properties[i]);
        return result;
    }
    public void debugProperty(Property p) {
        try {
            java.lang.reflect.Field f = Property.class.getDeclaredField("price");
            f.setAccessible(true);
            System.out.println("Debug price: $" + f.get(p));
        } catch (Exception e) {}
    }
}