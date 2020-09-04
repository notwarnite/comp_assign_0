package com.assignment;

public class Sensor {
    static int len = 1000;      //the length is fixed
    boolean[][] cellState;      //maintain information of the cells in the grid
    int tempW;
    double tempP;


    public void detectMovement(int uW, double uP) {
        tempW = uW;
        tempP = uP;
        cellState = new boolean[len][uW];      //lxw grid is created

        Clock local_clock = new Clock();
        Border local_border = new Border(len, uW, uP);
        Intruder local_intruder = new Intruder();

        //deciding for the first move
        while (local_intruder.curr_w == -1) {
            for(int k =1; k<=len-1; k++) {
                if(!cellInfo(k, 0)){
                    local_intruder.curr_w = 0;
                    local_intruder.curr_l = k;
                    break;
                }
            }

            //the (0,0) column is checked separately from the rest
            if(local_intruder.curr_w == -1) {
                if (!cellInfo(0, 0)) {
                    local_intruder.curr_w = 0;
                    local_intruder.curr_l = 0;
                }
            }
            local_clock.tenSecPass();
            dutyCycling();
        }

        //after the first move is done, deciding for further moves
        while (local_intruder.curr_w != -1 && local_intruder.curr_w != uW-1 ) {
            if (!cellInfo(local_intruder.curr_l, local_intruder.curr_w))       //if the intruders current cell sensor is off then only look for next moves
                if (!cellInfo(local_intruder.curr_l, local_intruder.curr_w + 1)) {
                    local_intruder.curr_w++;
                    System.out.println("forward");
                }
                else if (local_intruder.curr_l >= 1) {
                    if (!cellInfo(local_intruder.curr_l - 1, local_intruder.curr_w + 1)) {       //checking forward-left cell
                        local_intruder.curr_w++;
                        local_intruder.curr_l--;
                        System.out.println("forward and left");
                    } else if (!cellInfo(local_intruder.curr_l + 1, local_intruder.curr_w + 1)) {        //checking forward-right cell
                        local_intruder.curr_w++;
                        local_intruder.curr_l++;
                        System.out.println("forward and right");
                    }
                }
            local_clock.tenSecPass();
            dutyCycling();
        }
        System.out.println(local_clock.time());

    }

    public void dutyCycling(){
        for(int i = 0; i < len; i++){
            for(int j = 0; j < tempW; j++ )
                if (probGenerate() >= tempP) {
                    cellState[i][j] = true;        //means the sensor is on
                } else {
                    cellState[i][j] = false;     //means the sensor is off
                }
        }
    }

    public boolean cellInfo (int x, int y){
        return cellState[x][y];
    }

    public double probGenerate(){       //generating random double between 0 and 1
        var x = ((Math.random() * 1) + 0);
        double roundOff = Math.round(x * 100.0) / 100.0;
        return roundOff;
    }


}

