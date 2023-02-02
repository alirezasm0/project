package Controller;

import Model.*;
import View.View;
import View.AdminView;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.SplittableRandom;
import java.util.regex.Matcher;

public class AdminController extends AbstractController {

    private View view;
    private UserAccount userAccount;
    private static AdminController adminController = null;

    /**
     * constructor
     * @param view
     */
    public AdminController(View view){
        this.view = view;
    }

    /**
     * singeltone
     * @return
     */
    public static AdminController getInstance(){
        if(adminController == null){
            adminController = new AdminController(new AdminView());
            return adminController;
        }else return adminController;
    }

    /**
     * set user account for whole class
     * @param userAccount
     */
    public void setUserAccount(UserAccount userAccount){
        this.userAccount = userAccount;
    }

    /**
     * start function
     */
    public void start(){
        view.run();
    }

    /**
     * function for edit personal information
     * @param matcher
     * @param scanner
     * @return Enum
     */
    public Enum editPersonalInfo(Matcher matcher, Scanner scanner) {
        String field = matcher.group("field");
        Enum fields = findType(field);
        if (fields == Fields.USERNAME) {
            return E_Exepctions.INVALID_CHANGE;
        }

        else if (fields == Fields.FIRST_NAME) {
            String firstName = view.readString(scanner, "please enter your first name:");
            changeFirstName(firstName);
            return E_Exepctions.SUCCESSFULLY_CHANGED;
        }

        else if (fields == Fields.LAST_NAME){
            String lastName = view.readString(scanner, "please enter your last name:");
            changeLastName(lastName);
            return E_Exepctions.SUCCESSFULLY_CHANGED;
        }

        else if(fields == Fields.EMAIL){
            String email = view.readString(scanner, "please enter your email:");
            changeEmail(email);
            return E_Exepctions.SUCCESSFULLY_CHANGED;
        }

        else if(fields == Fields.PHONE_NUMBER){
            String number = view.readString(scanner, "please enter your number:");
            changeNumber(number);
            return E_Exepctions.SUCCESSFULLY_CHANGED;
        }

        else if(fields == Fields.COMPANY_NAME){
            String companyName = view.readString(scanner, "please enter your company name:");
            changeCompanyName(companyName);
            return E_Exepctions.SUCCESSFULLY_CHANGED;
        }

        return Roles.INVALID;
    }

    /**
     * function to find type entered by the user
     * @param type
     * @return Enum
     */
    private Enum findType(String type){
        if(type.equals("username")){
            return Fields.USERNAME;
        }else if(type.equals("firstName")){
            return Fields.FIRST_NAME;
        }else if(type.equals("lastName")){
            return Fields.LAST_NAME;
        }else if(type.equals("companyName")){
            return Fields.COMPANY_NAME;
        }else if(type.equals("email")){
            return Fields.EMAIL;
        }else if(type.equals("phoneNumber")){
            return Fields.PHONE_NUMBER;
        }
        return Roles.INVALID;
    }

    /**
     * function to show personal information
     * @return personal information string
     */
    public String personalInfo(){
        return  "first name: " + userAccount.getFirstName() + " \n" +
                "last name: " + userAccount.getLastName() + "\n" +
                "account name: " + userAccount.getAccountName() + "\n" +
                "role: " + userAccount.getRole() + "\n" +
                "company name: " + userAccount.getCompanyName() + "\n" +
                "phone number: " + userAccount.getPhoneNumber() + "\n";
    }

    /**
     * function for change first name
     * @param firstname
     */
    private void changeFirstName(String firstname){
        userAccount.setFirstName(firstname);
    }

    /**
     * function for change last name
     * @param lastname
     */
    private void changeLastName(String lastname){
        userAccount.setLastName(lastname);
    }

    /**
     * function for change email
     * @param email
     */
    private void changeEmail(String email){
        userAccount.setEmail(email);
    }

    /**
     * function for change company name
     * @param name
     */
    private void changeCompanyName(String name){
        userAccount.setCompanyName(name);
    }

    /**
     * function for change number
     * @param number
     */
    private void changeNumber(String number){
        userAccount.setNumber(number);
    }

    /**
     * function for show user lists
     * @return user lists
     */
    public ArrayList<UserAccount> manageUser(){
        return Database.getInstance().getUsersList();
    }

    /**
     * function for delete User
     * @param username
     * @return result enum
     */
    public Enum deleteUser(String username){
        UserAccount user = Database.getInstance().getUserByUsername(username);
        if(user == null){
            return Statics.NO_USER_FOUND;
        }
        Database.getInstance().deleteUser(user);
        return E_Exepctions.SUCCESSFUL;
    }

    /**
     * function for show products
     * @return products arraylist
     */
    public ArrayList<Product> mangeProducts(){
        return Database.getInstance().getProductArrayList();
    }

    /**
     * function for create auction
     * @param productID
     * @param bDate
     * @param eDate
     * @param discountAmount
     */
    public void createAuction(int productID, Matcher bDate, Matcher eDate, int discountAmount){
        Product product = Database.getInstance().findProductWithID(productID);
        Auction auction = new Auction(Database.getInstance().getLastAuctionID(),
                                      Integer.parseInt(bDate.group("year")),
                                      Integer.parseInt(bDate.group("month")),
                                      Integer.parseInt(bDate.group("day")),
                                      Integer.parseInt(eDate.group("year")),
                                      Integer.parseInt(eDate.group("month")),
                                      Integer.parseInt(eDate.group("day")),
                                      discountAmount);
        auction.addProduct(product);
        Database.getInstance().addAuction(auction);
    }

    /**
     * function for get categories list
     * @return category array list
     */
    public ArrayList<Category> getCategories(){
        return Database.getInstance().getCategories();
    }

    /**
     * function for find person by name
     * @param name
     * @return User
     */
    public UserAccount findPerson(String name){
        return Database.getInstance().getUserByUsername(name);
    }

    /**
     * function for get category by its name
     * @param name
     * @return category
     */
    public Category getCategory(String name){
        return Database.getInstance().getCategoryByName(name);
    }

    /**
     * function for create category
     * @param name
     * @return boolean result
     */
    public boolean createCategory(String name){
        if(Database.getInstance().getCategoryByName(name) == null){
            Category category = new Category(name);
            Database.getInstance().addCategory(category);
            return true;
        }
        return false;
    }

    /**
     * function for change menu
     */
    public void back(){
        GrandController.getInstance().setWhichController(Statics.LoginController);
    }

    /**
     * function for find product with id
     * @param id
     * @return product
     */
    public Product findProductWithID(int id){
        ArrayList<Product> products = Database.getInstance().getProductArrayList();
        for(Product product:products){
            if(product.getProductID() == id){
                return product;
            }
        }
        return null;
    }
}
