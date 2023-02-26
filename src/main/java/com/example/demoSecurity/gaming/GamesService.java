package com.example.demoSecurity.gaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.Random;
import static java.lang.Math.pow;
@Service
@Getter
@Setter
public class GamesService {
    private Long AppUserId;
    private String pc;
    public int playDice(int[] arr){
        int sum,oneCounter,twoCounter,threeCounter,fourCounter,fiveCounter,sixCounter;
        sum=oneCounter=twoCounter=threeCounter=fourCounter=fiveCounter=sixCounter=0;
        for (int i: arr)
            switch (i){
                case 1: oneCounter++; break;
                case 2: twoCounter++; break;
                case 3: threeCounter++; break;
                case 4: fourCounter++; break;
                case 5: fiveCounter++; break;
                case 6: sixCounter++; break;
            }
        if (oneCounter>=3)
            sum+=(oneCounter==3)?sum+=1000:(oneCounter-3)*100 + 1000;
        else
            sum+=oneCounter*100;
        if (twoCounter>=3)
            sum+=200;
        if (threeCounter>=3)
            sum+=300;
        if (fourCounter>=3)
            sum+=400;
        if (fiveCounter>=3)
            sum+=(fiveCounter==3)?sum+=500:(fiveCounter-3)*50;
        else
            sum+=fiveCounter*50;
        if (sixCounter>=3)
            sum+=600;
        return sum;
    }
    public int playPokemon(String player1,String player2,int myPower,int hisDefence){
        double effectiveness=0;
        if(player1.equals(player2)) {
            effectiveness=1;
        }
        else {
            if (player1.equals("fire"))
                effectiveness = (player2.equals("grass")) ? 2 : (player2.equals("water")) ? 0.5 : 1;
            else if (player1.equals("water"))
                effectiveness = (player2.equals("fire")) ? 2 : 0.5;
            else if (player1.equals("electric"))
                effectiveness = (player2.equals("water")) ? 2 : 1;
            else if (player1.equals("grass"))
                effectiveness = (player2.equals("water")) ? 2 : (player2.equals("electric")) ? 1 : 0.5;
        }
        return  (int) (50 * (myPower / hisDefence) * effectiveness);
    }
    public String playRockPaperScissors(int choice) {
        String result;
        String[] options = {"Rock", "Paper", "Scissors"};
        String player = options[choice - 1];
        pc = options[new Random().nextInt(options.length)];
        if (player.equals(pc))
            return "It's a draw";
        else if (player.toLowerCase().equals("rock"))
            result= (pc.toLowerCase().equals("paper")) ? "PC" : "Player";
        else if (player.toLowerCase().equals("paper"))
            result= (pc.toLowerCase().equals("scissors")) ? "PC" : "Player";
        else
            result= (pc.toLowerCase().equals("rock")) ? "PC" : "Player";
        return "The winner is " + result;
    }
    public int playTowerOfHanoi(int disks){
        return (int) (pow(2,disks)-1);
    }
    public int playBowling(int[] rolls){
        int[] rolled=new int[22];
        for (int i=0;i<rolls.length;i++)
            rolled[i]=rolls[i];
        int score=0;
        int thisBall=0;
        for (int i=0;i<10;i++) {
            if (rolled[thisBall]==10) { //strike
                score += 10 + rolled[thisBall + 1] + rolled[thisBall + 2];
                thisBall++;
            }
            else if (rolled[thisBall] + rolled[thisBall + 1] == 10) { //spare
                score +=10+rolled[thisBall + 2];
                thisBall+=2;
            }
            else {
                score += rolled[thisBall] + rolled[thisBall + 1];
                thisBall+=2;
            }
        }
        return score;
    }
    public String getPc() {
        return pc;
    }
}
