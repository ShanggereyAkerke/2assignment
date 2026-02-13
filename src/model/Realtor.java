package model;

public class Realtor {

    //1 attributes
    private int id;
    private String name;
    private String phoneNumber;
    private int yearsOfExperience;
    private double commissionRate;

    //1 Constructors
    public Realtor(int id, String name, String phoneNumber, int yearsOfExperience, double commissionRate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.commissionRate = commissionRate;
    }

    public int getId() { return id; } public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public double getCommissionRate() { return commissionRate; }
    public double calculateCost(double price) { return price * (commissionRate / 100); }
    public double calculateCost(double price, double customRate) { return price * (customRate / 100); }

    @Override public String toString() { return name + " | " + yearsOfExperience + " years | " + commissionRate + "%"; }
    @Override public boolean equals(Object o) { return this == o || (o instanceof Realtor && id == ((Realtor)o).id); }
    @Override public int hashCode() { return id; }


    //Builder pattern (telescoping constructor)
    public static class Builder {
        private int id; private String name; private String phoneNumber = " ";
        private int yearsOfExperience = 0; private double commissionRate = 3.0;

        public Builder(int id, String name) { this.id = id; this.name = name; }
        public Builder phoneNumber(String phone) { this.phoneNumber = phone; return this; }
        public Builder yearsOfExperience(int years) { this.yearsOfExperience = years; return this; }
        public Builder commissionRate(double rate) { this.commissionRate = rate; return this; }
        public Realtor build() { return new Realtor(id, name, phoneNumber, yearsOfExperience, commissionRate); }
    }
}