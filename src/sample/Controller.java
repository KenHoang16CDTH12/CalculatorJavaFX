package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.Random;


public class Controller {
    public Label lblSyntax;
    @FXML
    private TextField lblResult;
    private double number1 = 0;
    private String operator = "";
    private boolean start = true;
    private boolean flag_dot = true;
    private String numberSyntax = "";
    private String ketqua = "";
    private Operator model = new Operator();
    public static int CHANGE_THEME = 1;

    public void processNumpad(ActionEvent event) {
        if (start) {
            lblResult.setText("");
            start = false;
            lblSyntax.setText("");
            ketqua = "";
        }
        String value = ((Button) event.getSource()).getText();
        lblResult.setText(lblResult.getText() + value);
        numberSyntax += value;
        lblSyntax.setText(numberSyntax);
    }

    public void processDEL(ActionEvent event) {
        number1 = 0;
        operator = "";
        numberSyntax = "";
        start = true;
        lblResult.setText("0");
        lblSyntax.setText("");
        flag_dot = true;
    }

    public void processOperator(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        numberSyntax += " " + value + " ";
        lblSyntax.setText(numberSyntax);
        if (!"=".equals(value)) {
            if (!operator.isEmpty())
                return;
            operator = value;
            number1 = Double.parseDouble(lblResult.getText());
            lblResult.setText("");
        } else {
            if (operator.isEmpty())
                return;
            ketqua = String.valueOf(model.calculate(number1, Double.parseDouble(lblResult.getText()), operator));
            lblResult.setText(ketqua);
            operator = "";
            lblSyntax.setText("");
            numberSyntax = ketqua;
            start = true;
        }
    }

    public void processDOT(ActionEvent event) {
        if (lblResult.getText().indexOf(".") < 0) {
            lblResult.setText(lblResult.getText() + ".");
            numberSyntax += ".";
            lblSyntax.setText(numberSyntax);
        }
    }

    public void handleGiaiThua(ActionEvent event) {
        double resultMain = 1;
        number1 = Double.parseDouble(lblResult.getText());
        for (int i = 0; i <= number1; i++) {
            resultMain = factorial(i);
        }
        lblResult.setText(resultMain + "");
    }

    public void handle1ChiaX(ActionEvent event) {

        number1 = Double.parseDouble(lblResult.getText());
        lblResult.setText(1/number1 +"");
    }

    public void handleDec(ActionEvent event) {
        DecimalFormat dec = new DecimalFormat("#");
        double decResult = Double.parseDouble(lblResult.getText());
        lblResult.setText(dec.format(decResult) + "");
    }
    public static double factorial(double number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }

    public void changeTheme(ActionEvent event) {
        Main.scene.getStylesheets().clear();
        if (CHANGE_THEME == 3) {
            CHANGE_THEME = 1;
        } else {
            CHANGE_THEME++;
        }
        switch (CHANGE_THEME) {
            case 1 :
                Main.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                break;
            case 2 :
                Main.scene.getStylesheets().add(getClass().getResource("applicationwindow.css").toExternalForm());
                break;
            case 3:
                Main.scene.getStylesheets().add(getClass().getResource("applicationrecordsales.css").toExternalForm());
                break;
        }
    }
}

