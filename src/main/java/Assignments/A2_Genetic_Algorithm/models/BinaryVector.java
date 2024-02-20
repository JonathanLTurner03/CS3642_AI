package Assignments.A2_Genetic_Algorithm.models;

import java.util.Random;

/**
 * A class used to represent a binary vector that contains 6 integer values for binary values.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class BinaryVector implements Comparable<BinaryVector> {

    public int[] genes;

    /**
     * Default constructor for Individual. Generates a random set of genes.
     *
     * @precondition none
     * @postcondition a new Individual object is created with random genes.
     */
    public BinaryVector() {
        this.genes = new int[6];
        Random generator = new Random();
        for (int i = 0; i < 6; i++) {
            this.genes[i] = generator.nextInt(0,2);
        }
    }

    /**
     * Constructor for Individual. Takes in an array of 6 binary genes.
     *
     * @precondition genes.length == 6 && genes.contains(0, 1)
     * @postcondition a new Individual object is created with the given genes.
     *
     * @param genes an array of 6 int values.
     */
    public BinaryVector(int[] genes) {
        if (genes.length != 6) {
            throw new IllegalArgumentException("Genes cannot be any length other than 6.");
        }
        this.genes = genes;
    }

    /**
     * Returns the BinaryVector in string representation of the binary values of the genes.
     *
     * @precondition none
     * @postcondition none
     * @return the string representation of the BinaryVector.
     */
    @Override
    public String toString() {
        String result = "[";
        for (int gene : genes) {
            result += gene + ",";
        }
        result = result.substring(0, result.length()-1) + "]";
        return result;
    }

    /**
     * Compares two BinaryVectors against each other.
     *
     * @precondition o1 != null
     * @postcondition none
     *
     * @param o the object this is to be compared to.
     * @return 1 if this is bigger, -1 if the other is bigger, if equal 0.
     */
    @Override
    public int compareTo(BinaryVector o) {
        int firstFitness = PopulationManager.fitness(this);
        int secondFitness = PopulationManager.fitness(o);

        if (firstFitness > secondFitness) {
            return 1;
        } else if (firstFitness < secondFitness) {
            return -1;
        }
        return 0;
    }
}