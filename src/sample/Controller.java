package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    private TextField lblResult;

    private double number1 = 0;
    private String operator = "";
    private boolean start = true;
    private boolean flag_dot = true;

    private Model model = new Model();

    public void processNumpad(ActionEvent event) {
        if (start) {
            lblResult.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        lblResult.setText(lblResult.getText() + value);
    }

    public void processDEL(ActionEvent event) {
        number1 = 0;
        operator = "";
        start = true;
        lblResult.setText("0");
        flag_dot = true;
    }

    public void processOperator(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        if (!"=".equals(value)) {
            if (!operator.isEmpty())
                return;

            operator = value;
            number1 = Double.parseDouble(lblResult.getText());
            lblResult.setText("");
        } else {
            if (operator.isEmpty())
                return;

            lblResult.setText(String.valueOf(model.calculate(number1, Double.parseDouble(lblResult.getText()), operator)));
            operator = "";
            start = true;
        }
    }

    public void processDOT(ActionEvent event) {
        if (lblResult.getText().indexOf(".") < 0) {
            lblResult.setText(lblResult.getText() + ".");
        }
    }
}

