package Assignments.A2_Genetic_Algorithm.models;

import java.util.Random;

/**
 * A class to manage the population of individuals and performs
 * Genetic Algorithm operations and functions.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class PopulationManager {

    private BinaryVector[] population;

    /**
     * Create a new population of n_pop individuals with random genes.
     *
     * @precondition n_pop > 0
     * @postcondition population is initialized with n_pop individuals.
     *
     * @param n_pop the number of individuals in the population.
     */
    public PopulationManager(int n_pop) {
        /* Check if the population size is positive */
        if (n_pop < 1) {
            throw new IllegalArgumentException("Population size must be positive.");
        }

        /* Initialize and creates the population of n_pop individuals with random genes */
        this.population = new BinaryVector[n_pop];
        for (int i = 0; i < n_pop; i++) {
            this.population[i] = new BinaryVector();
        }
    }

    /**
     * Calculate the fitness of an BinaryVector based on the genes' value in binary form
     * and powers it by 2.
     *
     * @precondition individual != null
     * @postcondition none
     *
     * @param individual the BinaryVector to calculate the fitness of
     * @return the fitness of the individual
     */
    private int fitness(BinaryVector individual) {
        /* Calculate the decimal value of the binary genes */
        int decimal = 0;
        for (int i = individual.genes.length-1; i >= 0; i--) {
            if (individual.genes[i] == 1) {
                /* Add the value of the gene to the decimal value based on its position */
                decimal += (int) Math.pow(2, individual.genes.length-1-i);
            }
        }

        /* Return the fitness of the individual, which is f(x) = x^2. */
        return (int) Math.pow(decimal, 2);
    }

    /**
     * Mutates the genes of a BinaryVector with a probability p_m.
     *
     * @precondition individual != null && p_m >= 0 && p_m <= 1
     * @postcondition none
     *
     * @param individual the BinaryVector to mutate
     * @param p_m the probability of mutation
     * @return the mutated BinaryVector
     */
    private BinaryVector mutation(BinaryVector individual, double p_m) {
        /* Does the pre- and post-condition checks */
        if (individual == null) {
            throw new IllegalArgumentException("Individual cannot be null.");
        }
        if (p_m < 0 || p_m > 1) {
            throw new IllegalArgumentException("Mutation probability must be between 0 and 1.");
        }

        /* Creates the mutated binaryvector as a copy of the original */
        Random generator = new Random();
        BinaryVector mutated = new BinaryVector(individual.genes);

        /* Mutate the genes of the individual with a probability p_m by generating a random double between 0 and 1. */
        for (int i = 0; i < individual.genes.length; i++) {
            if (generator.nextDouble(0, 1) < p_m) {
                /* flips the gene from 0 to 1 or vise versa */
                if (mutated.genes[i] == 0) {
                    mutated.genes[i] = 1;
                } else {
                    mutated.genes[i] = 0;
                }
            }
        }
        return mutated;
    }

    /**
     * Breeds the two parent individuals to two new child individuals.
     *
     * @precondition firstParent != null && secondParent != null
     * @postcondition none
     *
     * @param firstParent the first parent individual
     * @param secondParent the second parent individual
     *
     * @return an array of two new individuals that are the children of the parents.
     */
    private BinaryVector[] crossover(BinaryVector firstParent, BinaryVector secondParent) {
        /* Check if the parents are not null */
        if (firstParent == null || secondParent == null) {
            throw new IllegalArgumentException("Parents cannot be null.");
        }

        /* Two int arrays to hold the genes of the children */
        int[] firstChild = new int[6];
        int[] secondChild = new int[6];

        /* Randomly select a crossover point */
        Random generator = new Random();
        int crossover_point = generator.nextInt(0, 6);

        /* Copy the genes of the same side parents to the same side child */
        for (int i = 0; i < crossover_point; i++) {
            firstChild[i] = firstParent.genes[i];
            secondChild[i] = secondParent.genes[i];
        }

        /* Copy the genes of the opposite side parents to the opposite side child */
        for (int i = crossover_point; i < 6; i++) {
            firstChild[i] = secondParent.genes[i];
            secondChild[i] = firstParent.genes[i];
        }

        return new BinaryVector[] {
                new BinaryVector(firstChild),
                new BinaryVector(secondChild)
        };
    }
}
