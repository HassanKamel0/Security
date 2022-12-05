package com.example.demoSecurity.gaming.games;
import org.springframework.stereotype.Service;

@Service
public class DiceScore {
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
}
