package dev.ansuro.rest.dto;

/**
 *
 * @author Andy
 */
public class CustomerDTO {
    private String firstname;
    private String lastname;
    private String street;
    private String housenumber;
    private int zip;
    private String city;
    private String phone;

    public CustomerDTO() {
    }

    public CustomerDTO(String firstname, String lastname, String street, String housenumber, int zip, String city, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.housenumber = housenumber;
        this.zip = zip;
        this.city = city;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStreet() {
        return street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public int getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }
}
