/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floatingpointdata;

import java.util.Random;

/**
 *
 * @author Kamil  
 */
public class Selection {
    public static Individual tornamentSelection(Individual[] population) {
        Random rand = new Random();
        Individual p1 = population[rand.nextInt(population.length)];
        Individual p2 = population[rand.nextInt(population.length)];
        
        if(p1.getFitness() > p2.getFitness()) {
            return new Individual(p1);
        }
        else{
            return new Individual(p2);
        }
    }
    
    public static Individual fitnessProportionateSelection(Individual[] population) {
        Random rand = new Random();
        
        double totalFitness = 0;
        for (Individual individual : population) {
            totalFitness += individual.getFitness();
        }
        
        double stop = rand.nextDouble();
        
        //if total fitness is 0 then we set it to 1
        if(totalFitness == 0) {
            System.out.println("total fitness = 0");
            totalFitness = 1;
        }
        
        for (Individual individual : population) {
            stop -= individual.getFitness()/totalFitness;
            
            if(stop <= 0) {
                return new Individual(individual);
            }
        }
        
        //new worst case = tornament selection
        return tornamentSelection(population);
    }
}
