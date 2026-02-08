package factory;

import model.Property;
import model.Apartment;
import model.House;

public class PropertyFactory {

    public static Property createProperty(String type, String address,
                                          int squareFootage, double price) {
        if ("APARTMENT".equalsIgnoreCase(type)) {
            return new Apartment(address, squareFootage, price);
        } else if ("HOUSE".equalsIgnoreCase(type)) {
            return new House(address, squareFootage, price);
        }
        throw new IllegalArgumentException("Unknown property type: " + type);
    }
}