package Assignments.A2_Genetic_Algorithm.models;

public class PopulationManager {

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
        // Check if the population size is positive
        if (n_pop < 1) {
            throw new IllegalArgumentException("Population size must be positive.");
        }

        // Create a new population of individuals
        this.population = new Individual[n_pop];

        // Initialize the population with random genes
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
        int decimal = 0;
        for (int i = individual.genes.length-1; i >= 0; i--) {
            if (individual.genes[i] == 1) {
                decimal += (int) Math.pow(2, individual.genes.length-1-i);
            }
        }
        return (int) Math.pow(decimal, 2);
    }
}
