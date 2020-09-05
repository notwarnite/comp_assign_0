package com.assignment;

public class Experiment {

    public int len = 1000;

    Clock clock = new Clock();
    Border border = new Border();
    Intruder intruder = new Intruder();
    Sensor sensor = new Sensor();

    public int distCovered;     //for keeping the track of distance covered by the intruder

    public int doExperiment(int numOfExp, double expP, int expW){

        sensor.changePvalue(expP);
        sensor.changeWvalue(expW);
        sensor.createGrid(expW);
        border.changeWidth(expW);


        int sum = 0;
        int finalsum = 0;

        for(int i=0; i<numOfExp; i++){
            clock.iniTime();
            distCovered = 0;        //initially
            intruder.reset();

            while(intruder.curr_w == -1){       //standing just outside the grid
                for(int k = 1; k<len-1; k++){
                    if(!sensor.cellInfo(k, 0)){
                        intruder.curr_l = k;
                        intruder.curr_w = 0;
                        distCovered++;
                        break;
                    }
                }
                //still is outside then the corner cells are checked
                if(intruder.curr_w == -1){
                    if(!sensor.cellInfo(0,0)){      //the first cell(corner)
                        intruder.curr_l = 0;
                        intruder.curr_w = 0;
                        distCovered++;
                    }
                    else if(!sensor.cellInfo(0, len-1)){        //the last cell(corner)
                        intruder.curr_l = len-1;
                        intruder.curr_w = 0;
                        distCovered++;
                    }
                }
                clock.tenSecPass();
                sensor.dutyCycling();

            }
            //now once the initial move is made, the rest is decided
            while (intruder.curr_w != -1 && intruder.curr_w != expW-1 ) {
                if (!sensor.cellInfo(intruder.curr_l, intruder.curr_w))       //if the intruders current cell sensor is off then only look for next moves
                    if (!sensor.cellInfo(intruder.curr_l, intruder.curr_w + 1)) {
                        intruder.curr_w++;
                        distCovered++;
                        System.out.println("forward");
                    }
                    else if (intruder.curr_l >= 1) {
                        if (!sensor.cellInfo(intruder.curr_l - 1, intruder.curr_w + 1)) {       //checking forward-left cell
                            intruder.curr_w++;
                            intruder.curr_l--;
                            distCovered++;
                            System.out.println("forward and left");
                        } else if (!sensor.cellInfo(intruder.curr_l + 1, intruder.curr_w + 1)) {        //checking forward-right cell
                            intruder.curr_w++;
                            intruder.curr_l++;
                            distCovered++;
                            System.out.println("forward and right");
                        }
                    }
                clock.tenSecPass();
                sensor.dutyCycling();
            }

            sum += clock.time();
            //System.out.println(sum);
            System.out.println("\n");
            finalsum += sum;


        }
        return(finalsum/numOfExp);

    }
}
