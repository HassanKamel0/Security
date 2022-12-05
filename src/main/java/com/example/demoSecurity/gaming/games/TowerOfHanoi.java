package com.example.demoSecurity.gaming.games;
import org.springframework.stereotype.Service;
import static java.lang.Math.pow;
@Service
public class TowerOfHanoi {
    public int playTowerOfHanoi(int disks){
        return (int) (pow(2,disks)-1);
    }
}
