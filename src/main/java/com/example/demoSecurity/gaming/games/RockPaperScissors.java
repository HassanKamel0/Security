package com.example.demoSecurity.gaming.games;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class RockPaperScissors {
    public String playRockPaperScissors(int choice) {
        String result = "";
        Random random = new Random();
        String[] options = {"Rock", "Paper", "Scissors"};
        String player = options[choice - 1];
        int randomIndex = random.nextInt(options.length);
        String pc = options[randomIndex];
        if (player == pc)
            return "It's a draw";
        else if (player == "Rock")
            result = (pc == "Paper") ? "PC" : "Player";
        else if (player == "Paper")
            result = (pc == "Scissors") ? "PC" : "Player";
        else
            result = (pc == "Rock") ? "PC" : "Player";
        return "The winner is " + result;
    }
}
