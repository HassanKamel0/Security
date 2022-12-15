package com.example.demoSecurity.gaming;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class GamesServiceTest {
    @InjectMocks GamesService gamesService;

    @Test
    void playDice() {
        assertEquals(0,gamesService.playDice(new int[]{2, 3, 4, 6, 2}));
        assertEquals(1000,gamesService.playDice(new int[]{1,1,1,3,3}));
        assertEquals(450,gamesService.playDice(new int[]{2,4,4,5,4}));
        assertEquals(600,gamesService.playDice(new int[]{6,6,6,3,3}));
        assertEquals(400,gamesService.playDice(new int[]{4,4,4,3,3}));
        assertEquals(1150,gamesService.playDice(new int[]{1,1,1,1,5}));
        assertEquals(300,gamesService.playDice(new int[]{3,3,3,3,3}));
    }
    @Test
    void playPokemon() {
        assertEquals(25,gamesService.playPokemon("fire", "water", 100, 100));
        assertEquals(250,gamesService.playPokemon("fire", "electric", 10, 2));
        assertEquals(175,gamesService.playPokemon("grass", "fire", 35, 5));
        assertEquals(150,gamesService.playPokemon("grass", "electric", 57, 19));
        assertEquals(50,gamesService.playPokemon("electric", "fire", 100, 100));
        assertEquals(100,gamesService.playPokemon("grass", "water", 100, 100));
    }
    @Test
    void playRockPaperScissors() {
        String[] options = {"Rock", "Paper", "Scissors"};
        String pc = options[new Random().nextInt(options.length)];
        given(options[new Random().nextInt(options.length)]).willReturn(pc);
        if (pc.equals("Rock")) {
            assertEquals("It's a draw", gamesService.playRockPaperScissors(1));
            assertEquals("The winner is PC", gamesService.playRockPaperScissors(2));
            assertEquals("The winner is Player", gamesService.playRockPaperScissors(3));
        }
        else if (pc.equals("Paper")) {
            assertEquals("The winner is PC", gamesService.playRockPaperScissors(1));
            assertEquals("It's a draw", gamesService.playRockPaperScissors(2));
            assertEquals("The winner is Player", gamesService.playRockPaperScissors(3));
        }
        else if (pc.equals("Scissors")){
            assertEquals("The winner is Player",gamesService.playRockPaperScissors(1));
            assertEquals("The winner is PC",gamesService.playRockPaperScissors(2));
            assertEquals("It's a draw",gamesService.playRockPaperScissors(3));
        }

    }
    @Test
    void playTowerOfHanoi() {
        assertEquals(0,gamesService.playTowerOfHanoi(0));
        assertEquals(1,gamesService.playTowerOfHanoi(1));
        assertEquals(7,gamesService.playTowerOfHanoi(3));
        assertEquals(31,gamesService.playTowerOfHanoi(5));
        assertEquals(511,gamesService.playTowerOfHanoi(9));
        assertEquals(8191,gamesService.playTowerOfHanoi(13));
        assertEquals(524287,gamesService.playTowerOfHanoi(19));
    }
    @Test
    void playBowling() {
        int[] whenRollIsZero={0};
        int[] openFramesAddsPins={4,2};
        int[] spareAddNextBall={4,6,3,0};
        int[] twoTenInTwoFramesAreNotASpare={3,6,4,2};
        int[] aStrikeAddNextTwoBalls={10,3,2};
        int[] perfectGameScoreIs300={10,10,10,10,10,10,10,10,10,10,10,10};
        assertEquals(0,gamesService.playBowling(whenRollIsZero));
        assertEquals(6,gamesService.playBowling(openFramesAddsPins));
        assertEquals(16,gamesService.playBowling(spareAddNextBall));
        assertEquals(15,gamesService.playBowling(twoTenInTwoFramesAreNotASpare));
        assertEquals(20,gamesService.playBowling(aStrikeAddNextTwoBalls));
        assertEquals(300,gamesService.playBowling(perfectGameScoreIs300));
    }
}