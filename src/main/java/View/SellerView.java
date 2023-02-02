package View;

import Controller.SellerController;
import Model.Commands;
import Model.E_Exepctions;
import Model.Fields;
import Model.Product;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SellerView extends View{
    @Override
    public void run() {
        Matcher matcher;
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_PURPLE + "welcome to seller menu! you can type help to see all commands supported here." + ANSI_RESET);
        String input;
        while(true){
            input = scanner.nextLine();
            if(input.equals("EXIT")){
                break;
            }
            if((matcher = Commands.getMatcher(input, Commands.viewPersonalInfo)) != null){
                System.out.println(SellerController.getInstance().getPersonalInfo());
            }else if((matcher = Commands.getMatcher(input, Commands.sellerMangeProducts)) != null){
                ArrayList<Product> products = SellerController.getInstance().manageProducts();
                if(products.size() == 0){
                    System.out.println("no item exits!");
                }else{
                    printProductsList(products);
                }
            }else if((matcher = Commands.getMatcher(input, Commands.addProduct))!=null){
                String name = null, category = null, producerCompany = null;
                System.out.println("please enter product name:");
                name = scanner.nextLine();
                System.out.println("please enter company name:");
                producerCompany = scanner.nextLine();
                System.out.println("please enter category name:");
                category = scanner.nextLine();
                System.out.println("please enter price : ");
                int price = Integer.parseInt(scanner.nextLine());
                if(SellerController.getInstance().isCategoryExits(category)){
                    SellerController.getInstance().addProduct(name, producerCompany, category, price);
                    System.out.println("product added successfully!");
                }else System.out.println("no category exits with this name!");
            }else if((matcher = Commands.getMatcher(input, Commands.removeProduct)) != null){
                if (SellerController.getInstance().removeProduct(Integer.parseInt(matcher.group("id")))){
                    System.out.println("product deleted successfully!");
                }else System.out.println("product not found!");
            }else if((matcher = Commands.getMatcher(input, Commands.back)) != null){
                SellerController.getInstance().back();
                break;
            }else if((matcher = Commands.getMatcher(input, Commands.help)) != null){
                help();
            }else if((matcher = Commands.getMatcher(input, Commands.editInfo)) != null){
                Enum e = SellerController.getInstance().getEditField(matcher.group("field"));
                if(e == E_Exepctions.INVALID_CHANGE){
                    System.out.println(ANSI_RED + "You cannot change this item!" + ANSI_RESET);
                }else if(e == E_Exepctions.INVALID_TYPE){
                    System.out.println(ANSI_RED + "Invalid type!" + ANSI_RESET);
                }else if(e == Fields.FIRST_NAME){
                    System.out.println("Please enter new first name: ");
                    String first = scanner.nextLine();
                    SellerController.getInstance().changeFirstName(first);
                    System.out.println(ANSI_GREEN + "First name successfully changed." + ANSI_RESET);
                }else if(e == Fields.LAST_NAME){
                    System.out.println("Please enter new last name: ");
                    String last = scanner.nextLine();
                    SellerController.getInstance().changeLastName(last);
                    System.out.println(ANSI_GREEN + "Last name changed successfully." + ANSI_RED);
                }else if(e == Fields.COMPANY_NAME){
                    System.out.println("Please enter new company name:");
                    String company = scanner.nextLine();
                    SellerController.getInstance().changeCompanyName(company);
                    System.out.println(ANSI_GREEN + "Company name changed successfully." + ANSI_RESET);
                }else if(e == Fields.EMAIL){
                    System.out.println("Please enter new email:");
                    String email = scanner.nextLine();
                    SellerController.getInstance().changeEmail(email);
                    System.out.println(ANSI_GREEN + "Email changed successfully." + ANSI_RESET);
                }else if(e == Fields.PHONE_NUMBER){
                    System.out.println("Please enter new phone number:");
                    String phoneNumber = scanner.nextLine();
                    SellerController.getInstance().changeNumber(phoneNumber);
                    System.out.println(ANSI_GREEN + "Phone number changed successfully." + ANSI_RESET);
                }
            }
            else{
                System.out.println("invalid command!");
            }
        }
    }

    @Override
    public String readString(Scanner scanner, String string) {
        return null;
    }

    public void printProductsList(ArrayList<Product> productArrayList){
        for(Product product:productArrayList){
            System.out.println(product.productInfoToString());
        }
    }


    private void help(){
        System.out.println(ANSI_CYAN + "welcome to seller menu! these are all command supported here:\n" +
                "1- view personal info: with this command you will see some information about yourself!\n" +
                "2- manage products: with this command you can see all products of yourself\n" +
                "3- add product: with this command you can add a product\n" +
                "4- remove product <product id>: with this command you can remove a product" +
                "5- edit <field>: with this command you can edit your personal information."+ ANSI_RESET);
    }
}
