package Controller;

import Model.Statics;
import Model.UserAccount;

import java.util.HashMap;
import java.util.Map;

public class GrandController {

    private static GrandController grandController = null;
    private Statics whichController;
    private Map<Statics, AbstractController> map;
    private UserAccount userAccount;

    public static GrandController getInstance(){
        if(grandController == null){
            grandController = new GrandController();
            return grandController;
        }
        return grandController;
    }

    public GrandController(){
        map = new HashMap<Statics, AbstractController>();
        initialize();
        whichController = Statics.LoginController;
    }

    public void start(){
        while (true){
            switch (whichController){
                case LoginController:
                    map.get(Statics.LoginController).start();
                    break;
                case AdminController:
                    AdminController.getInstance().setUserAccount(userAccount);
                    map.get(Statics.AdminController).start();
                    break;
                case BuyerController:
                    BuyerController.getInstance().setUserAccount(userAccount);
                    map.get(Statics.BuyerController).start();
                    break;
                case SellerController:
                    SellerController.getInstance().setUserAccount(userAccount);
                    map.get(Statics.SellerController).start();
            }
        }
    }

    public void setWhichController(Statics controller){
        whichController = controller;
    }

    public void setUserAccount(UserAccount userAccount){
        this.userAccount = userAccount;
    }

    private void initialize(){
        map.put(Statics.LoginController, LoginController.getInstance());
        map.put(Statics.AdminController, AdminController.getInstance());
        map.put(Statics.BuyerController, BuyerController.getInstance());
        map.put(Statics.SellerController, SellerController.getInstance());
    }
}
