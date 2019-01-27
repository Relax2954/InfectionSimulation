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
public class Individual extends Lab3{

    
    int dayOfInfection, illDays, Dead, Healthy, Immune;

    public Individual(int minDays, int maxDays) {
        Healthy = 1;
        Dead = 0;
        Immune = 0;
        dayOfInfection = 0;
        illDays = minDays + (int) (generator.nextDouble() * ((maxDays - minDays) + 1));
    }
    //Change the condition of an individual

    public void setHealth(int value) {
            this.Healthy = value;
    }

    public void setDead(int value) {
        this.Dead = value;
    }

    public void setImmune(int value) {
        this.Immune = value;
    }

    //Get the condition of the individual
    public String getCondit() {
        if (Immune != 0) {
            return "immune";
        } else if (Healthy != 0) {
            return "healthy";
        } else if (Healthy == 0 && Dead != 1) {
            return "infected";
        } else {
            return "dead";
        }
    }
    //Set the day of the infection of the individual      

    public void setInfDay(int day) {
        this.dayOfInfection = day;
    }

    //Get the infection day of the individual
    public int getInfDay() {
        return dayOfInfection;
    }

    public int getIlldays() {
        return illDays;
    }

}
