/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalano.Genetic.Crossover;

import Catalano.Genetic.Chromosome.IChromosome;
import Catalano.Genetic.Chromosome.IntegerChromosome;
import Catalano.Genetic.Chromosome.PermutationChromosome;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Order Crossover (OX)
 * @author Diego Catalano
 */
public class OrderCrossover implements ICrossover<IChromosome>{

    public OrderCrossover() {}

    @Override
    public List<IChromosome> Compute(IChromosome chromosome1, IChromosome chromosome2) {

        if(chromosome1 instanceof PermutationChromosome || chromosome1 instanceof IntegerChromosome){
            return ComputeIC((PermutationChromosome)chromosome1,(PermutationChromosome)chromosome2);
        } else {
            throw new IllegalArgumentException("Support only Permutation and Integer chromosomes");
        }
        
    }
    
    private List<IChromosome> ComputeIC(IChromosome chromosome1, IChromosome chromosome2){
        Random rand = new Random();
        
        int length = chromosome1.getLength();
        
        //Cut points
        int[] cuts = {rand.nextInt(length), rand.nextInt(length)};
        Arrays.sort(cuts, 0, cuts.length);
        
        IChromosome c1 = chromosome1.Clone();
        IChromosome c2 = chromosome2.Clone();
        
        int[] v1 = new int[length - (cuts[1] - cuts[0]) - 1];
        int[] v2 = new int[length - (cuts[1] - cuts[0]) - 1];
        
        int index = 0;
        for (int i = 0; i < length; i++) {
            if(i < cuts[0] || i > cuts[1]){
                v1[index] = (Integer)chromosome1.getGene(i);
                v2[index] = (Integer)chromosome2.getGene(i);
                index++;
            }
        }
        
        Arrays.sort(v1);
        Arrays.sort(v2);
        
        index = 0;
        for (int i = 0; i < length; i++) {
            if(i < cuts[0] || i > cuts[1]){
                c1.setGene(i, v1[index]);
                c2.setGene(i, v2[index]);
                index++;
            }
        }
        
        List<IChromosome> lst = new ArrayList<IChromosome>(2);
        lst.add(c1);
        lst.add(c2);
        
        return lst;
    }
    
}