package com.example.demoSecurity.gaming.games;
import org.springframework.stereotype.Service;
@Service
public class Pokemon {
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
}
