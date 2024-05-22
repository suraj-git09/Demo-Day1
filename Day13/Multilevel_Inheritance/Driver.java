package Day13.Multilevel_Inheritance;

class Driver extends Company {
    private String driverName;

    public Driver(String companyName, String driverName) {
        super(companyName); // Call the constructor of the base class
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Call the displayInfo method of the base class
        System.out.println("Driver Name: " + driverName);
    }
}
