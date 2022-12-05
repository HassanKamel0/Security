package com.example.demoSecurity.gaming;
import com.example.demoSecurity.appuser.AppUser;
import com.example.demoSecurity.appuser.AppUserRepository;
import com.example.demoSecurity.gaming.games.DiceScore;
import com.example.demoSecurity.gaming.games.Pokemon;
import com.example.demoSecurity.gaming.games.RockPaperScissors;
import com.example.demoSecurity.gaming.games.TowerOfHanoi;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/v1/games")
@AllArgsConstructor
public class GamesController {
    @Autowired

    private final RockPaperScissors rockPaperScissors;
    private final Pokemon pokemon;
    private final TowerOfHanoi towerOfHanoi;
    private final DiceScore diceScore;
    private final AppUserRepository appUserRepository;
    private final AppUser appUser=appUserRepository.findById(1L).get();
    @GetMapping(path = "/rockPaperScissors")
    public String playRock(int choice){
        String result=rockPaperScissors.playRockPaperScissors(choice);
        if (result.equals("The winner is Player")){
            appUser.setScore(appUser.getScore()+1);
            appUserRepository.save(appUser);
        }
        return result;
    }
    @PostMapping(path = "/pokemon")
    public int playPokemon(String player1,String player2,int myPower,int hisDefence){
        int result=pokemon.playPokemon(player1,player2,myPower,hisDefence);
        if (result<100){
            appUser.setScore(appUser.getScore()+1);
            appUserRepository.save(appUser);
        }
        return result;
    }
    @GetMapping(path = "/diceScore")
    public int playDiceScore(int[] diceNumbers){return diceScore.playDice(diceNumbers);}
    @GetMapping(path = "/towerOfHanoi")
    public int playTowerOfHanoi(int disks){
        return towerOfHanoi.playTowerOfHanoi(disks);
    }
}
