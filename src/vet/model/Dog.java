package vet.model;

import emily.MyProgram;

public class Dog extends Pet {
    private boolean dangerousBreed;
    public Dog(String name, boolean dangerousBreed) {
        super(name);
        this.dangerousBreed = dangerousBreed;
    }

    public void displayPet() {
        super.displayPet();
        System.out.println("Dangerous Breed?: " + dangerousBreed);
        System.out.println("---------------------------");
    }
}
