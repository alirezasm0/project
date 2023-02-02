package View;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginPageFxController {
    @FXML
    private TextField username;

    public void getUsername(MouseEvent keyEvent) {
        System.out.println(username.getText());
    }
}
