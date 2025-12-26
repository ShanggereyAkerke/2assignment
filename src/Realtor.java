public class Realtor {
    private int id;
    private String name;
    private String phoneNumber;
    private int yearsOfExperience;
    private double commissionRate;

    public Realtor(int id, String name, String phoneNumber, int yearsOfExperience, double commissionRate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.commissionRate = commissionRate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public double getCommissionRate() { return commissionRate; }

    public double calculateCost(double price) {
        return price * (commissionRate / 100);
    }

    @Override
    public String toString() {
        return name + " | " + yearsOfExperience + " years | " + commissionRate + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Realtor)) return false;
        Realtor r = (Realtor) o;
        return id == r.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
