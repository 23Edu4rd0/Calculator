
public class CalculatorLogic {
    private String operand1 = "";
    private String operand2 = "";
    private String operator = "";

    public String getOperand1() {
        return operand1;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double calculate() {
        double n1 = Double.parseDouble(operand1);
        double n2 = Double.parseDouble(operand2);
        switch (operator) {
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            case "*":
                return n1 * n2;
            case "/":
                if (n2 == 0) {
                    throw new ArithmeticException("Divisão por zero");
                }
                return n1 / n2;
            default:
                throw new IllegalArgumentException("Operador inválido: " + operator);
        }
    }

    public void clear() {
        operand1 = "";
        operand2 = "";
        operator = "";
    }
}