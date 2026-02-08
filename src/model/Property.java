package model;


public abstract class Property {
    private final double price;
    private final String address;
    private final int squareFootage;
    private boolean isAvailable = true;

    public Property(String address, int squareFootage, double price) {
        this.address = address;
        this.squareFootage = squareFootage;
        this.price = price;
    }

    public abstract double calculateTax();
    public final void markSold() { isAvailable = false; }

    public String getAddress() { return address; }
    public double getPrice() { return price; }
    public int getSquareFootage() { return squareFootage; }
    public boolean isAvailable() { return isAvailable; }

    @Override public String toString() { return address + ", " + squareFootage + "m², $" + price; }
    @Override public boolean equals(Object o) {
        return this == o || (o instanceof Property && address.equals(((Property)o).address));
    }
    @Override public int hashCode() { return address.hashCode(); }
}