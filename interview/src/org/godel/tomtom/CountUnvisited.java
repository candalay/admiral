package org.godel.tomtom;

public class CountUnvisited {

    public static void main(String[] args) {
        
        System.out.println(solution(new int[]{3,-5,0,-1,-3}));
//
        System.out.println(solution(new int[]{1,2,3}));

        System.out.println(solution(new int[]{1,1,1,-2,5,6,7}));

    }
    
    static int solution(int A[]){
        
        if(A == null || A.length == 0){
            return 0;
        }

        if(A[0] == 0 || A.length == 1){
            return A.length - 1;
        }
        
        boolean visited[] = new boolean[A.length];
        int count = 0;
        for (int i = 0; i>=0 &&i < A.length;) {            
            if(visited[i]){
                break;
            }
            count++;  
            visited[i] = true;
            if(A[i] == 0){
                break;
            }            
            i += A[i];            
        }
        return A.length - count;        
    }

}