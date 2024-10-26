package org.example.javafxlearning;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
        txtOut.setText("");
    }

    @FXML
    public void onBtnPress() {
        String txt = txtIn.getText();
        if(Double.parseDouble(txt)>Double.parseDouble(lblBalance.getText())){
//            Alert alert = new  Alert(Alert.AlertType.WARNING);
//            alert.setHeaderText("Insufficient funds for this expense");
//            alert.showAndWait();

            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Insufficient Balance");

            VBox dialogContent = new VBox(10);
            dialogContent.getChildren().add(new Text("You need $" + (Double.parseDouble(txt) - Double.parseDouble(lblBalance.getText())) + " more to cover this expense."));
            dialog.getDialogPane().setContent(dialogContent);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.showAndWait();
        }else {
            String type = dropList.getValue();
            txtOut.setText(txtOut.getText() + "\n" + txt + " for some " + type);
            lblBalance.setText(String.valueOf(Double.parseDouble(lblBalance.getText()) -Double.parseDouble(txt)));
        }
    }

    public void onBtnAddPress() {
        lblBalance.setText(String.valueOf(Double.parseDouble(lblBalance.getText()) + Double.parseDouble(txtAddBalance.getText())));
    }


}