public class Apartment extends Property {
    private int floor;

    public Apartment(String address, int squareFootage, double price) {
        super(address, squareFootage, price);
        this.floor = floor;
    }
    @Override
    public double calculateTax() {
        return price * 0.01;
    }
    @Override
    public String toString() {
        return "Apartment | " + super.toString() + ", floor: " + floor;
    }
}
