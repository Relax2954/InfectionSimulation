/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Relax2954
 */
public class Lab3 {

    public static int seed; //= 30684734;
    public static Random generator;// = new Random(seed);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int mycounter, populationSize, minDays, maxDays, numOfIllInit;
        double probabilityOfInfection, probabilityOfDeath;
        mycounter = 0;
        Scanner myscanner = new Scanner(System.in);
        System.out.println("Please enter the seed number:");
        seed = myscanner.nextInt();
        generator = new Random(seed);
        System.out.println("Enter info for the simulation");
        System.out.println("If you want to test the values I used for benchmarking, type 1. If you want to use new values, press 2.");
        if (myscanner.nextInt() == 1) {
            //adition start
            populationSize = 20;
            System.out.println("Probability/day that a sick individual infects a healthy neighbor(who is not immune) (0-100): ");
            probabilityOfInfection=myscanner.nextInt();
            minDays = 7;
            maxDays = 10;
            probabilityOfDeath = 1;
            numOfIllInit = 2;
            //Array to save the location of the people that were ill initially
            Location illArray[] = new Location[numOfIllInit];
            //adition end
            //addition start
            int x = 0;
            int y = 0;
            illArray[0] = new Location(x, y);

            x = 3;
            y = 2;
            illArray[1] = new Location(x, y);
//addition end 
            System.out.println("Simulating!");
            //Create the test population
            testPopulation myPopulation = new testPopulation(minDays, maxDays, populationSize);

            //Initialize the sick individuals of the test polulation
            for (Location loc : illArray) {
                myPopulation.setIll(loc.getX(), loc.getY());
            }
            //Do the simulation
            InfSimulation.InfSimulation(myPopulation, populationSize, probabilityOfInfection, probabilityOfDeath, numOfIllInit);

        } else {
            System.out.println("Population dimension: ");
            populationSize = myscanner.nextInt();
            System.out.println("Probability/day that a sick individual infects a healthy neighbor(who is not immune) (0-100): ");
            probabilityOfInfection = myscanner.nextDouble();
            System.out.println("Min and max number of days that an individual is ill(input min then max): ");
            minDays = myscanner.nextInt();
            maxDays = myscanner.nextInt();
            System.out.print("Probability that an individual dies per day (0-100): ");
            probabilityOfDeath = myscanner.nextDouble();
            System.out.print("The number of individuals that are ill initially: ");
            numOfIllInit = myscanner.nextInt();

//        //adition start
//        populationSize=20;
//        //System.out.println("Probability/day that a sick individual infects a healthy neighbor(who is not immune) (0-100): ");
//        probabilityOfInfection = 5;
//        minDays = 7;
//        maxDays = 10;
//        probabilityOfDeath = 1;
//        numOfIllInit = 2;
//        //adition end
//Array to save the location of the people that were ill initially
            Location illArray[] = new Location[numOfIllInit];
            //Save the location of its sick individual
            System.out.println("Now, input location of initially infected individuals in x,y cordinates:");
            while (mycounter < numOfIllInit) {
                System.out.println("Input the location of infected individual " + (mycounter + 1) + " in x,y coordinates.");
                System.out.println("Note that x, y are in (1-" + populationSize + ")");
                int x = myscanner.nextInt() - 1;
                int y = myscanner.nextInt() - 1;
                illArray[mycounter] = new Location(x, y);
                mycounter++;
            }
            System.out.println("Simulating!");
            //Create the test population
            testPopulation myPopulation = new testPopulation(minDays, maxDays, populationSize);

            //Initialize the sick individuals of the test polulation
            for (Location loc : illArray) {
                myPopulation.setIll(loc.getX(), loc.getY());
            }
            //Do the simulation
            InfSimulation.InfSimulation(myPopulation, populationSize, probabilityOfInfection, probabilityOfDeath, numOfIllInit);

        }

////addition start
//            int x = 0;
//            int y = 0;
//            illArray[0] = new Location(x, y);
//            
//            x=3;
//            y=2;
//            illArray[1] = new Location(x, y);
////addition end            
//        System.out.println("Simulating!");
//        //Create the test population
//        testPopulation myPopulation = new testPopulation(minDays, maxDays, populationSize);
//
//        //Initialize the sick individuals of the test polulation
//        for (Location loc : illArray) {
//            myPopulation.setIll(loc.getX(), loc.getY());
//        }
//        //Do the simulation
//        InfSimulation.InfSimulation(myPopulation, populationSize, probabilityOfInfection, probabilityOfDeath, numOfIllInit);
    }

}
