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

    public final void markSold() {
        isAvailable = false;
    }

    public String getAddress() { return address; }
    public int getSquareFootage() { return squareFootage; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }

    @Override
    public String toString() {
        return address + ", " + squareFootage + "m², $" + price;
    }
}
