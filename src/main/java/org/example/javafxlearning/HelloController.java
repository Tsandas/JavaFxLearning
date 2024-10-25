package org.example.javafxlearning;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class HelloController {
    @FXML
    private TextArea txtIn;

    @FXML
    private TextArea txtOut;

    @FXML
    private Button btn1;

    @FXML
    private ChoiceBox<String> dropList;

    @FXML
    private Label lblBalance;

    @FXML
    private TextArea txtAddBalance;

    @FXML
    private Button btnAddToBalance;


    @FXML
    public void initialize() {
        dropList.getItems().addAll("Food", "Drink");
        dropList.setValue("Food");
        lblBalance.setText("0");
    }

    String genV = "";

    @FXML
    public void onBtnPress() {

        String txt = txtIn.getText();
        String type = dropList.getValue();
        genV += (txt + " for some " + type + "\n");
        txtOut.setText(genV);
    }

    public void onBtnAddPress() {
        lblBalance.setText(String.valueOf(Double.parseDouble(lblBalance.getText()) + Double.parseDouble(txtAddBalance.getText())));
    }


}