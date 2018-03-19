package com.servlets;

import com.models.pets.Cat;
import com.models.pets.Dog;
import com.models.pets.Pet;

import javax.servlet.http.HttpServletRequest;

class CreatePet {
    public static Pet createPet(HttpServletRequest request) {
        final Pet pet;
        switch (request.getParameter("kindOfPet")){
            case "Cat": pet = new Cat(request.getParameter("petName"));
                break;
            case "Dog": pet = new Dog(request.getParameter("petName"));
                break;
            default:  pet = new Pet(request.getParameter("petName"));
        }
        return pet;
    }
}
