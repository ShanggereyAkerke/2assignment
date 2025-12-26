public class House extends Property {
    public House(String address, int squareFootage, double price) {
        super(address, squareFootage, price);
    }
    @Override
    public double calculateTax() {
        return price * 0.015;
    }
    @Override
    public String toString() {
        return "House | " + super.toString() ;
    }
}
