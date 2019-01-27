/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

/**
 *
 * @author Relax2954
 */

public class testPopulation {            

    Individual[][] individualArray;
    //Creates the array and fills it out
    public testPopulation(int minDays, int maxDays, int n) {
        individualArray = new Individual[n][n];
        int i=0;
        while(i<n) {
            int j=0;
            while(j<n) {
                individualArray[i][j] = new Individual(minDays,maxDays);
                j++;
            }
            i++;
        }
    }

    //Position of initially infected individuals
    public void setIll(int x, int y) {
        individualArray[x][y].setHealth(0);
        individualArray[x][y].setInfDay(1);
        
    }
}
