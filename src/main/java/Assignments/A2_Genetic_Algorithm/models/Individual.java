package Assignments.A2_Genetic_Algorithm.models;

import java.util.Random;

public class Individual {

    public int[] genes;

    /**
     * Default constructor for Individual. Generates a random set of genes.
     *
     * @precondition none
     * @postcondition a new Individual object is created with random genes.
     */
    public Individual() {
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
    public Individual(int[] genes) {
        if (genes.length != 6) {
            throw new IllegalArgumentException("Genes cannot be any length other than 6.");
        }
        this.genes = genes;
    }
}