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
        int damage=gamesService.playPokemon(player1,player2,myPower,hisDefence);
        if (damage<100){
            var appUser=appUserRepository.findById(gamesService.getAppUserId()).get();
            appUser.setScore(appUser.getScore()+50);
            appUserRepository.save(appUser);
        }
        return damage;
    }
    @GetMapping(path = "/diceScore")
    public int playDiceScore(int[] diceNumbers){
        var appUser=appUserRepository.findById(gamesService.getAppUserId()).get();
        int score=gamesService.playDice(diceNumbers);
        appUser.setScore(appUser.getScore()+score);
        appUserRepository.save(appUser);
        return score;}
    @GetMapping(path = "/towerOfHanoi")
    public int playTowerOfHanoi(int disks){
        var appUser=appUserRepository.findById(gamesService.getAppUserId()).get();
        int score=gamesService.playTowerOfHanoi(disks);
        appUser.setScore(appUser.getScore()+score);
        appUserRepository.save(appUser);
        return score;
    }
    @GetMapping(path = "/bowling")
    public int playBowling(int[] rolls){
        var appUser=appUserRepository.findById(gamesService.getAppUserId()).get();
        int score=gamesService.playBowling(rolls);
        appUser.setScore(appUser.getScore()+score);
        appUserRepository.save(appUser);
        return score;
    }
}
