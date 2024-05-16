import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculatorGUI {
    private JFrame frame;
    private JTextField displayField;

    private double num1, num2, result;
    private char operator;

    public BasicCalculatorGUI() {
        frame = new JFrame("Basic Calculator");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        displayField = new JTextField();
        displayField.setEditable(false);
        frame.add(displayField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        String[] buttonLabels = {"7", "8", "9", "/",
                                 "4", "5", "6", "*",
                                 "1", "2", "3", "-",
                                 "0", ".", "=", "+"};

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.setVisible(true);
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (Character.isDigit(command.charAt(0))) {
                displayField.setText(displayField.getText() + command);
            } else if (command.charAt(0) == '.') {
                if (!displayField.getText().contains(".")) {
                    displayField.setText(displayField.getText() + ".");
                }
                
            } else if (command.charAt(0) == '=') {
                num2 = Double.parseDouble(displayField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            displayField.setText("Error");
                            return;
                        }
                        break;
                }
                displayField.setText(String.valueOf(result));
            } else {
                num1 = Double.parseDouble(displayField.getText());
                operator = command.charAt(0);
                displayField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasicCalculatorGUI());
    }
}
