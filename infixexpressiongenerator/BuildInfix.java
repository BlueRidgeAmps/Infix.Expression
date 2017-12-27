package infixexpressiongenerator;


// import class packages
import java.util.*;
import javax.swing.*;

public class BuildInfix {

    // define class variable
    private String invalidToken;
    private final String inputExpression;

    // create two stacks for operands and operators
    Stack<String> operandStk = new Stack();
    Stack<String> operatorStk = new Stack();

    // create an instance of the AssemblyCode class
    AssemblyCode assemblyCode = new AssemblyCode();
    
    
    StringBuilder outputFile = new StringBuilder();

    // define an instance of interface Node
    Node tree;

    // class constructor
    public BuildInfix(String inputExpression) {
        this.inputExpression = inputExpression;
    }

    // BuildTree() method to obtain elements from postfix expression
    // and build an expression tree
    public void buildTree() throws RuntimeException, DivideByZero {
        // define local variable to count number of times an operator 
        // is pushed into it's associated stack. This will be use to 
        // count register values
        int i = 0;

        // split postfix expression enterend into the textbox into tokens using 
        // StringTokenizer.  Delimeters are used so that different inputs 
        // can be parsed without spaces being necessary 
        StringTokenizer token = new StringTokenizer(inputExpression, " */+-", true);

        // while loop to build tree out of the postfix expression
        while (token.hasMoreTokens()) {
            // get the next token in series
            String nextItem = token.nextToken();

            // use selection statements to determine if the next token
            // in the String is an operand, operator, or unknown
            if (nextItem.matches("[0-9]+")) {
                // push operand to associated stack
                operandStk.push(nextItem);
                
                // push operand to AssemblyCode class
                assemblyCode.pushStack(nextItem);

            } else if (nextItem.equals("*") || nextItem.equals("/")
                    || nextItem.equals("+") || nextItem.equals("-")) {
                // push current operator to operators stack
                operatorStk.push(nextItem);
                // call the getNodes() method to perform operation
                getNodes(i);
                // count each time an operator is pushed so registers can be counted
                i++;
            } else if (!nextItem.equals(" ")){   
                // set class variable to equal invalid character
                invalidToken = nextItem;                
                // throw exception if illegal operator or operand is parsed
                throw new RuntimeException();
            }
        } // end while loop
    } // end method


    // getNodes() method to pop two operands and an operator, determine the 
    // function of the operator and use the Node interface to call the OperandNode 
    // and OperatorNode classes to create an expression tree and build an infix
    // expression
    public void getNodes(int i) throws RuntimeException, DivideByZero{
        // define local variables
        String operand1 = operandStk.pop();
        String operand2 = operandStk.pop();
        
        // pop the operand stack twice using Node interface to declare an instance
        Node value1 = new OperandNode(operand1);
        Node value2 = new OperandNode(operand2);

        // define local variable to hold operator
        String operator = operatorStk.pop();

        // invoke instances of TreeNode class 
        TreeNode operatorNode;

        // use switch selection statement to determine the operator and 
        // the associated class to use
        switch (operator) {
            case "*":
                // pass * operator
                operatorNode = new MulOperator();
                break;
            case "/":
                // use selection statement to determine if divisor is "0"
                if (operand1.equals("0")) {
                    // throw checked exception
                    throw new DivideByZero();
                } else {
                    // pass / operator
                    operatorNode = new DivOperator();
                    break;
                }
            case "+":
                // pass + operator
                operatorNode = new AddOperator();
                break;
            case "-":
                // pass - operator
                operatorNode = new SubOperator();
                break;
            default:
                // throw RuntimeException for fault found
                throw new RuntimeException();
        }
        // create an instance of the OperatorNode to pass data
        // The variables are in order they come in the postfix expression
        tree = new OperatorNode(value1, value2, operatorNode);

        // push String of the created fragment of the infix 
        // expression to the operand stack
        operandStk.push(tree.infixExpression());

        // call the buildString() method to push current operator to AssemblyCode class
        assemblyCode.buildAssemblyCode(operatorNode.operator(), i);

        // call pushStack() method in AssemblyCode class to push register value
        assemblyCode.pushStack(assemblyCode.getRegister(i));
    } // end method

    // getExpression() method to return complete infix expression
    // to the main class to display in output textbox of GUI
    public String getExpression() {
        return tree.infixExpression();
    } // end method
    
    // getMessage() method to display message when invalid character is parsed
    public void getMessage() {
        // use JOptionPane to have a pop up box informing user of the invalid token
        JOptionPane.showMessageDialog(null, "Invalid Token " + invalidToken);
    }
    
    // getOutput() method to retrieve assembly code output from AssemblyCode 
    // class.  blank lines and divider line are appended between outputs
    public String getOutput(){
        // add assembly code to outputFile
        outputFile.append(assemblyCode.getOutputFile());
        
        // add a line spaces so the reader of the output file can determine
        // that a new assembly code sequence has started
        outputFile.append(System.lineSeparator());
        outputFile.append("************");
        outputFile.append(System.lineSeparator());
        
        // return outputFile to a String
        return outputFile.toString();
    } // end method
} // end class
