package com.assignment;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) throws IOException {



        int numberOfExp = 10;
        Experiment experiment = new Experiment();
//
//        Scanner input = new Scanner(System.in);
//        System.out.print("enter p: ");
//        double userP = input.nextDouble();
//
//        System.out.print("enter w: ");
//        int userW = input.nextInt();

//        for(double pC = 0.1; pC<1; pC+=0.1){
//            for(int wC = 2; wC<10; wC+=2){
//                int avg = experiment.doExperiment(numberOfExp, pC, wC);
//                System.out.println(avg);
//            }
//        }

        try {

            //csvWriter Object instantiation to write ouput to file : output.csv
            FileWriter csvWriter = new FileWriter("output.csv");

            //Writing the Column Names in output.csv
            csvWriter.write("Width");
            csvWriter.write(",");
            csvWriter.write("P-value");
            csvWriter.write(",");
            csvWriter.write("Average_Time");
            csvWriter.write("\n");


            //Varying Width value from 5 to 100 in steps of 5
            for (int expw = 5; expw <100 ; expw += 5) {

                //Varying expProb value from 0 to 0.95 in steps of 0.05
                for (double prob = 0.1; prob < 1; prob+=0.1) {

                    int avgtime = experiment.doExperiment(numberOfExp, prob, expw);

                    //Writing experiment values to file : output.csv for each iteration
                    csvWriter.write(String.valueOf(expw));
                    csvWriter.write(",");
                    csvWriter.write(String.valueOf(prob));
                    csvWriter.write(",");
                    csvWriter.write(String.valueOf(avgtime));
                    csvWriter.write("\n");

                    //Console OUT message for each iteration completion
                    System.out.println("Writing values for WIDTH = " + expw + " and P-VALUE = " + String.format("%.2f",prob) + " and AvgTimeTaken = " + avgtime);

                }

            }

            //Flushing out buffer data from csvWriter object
            csvWriter.flush();

            //Closing csvWriter object
            csvWriter.close();

        }

        //Catch Block in case of any exception to catch the exception
        catch (Exception e) {

            //Print Exception details to console OUT
            System.out.println(e);

        }

        //Final confirmation of completion of writing file : output.csv
        System.out.println("Writing FILE : 'output.csv' completed!");

    }

    }




