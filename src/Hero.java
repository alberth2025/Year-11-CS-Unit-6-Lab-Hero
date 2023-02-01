import javax.swing.*;
import java.util.*;
public class Hero {
    //properties
    private String name;
    private int hitPoints;

    //constructor
    public Hero(String n){
        name = n;
        hitPoints = 100;
    }

    //behavior
    public String getName(){
        return name;
    }
    public int getHitPoints(){
        return hitPoints;
    }
    public String toString(){
        return ("Hero{name='" + name + "', hitPoints=" + hitPoints + "}");
    }
    public void attack(Hero opponent){
        Random rand = new Random();
        int hitRoll = rand.nextInt(0,99);
        if (hitRoll>=50){
            hitPoints -= 10;
        }else{
            opponent.hitPoints-=10;
        }
    }

    public void senzuBean(){
        hitPoints = 100;
    }

    private void fightUntilTheDeathHelper(Hero opponent){
        while (opponent.getHitPoints()>0&&hitPoints>0){
            attack(opponent);
        }
    }

    public String fightUntilTheDeath(Hero opponent){
        fightUntilTheDeathHelper(opponent);
        return (name + ": " + hitPoints + "         " + opponent.getName() + ": " + opponent.getHitPoints());
    }

    public int[] nFightsToTheDeathHelper(Hero opponent, int n){
        int[] scoreBoard = new int[2];
        while (n>0){
            fightUntilTheDeath(opponent);
            if (hitPoints == 0 && opponent.getHitPoints()==0){
                if (hitPoints==0) {
                    scoreBoard[1]++;
                }else
                    scoreBoard[0]++;
                senzuBean();
                opponent.senzuBean();
            }
            n--;
        }
        return scoreBoard;
    }

    public String nFightsToTheDeath(Hero opponent, int n){
        int[] scoreBoard = nFightsToTheDeathHelper(opponent, n);
        String message = (name + ": " + scoreBoard[0] + " wins /n" + opponent.getName() + ": " + scoreBoard[1] + " wins /n");
        if (scoreBoard[0]>scoreBoard[1]){
            return (message + name + " wins!");
        } else if (scoreBoard[0] == scoreBoard[1]) {
            return (message + "OMG! It was actually a draw!");
        }else
            return (message + opponent.getName() + " wins!");
    }

    public void dramaticFightToTheDeath(Hero opponent) throws InterruptedException {
        while (opponent.getHitPoints()>0&&hitPoints>0){
            attack(opponent);
            System.out.println(name + ": " + hitPoints + "     " + opponent.getName() + ": " + opponent.getHitPoints());
            Thread.sleep(1000);
        }
    }
}
