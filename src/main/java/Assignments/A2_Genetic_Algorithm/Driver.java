package Assignments.A2_Genetic_Algorithm;

import Assignments.A2_Genetic_Algorithm.models.BinaryVector;
import Assignments.A2_Genetic_Algorithm.models.PopulationManager;

import java.util.Date;
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
        if (option == 1) {
            System.out.println("Please enter a population size.");
            System.out.print("Size: ");
            int n_pop = this.getIntegerInput();
            while (n_pop < 0) {
                System.out.println("Please enter a valid population size. Must be greater than 0.");
                System.out.print("Size: ");
                n_pop = this.getIntegerInput();
            }
            PopulationManager.n_pop = n_pop;
        } else if (option == 2) {
            if (PopulationManager.n_pop == 0) {
                System.out.println("Please enter a population size before mutation frequency.");
                System.out.println();
                return;
            }

            System.out.println("Please enter a mutation frequency. (Ex. 17% = .17)");
            System.out.print("Frequency: ");
            double p_m = this.getDoubleInput();
            while (p_m <= 0 || p_m >= 1) {
                System.out.println("Please enter a valid mutation frequency. (Ex. 17% = .17) (0 <= freq <= 1)");
                System.out.print("Frequency: ");
                p_m = this.getDoubleInput();
            }
            PopulationManager.p_m = p_m;
        } else if (option == 3) {
            if (PopulationManager.n_pop == 0 || PopulationManager.p_m == 0.0) {
                System.out.println("You must enter a population size and mutation frequency to run the GA.");
                System.out.println();
                return;
            }

            long start = System.currentTimeMillis();
            BinaryVector[] population = PopulationManager.generate_population(PopulationManager.n_pop);
            int result = PopulationManager.evolution(population);
            long end = System.currentTimeMillis();
            System.out.println("The Genetic Algorithm (GA) proceeded through " + result + " generations.");
            System.out.println("The algorithm took " + (end-start) + " ms to find the BinaryVector with fitness 0.");
        }
        System.out.println();
    }

    /**
     * Gets user input to a number. A prompt must be provided prior to running this method.
     * If the input was not an integer/(unable to be autoboxed), returns -1.
     *
     * @precondition none
     * @postcondition none
     *
     * @return the integer input, if invalid -1
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
     * Gets user input to a number. A prompt must be provided prior to running this method.
     * If the input was not a double/(unable to be autoboxed), returns -1.0.
     *
     * @precondition none
     * @postcondition none
     *
     * @return the double input, if invalid -1.0
     */
    protected double getDoubleInput() {
        Scanner sc = new Scanner(System.in);
        try {
            String textInput = sc.nextLine();
            return Double.parseDouble(textInput);
        } catch (Exception e) {
            return -1.0;
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
