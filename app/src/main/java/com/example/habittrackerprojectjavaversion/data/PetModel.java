package com.example.habittrackerprojectjavaversion.data;

public class PetModel {
    String petname ;
    String petsound ;
    int petimage ;

    public PetModel(String petname, String petsound, int petimage) {
        this.petname = petname;
        this.petsound = petsound;
        this.petimage = petimage;
    }

    public String getPetname() {
        return petname;
    }

    public String getPetsound() {
        return petsound;
    }

    public int getPetimage() {
        return petimage;
    }
}
