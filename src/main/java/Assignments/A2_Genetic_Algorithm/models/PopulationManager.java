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

    // The population of individuals
    private Individual[] population;

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
        this.population = new Individual[n_pop];
        for (int i = 0; i < n_pop; i++) {
            this.population[i] = new Individual();
        }
    }

    /**
     * Calculate the fitness of an individual based on the genes' value in binary form
     * and powers it by 2.
     *
     * @precondition individual != null
     * @postcondition none
     *
     * @param individual the individual to calculate the fitness of
     * @return the fitness of the individual
     */
    private int fitness(Individual individual) {
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
    private Individual[] crossover(Individual firstParent, Individual secondParent) {
        /* Check if the parents are not null */
        if (firstParent == null || secondParent == null) {
            throw new IllegalArgumentException("Parents cannot be null.");
        }

        /* Create a new individual to hold the genes of the children */
        Individual firstChild = new Individual();
        Individual secondChild = new Individual();

        /* Randomly select a crossover point */
        Random generator = new Random();
        int crossover_point = generator.nextInt(0, 6);

        /* Copy the genes of the same side parents to the same side child */
        for (int i = 0; i < crossover_point; i++) {
            firstChild.genes[i] = firstParent.genes[i];
            secondChild.genes[i] = secondParent.genes[i];
        }

        /* Copy the genes of the opposite side parents to the opposite side child */
        for (int i = crossover_point; i < 6; i++) {
            firstChild.genes[i] = secondParent.genes[i];
            secondChild.genes[i] = firstParent.genes[i];
        }

        return new Individual[] {firstChild, secondChild};
    }
}
