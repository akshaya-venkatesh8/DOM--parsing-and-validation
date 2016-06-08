package com.github.akshayavenkatesh8;
public class Address {
    public String name;
    public String streetNumber;
    public String streetName;
    public String secondaryAddress;
    public String city;
    public String stateCode;
    public String zipCode;
    public String toString()
    {
       return "Address :\n"+name+",\n"+streetNumber+" "+streetName+",\n"+secondaryAddress+",\n"+city+",\n"+stateCode+" "+zipCode;
    }
}    

