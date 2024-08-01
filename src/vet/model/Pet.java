package vet.model;

public abstract class Pet
{
    protected String name;

    public Pet(String name) {
        this.name = name;


    }

    public String getName() {
        return name;
    }



    @Override
    public String toString() {
        return name;
    }

    public void displayPet() {
        System.out.println("Name: " + name);
        System.out.println("---------------------------");
    }
}
