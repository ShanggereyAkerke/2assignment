public class RealEstateAgency {
    private String name;
    private String location;
    private Property[] properties;
    private Realtor[] realtors;
    private int propertyCount;
    private int realtorCount;

    public RealEstateAgency(String name, String location, int maxProperties, int maxRealtors) {
        this.name = name;
        this.location = location;
        properties = new Property[maxProperties];
        realtors = new Realtor[maxRealtors];
    }
    public void addProperty(Property property) {
        if (propertyCount < properties.length) {
            properties[propertyCount++] = property;
        }
    }
    public void addRealtor(Realtor realtor) {
        if (realtorCount < realtors.length) {
            realtors[realtorCount++] = realtor;
        }
    }
    public Property[] getProperties() {
        return properties;
    }
    public Property findMostExpensiveProperty() {
        if (propertyCount == 0) return null;
        Property max = properties[0];
        for (int i = 1; i < propertyCount; i++) {
            if (properties[i].getPrice() > max.getPrice()) {
                max = properties[i];
            }
        }
        return max;
    }
    public Realtor findMostExperiencedRealtor() {
        if (realtorCount == 0) return null;
        Realtor best = realtors[0];
        for (int i = 1; i < realtorCount; i++) {
            if (realtors[i].getYearsOfExperience() > best.getYearsOfExperience()) {
                best = realtors[i];
            }
        }
        return best;
    }

    public Property findPropertyByAddress(String address) {
        for (int i = 0; i < propertyCount; i++) {
            if (properties[i].getAddress().equalsIgnoreCase(address)) {
                return properties[i];
            }
        }
        return null;
    }
    public Property[] getAvailableProperties() {
        int count = 0;
        for (int i = 0; i < propertyCount; i++) {
            if (properties[i].isAvailable()) count++;
        }
        Property[] result = new Property[count];
        int index = 0;

        for (int i = 0; i < propertyCount; i++) {
            if (properties[i].isAvailable()) {
                result[index++] = properties[i];
            }
        }
        return result;
    }
    public void sortPropertiesByPrice() {
        for (int i = 0; i < propertyCount - 1; i++) {
            for (int j = 0; j < propertyCount - i - 1; j++) {
                if (properties[j].getPrice() > properties[j + 1].getPrice()) {
                    Property temp = properties[j];
                    properties[j] = properties[j + 1];
                    properties[j + 1] = temp;
                }
            }
        }
    }
    public void printInfo() {
        System.out.println("Agency: " + name);
        System.out.println("Location: " + location);
        System.out.println("Properties: " + propertyCount);
        System.out.println("Realtors: " + realtorCount);
    }
}
