package Day13.Multilevel_Inheritance;

class Company {
    private String companyName;

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void displayInfo() {
        System.out.println("Company Name: " + companyName);
    }
}
