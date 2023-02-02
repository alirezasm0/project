package View;

import Controller.LoginController;
import Model.Commands;
import Model.Statics;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginView extends View{

    @Override
    public String readString(Scanner scanner, String string) {
        System.out.println(string);
        String pass = scanner.nextLine();
        return pass;
    }

    public void run(){
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_PURPLE + "Welcome to Login menu!, you can type help to see all commands supported here."+ANSI_RESET);
        while (true){
            Matcher matcher;
            input = scanner.nextLine();
            if(input.equals("EXIT")){
                scanner.close();
                break;
            }
            if((matcher = Commands.getMatcher(input, Commands.CREATEACCOUNT)) != null){
                Enum status = LoginController.getInstance().createAccountRequest(matcher, scanner);
                if( status == Statics.ACCOUNT_CREATED){
                    System.out.println(ANSI_GREEN + "Account created successfully!" + ANSI_RESET);
                }else if(status == Statics.ACCOUNT_CREATION_FAILED_INVALID_TYPE){
                    System.out.println(ANSI_RED + "Invalid type" + ANSI_RESET);
                }else if(status == Statics.ACCOUNT_CREATION_FAILED_USER_EXITS){
                    System.out.println(ANSI_RED + "A user exits with this user name." + ANSI_RESET);
                }
            }else if((matcher = Commands.getMatcher(input, Commands.LoginReq)) != null){
                Enum result = LoginController.getInstance().loginRequest(matcher, scanner);
                if(result == Statics.NO_USER_FOUND){
                    System.out.println(ANSI_RED + "NO user found with this username" + ANSI_RESET);
                }else if(result == Statics.LOGIN_FAILED_WRONG_PASSWORD){
                    System.out.println(ANSI_RED + "Wrong password!" + ANSI_RESET);
                }else if(result == Statics.LOGGED_IN_SUCCESSFULLY){
                    System.out.println(ANSI_GREEN + "Logged in successfully!" + ANSI_RESET);
                    break;
                }
            }else if((matcher = Commands.getMatcher(input, Commands.help)) != null){
                help();
            }
            else{
                System.out.println(ANSI_RED + "Invalid command!" + ANSI_RESET);
            }
        }
    }

    private void help(){
        System.out.println(ANSI_CYAN + "welcome to the digikala application! you are in login menu now! these are all commands you can use:\n" +
                "1- create account <type> <user name>: with this command you can create a new account, please note that we support 3 types: 1-admin, 2- buyer, 3- seller\n" +
                "2- Login <user name>: with this command you can login to your account!\n" +
                "3- help: with this command you can see these information :)" + ANSI_RESET);
    }


}
