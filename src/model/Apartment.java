package model;

//2 Inheritance
public class Apartment extends Property {

    public Apartment(String address, int squareFootage, double price) {super(address, squareFootage, price);
    }
    @Override
    public double calculateTax() {return getPrice() * 0.01;
    }

    @Override
    public String toString() {
        return "Apartment: " + super.toString();
    }
}