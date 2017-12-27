package infixexpressiongenerator;

/**
 * Course: CMSC 350-6380 Data Structures and Analysis 
 * Project 2
 *
 * File: AssemblyCode.java 
 * Author: Jonathan Pearson 
 * Date: February 4, 2017
 * Purpose: The AssemblyCode class defines methods to import elements from the
 *          BuildInfix class so that an output file can be created with assembly 
 *          code. The pushStack() method accepts operand elements from the BuildInfix 
 *          class and pushes them into a stack. The getRegister() method takes the 
 *          count value from the BuildInfix class and creates a String representation 
 *          of the register. The buildAssemblyCode() method pops two operands, 
 *          receives an operator from the BuildInfix class using the OperatorNode 
 *          class and a String is build that represents a line of assembly code. 
 *          The assembly code String is then appended to outputFile created using
 *          the StringBuilder() class.  The getOutputFile() method returns the 
 *          assembly code to the BuildInfix class where it is appended to any
 *          existing assembly code.
 *
 */
// import class packages
import java.util.*;

public class AssemblyCode {   
    // define and instance of the StringBuilder() class
    // to build string lines for assembly code output
    StringBuilder outputFile = new StringBuilder();

    // define stack for assembly code operands
    Stack<String> stack = new Stack();

    // class constructor
    public AssemblyCode() {
        
    }
        
    // pushStack() method to push operands onto stack to construct assembly code
    public void pushStack(String value) {
        stack.push(value);
    }
    
    // getRegister() method to append R to int i to create the register variable
    public String getRegister(int i) {
        // define variable and convert int to string
        String register = Integer.toString(i);
        // return register value (e.g. R0, R1, R2, ...)
        return "R" + register;
    } // end method

    // buildAssemblyCode() to construct a format to display the assembly code
    public void buildAssemblyCode(String operator, int i) {
        // define local variables to hold popped vales off stack
        String rightNode = stack.pop();
        String leftNode = stack.pop();

        // build a line of assembly code to be used by buildOutputFile() method
        String buildString = String.format(operator + " " + getRegister(i)
                + " " + leftNode + " " + rightNode);

        // add the assembly code string to outputFile
        outputFile.append(buildString);
        // return to next line
        outputFile.append(System.lineSeparator());
    }  

    // getOutputFile() method to return outputFile to BuildInfix class where
    // for each operation of the GUI "Construct Tree" button, this method
    // will append to it.
    public String getOutputFile() {
        return outputFile.toString();
    } // end method 
} // end class
