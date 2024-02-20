package Assignments.A2_Genetic_Algorithm;

import Assignments.A2_Genetic_Algorithm.models.BinaryVector;
import Assignments.A2_Genetic_Algorithm.models.PopulationManager;

import java.util.Random;

public class Driver {

    public static void main(String[] args) {
        Random random = new Random();
        PopulationManager.n_pop = 10;
        PopulationManager.p_m = random.nextDouble(0, 1);
        BinaryVector[] population = PopulationManager.generate_population(PopulationManager.n_pop);

        System.out.println(PopulationManager.evolution(population));
    }
}
