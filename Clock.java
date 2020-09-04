package com.assignment;

public class Clock {
    public int t = 0;

    public  void tenSecPass(){      //time to move
        t += 10;
    }

    public int time(){
        return t;
    }

}

