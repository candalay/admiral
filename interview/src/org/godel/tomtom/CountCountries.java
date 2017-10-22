package org.godel.tomtom;

public class CountCountries {

    public static void main(String[] args) {

        
        int[][] island= new int[][]{ 
            {5, 4, 4},
            {4, 3, 4},
            {3, 2, 4},
            {2, 2, 2},
            {3, 3, 4},
            {1, 4, 4},            
            {4, 1, 1},            
          };

        
        System.out.println(solution(island));
        
    }
    
    public static int solution(int[][] A) {

        boolean[][] visitedCountries = new boolean[A.length][A[0].length]; 
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for(int j=0;j<A[i].length;j++){
                if(!visitedCountries[i][j]){
                    search(A, visitedCountries, i, j, A[i][j], i, j);
                    count++;
                }
            }
        }
        return count;
    }

    
    public static void search(int[][] A, boolean[][] visitedCountries,int i, int j, int color,int parentI,int parentJ){
        
        if(i<0 || j< 0 || parentI < 0 || parentJ <0){
            return;
        }
        
        if(i>=A.length || j>= A[0].length || parentI >= A.length || parentJ >=A[0].length){
            return;
        }

        
        if(visitedCountries[i][j] || A[i][j] != color){
            return;
        }
        
        if(A[Math.min(parentI,i)][Math.min(parentJ, j)] != color && A[Math.max(parentI,i)][Math.max(parentJ, j)] != color){
            return;
        }
        
        if(A[Math.min(parentI,i)][Math.max(parentJ, j)] != color && A[Math.max(parentI,i)][Math.min(parentJ, j)] != color){
            return;
        }
        
        visitedCountries[i][j] = true; 
        search(A,visitedCountries,i-1,j-1,color,i,j);
        search(A,visitedCountries,i-1,j,color,i,j);
        search(A,visitedCountries,i-1,j+1,color,i,j);
        search(A,visitedCountries,i,j-1,color,i,j);
        search(A,visitedCountries,i,j+1,color,i,j);
        search(A,visitedCountries,i+1,j-1,color,i,j);
        search(A,visitedCountries,i+1,j,color,i,j);
        search(A,visitedCountries,i+1,j+1,color,i,j);
    }
}