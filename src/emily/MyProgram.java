package emily;

import vet.model.Pet;
import vet.model.Cat;
import vet.model.Dog;
import vet.model.Owner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MyProgram {

    public static final char LOGIN_CHOICE_REGISTER = '1';
    public static final char PET_CHOICE_REGISTER = '1';
    public static final char PET_CHOICE_UNREGISTER = '2';
    public static final char PET_CHOICE_VIEW = '3';
    private static long id = 10000000;
    private static Owner owner;
    private static Map<Long, Owner> owners = new LinkedHashMap<>();
    public String abc;

    public void test() {

    }
    public static void main(String[] args) throws IOException {

        // Enter data using BufferReader
        InputStream in = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        Scanner sc = new Scanner(System.in);

//        System.out.println("You have entered "+loginChoice);
        char loginChoice = captureLoginChoice(sc);

//        Runtime.getRuntime().exec(new String[] {"clear"});
//        System.out.print("\033[H\033[2J");
//        System.out.flush();

        if (loginChoice == LOGIN_CHOICE_REGISTER) {
            ownerRegistration(reader);
        } else {
            login(reader);
        }

        int count = 0;
        while (count++ < 5) {
            petRegistration(sc, reader);
        }

//        System.out.println("Enter name of pet: ");
//        String name = reader.readLine();
//        System.out.print("Enter gender of pet: ");
//        String gender = reader.readLine();
//        String name = promptForInput(reader, "Enter name of pet: ");
//        String gender = promptForInput(reader, "Enter gender of pet: ");

//        Animal animal = new Animal();
//
//        DatabaseService.save(animal);

        // Printing the read line
        //System.out.println("the name you entered is: " + name);

    }

    private static void login(BufferedReader reader) throws IOException {
        // do login
        short loginAttempts = 0;
        while (owner == null) {
            System.out.println("------------------ Main Menu ------------------");
            System.out.println("Create a username: ");
            String username = reader.readLine();
            System.out.println("Create a password: ");
            String password = reader.readLine();
            owner = checkLogin(username, password);
            if (owner != null) {
                System.out.println("Welcome " + owner.getName());
                break;
            } else {
                if (loginAttempts++ >= 4) {
                    System.out.println("You are an utter knob - bye bye");
                    System.exit(0);
                }
                System.out.println("Incorrect login details, please try again");
            }
        }

        System.out.println("Welcome " + owner.getName());
    }

    private static Owner checkLogin(String username, String password) {
        for(Owner owner: owners.values()) {
            if (owner.getUsername().equals(username)) {
                if (owner.getPassword().equals(password)) {
                    return owners.get(owner.getId());
                }
                else {
                    return null;
                }
            }
        }
        return null;
    }

    private static void petRegistration(Scanner sc, BufferedReader reader) throws IOException {
        char petChoiceRegister;
        // do pet registration
        System.out.println("------------------ Pet Registration ------------------");
        System.out.println("Press 1 to register new pet");
        System.out.println("Press 2 to unregister pet");
        System.out.println("Press 3 to view pets");

        petChoiceRegister = sc.next().charAt(0);
        if (petChoiceRegister == PET_CHOICE_REGISTER) {
            Pet pet;
            System.out.println("Enter your pets name: ");
            String petName = reader.readLine();
            System.out.println("What type of pet do you have? (cat, dog): ");
            String type = reader.readLine();
            if (type.equalsIgnoreCase("cat")) {
                System.out.println("Is it siamese? (y/n): ");
                petChoiceRegister = sc.next().charAt(0);
                pet = new Cat(petName, petChoiceRegister == 'y');
            } else {
                System.out.println("Is it an official dangerous breed (y/n): ");
                petChoiceRegister = sc.next().charAt(0);
                pet = new Dog(petName, petChoiceRegister == 'y');
            }

            owner.addPet(pet);

        } else if (petChoiceRegister == PET_CHOICE_UNREGISTER) {
            System.out.println("Enter your name: ");
            String petName = reader.readLine();
            owner.removePet(petName);
        } else if (petChoiceRegister == PET_CHOICE_VIEW) {
            viewPet(reader);
        }
    }

    private static void viewPet(BufferedReader reader) throws IOException {
        owner.displayPets();
        System.out.println("Enter the name of the Pet to view billing and surgery information: ");
        String name = reader.readLine();
        owner.getPet(name).displayPet();
        System.out.println("Press 1 to view bills for: " + name);
        System.out.println("Press 2 to view surgeries for: " + name);
        String option = reader.readLine();
    }

    private static char captureLoginChoice(Scanner sc) {
        int count = 0;
        char c = 'x';
        while (c != '1' && c != '2') {
            if (count > 0) {
                System.out.println("------------------ Error - Please try again ------------------");
            }

            System.out.println("------------------ Login Menu ------------------");
            System.out.println("Press 1 to sign up");
            System.out.println("Press 2 to sign in");
            System.out.print("Please enter choice and press return: ");
            c = sc.next().charAt(0);
            count = count + 1;
            if (count > 5) {
                System.out.println("You are an utter knob - bye bye");
                System.exit(0);
            }
        }
        return c;
    }

    private static void ownerRegistration(BufferedReader reader) throws IOException {
        // do registration
        System.out.println("------------------ Registration ------------------");
        System.out.println("Enter your name: ");
        String name = reader.readLine();
        System.out.println("Enter your email: ");
        String email = reader.readLine();
        System.out.println("Create a username: ");
        String username = reader.readLine();
        System.out.println("Create a password: ");
        String password = reader.readLine();

        owner = new Owner(++id, name, email, username, password);
        owners.put(id, owner);

        System.out.println("The user is stored in the database and your unique referfence is: " + id);
    }


}
