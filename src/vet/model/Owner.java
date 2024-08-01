package vet.model;

import java.util.HashMap;
import java.util.Map;

public class Owner {
    private long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private Map<String, Pet> pets;

    public Owner(long id, String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        pets = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, Pet> getPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        pets.put(pet.getName(), pet);
    }

    public void removePet(String name) {
        pets.remove(name);
    }

    public Pet getPet(String name) {
        return pets.get(name);
    }

    public void displayPets() {
        for(Pet pet: pets.values()) {
            pet.displayPet();
        }

    }
}