package com.example.singh.crystalball;

import java.util.Random;

/**
 * Created by singh on 8/6/2015.
 */
public class CrystalBall {

public    String[] answers={"Future Pornstar","Congratulations U are an engineer","Go home and sleep","Study first then fuck","You are a rockstar"};




    //methods(things that object will do)
    public String getAnAnswer()
    {
        String answer="";

        Random randomGenerator= new Random();
        int r=randomGenerator.nextInt(answers.length);


        answer=answers[r];

        return answer;

    }
}
