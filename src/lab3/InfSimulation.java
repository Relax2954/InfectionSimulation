/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.Random;

/**
 *
 * @author Relax2954
 */
public class InfSimulation extends Lab3{

    static int day, numberOfIll, infectedPerDay, deathsPerDay, immunePerDay, totInfected, totDeaths;
    //static Random generator;
    public static void InfSimulation(testPopulation myPopulation, int populationSize, double infectionPropability, double deathPropability, int numOfIllInit) {

        day = 1;
        numberOfIll = numOfIllInit;
        infectedPerDay = 0;
        deathsPerDay = 0;
        immunePerDay = 0;
        totDeaths = 0;
        totInfected = 0;
        //generator = new Random(seed);
        while (numberOfIll > 0) {

            infectedPerDay = 0;
            immunePerDay = 0;
            deathsPerDay = 0;
            int i = 0;
            while (i < populationSize) {
                int j = 0;
                while (j < populationSize) {
                    if (myPopulation.individualArray[i][j].getCondit().equals("dead")) {
                        //do nothing
                    } else if (myPopulation.individualArray[i][j].getCondit().equals("healthy")) {
                        //do nothing
                    } else if ((day == myPopulation.individualArray[i][j].getInfDay()+ myPopulation.individualArray[i][j].getIlldays()) && myPopulation.individualArray[i][j].getCondit().equals("infected")) {

                        myPopulation.individualArray[i][j].setImmune(1);
                        immunePerDay = immunePerDay + 1;
                        numberOfIll = numberOfIll - 1;

                    } else if (myPopulation.individualArray[i][j].getInfDay() == day && myPopulation.individualArray[i][j].getCondit().equals("infected")) {
                        if (generator.nextDouble() * 100 <= deathPropability) {
                            myPopulation.individualArray[i][j].setHealth(0);
                            myPopulation.individualArray[i][j].setDead(1);
                            deathsPerDay = deathsPerDay + 1;
                            totDeaths = totDeaths + 1;
                            numberOfIll = numberOfIll - 1;
                        }
                    } else if (myPopulation.individualArray[i][j].getCondit().equals("infected") && myPopulation.individualArray[i][j].getInfDay() < day) {
                        if (isDead(myPopulation, i, j, deathPropability, infectionPropability) == 0) {

                            infect(populationSize, myPopulation, i, j, day, infectionPropability, deathPropability);
                        }
                    } else {

                    }
                    j++;
                }
                i++;
            }
            //results(day);
            day = day + 1;
        }

        System.out.println("\nResult: \nPopulation: " + populationSize * populationSize + "\n Num ofInfected: "
                + totInfected + "\n num of dead: " + totDeaths + "\nTotal Healthy =  " + populationSize * populationSize + "(population) - " + totInfected + "(infected) - " + numOfIllInit + "(initially infected) = "
                + (populationSize * populationSize - totInfected - numOfIllInit));
        if (totInfected > populationSize * populationSize / 2) {
            System.out.println("Disease has developed into an epidemic");
        } else {
            System.out.println("Disease has not develop into an epidemic.");
        }
    }

    public static int isDead(testPopulation mypop, int row, int column, double deathPropability, double infprob) {
        if (generator.nextDouble() * 100 <= deathPropability) {
            mypop.individualArray[row][column].setHealth(0);
            mypop.individualArray[row][column].setDead(1);
            deathsPerDay = deathsPerDay + 1;
            totDeaths = totDeaths + 1;
            numberOfIll = numberOfIll - 1;
            return 1;
        } else {
            return 0;
        }
    }

    public static void infect(int sizeOfPopulation, testPopulation mypop, int row, int column, int dayOfInf, double infectionPropability, double deathPropability) {
        //Check upper left corner of matrix
        if (row == 0 && column == 0) {
            int i=row;
            while(i < row + 2){
                for (int j = column; j < column + 2; j++) {
                    if (mypop.individualArray[i][j].getCondit().equals("healthy") && i == row && j == column) {
                        //nothing
                    } else if (mypop.individualArray[i][j].getCondit().equals("healthy") && generator.nextDouble() * 100 <= infectionPropability) {
                        inf2(mypop, dayOfInf, i, j);
                    }
                }
                i++;
            }

        } //Check upper right corner of square matrix 
        else if (row == 0 && column == sizeOfPopulation - 1) {
            int i=row;
            while(i < row + 2){
                for (int j = column - 1; j < column+1; j++) {
                    if (mypop.individualArray[i][j].getCondit().equals("healthy") && i == row && j == column) {
                        //nothing
                    } else if (mypop.individualArray[i][j].getCondit().equals("healthy") && generator.nextDouble() * 100 <= infectionPropability) {
                        inf2(mypop, dayOfInf, i, j);
                    }
                }
                i++;
            }

        } //Check bottom left corner of matrix
        else if (row == sizeOfPopulation - 1 && column == 0) {
            int i=row-1;
            while(i<row+1){
                for (int j = column; j < column + 2; j++) {
                    if (mypop.individualArray[i][j].getCondit().equals("healthy") && i == row && j == column) {
                        //nothing
                    } else if (mypop.individualArray[i][j].getCondit().equals("healthy") && generator.nextDouble() * 100 <= infectionPropability) {
                        inf2(mypop, dayOfInf, i, j);
                    }
                }
                i++;
            }
        } //Check bottom right corner of matrix
        else if (row == sizeOfPopulation - 1 && column == sizeOfPopulation - 1) {
            int i=row-1;
            while(i<row+1){
                for (int j = column - 1; j < column+1; j++) {
                    if (mypop.individualArray[i][j].getCondit().equals("healthy") && i == row && j == column) {
                        //nothing
                    } else if (mypop.individualArray[i][j].getCondit().equals("healthy") && generator.nextDouble() * 100 <= infectionPropability) {
                        inf2(mypop, dayOfInf, i, j);
                    }
                }
                i++;
            }

        } else if (row == 0 && column != 0 && (column < sizeOfPopulation - 1 || column >sizeOfPopulation - 1)) {
            int i=row;
            while(i<row+2){
                for (int j = column - 1; j < column + 2; j++) {
                    if (mypop.individualArray[i][j].getCondit().equals("healthy") && i == row && j == column) {
                        //nothing
                    } else if (mypop.individualArray[i][j].getCondit().equals("healthy") && generator.nextDouble() * 100 <= infectionPropability) {
                        inf2(mypop, dayOfInf, i, j);
                    }
                }
                i++;
            }
        } else if (row != 0 && (row < sizeOfPopulation - 1 || row >sizeOfPopulation - 1) && column == 0) {
            int i=row-1;
            while(i<row+2){
                for (int j = column; j < column + 2; j++) {
                    if (mypop.individualArray[i][j].getCondit().equals("healthy") && i == row && j == column) {
                        //nothing
                    } else if (mypop.individualArray[i][j].getCondit().equals("healthy") && generator.nextDouble() * 100 <= infectionPropability) {
                        inf2(mypop, dayOfInf, i, j);
                    }
                }
                i++;
            }
        } else if (row == sizeOfPopulation - 1 && column != 0 && (column < sizeOfPopulation - 1 || column >sizeOfPopulation - 1)) {
            int i=row-1;
            while(i<row+1){
                for (int j = column - 1; j < column + 2; j++) {
                    if (mypop.individualArray[i][j].getCondit().equals("healthy") && i == row && j == column) {
                        //nothing
                    } else if (mypop.individualArray[i][j].getCondit().equals("healthy") && generator.nextDouble() * 100 <= infectionPropability) {
                        inf2(mypop, dayOfInf, i, j);
                    }
                }
                i++;
            }
        } else if (row != 0 && (row < sizeOfPopulation - 1 || row >sizeOfPopulation - 1) && column == sizeOfPopulation - 1) {
            int i=row-1;
            while(i<row+2){
                for (int j = column - 1; j < column+1; j++) {
                    if (mypop.individualArray[i][j].getCondit().equals("healthy") && i == row && j == column) {
                        //nothing
                    } else if (mypop.individualArray[i][j].getCondit().equals("healthy") && generator.nextDouble() * 100 <= infectionPropability) {
                        inf2(mypop, dayOfInf, i, j);
                    }
                }
                i++;
            }
        } else {
            int i=row-1;
            while(i<row+2){
                for (int j = column - 1; j < column + 2; j++) {
                    if (mypop.individualArray[i][j].getCondit().equals("healthy") && i == row && j == column) {
                        //nothing
                    } else if (mypop.individualArray[i][j].getCondit().equals("healthy") && generator.nextDouble() * 100 <= infectionPropability) {
                        inf2(mypop, dayOfInf, i, j);
                    }
                }
                i++;
            }
        }
    }

    public static void inf2(testPopulation mypop, int dayOfInf, int i, int j) {
        mypop.individualArray[i][j].setHealth(0);
        mypop.individualArray[i][j].setInfDay(dayOfInf);
        infectedPerDay = infectedPerDay + 1;
        numberOfIll = numberOfIll + 1;
        totInfected = totInfected + 1;
    }

    public static void res(int day) {
        System.out.println("On day " + day + " infected " + infectedPerDay + ",died:" + deathsPerDay + ",got immune: " + immunePerDay
                + ",got sick: " + numberOfIll + ",total infected so far: " + totInfected + ",total deaths so far:" + totDeaths);
    }

}
