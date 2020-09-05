package com.assignment;


public class Sensor {
    static int len = 1000;      //the length is fixed
    boolean[][] cellState;      //maintain information of the cells in the grid
    int tempW;
    double tempP;



    public void createGrid(int tW){
        cellState = new boolean[len][tW];
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

    public void changePvalue(double newP){
        tempP = newP;
    }

    public void changeWvalue(int newW){
        tempW = newW;
    }



}

