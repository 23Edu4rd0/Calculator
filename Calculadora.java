import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class Calculadora extends JFrame implements ActionListener {

    private JTextField display;
    private JPanel panel;
    private String[] buttons = { 
                "7", "8", "9", "/", 
                "4", "5", "6", "*", 
                "1", "2", "3", "-", 
                "0", ".", "=", "+",
                "C"  
            };
    private JButton[] button = new JButton[buttons.length];
    private CalculatorLogic logic = new CalculatorLogic();

    public Calculadora() {

        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        add(display, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));
        for (int i = 0; i < buttons.length; i++) {
            button[i] = new JButton(buttons[i]);
            button[i].setFont(new Font("Arial", Font.BOLD, 17));
            button[i].addActionListener(this);
            panel.add(button[i]);
        }
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            if (!logic.getOperator().equals("")) {
                logic.setOperand2(logic.getOperand2() + command);
            } else {
                logic.setOperand1(logic.getOperand1() + command);
            }
            display.setText(logic.getOperand1() + logic.getOperator() + logic.getOperand2());
        } else if (command.equals("=")) {
            try {
                double result = logic.calculate();
                display.setText(logic.getOperand1() + logic.getOperator() + logic.getOperand2() + "=" + result);
                logic.setOperand1(Double.toString(result));
                logic.setOperator("");
                logic.setOperand2("");
            } catch (NumberFormatException | ArithmeticException ex) {
                display.setText("Erro");
                logic.clear();
            }
        } else if (command.equals("C")) {
            logic.clear();
            display.setText("");
        } else {
            if (logic.getOperator().equals("") || logic.getOperand2().equals("")) {
                logic.setOperator(command);
            } else {
                try {
                    double result = logic.calculate();
                    logic.setOperand1(Double.toString(result));
                    logic.setOperator(command);
                    logic.setOperand2("");
                } catch (NumberFormatException | ArithmeticException ex) {
                    display.setText("Erro");
                    logic.clear();
                }
            }
            display.setText(logic.getOperand1() + logic.getOperator() + logic.getOperand2());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);
        });
    }
}