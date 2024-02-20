package Assignments.A2_Genetic_Algorithm;

import Assignments.A2_Genetic_Algorithm.models.BinaryVector;
import Assignments.A2_Genetic_Algorithm.models.PopulationManager;

import java.util.Random;
import java.util.Scanner;

/**
 * Drives the Genetic Algorithm (GA) program.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class Driver {

    /**
     * Starts the program by creating and starting the sentinel loop.
     * @param args the program args
     */
    public static void main(String[] args) {
        Driver program = new Driver();
        program.start();
    }


    /**
     * Used as the main sentential loop and loops until the exit option is selected.
     *
     * @precondition none
     * @postcondition none
     */
    public void start() {
        int option = this.getOption();
        while (option != 4) {
            this.executeOption(option);
            option = this.getOption();
        }
    }

    /**
     * This method is used to handle the option being selected.
     *
     * @precondition 0 < option <= 4
     * @postcondition none
     *
     * @param option the Option being executed.
     */
    public void executeOption(int option) {
        if (option == 0) {
            System.out.println("Please enter a population size.");
            System.out.print("Size: ");
            int n_pop = this.getIntegerInput();
            while (n_pop < 0) {
                System.out.println("Please enter a valid population size. Must be greater than 0.");
                System.out.print("Size: ");
                n_pop = this.getIntegerInput();
            }
        }
    }

    /**
     * Gets user input to a number. A prompt must be provided prior to running this method.
     * If the input was not an integer/(unable to be autoboxed), returns -1.
     *
     * @precondition none
     * @postcondition none
     *
     * @return the integer input, if invalid -1.
     */
    protected int getIntegerInput() {
        Scanner sc = new Scanner(System.in);
        try {
            String textInput = sc.nextLine();
            return Integer.parseInt(textInput);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Displays the menu options.
     *
     * @precondition none
     * @postcondition the menu is displayed.
     */
    private void displayMenu() {
        System.out.println("-----------------MAIN MENU-----------------");
        System.out.println("1. Set Population Size");
        System.out.println("2. Set Mutation Percentage");
        System.out.println("3. Perform Genetic Algorithm (GA)");
        System.out.println("4. Exit program");
        System.out.println();
    }

    /**
     * Prints out the menu of options, asks for an input, and if that input is invalid it prints an error and
     * prompts the user again for the input.
     *
     * @precondition none
     * @postcondition none
     *
     * @return the option selected
     */
    private int getOption() {
        this.displayMenu(); // Prints out the menu

        // Asks for the option and begins the loop until a valid option is gathered.
        System.out.print("Enter option number: ");
        int input = getIntegerInput();
        while (input < 1 || input > 4) { // Compares it to the valid options available.
            System.out.println("\nPlease enter a valid input.");
            System.out.print("Enter option number: ");
            input = getIntegerInput();
        }
        System.out.println();
        return input;
    }
}
