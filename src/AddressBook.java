
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Contact{
    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private String state;
    private int zip;
    private long phone_number;
    private String email;

    Contact(String first_name, String last_name, String address, String city, String state, int zip, long phone_number, String email){
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone_number = phone_number;
        this.email = email;
    }

    public String getFirst_name(){
        return first_name;
    }
    public String getLast_name(){
        return last_name;
    }

    public void updatedContact(String address, String city, String state, int zip, long phone_number, String email){
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone_number = phone_number;
        this.email = email;
    }

    public void displayContact(){
        System.out.println("Contact Details: ");
        System.out.println("Name: "+ first_name+" "+last_name);
        System.out.println("Address: "+address);
        System.out.println("City: "+city);
        System.out.println("State: "+state);
        System.out.println("Zip: "+zip);
        System.out.println("Phone Number: "+phone_number);
        System.out.println("Email: "+email);
    }
}
class AddressBook1 {
    private List<Contact> contactList;

    public AddressBook1() {
        this.contactList = new ArrayList<>();
    }

    public void addContact() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String addMore;

        do {


            System.out.println(" Enter the First Name: ");
            String firstName = br.readLine();
            System.out.println("Last Name: ");
            String lastName = br.readLine();
            System.out.println("Enter Address: ");
            String address = br.readLine();
            System.out.println("Enter City Name: ");
            String city = br.readLine();
            System.out.println("State: ");
            String state = br.readLine();
            System.out.println("Enter Zip code: ");
            int zip = Integer.parseInt(br.readLine());
            System.out.println("Enter mail Id: ");
            String email = br.readLine();
            System.out.println("Enter Phone Number: ");
            long phone_number = Long.parseLong(br.readLine());

            Contact contact = new Contact(firstName, lastName, address, city, state, zip, phone_number, email);
            contactList.add(contact);
            System.out.println("Contact added Successfully");

            System.out.println("Do you want to add anoher contact? (yes/no)");
            addMore = br.readLine();

        } while (addMore.equalsIgnoreCase("yes"));
    }

    public void displayContact() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts to display.");
        } else {
            System.out.println("\nAddress Book Contacts:- ");
            for (Contact contact : contactList) {
                contact.displayContact();
            }
        }
    }

    public void editContact() {
        Scanner sc = new Scanner((System.in));
        System.out.println("Editing contact First Name: ");
        String firstname = sc.nextLine();
        System.out.println("Editing contact Last Name: ");
        String lastname = sc.nextLine();

        for (Contact contact : contactList) {
            if (contact.getFirst_name().equalsIgnoreCase(firstname) && contact.getLast_name().equalsIgnoreCase(lastname)) {
                System.out.println("Enter New Address: ");
                String address = sc.nextLine();
                System.out.println("Enter new City: ");
                String city = sc.nextLine();
                System.out.println("Enter new state: ");
                String state = sc.nextLine();
                System.out.println("Enter zip code: ");
                int zip = sc.nextInt();
                System.out.println("Enter new Phone Number: ");
                long phone_number = sc.nextLong();
                System.out.println("Enter new Mail Id: ");
                String mail = sc.nextLine();

                contact.updatedContact(address, city, state, zip, phone_number, mail);
                System.out.println("Contact updated Successfully");
                return;
            }
        }
        System.out.println("Contact not found.");

    }

    public void deleteContact() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the First name of the contact to delete: ");
        String firstname = sc.nextLine();
        System.out.println("Enter the Last name of the contact to delete: ");
        String lastname = sc.nextLine();

        for (int i = 0; i < contactList.size(); i++) {
            Contact contact = contactList.get(i);
            if (contact.getFirst_name().equalsIgnoreCase(firstname) && contact.getLast_name().equalsIgnoreCase(lastname)) {
                contactList.remove(i);
                System.out.println("Contact deleted successfully.");
                return;
            }
        }
        System.out.println("Contacts not found.");

    }
}

public class AddressBook {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Address Book Program");
        AddressBook1 ab = new AddressBook1();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** Menu ***");
            System.out.println("1. Add Contact");
            System.out.println("2. Display Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ab.addContact();
                    break;

                case 2:
                    ab.displayContact();
                    break;
                case 3:
                    ab.editContact();
                    break;
                case 4:
                    ab.deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Input");
            }
        }
    }
}