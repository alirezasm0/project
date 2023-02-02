package Controller;

import Model.*;
import View.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class BuyerController extends AbstractController {

    private static BuyerController buyerController = null;
    private UserAccount userAccount;
    private  View view;

    /**
     * singeltone
     * @return buyer controller instance
     */
    public static BuyerController getInstance(){
        if(buyerController == null){
            buyerController = new BuyerController(new BuyerView());
            return buyerController;
        }
        return buyerController;
    }

    /**
     * constructor
     * @param view
     */
    public BuyerController(View view){
        this.view = view;
    }

    /**
     * function for set user account for whole class
     * @param userAccount
     */
    public void setUserAccount(UserAccount userAccount){
        this.userAccount = userAccount;
    }

    @Override
    public void start() {
        view.run();
    }

    /**
     * function for show user personal information
     * @return information string
     */
    public String viewPersonalInfo(){
        return userAccount.personalInfoToString();
    }

    /**
     * function for find field entered by user
     * @param type
     * @return field type
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
     * function for change first name
     * @param firsName
     */
    private void changeFirstName(String firsName){
        userAccount.setFirstName(firsName);
    }

    /**
     * function for change last name
     * @param last
     */
    private void changeLastName(String last){
        userAccount.setLastName(last);
    }

    /**
     * function for change email
     * @param email
     */
    private void changeEmail(String email){
        userAccount.setEmail(email);
    }

    /**
     * function for change number
     * @param number
     */
    private void changeNumber(String number){
        userAccount.setNumber(number);
    }

    /**
     * function for edit personal information
     * @param matcher
     * @param scanner
     * @return result enum
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


        return Roles.INVALID;
    }

    /**
     * function for find product using id
     * @param matcher
     * @return product
     */
    public Product findProductID(Matcher matcher){
        return Database.getInstance().findProductWithID(Integer.parseInt(matcher.group("id")));
    }

    /**
     * function for find product using name
     * @param matcher
     * @return product
     */
    public Product findProductName(Matcher matcher){
        return Database.getInstance().getProductWithName(matcher.group("name"));
    }

    /**
     * function for get product information string
     * @param product
     * @return product information string
     */
    public String printProductInfo(Product product){
        StringBuilder comment = new StringBuilder("");
        ArrayList<Comments> comments = product.getComments();
        if(comments.size() == 0){
            comment.append("no comment yet!");
        }else{
            for(Comments comments1 : comments){
                comment.append(comments1.getUser().getAccountName()).append("(has bought : ").append(hasBought(product)).append(" ) : ").append(comments1.getComment());
            }
        }
        return product.productInfoToString() + comment.toString();
    }

    /**
     * function for check if user has bought the product
     * @param product
     * @return result boolean
     */
    private boolean hasBought(Product product){
        return userAccount.haveBought(product);
    }

    /**
     * function for add comment
     * @param comment
     * @param product
     */
    public void addComment(String comment, Product product){
        boolean haveBought = false;
        if(userAccount.haveBought(product)) haveBought = true;
        Comments comments = new Comments(userAccount, product, comment, haveBought);
        product.addComment(comments);
    }

    /**
     * function for add score to the product
     * @param score
     * @param matcher
     */
    public void addScore(float score, Matcher matcher){
        Product product = findProductName(matcher);
        product.addScore(score);
    }

    /**
     * function for buy product by the user
     * @param product
     */
    public void buyProduct(Product product){
        userAccount.addProductBought(product);
    }

    /**
     * function for change menu
     */
    public void back(){
        GrandController.getInstance().setWhichController(Statics.LoginController);
    }

    /**
     * function for check if user has enough money or not
     * @param product
     * @return
     */
    public boolean hasEnoughMoney(Product product){
        if(product.getPrice() <= userAccount.getMoneyHave()){
            return true;
        }
        return false;
    }
}
