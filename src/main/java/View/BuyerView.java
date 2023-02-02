package View;

import Controller.BuyerController;
import Model.Commands;
import Model.E_Exepctions;
import Model.Product;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BuyerView extends View{

    @Override
    public void run() {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_PURPLE + "welcome to buyer menu!, you can type help to see all commands supported here" + ANSI_RESET);
        Matcher matcher;
        while (true){
            input = scanner.nextLine();
            if(input.equals("EXIT")){
                break;
            }else if((matcher = Commands.getMatcher(input, Commands.viewPersonalInfo)) != null){
                System.out.println(BuyerController.getInstance().viewPersonalInfo());
            }else if((matcher = Commands.getMatcher(input, Commands.editPersonalInfo)) != null){
                Enum status = BuyerController.getInstance().editPersonalInfo(matcher, scanner);
                printStatus(status);
            }else if((matcher = Commands.getMatcher(input, Commands.findProductWithID)) != null){
                if(BuyerController.getInstance().findProductID(matcher) != null){

                    printProductInfo(BuyerController.getInstance().findProductID(matcher));
                }else System.out.println(ANSI_RED + "No item with this id exits!"+ANSI_RESET);
            }else if((matcher = Commands.getMatcher(input, Commands.getFindProductWithName)) != null){
                if(BuyerController.getInstance().findProductName(matcher) != null){
                    printProductInfo(BuyerController.getInstance().findProductName(matcher));
                }else System.out.println(ANSI_RED+"No item with this name exits!"+ANSI_RESET);
            }else if((matcher = Commands.getMatcher(input, Commands.comment)) != null){
                if(BuyerController.getInstance().findProductName(matcher) != null){
                    System.out.println("Please enter your comment:");
                    String comment = scanner.nextLine();
                    BuyerController.getInstance().addComment(comment, BuyerController.getInstance().findProductName(matcher));
                    System.out.println(ANSI_GREEN + "Comment added successfully!"+ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED+ "No product exits with this name!"+ANSI_RESET);
                }
            }else if((matcher = Commands.getMatcher(input, Commands.back)) != null){
                BuyerController.getInstance().back();
                break;
            } else if((matcher = Commands.getMatcher(input, Commands.setScore)) != null){
                if(BuyerController.getInstance().findProductName(matcher) != null){
                    System.out.println("Please enter your score: ");
                    float score = Float.parseFloat(scanner.nextLine());
                    BuyerController.getInstance().addScore(score, matcher);
                    System.out.println(ANSI_GREEN + "Score added successfully!" + ANSI_RESET);
                }else{
                    System.out.println(ANSI_RED + "No item with this name exits!" + ANSI_RESET);
                }
            }else if((matcher = Commands.getMatcher(input, Commands.buyProductWithName)) != null){
                Product product = BuyerController.getInstance().findProductName(matcher);
                if(product != null){
                    BuyerController.getInstance().buyProduct(product);
                    System.out.println(ANSI_GREEN + "product bought successfully!" + ANSI_RESET);
                }else {
                    System.out.println(ANSI_RED + "No item exits with this name!" + ANSI_RESET);
                }
            }else if((matcher = Commands.getMatcher(input, Commands.buyProductWithID)) != null){
                Product product = BuyerController.getInstance().findProductID(matcher);
                if(product != null){
                    if(BuyerController.getInstance().hasEnoughMoney(product)){
                        BuyerController.getInstance().buyProduct(product);
                        System.out.println(ANSI_GREEN + "product bought successfully!" + ANSI_RESET);
                    }else {
                        System.out.println(ANSI_GREEN + "you dont have enough money!" + ANSI_RESET);
                    }
                }else{
                    System.out.println(ANSI_RED + "No item exits with this id!" + ANSI_RESET);
                }
            }else if((matcher = Commands.getMatcher(input, Commands.addMoney)) != null){

            }else if((matcher = Commands.getMatcher(input, Commands.help)) != null){
                help();
            }
            else{
                System.out.println(ANSI_RED + "invalid command!" + ANSI_RESET);
            }
        }
    }

    @Override
    public String readString(Scanner scanner, String string) {
        return null;
    }

    private void printStatus(Enum status){
        if(status == E_Exepctions.SUCCESSFULLY_CHANGED){
            System.out.println("changes successfully applied!");
        }else if(status == E_Exepctions.INVALID_CHANGE){
            System.out.println("you can not change this item!");
        }else {
            System.out.println("invalid filed!");
        }
    }

    private void printProductInfo(Product product){
        System.out.println(BuyerController.getInstance().printProductInfo(product));
    }

    private void help(){
        System.out.println("welcome to the buyer menu!, here are all commands you can use!\n" +
                "1- view personal info: with this command you can see your personal info!\n" +
                "2- edit <field>: with this command you can edit your personal info\n" +
                "3- find product with id <id>: with this command you can find a products with its id\n" +
                "4- find product with name <name>: with this command you can find a product with its name!\n" +
                "5- comment <product name>: with this command you can comment on a particular product\n" +
                "6- set score <product name>: with this command you can give score to special product\n" +
                "7- buy product with name <name>: with this command you can buy a product with its name\n" +
                "8- buy product with id <id>: with this command you can buy a product with its id\n" +
                "9- add money <amount>: with this command you can add money to your pocket\n" +
                "10- back: with this command you go to login menu!\n" +
                "11- help: with this command you can see these information :)");
    }
}
