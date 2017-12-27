package infixexpressiongenerator;

/**
 * Course: CMSC 350-6380 Data Structures and Analysis
 * Project 2
 * 
 * File: DivideByZero.java 
 * Author: Jonathan Pearson 
 * Date: February 4, 2017
 * Purpose: This class creates a custom exception to be thrown if the user input
 *          results in a division by zero case.
 * 
 */
public class DivideByZero extends Exception {

    public DivideByZero() {

    }

    public DivideByZero(String message) {
        super(message);
    }
}
