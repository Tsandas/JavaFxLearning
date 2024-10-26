package org.example.javafxlearning;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    public void onBtnRemovePress() {
        String txt = txtIn.getText();
        if(Double.parseDouble(txt)>Double.parseDouble(lblBalance.getText())){

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
            lblBalance.setText(String.valueOf(Double.parseDouble(lblBalance.getText()) - Double.parseDouble(txt)));
            if(txtAddBalance.getText() != null){
                Transactions tran = new Transactions("Removed",type,Double.parseDouble(lblBalance.getText()));
                addTransactionToFile(tran);
            }
        }
        txtIn.setText("");
    }

    private static final String FILE_PATH = "transactions_log.json";

    public void onBtnAddPress() {
        lblBalance.setText(String.valueOf(Double.parseDouble(lblBalance.getText()) + Double.parseDouble(txtAddBalance.getText())));
        if(txtAddBalance.getText() != null){
            Transactions tran = new Transactions("Added","Balance",Double.parseDouble(txtAddBalance.getText()));
            addTransactionToFile(tran);
        }
        txtAddBalance.setText("");
    }


    public static void addTransactionToFile(Transactions transaction) {
        try {
            JSONArray jsonArray = new JSONArray();
            File file = new File(FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder jsonContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonContent.append(line);
                }
                reader.close();
                jsonArray = new JSONArray(jsonContent.toString());
            }

            JSONObject jsonTransaction = new JSONObject();
            jsonTransaction.put("addedSpent", transaction.getAddedSpent());
            jsonTransaction.put("type", transaction.getType());
            jsonTransaction.put("amount", transaction.getAmount());

            jsonArray.put(jsonTransaction);

            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(jsonArray.toString(4)); // 4 is for pretty print
            fileWriter.close();

            System.out.println("Transaction added successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}