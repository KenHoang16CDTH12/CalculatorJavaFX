package sample;

import java.math.BigDecimal;

/**
 * Created by kenhoang on 5/22/17.
 */
public class Operator {

    public Double calculate(double number1, double number2, String operator) {
        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                if (number2 == 0)
                    return 0.0;

                return number1 / number2;
        }

        System.out.println("Unknown operator - " + operator);
        return 0.0;
    }
}
