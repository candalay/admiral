package org.godel.booking;


import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Stream;

public class DeltaEncoding {

    public static void main(String[] args) {        
        Scanner in = new Scanner(System.in);        
        LinkedList<Integer> deltaNumbers = new LinkedList<>();    
        int[] numbers = Stream.of(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        deltaNumbers.add(numbers[0]);
        
        for (int i = 1; i < numbers.length; i++) {
            
            int delta = numbers[i]-numbers[i-1];
            if(delta > 127 || delta < -127){
                deltaNumbers.add(-128);                
            }
            deltaNumbers.add(delta);
        }
        
        in.close();
        
        for (int i : deltaNumbers) {            
            System.out.print(i+" ");
        }
        
    }
    

}