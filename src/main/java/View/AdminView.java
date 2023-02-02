package View;

import Controller.AdminController;
import Model.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminView extends View{
    @Override
    public void run() {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_PURPLE + "welcome admin!, you can type help to see all commands supported here." + ANSI_RESET);
        while (true){
            Matcher matcher;
            input = scanner.nextLine();
            if(input.equals("EXIT")){
                scanner.close();
                break;
            }
            if((matcher = Commands.getMatcher(input, Commands.viewPersonalInfo)) != null){
                System.out.println(AdminController.getInstance().personalInfo());
            }else if((matcher = Commands.getMatcher(input, Commands.editPersonalInfo)) != null){
                Enum status = AdminController.getInstance().editPersonalInfo(matcher, scanner);
                printStatus(status);
            }else if((matcher = Commands.getMatcher(input, Commands.manageUser)) != null){
                printUsers(AdminController.getInstance().manageUser());
            }else if((matcher = Commands.getMatcher(input, Commands.deleteUser)) != null){
                Enum status = AdminController.getInstance().deleteUser(matcher.group("user"));
                if(status == Statics.NO_USER_FOUND){
                    System.out.println(ANSI_RED + "No user found with this user name!" + ANSI_RESET);
                }else{
                    System.out.println(ANSI_GREEN + "Successfully removed." + ANSI_RESET);
                }
            }else if((matcher = Commands.getMatcher(input, Commands.manageProducts)) != null){
                printProducts(AdminController.getInstance().mangeProducts());
            }else if((matcher = Commands.getMatcher(input, Commands.createDiscountCode)) != null){
                Matcher bDate = null;
                Matcher eDate = null;
                int amount = 0;
                int productId = getProductID(scanner);
                if(AdminController.getInstance().findProductWithID(productId) == null){
                    System.out.println(ANSI_RED + "No item with this id exits!" + ANSI_RESET);
                }else {
                    bDate = getDate(scanner, "Please enter begin date:");
                    if(bDate != null){
                        eDate = getDate(scanner, "Please enter end date:");
                        if(eDate != null){
                            amount = getDiscountAmount(scanner);
                            AdminController.getInstance().createAuction(productId, bDate, eDate, amount);
                        }
                    }
                }
            }else if((matcher = Commands.getMatcher(input, Commands.manageCategory)) != null){
                printCategories(AdminController.getInstance().getCategories());
                manageCategory(scanner, matcher);
            }else if((matcher = Commands.getMatcher(input, Commands.findPerson)) != null){
                UserAccount temp = AdminController.getInstance().findPerson(matcher.group("personName"));
                if(temp != null){
                    System.out.println(temp.personalInfoToString());
                }else{
                    System.out.println(ANSI_RED + "No user exits with this user name!" + ANSI_RESET);
                }
            }else if((matcher = Commands.getMatcher(input, Commands.getCategory)) != null){
                Category temp = AdminController.getInstance().getCategory(matcher.group("category"));
                if(temp != null){
                    printCategory(temp);
                }else{
                    System.out.println(ANSI_RED + "No category exits with this name." + ANSI_RESET);
                }
            }else if((matcher = Commands.getMatcher(input, Commands.createCategory)) != null){
                if(AdminController.getInstance().createCategory(matcher.group("name"))){
                    System.out.println(ANSI_GREEN + "Category created successfully!" + ANSI_RESET);
                }else{
                    System.out.println(ANSI_RED + "A category exits with this name!" + ANSI_RESET);
                }
            }else if((matcher = Commands.getMatcher(input, Commands.back)) != null){
                AdminController.getInstance().back();
                break;
            }else if((matcher = Commands.getMatcher(input, Commands.help)) != null){
                help();
            }
            else{
                System.out.println(ANSI_RED + "Invalid command!" + ANSI_RESET);
            }

        }
    }
    private void manageCategory(Scanner scanner, Matcher matcher){
        String input;
        while (true){
            input = scanner.nextLine();
            if(input.equals("EXIT")){
                return;
            }else if((matcher = Commands.getMatcher(input, Commands.editInfo)) != null){
                System.out.println("Please enter new category name:");
            }else if((matcher = Commands.getMatcher(input, Commands.addInfo)) != null){

            }else if((matcher = Commands.getMatcher(input, Commands.remove)) != null){

            }
        }
    }

    @Override
    public String readString(Scanner scanner, String string) {
        System.out.println(string);
        String pass = scanner.nextLine();
        return pass;
    }

    private void printStatus(Enum status){
        if(status == E_Exepctions.SUCCESSFULLY_CHANGED){
            System.out.println(ANSI_GREEN + "Changes successfully applied!" + ANSI_RESET);
        }else if(status == E_Exepctions.INVALID_CHANGE){
            System.out.println(ANSI_RED + "You can not change this item!" + ANSI_RESET);
        }else {
            System.out.println(ANSI_RED + "Invalid filed!" + ANSI_RESET);
        }
    }

    private void printUsers(ArrayList<UserAccount> accountArrayList){
        if(accountArrayList.size() == 0){
            System.out.println(ANSI_RED + "Empty list!" + ANSI_RESET);
        }else {
            for (UserAccount userAccount : accountArrayList) {
                System.out.println(userAccount.personalInfoToString());
            }
        }
    }

    private void printProducts(ArrayList<Product> productArrayList){
        if(productArrayList.size() == 0){
            System.out.println(ANSI_RED + "No item exits!" + ANSI_RESET);
        }else {
            for (Product product : productArrayList) {
                System.out.println(product.productInfoToString());
            }
        }
    }

    private int getProductID(Scanner scanner){
        System.out.println("Please enter product ID:");
        return Integer.parseInt(scanner.nextLine());
    }

    private Matcher getDate(Scanner scanner, String output){
        System.out.println(output);
        System.out.println("Please enter your date in <year/month/day> format!");
        String input = scanner.nextLine();
        Matcher matcher;
        if((matcher = Commands.getMatcher(input, Commands.date)) != null){
            return matcher;
        }else{
            System.out.println(ANSI_RED + "Invalid date type!" + ANSI_RESET);
        }
        return null;
    }

    private int getDiscountAmount(Scanner scanner){
        System.out.println("Please enter discount amount: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private void printCategories(ArrayList<Category> categoryArrayList){
        if(categoryArrayList.size() == 0){
            System.out.println(ANSI_RED + "Empty list!" + ANSI_RESET);
        }else{
            for (Category category: categoryArrayList){
                System.out.println(category.getName());
            }
        }
    }

    private void printCategory(Category category){
        ArrayList<Product> products = category.getCategoryProducts();
        for(Product product : products){
            System.out.println(product.productInfoToString());
        }
    }

    private void help(){
        System.out.println(ANSI_CYAN + "Hello admin!\nyou are in admin menu and commands you can enter is as follow:\n" +
                "1- <view personal info>: with this command you can see your personal info\n" +
                "2- edit <field>: with this command you can edit your personal info\n" +
                "3- manage user: with this command you can see all users!\n" +
                "4- delete user <username>: with this command you can delete a user\n" +
                "5- manage all products: with this command you can see all products available!\n" +
                "6- create discount code: with this command you can create a discount code after that you have to enter begin date and end date\n" +
                "7- manage categories: with this command you can see all categories available\n" +
                "8- find person <person name>: with this command you can find a person by its user name\n" +
                "9- get category <category name>: with this command you can find a category by its name\n" +
                "10- create category <category name>: with this command you can create a new category!\n" +
                "11- back: with this command you go to login menu\n" +
                "12- help: with this command you can see some information about how to use this menu!" + ANSI_RESET);
    }

}
