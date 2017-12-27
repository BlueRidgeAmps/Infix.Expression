package infixexpressiongenerator;

/**
 * Course: CMSC 350-6380 Data Structures and Analysis 
 * Project 2
 *
 * File: InfixExpressionGenerator.java 
 * Author: Jonathan Pearson 
 * Date: February 4, 2017 
 * Purpose: The InfixExpressionGenerator class defines the GUI
 *          environment of an application that will allow a user to input a postfix
 *          expression. The GUI contains a button called "Construct Tree" that, when
 *          clicked will retrieve the user input using the getPostfix() method, call 
 *          the BuildTree() method from the BuildInfix class, and then output the 
 *          infix expression into a text box. A try/catch block is used to catch a
 *          RuntimeException that may occur with an improper character input. When 
 *          the checked exception is thrown a JOptionPane window opens informing the 
 *          user that there is an error with the input.  Also, the writeFile() is 
 *          called to build a text file of the assembly code each time the "Construct 
 *          Tree" button is clicked.  The file "AssemblyCode.txt" will continually 
 *          be appended. The main() method will display the GUI upon program 
 *          initialization by calling the display() method.
 */

// import class packages
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class InfixExpressionGenerator extends JFrame {

    // Define constans to set dimensions of GUI
    private static final int WIDTH = 425, HEIGHT = 150;

    // Define and initialize variables to build GUI 
    // frame and panel to contain objects
    private final JFrame panelFrame = new JFrame();
    private final JPanel inputPanel = new JPanel();
    private final JPanel btnPanel = new JPanel();
    private final JPanel outputPanel = new JPanel();

    // Define and intialize variables to populate GUI
    private final JLabel inputLbl = new JLabel("Enter Postfix Expression");
    private final JLabel resultLbl = new JLabel("Infix Expression");
    private final JTextField postfixTxt = new JTextField(23);
    private final JTextField outputTxt = new JTextField(23);
    private final JButton constructTreeBtn = new JButton("Construct Tree");

    // create an instance of ExpressionEvaluate and AssemblyCode classes
    private BuildInfix expression = new BuildInfix("");

    // Constructor to build GUI
    public InfixExpressionGenerator() {
        panelFrame.setTitle("Three Adddress Generator"); // GUI Title
        panelFrame.setSize(WIDTH, HEIGHT); // set GUI window size
        panelFrame.setLocationRelativeTo(null); // set GUI to defaul to center
        panelFrame.setResizable(false); // keep the frame size unresizable
        // set the layout for the panel
        panelFrame.setLayout(new GridLayout(3, 1, 0, 0));
        // exit application when "X" icon is clicked
        panelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add inputPanel to the panelFrame
        panelFrame.add(inputPanel, BorderLayout.NORTH);
        inputPanel.add(inputLbl); // add label to inputPanel
        inputPanel.add(postfixTxt); // add textbox to inputPanel

        //add btnPanel to panelFrame
        panelFrame.add(btnPanel, BorderLayout.CENTER);
        btnPanel.add(constructTreeBtn); // add button to btnPanel
        constructTreeBtn.setToolTipText("<html>Create Infix Expression. "
                + "Assembly Code<br/>will be written to local "
                + "program directory.<html>");

        // add outputPanel to panelFrame
        panelFrame.add(outputPanel, BorderLayout.SOUTH);
        outputPanel.add(resultLbl); // add label to outputPanel
        outputPanel.add(outputTxt); // add textbox to outputPanel
        outputTxt.setEditable(false); // set textbox to be uneditable

        // COnstruct Tree button
        // When clicked, the button will allow for the postfix expression
        // to be converted to an infix expression and result to be shown 
        // in resultLbl textbox.  Also a text file will be created with
        // assembly code notation of the expression
        constructTreeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // invoke an instance of the BuildInfix class
                // call the getPostfix() method to retrieve user input
                expression = new BuildInfix(getPostfix());

                // use try/catch block to handle runtime exception
                try {
                    // call the BuildTree() method to create expression tree from input
                    expression.buildTree();
                    // return the evaulation of the postfix to the output textbox
                    outputTxt.setText(expression.getExpression());
                    // write assembly code to a text document
                    writeFile(expression.getOutput());
                    // inform user of successful write to text file
                    JOptionPane.showMessageDialog(null, "File AssemblyCode.txt "
                            + "successfully\nwritten to local program directory.");
                } catch (RuntimeException ex) {
                    // Inform user that there is an issue with the expression
                    // call getMessage() method
                    expression.getMessage();
                } catch (DivideByZero ex) {
                    // inform user of Divide By Zero instance
                    JOptionPane.showMessageDialog(null, "Expression has a "
                            + "divide by zero instance.");
                }
            } // end actionPerformed
        }); // end actionListener
    } // end constructor method

    // getInfix() method to retrieve user input
    public String getPostfix() {
        return postfixTxt.getText();
    } // end method

    // writeToFile() method defines file that the Strings of 
    // the buildToOutput() file will be written to
    public void writeFile(String file) {
        // use try/catch statement
        try {
            // define FileWriter so that new data can be appended for
            // each time the "Construct Tree" button is clicked
            // the file is continually expanding
            FileWriter writer = new FileWriter("AssemblyCode.txt", true);
            writer.write(file);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    } // end method

    // display() method
    public void display() {
        // set the GUI to be visible upon program initialization
        panelFrame.setVisible(true);
    } // end method

    // main class will call display() method and initiate GUI
    public static void main(String[] args) {
        // create an object of the InfixExpressionEvaluator
        InfixExpressionGenerator displayGUI = new InfixExpressionGenerator();
        // call display() to display the GUI
        displayGUI.display();
    } // end main
} // end class
