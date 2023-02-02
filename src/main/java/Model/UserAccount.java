package Model;

import java.util.ArrayList;

public class UserAccount{
    private String accountName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String companyName;
    private ArrayList<Auction> discountList;
    private Roles role;
    private ArrayList<Product> boughtProducts;
    private ArrayList<Product> productSale;
    private int moneyHave;


    /**
     * constructor
     * @param username
     * @param role
     * @param password
     */
    public UserAccount(String username, Roles role, String password){
        this.accountName = username;
        this.role = role;
        this.password = password;
        phoneNumber = "";
        companyName = "didn't set!";
        email = "didn't set!";
        firstName = "didn't set!";
        lastName = "didn't set!";
        moneyHave = 0;
        boughtProducts = new ArrayList<>();
        if(role == Roles.Seller){
            productSale = new ArrayList<>();
        }
    }

    /**
     * function for add money
     * @param money
     */
    public void addMoney(int money){
        this.moneyHave += money;
    }

    /**
     * function for get money in wallet
     * @return money in wallet
     */
    public int getMoneyHave(){
        return moneyHave;
    }

    /**
     * function for get user role
     * @return user role
     */
    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    /**
     * function for get account name
     * @return get account name
     */
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * function for get phone number
     * @return user phone numberf
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * function for set phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDiscountList(Auction discountList) {
        this.discountList.add(discountList);
    }

    public ArrayList<Logs> getLogsArrayList() {
        return logsArrayList;
    }

    public void setLogsArrayList(ArrayList<Logs> logsArrayList) {
        this.logsArrayList = logsArrayList;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    private ArrayList<Logs> logsArrayList;
    private int wallet;

    public String getNumber() {
        return phoneNumber;
    }

    /**
     * function for get user password
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * function for get user company name
     * @return company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * function for get email
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * function for get first name
     * @return user first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * function for get last name
     * @return user last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * function for set company name
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * function for set email address
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * function for set user first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * function for set user last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * function for set user phone number
     * @param number
     */
    public void setNumber(String number) {
        this.phoneNumber = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Auction> getDiscountList() {
        return discountList;
    }

    /**
     * function for get personal information string
     * @return information string
     */
    public String personalInfoToString(){
        return  "first name: " + this.getFirstName() + " \n" +
                "last name: " + this.getLastName() + "\n" +
                "account name: " + this.getAccountName() + "\n" +
                "role: " + this.getRole() + "\n" +
                "company name: " + this.getCompanyName() + "\n" +
                "phone number: " + this.getPhoneNumber() + "\n";
    }

    /**
     * function for check if user bought a product
     * @param product
     * @return result boolean
     */
    public boolean haveBought(Product product){
        for(Product product1: boughtProducts){
            if(product1.getProductID() == product.getProductID()){
                return true;
            }
        }
        return false;
    }

    /**
     * function for get seller products
     * @return product array list
     */
    public ArrayList<Product> getProductSale(){
        return productSale;
    }

    /**
     * function for add product for seller
     * @param product
     */
    public void addProductSale(Product product){
        productSale.add(product);
    }

    /**
     * function for add product bought
     * @param product
     */
    public void addProductBought(Product product){
        boughtProducts.add(product);
    }
}
