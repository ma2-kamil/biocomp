/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary;

import java.util.Random;

/**
 *
 * @author Kamil
 */
// for data sets 1 and 2 binary.
public class Individual {
    
    // variables for genes and fitness
    private int[][] genes;
    private int fitness;

    public Individual(int GENE_SIZE, int RULE_SIZE) {
        genes = new int[GENE_SIZE][RULE_SIZE];
    }

    public Individual(Individual clone) {
        //copy the genes
        int[][] tempGenes = clone.getGenes();
        genes = new int[tempGenes.length][tempGenes[0].length];
        for (int i = 0; i < tempGenes.length; i++) {
            for (int j = 0; j < tempGenes[i].length; j++) {
                genes[i][j] = tempGenes[i][j];
            }
        }

        //copy over the fitness
        fitness = clone.getFitness();
    }

    // getters and setters
    public int[][] getGenes() {
        return genes;
    }

    public void setGenes(int[][] genes) {
        this.genes = genes;
    }

    public int[] getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, int[] gene) {
        this.genes[index] = gene;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void randomiseGenes() {
        // random num generator
        Random rand = new Random();
        for (int i = 0; i < genes.length; i++) {
            //randomise conditions
            for (int j = 0; j < genes[i].length - 1; j++) {
                genes[i][j] = rand.nextInt(3);

            }
            //randomise output
            genes[i][genes[i].length - 1] = rand.nextInt(2);
        }
    }

    public void fitnessFunction(int[][] dataset) {
        fitness = 0;

        boolean condition = true;
        for (int i = 0; i < dataset.length; i++) { //loop through the dataset

            for (int j = 0; j < genes.length; j++) { //loop through each gene
                condition = true;

                //check all the conditions
                for (int k = 0; k < genes[j].length - 1; k++) { //loop through each gene BIT
                    if (genes[j][k] == dataset[i][k] || genes[j][k] == 2) {
                        condition = true;
                    } else {
                        condition = false;
                        break;
                    }

                }

                //check if all conditions are matched
                if (condition) {
                    //if outputs match then increment the fitness
                    if (genes[j][genes[j].length - 1] == dataset[i][dataset[i].length - 1]) {
                        fitness++;
                    }
                    break; //(first match made = only gene to match this rule) first come first serve 
                }
            }
        }

    }

    public void mutation(double mutationRate) {
        // random num generator
        Random rand = new Random();
        for (int i = 0; i < genes.length; i++) {
            for (int j = 0; j < genes[i].length - 1; j++) {
                if (mutationRate > Math.random()) {
                    genes[i][j] = rand.nextInt(3);
                }
            }
            if (mutationRate > Math.random()) {
                genes[i][genes[i].length-1] = rand.nextInt(2);
            }
        }
    }

}
