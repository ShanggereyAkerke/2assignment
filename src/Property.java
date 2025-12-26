public abstract class Property {
    protected String address;
    protected int squareFootage;
    protected double price;
    protected boolean isAvailable;

    public Property(String address, int squareFootage, double price) {
        this.address = address;
        this.squareFootage = squareFootage;
        this.price = price;
        this.isAvailable = true;
    }
    public abstract double calculateTax();

    public String getAddress() { return address; }
    public int getSquareFootage() { return squareFootage; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }

    public void markSold() {
        isAvailable = false;
    }
    @Override
    public String toString() {
        return address + ", " + squareFootage + "m², $" + price;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property p = (Property) o;
        return address.equals(p.address);
    }
    @Override
    public int hashCode() {
        return address.hashCode();
    }
}
