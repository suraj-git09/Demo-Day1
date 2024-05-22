package Day13.Multilevel_Inheritance;

class Vehicle extends Driver {
    private String vehicleType;

    public Vehicle(String companyName, String driverName, String vehicleType) {
        super(companyName, driverName); // Call the constructor of the intermediate class
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Call the displayInfo method of the intermediate class
        System.out.println("Vehicle Type: " + vehicleType);
    }
}
