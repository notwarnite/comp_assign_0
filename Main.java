package com.assignment;


import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {

        Scanner input = new Scanner(System.in);
        var local_sensor = new Sensor();

        System.out.print("enter p: ");
        double userP = input.nextDouble();

        System.out.print("enter w: ");
        int userW = input.nextInt();
        local_sensor.detectMovement(userW, userP);

    }

    }


