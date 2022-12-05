package com.example.demoSecurity.gaming;
import com.example.demoSecurity.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/v1/games")
@AllArgsConstructor
public class GamesController {
    @Autowired
    private GamesService gamesService;
    private final AppUserRepository appUserRepository;
    @GetMapping(path = "/rockPaperScissors")
    public String playRock(int choice){
        String result=gamesService.playRockPaperScissors(choice);
        if (result.equals("The winner is Player")){
            var appUser=appUserRepository.findById(gamesService.getAppUserId()).get();
            appUser.setScore(appUser.getScore()+1);
            appUserRepository.save(appUser);
        }
        return result;
    }
    @PostMapping(path = "/pokemon")
    public int playPokemon(String player1,String player2,int myPower,int hisDefence){
        int result=gamesService.playPokemon(player1,player2,myPower,hisDefence);
        if (result<100){
            var appUser=appUserRepository.findById(gamesService.getAppUserId()).get();
            appUser.setScore(appUser.getScore()+1);
            appUserRepository.save(appUser);
        }
        return result;
    }
    @GetMapping(path = "/diceScore")
    public int playDiceScore(int[] diceNumbers){return gamesService.playDice(diceNumbers);}
    @GetMapping(path = "/towerOfHanoi")
    public int playTowerOfHanoi(int disks){
        return gamesService.playTowerOfHanoi(disks);
    }
}
