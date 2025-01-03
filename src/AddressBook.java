import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Contact{
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int zip;
    private long phoneNumber;
    private String email;

    Contact(String firstName, String lastName, String address, String city, String state, int zip, long phoneNumber, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirst_name(){
        return firstName;
    }
    public String getLast_name(){
        return lastName;
    }

    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return firstName.equalsIgnoreCase(contact.firstName) && lastName.equalsIgnoreCase(contact.lastName);
    }

    @Override
    public int hashCode(){
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase());
    }

    public void updatedContact(String address, String city, String state, int zip, long phone_number, String email){
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void displayContact(){
        System.out.println("Contact Details: ");
        System.out.println("Name: "+ firstName+" "+lastName);
        System.out.println("Address: "+address);
        System.out.println("City: "+city);
        System.out.println("State: "+state);
        System.out.println("Zip: "+zip);
        System.out.println("Phone Number: "+phoneNumber);
        System.out.println("Email: "+email);
    }
}
class AddressBook1 {
    private List<Contact> contactList;
    private Map<String, List<Contact>> cityMap;
    private Map<String, List<Contact>> stateMap;

    public AddressBook1() {
        this.contactList = new ArrayList<>();
        this.cityMap = new HashMap<>();
        this.stateMap = new HashMap<>();
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

            Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phone_number, email);
            boolean exists = contactList.stream().anyMatch(contact -> contact.equals(newContact));
            System.out.println("Contact added Successfully");

            if (exists) {
                System.out.println("Contact with this name already exists. Please enter a different contact.");
            } else {
                contactList.add(newContact);
                System.out.println("Contact added Successfully");
            }

            cityMap.computeIfAbsent(city.toLowerCase(), k -> new ArrayList<>()).add(newContact);
            stateMap.computeIfAbsent(state.toLowerCase(), k-> new ArrayList<>()).add(newContact);
            System.out.println("Do you want to add another contact? (yes/no)");
            addMore = br.readLine();

        } while (addMore.equalsIgnoreCase("yes"));
    }

    public void displayContactByCity(String city){
        List<Contact>  contacts = cityMap.get(city.toLowerCase());
        if (contacts!=null && !contacts.isEmpty()){
            System.out.println("Contacts in "+city+":");
            contacts.forEach(Contact::displayContact);
        }else {
            System.out.println("No contacts found in "+city+".");
        }
    }

    public void displayContactByState(String state){
        List<Contact> contacts = stateMap.get(state.toLowerCase());
        if (contacts!=null && !contacts.isEmpty()){
            System.out.println("Contacts in "+state+":");
            contacts.forEach(Contact::displayContact);
        }else {
            System.out.println("No contacts found in "+state+".");
        }
    }


    public List<Contact> getContact () {
        return contactList;
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
                sc.nextLine();
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
    private Map<String, AddressBook1> addressBooks;

    public AddressBook() {
        this.addressBooks = new HashMap<>();
    }

    public void createAddressBook(String name) {
        if(!addressBooks.containsKey(name)) {
            addressBooks.put(name, new AddressBook1());
            System.out.println("Address Book " + name + " created successfully.");
        }else {
            System.out.println("address Book with this name already exists.");
        }
    }

    public AddressBook1 getAddressBooks(String name) {
        return addressBooks.get(name);
    }

    public void displayAddressBooks() {
        if(addressBooks.isEmpty()) {
            System.out.println("No address Books available.");
        } else {
            System.out.println("Available address book:");
            for(String name : addressBooks.keySet()) {
                System.out.println("- " + name);
            }
        }
    }

    public List<Contact> searchByCityOrState(String city, String state) {
        return addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getContact().stream())
                .filter(Contact -> Contact.getCity().equalsIgnoreCase(city) || Contact.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Address Book Program");
        AddressBook book = new AddressBook();
        Scanner sc = new Scanner(System.in);
        String option;

        do {
            System.out.println("\n*** Address Book Manager ***");
            System.out.println("1. Create Address Book");
            System.out.println("2. Add Contact to Address Book");
            System.out.println("3. Display Contacts in Address Book");
            System.out.println("4. Edit Contact in Address Book");
            System.out.println("5. Delete Available Address Books");
            System.out.println("6. Search contacts by City or State");
            System.out.println("7. Display Contacts by City");
            System.out.println("8. Display Contacts by State");
            System.out.println("9. Display Available Address Books");
            System.out.println("10. Exit");
            System.out.println("Choose an option: ");
            option = sc.nextLine();


            switch (option) {
                case "1":
                    System.out.println("Enter Address Book name: ");
                    String bookName = sc.nextLine();
                    book.createAddressBook(bookName);
                    break;

                case "2":
                    System.out.println("Enter Address Book name: ");
                    String addBookName = sc.nextLine();
                    AddressBook1 addBook = book.getAddressBooks(addBookName);
                    if (addBook != null) {
                        addBook.addContact();
                    } else {
                        System.out.println("Address book not found. ");
                    }
                    break;

                case "3":
                    System.out.println("Enter Address Book name: ");
                    String displayBookName = sc.nextLine();
                    AddressBook1 displayBook = book.getAddressBooks(displayBookName);
                    if (displayBook!=null) {
                        displayBook.displayContact();
                    } else {
                        System.out.println("Address Book not found.");
                    }
                    break;

                case "4":
                    System.out.println("Enter Address Book name: ");
                    String editBookName = sc.nextLine();
                    AddressBook1 editBook = book.getAddressBooks(editBookName);
                    if(editBook!=null) {
                        editBook.editContact();
                    } else {
                        System.out.println("Address book not found");
                    }
                    break;

                case "5":
                    System.out.println("Enter Address Book name: ");
                    String deleteBookName = sc.nextLine();
                    AddressBook1 deleteBook = book.getAddressBooks(deleteBookName);
                    if ( deleteBook!=null) {
                        deleteBook.deleteContact();
                    } else {
                        System.out.println("Address book not found");
                    }
                    break;

                case "6":
                    System.out.println("Enter city to search");
                    String searchCity = sc.nextLine();
                    System.out.println("Enter state to search: ");
                    String searchState = sc.nextLine();
                    List<Contact> searchResults = book.searchByCityOrState(searchCity, searchState);
                    if (searchResults.isEmpty()) {
                        System.out.println("No contacts found in the specified city or state.");
                    } else {
                        System.out.println("Search Results");
                        searchResults.forEach(Contact::displayContact);
                    }
                    break;

                case "7":
                    System.out.println("Enter Address Book name: ");
                    String cityBookName = sc.nextLine();
                    AddressBook1 cityBook = book.getAddressBooks(cityBookName);
                    if (cityBook != null) {
                        System.out.println("Enter city to search: ");
                        String city = sc.nextLine();
                        cityBook.displayContactByCity(city);
                    } else {
                        System.out.println("Address Book not found.");
                    }
                    break;

                case "8":
                    System.out.println("Enter Address Book name: ");
                    String stateName = sc.nextLine();
                    AddressBook1 stateBook = book.getAddressBooks(stateName);
                    if (stateBook != null) {
                        System.out.println("Enter State to search: ");
                        String state = sc.nextLine();
                        stateBook.displayContactByState(state);
                    } else {
                        System.out.println("Address Book not found");
                    }
                    break;

                case "9":
                    book.displayAddressBooks();
                    break;

                case "10":
                default:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
            }
        } while(true);
    }
}
