package Model;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Product> productsList;

    /**
     * constructor
     * @param name
     */
    public Category(String name){
        this.name = name;
        productsList = new ArrayList<>();
    }

    /**
     * function for get category name
     * @return category name
     */
    public String getName() {
        return name;
    }

    /**
     * function for set category name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void addProduct(Product product){
        productsList.add(product);
    }

    /**
     * function for get products in this category
     * @return products list
     */
    public ArrayList<Product> getCategoryProducts(){
        return productsList;
    }
}
