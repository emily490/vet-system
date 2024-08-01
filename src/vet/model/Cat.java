package vet.model;

public class Cat extends Pet
{
    private boolean siamese;

    public Cat(String name, boolean siamese) {
        super(name);
        this.siamese = siamese;
    }

    public void displayPet() {
        super.displayPet();
        System.out.println("Siamese?: " + siamese);
        System.out.println("---------------------------");
    }
}
