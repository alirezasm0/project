package Model;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.*;
public class Database {
    private static Database instance = null;
    private ArrayList<Logs> logsArrayList = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<UserAccount> userAccountArrayList = new ArrayList<>();
    private ArrayList<Product> productArrayList = new ArrayList<>();
    private ArrayList<Auction> auctionArrayList = new ArrayList<>();

    /**
     * singeltone
     * @return Database instance
     */
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }return instance;
    }

    /**
     * function for get user list
     * @return user list
     */
    public ArrayList<UserAccount> getUsersList(){
        return userAccountArrayList;
    }

    /**
     * function for check if username is uniqe or not
     * @param username
     * @return result boolean
     */
    public boolean isUsernameUnique(String username){
        for(int i = 0; i < userAccountArrayList.size(); i++){
            if(userAccountArrayList.get(i).getAccountName().equals(username))
                return false;
        }
        return true;
    }

    /**
     * function for adding user
     * @param userAccount
     */
    public void addUser(UserAccount userAccount){
        userAccountArrayList.add(userAccount);
    }

    /**
     * function for get user by user name
     * @param username
     * @return user
     */
    public UserAccount getUserByUsername(String username){
        for(int i = 0; i < userAccountArrayList.size(); i++){
            if(userAccountArrayList.get(i).getAccountName().equals(username))
                return userAccountArrayList.get(i);
        }
        return null;
    }

    /**
     * function for delete user
     * @param userAccount
     */
    public void deleteUser(UserAccount userAccount){
        userAccountArrayList.remove(userAccount);
    }

    /**
     * function for get products list
     * @return products list
     */
    public ArrayList<Product> getProductArrayList(){
        return productArrayList;
    }

    /**
     * function for find product with ID
     * @param id
     * @return product
     */
    public Product findProductWithID(int id){
        for(Product product: productArrayList){
            if(product.getProductID() == id){
                return product;
            }
        }
        return null;
    }

    /**
     * function for indexing auction and find last one
     * @return last auction id
     */
    public int getLastAuctionID(){
        if(auctionArrayList.size() == 0){
            return 0;
        }else return auctionArrayList.get(auctionArrayList.size() - 1).getAuctionID() + 1;
    }


    /**
     * function for add auction
     * @param auction
     */
    public void addAuction(Auction auction){
        auctionArrayList.add(auction);
    }

    /**
     * function for get category with name
     * @param name
     * @return category
     */
    public Category getCategoryWithName(String name){
        for(Category category: categories){
            if(category.getName().equals(name)){
                return category;
            }
        }
        return null;
    }

    /**
     * function for get category list
     * @return category list
     */
    public ArrayList<Category> getCategories(){
        return categories;
    }

    /**
     * function for get product with name
     * @param name
     * @return product
     */
    public Product getProductWithName(String name){
        for(Product product:productArrayList){
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    /**
     * function for get category with name
     * @param name
     * @return category
     */
    public Category getCategoryByName(String name){
        for(Category category: categories){
            if(category.getName().equals(name))
                return category;
        }
        return null;
    }

    /**
     * function for add category
     * @param category
     */
    public void addCategory(Category category){
        categories.add(category);
    }

    /**
     * function for indexing products and get last product id
     * @return last product id
     */
    public int getLastProductID(){
        if(productArrayList.size() == 0){
            return 0;
        }
        return productArrayList.get(productArrayList.size() - 1).getProductID();
    }

    /**
     * function for add product
     * @param product
     */
    public void addProduct(Product product){
        productArrayList.add(product);
    }
}

