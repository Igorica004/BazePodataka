package view;

import app.App;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utility.JDBCUtils;
import javafx.scene.control.PasswordField;

public class ProzorPrijava extends VBox {

    Button btnSign = new Button("sign up");
    Button btnLog = new Button("log in");
    TextField tfUsername = new TextField();
    PasswordField tfPassword = new PasswordField();
    Label lbUsername = new Label("Usename");
    Label lbPassword = new Label("Password");
    Label poruka = new Label("");
    HBox hb1 = new HBox();
    HBox hb2 = new HBox();
    public ProzorPrijava() {
        hb1.getChildren().addAll(lbUsername, tfUsername);
        hb2.getChildren().addAll(lbPassword, tfPassword);
        hb1.setAlignment(Pos.CENTER);
        hb1.setSpacing(10);
        hb1.setPadding(new Insets(10));
        hb2.setAlignment(Pos.CENTER);
        hb2.setSpacing(10);
        hb2.setPadding(new Insets(10));

        this.getChildren().addAll(btnSign,btnLog,hb1,hb2,poruka);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(10));

        setSignAction();
        setLoginAction();
    }

    private void setSignAction() {
        btnSign.setOnAction((action)->{
            App.window.setScene(new Scene(new ProzorSignUp(),800,600));
        });
    }

    private void setLoginAction() {
        btnLog.setOnAction((action)->{

            int psihoterapeut = JDBCUtils.login(tfUsername.getText(),tfPassword.getText());
            if(psihoterapeut == -1)
                poruka.setText("Nije dobar username ili password.");
            else{
                App.window.setScene(new Scene(new ProzorGlavni(psihoterapeut),800,720));
            }

        });
    }


}
