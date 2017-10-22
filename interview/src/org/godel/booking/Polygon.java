package org.godel.booking;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Polygon {

    static int squares=0;
    static int rectangles=0;        
    static int others=0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        Thread runnable = new Thread() {             
            @Override
            public void run() {               
                while(in.hasNextLine()){

                    String sideVal = in.nextLine().trim();                
                    String[] sideValues =  sideVal.split(" ");
                    Integer[] sides = new Integer[4];
                    IntStream.range(0, sideValues.length).forEach(i -> sides[i] = Integer.valueOf(sideValues[i]));

                    boolean allpositive = Arrays.asList(sides).stream().allMatch(i -> i>0);
                    boolean allnegative = Arrays.asList(sides).stream().allMatch(i -> i<0);

                    if(allpositive || allnegative){
                        if(sides[0].equals(sides[1]) && sides[1].equals(sides [2]) && sides [2].equals(sides[3])){
                            squares++; 
                        }else if(sides[0].equals(sides[2]) && sides[1].equals(sides[3])){
                            rectangles++;
                        }else{
                            others++;
                        }
                    }else{
                        others++;
                    }
                }
            }
        };

        runnable.setDaemon(true);
        runnable.start();

        try {            
            runnable.join(500L);
        } catch (Exception e) {

        }


        System.out.println(squares +" "+ rectangles+" "+others);
        in.close();
    }
}