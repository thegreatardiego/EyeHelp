package com.example.eyehelp;

public class UserInformation {
    public String name;
    public double latitude;
    public double longitude;
    public String location;
    public String email;
    public String key;
    public String getKey(){return key;}

    public String getName() {return name;}
    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }
    public String opening;
    public String getOpening()  {return opening;}

    public UserInformation(){

    }
    public UserInformation(String name, double latitude, double longitude, String location, String email){
        this.name=name;
        this.latitude=latitude;
        this.longitude=longitude;
        this.location=location;
        this.email=email;
    }

}
