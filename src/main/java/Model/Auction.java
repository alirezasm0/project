package Model;

import java.util.ArrayList;

public class Auction {
    private int auctionID;
    private ArrayList<Product> productsOnSaleList;
    private String saleStatus;
    private int beginYearDate;
    private int beginMonthDate;
    private int beginDayDate;
    private int endYearDate;
    private int endMonthDate;
    private int endDayDate;
    private int discountAmount;

    /**
     * constructor
     * @param auctionID
     * @param beginYearDate
     * @param beginMonthDate
     * @param beginDayDate
     * @param endYearDate
     * @param endMonthDate
     * @param endDayDate
     * @param discountAmount
     */
    public Auction(int auctionID, int beginYearDate, int beginMonthDate, int beginDayDate
                  ,int endYearDate, int endMonthDate, int endDayDate, int discountAmount){
        this.auctionID = auctionID;
        this.beginYearDate = beginYearDate;
        this.beginDayDate = beginDayDate;
        this.beginMonthDate = beginMonthDate;
        this.endYearDate = endYearDate;
        this.endMonthDate = endMonthDate;
        this.endDayDate = endDayDate;
        this.discountAmount = discountAmount;
        productsOnSaleList = new ArrayList<>();
    }

    /**
     * function for get auction id
     * @return auction id
     */
    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public ArrayList<Product> getProductsOnSaleList() {
        return productsOnSaleList;
    }

    public void setProductsOnSaleList(ArrayList<Product> productsOnSaleList) {
        this.productsOnSaleList = productsOnSaleList;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public int getBeginYearDate() {
        return beginYearDate;
    }


    public int getDiscountAmount() {
        return discountAmount;
    }

    /**
     * function for add product
     * @param product
     */
    public void addProduct(Product product){
        productsOnSaleList.add(product);
    }

}
