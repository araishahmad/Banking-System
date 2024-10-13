public class Address {
    private int houseNo;
    private int streetNo;
    private String city;
    private String province;
    private String country;

    public Address (int houseNo, int streetNo, String city, String province, String country){
        setHouseNo(houseNo);
        setStreetNo(streetNo);
        setCity(city);
        setProvince(province);
        setCountry(country);
    }

    public int getHouseNo() {
        return this.houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public int getStreetNo() {
        return this.streetNo;
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}