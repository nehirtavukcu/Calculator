import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

   Color customPink = new Color(224, 187, 228);
   Color customPurple = new Color(149, 125, 173);
   Color customLightpink = new Color(254, 200, 216);
   Color customLightPurple = new Color(210, 145, 188);

   String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%","√"};



    JFrame frame = new JFrame("Calculator");

   JLabel displayLabel = new JLabel();
   JPanel displayPanel = new JPanel();
   JPanel buttonsPanel = new JPanel();

   String A="0";
   String B=null;
   String operator=null;

    Calculator(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customLightPurple);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customLightPurple);
        frame.add(buttonsPanel);

        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
             if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(customPink);
                button.setForeground(Color.white);
             }
             else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(customPurple);
                button.setForeground(Color.white);
             }
             else{
                button.setBackground(customLightpink);
                button.setForeground(Color.black);
             }
               buttonsPanel.add(button);



           


            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                 JButton button = (JButton) e.getSource();
                 String buttonValue = button.getText();
                 if(Arrays.asList(rightSymbols).contains(buttonValue)){
                    if(buttonValue=="="){
                        if(A!=null){
                            B=displayLabel.getText();
                            double numA = Double.parseDouble(A);
                            double numB = Double.parseDouble(B);

                            if(operator.equals("+")){
                                double result = numA + numB;
                                displayLabel.setText(removeZeroDecimal(result));
                            }else if(operator.equals("-")){
                                double result = numA - numB;
                                displayLabel.setText(removeZeroDecimal(result));
                            }else if(operator.equals("×")){
                                double result = numA * numB;
                                displayLabel.setText(removeZeroDecimal(result));
                            }else if(operator.equals("÷")){
                                double result = numA / numB;
                                displayLabel.setText(removeZeroDecimal(result));
                            }
                        }

                    }else if("+-×÷".contains(buttonValue)){
                       if(operator==null){
                        A=displayLabel.getText();
                         displayLabel.setText("0");
                         B="0";
                    }
                    operator=buttonValue;
                }

                 }else if(Arrays.asList(topSymbols).contains(buttonValue)){ 
                     if(buttonValue.equals("AC")){
                          clearAll();
                          displayLabel.setText("0");



                        displayLabel.setText("0");}
                        else if(buttonValue.equals("+/-")){
                              double numDisplay = Double.parseDouble(displayLabel.getText());
                              numDisplay = -numDisplay;
                              displayLabel.setText(removeZeroDecimal(numDisplay));


                        }else if(buttonValue.equals("%")){

                            double numDisplay = Double.parseDouble(displayLabel.getText());
                              numDisplay /=100;
                              displayLabel.setText(removeZeroDecimal(numDisplay));

                        }   else if(buttonValue.equals("√")){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            if (numDisplay >= 0) {
                            numDisplay = Math.sqrt(numDisplay);
                         displayLabel.setText(removeZeroDecimal(numDisplay));
                         } else {
                        displayLabel.setText("Error"); // Negatif sayıların karekökü için
    }
}
                            

         }else{
            if(buttonValue.equals(".")){
                if(!displayLabel.getText().contains(buttonValue)){
                    displayLabel.setText(displayLabel.getText() + buttonValue);}
               
                }else if("0123456789".contains(buttonValue)){
                    if(displayLabel.getText().equals("0")){
                        displayLabel.setText(buttonValue);
                    } else {
                        displayLabel.setText(displayLabel.getText() + buttonValue);
                     }
                     }
                    }
                }
          });
        }

    }
        void clearAll(){
            A="0";
            B=null;
            operator=null;
        }
        String removeZeroDecimal(double numDisplay){
           if(numDisplay %1==0){
            return Integer.toString((int)numDisplay); 
        }   
        return Double.toString(numDisplay);

}
}

