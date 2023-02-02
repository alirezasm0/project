package Controller;

import Model.*;
import View.*;

import java.nio.channels.SelectableChannel;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class SellerController extends AbstractController{
    private UserAccount userAccount;
    private View view;
    private static SellerController sellerController = null;

    /**
     * singeltone
     * @return seller controller instance
     */
    public static SellerController getInstance(){
        if(sellerController == null){
            sellerController = new SellerController(new SellerView());
        }
        return sellerController;
    }

    /**
     * function for set user for whole class
     * @param userAccount
     */
    public void setUserAccount(UserAccount userAccount){
        this.userAccount = userAccount;
    }

    /**
     * constructor
     * @param view
     */
    public SellerController(View view){
        this.view = view;
    }

    @Override
    public void start() {
        view.run();
    }

    /**
     * function for get personal information string
     * @return personal information string
     */
    public String getPersonalInfo(){
        return userAccount.personalInfoToString();
    }

    /**
     * function for get products
     * @return products array list
     */
    public ArrayList<Product> manageProducts(){
        return userAccount.getProductSale();
    }

    /**
     * function for add product
     * @param name
     * @param producerCompany
     * @param category
     * @param price
     */
    public void addProduct(String name, String producerCompany, String category, int price){
        int id = Database.getInstance().getLastProductID();
        Category category1 = Database.getInstance().getCategoryByName(category);
        Product product = new Product(name, id + 1, category1, producerCompany, price);
        userAccount.addProductSale(product);
        Database.getInstance().addProduct(product);
    }

    /**
     * function for remove special product
     * @param id
     * @return result boolean
     */
    public boolean removeProduct(int id){
        ArrayList<Product> productArrayList = userAccount.getProductSale();
        ArrayList<Product> productsListDatabase = Database.getInstance().getProductArrayList();
        Product temp = null;
        boolean found = false;
        for(Product product : productArrayList){
            if(product.getProductID() == id){
                found = true;
                temp = product;
            }
        }
        if(found){
            productsListDatabase.remove(temp);
            productArrayList.remove(temp);
            return true;
        }
        return false;
    }

    /**
     * function for check if category exits
     * @param name
     * @return result boolean
     */
    public boolean isCategoryExits(String name){
        if(Database.getInstance().getCategoryByName(name) == null){
            return false;
        }
        return true;
    }

    /**
     * function for change menu
     */
    public void back(){
        GrandController.getInstance().setWhichController(Statics.LoginController);
    }

    /**
     * function for change phone number
     * @param  phoneNumber
     */
    public void changeNumber(String phoneNumber){
        userAccount.setPhoneNumber(phoneNumber);
    }

    /**
     * function for change email address
     * @param email
     */
    public void changeEmail(String email){
        userAccount.setEmail(email);
    }

    /**
     * function for change first name
     * @param firstName
     */
    public void changeFirstName(String firstName){
        userAccount.setFirstName(firstName);
    }

    /**
     * function for change last name
     * @param lastName
     */
    public void changeLastName(String lastName){
        userAccount.setLastName(lastName);
    }

    /**
     * function for change company name
     * @param  companyName
     */
    public void changeCompanyName(String companyName){
        userAccount.setCompanyName(companyName);
    }

    /**
     * function for check which field user requested to change
     * @param field
     * @return target field to change
     */
    public Enum getEditField(String field){
        if(field.equals("last name")){
            return Fields.LAST_NAME;
        }else if(field.equals("first name")){
            return Fields.FIRST_NAME;
        }else if(field.equals("account name")){
            return E_Exepctions.INVALID_CHANGE;
        }else if(field.equals("company name")){
            return Fields.COMPANY_NAME;
        }else if(field.equals("phone number")){
            return Fields.PHONE_NUMBER;
        }else if(field.equals("email")){
            return Fields.EMAIL;
        }
        return E_Exepctions.INVALID_TYPE;
    }
}
