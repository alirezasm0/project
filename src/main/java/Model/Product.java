package Model;

import java.util.ArrayList;

public class Product {
    private int productID;
    private String productStatus;
    private Category category;
    private String name;
    private String producerCompany;
    private float averageScore;
    private int price;
    private float sum;
    private int totalUserScored;
    ArrayList<Comments> comments;

    /**
     * constructor
     */
    public Product(){

    }

    /**
     * constructor
     * @param name
     * @param id
     * @param category
     * @param producerCompany
     * @param price
     */
    public Product(String name, int id, Category category, String producerCompany, int price){
        this.productID = id;
        this.name = name;
        this.category = category;
        this.producerCompany = producerCompany;
        this.productStatus = "available";
        this.averageScore = 0.0F;
        this.sum = 0.0F;
        this.price = price;
        this.totalUserScored = 0;
        comments = new ArrayList<>();
    }

    /**
     * function for get product id
     * @return product id
     */
    public int getProductID() {
        return productID;
    }

    /**
     * function for get product status
     * @return product status
     */
    public String getProductStatus() {
        return productStatus;
    }

    /**
     * function for set product status
     * @param productStatus
     */
    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * function for get product category
     * @return product category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * function for get product name
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * function for set product name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * function for get producer company name
     * @return producer company name
     */
    public String getProducerCompany() {
        return producerCompany;
    }

    /**
     * function for get average score
     * @return average score
     */
    public float getAverageScore() {
        return averageScore;
    }


    /**
     * function for get product information
     * @return product information string
     */
    public String productInfoToString(){
        return  "name: " + this.name + "\n" +
                "category: " + this.category.getName() + "\n" +
                "product ID: " + this.productID + "\n" +
                "product status: "+ this.productStatus + "\n"+
                "product company: " + this.producerCompany + "\n" +
                "average score: " + this.getAverageScore() +  "\n" +
                "price: " + this.price + "\n";
    }

    private String commentToString(UserAccount userAccount){
        StringBuilder n = new StringBuilder();
        if(comments.size() == 0){
            return "no comments yet!";
        }else{
            for(Comments comment : comments){
                n.append(comment.getUser().getAccountName()).append(":").append(comment.getComment()).append("\n");
            }
        }
        return n.toString();
    }

    /**
     * function for add comment
     * @param comments
     */
    public void addComment(Comments comments){
        this.comments.add(comments);
    }

    /**
     * function for get comment list
     * @return comment list
     */
    public ArrayList<Comments> getComments(){
        return comments;
    }

    /**
     * function for add score
     * @param score
     */
    public void addScore(float score){
        this.sum += score;
        this.totalUserScored += 1;
        setAverageScore();
    }

    /**
     * function for calculate average score
     */
    private void setAverageScore(){
        if(totalUserScored != 0){
            this.averageScore = this.sum / this.totalUserScored;
        }
    }

    /**
     * function for get product price
     * @return product price
     */
    public int getPrice(){
        return price;
    }
}
