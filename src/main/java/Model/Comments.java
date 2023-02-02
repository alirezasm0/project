package Model;

public class Comments {
    private UserAccount user;
    private Product product;
    private String comment;
    private String commentStatus;
    private boolean haveBought;
    public Comments(UserAccount user, Product product, String comment, boolean haveBought){
        this.user = user;
        this.comment = comment;
        this.product = product;
        this.haveBought = haveBought;
    }
    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public boolean isHaveBought() {
        return haveBought;
    }

    public void setHaveBought(boolean haveBought) {
        this.haveBought = haveBought;
    }
}
