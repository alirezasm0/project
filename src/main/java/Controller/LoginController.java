package Controller;

import Model.*;
import View.LoginView;
import View.View;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginController extends AbstractController{

    private static LoginController loginController = null;
    private View view;

    /**
     * constructor
     * @param view
     */
    public LoginController(View view){
        this.view = view;
    }

    /**
     * this function is singletone and create this controller once
     */
    public static LoginController getInstance(){
        if(loginController == null){
            loginController = new LoginController(new LoginView());
            return loginController;
        }
        return loginController;
    }

    /**
     * function to start this controller and it's view
     */
    public void start(){
        view.run();
    }


    /**
     * this function is public and send a request to login
     * @param matcher
     * @param scanner
     * @return Enum, status of login
     */
    public Enum loginRequest(Matcher matcher, Scanner scanner){
        return login(matcher, scanner);
    }

    /**
     * this function send request to create an account
     * @param matcher
     * @param scanner
     * @return Enum, status of create account process
     */
    public Enum createAccountRequest(Matcher matcher, Scanner scanner){
        return createAccount(matcher, scanner);
    }

    /**
     * this function is private and create an account
     * @param matcher
     * @param scanner
     * @return Enum, status of create account process
     */
    private Enum createAccount(Matcher matcher, Scanner scanner){
        String type = matcher.group("type");
        Roles E_type = findType(type);
        if(E_type == Roles.INVALID){
            return Statics.ACCOUNT_CREATION_FAILED_INVALID_TYPE;
        }
        String username = matcher.group("username");
        if(isUsernameValid(username)) {
            String password = view.readString(scanner, "please enter your password: ");
            UserAccount userAccount = new UserAccount(username, E_type, password);
            Database database = Database.getInstance();
            database.addUser(userAccount);
        }else {
            return Statics.ACCOUNT_CREATION_FAILED_USER_EXITS;
        }
        return Statics.ACCOUNT_CREATED;
    }

    /**
     * this function find the type entered by the user
     * @param type
     * @return Role, the role entered by the user
     */
    private Roles findType(String type){
        if(type.equals("admin")){
            return Roles.Admin;
        }else if(type.equals("buyer")){
            return Roles.Buyer;
        }else if(type.equals("seller")){
            return Roles.Seller;
        }
        return Roles.INVALID;
    }

    /**
     * this function change the Controller and view
     * @param controller
     */
    private void changeController(Statics controller){
        GrandController.getInstance().setWhichController(controller);
    }

    /**
     * this function check is username valid or not
     * @param username
     * @return boolean
     */
    private boolean isUsernameValid(String username){
        Database database = Database.getInstance();
        return database.isUsernameUnique(username);
    }


    /**
     * this function is private and do login process and set view and user account in grand controller
     * @param matcher
     * @param scanner
     * @return Enum, login request status
     */
    private Enum login(Matcher matcher, Scanner scanner){
        String  username = matcher.group("username");
        Database database = Database.getInstance();
        if(!isUsernameValid(username)){
            UserAccount user = database.getUserByUsername(username);
            if(user != null){
                String password = view.readString(scanner, "Please enter your password: ");
                if(user.getPassword().equals(password)){
                    if(user.getRole() == Roles.Admin){
                        changeController(Statics.AdminController);
                        setUserAccount(user);
                        return Statics.LOGGED_IN_SUCCESSFULLY;
                    }else if(user.getRole() == Roles.Buyer){
                        changeController(Statics.BuyerController);
                        setUserAccount(user);
                        return Statics.LOGGED_IN_SUCCESSFULLY;
                    }else if(user.getRole() == Roles.Seller){
                        changeController(Statics.SellerController);
                        setUserAccount(user);
                        return Statics.LOGGED_IN_SUCCESSFULLY;
                    }
                }else{
                    return Statics.LOGIN_FAILED_WRONG_PASSWORD;
                }
            }else{
                return Statics.NO_USER_FOUND;
            }
        }
        return Statics.NO_USER_FOUND;
    }

    private void setUserAccount(UserAccount userAccount){
        GrandController.getInstance().setUserAccount(userAccount);
    }
}
