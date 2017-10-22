package org.godel.booking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Hotels {

    public static void main(String[] args) throws Exception {

        HashSet<String> words = new HashSet<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stream.of(in.readLine().split(" ")).forEach(w -> words.add(w));        
        int count = Integer.valueOf(in.readLine());

        HashMap<Integer, Integer> map = new HashMap<>();

        while(count-->0){

            int id = Integer.valueOf(in.readLine());

            Stream.of(in.readLine().split(" ")).forEach(w -> {    
                if(words.contains(w.replaceAll(",","").replaceAll("\\.","").trim())){
                    map.put(id,map.get(id)==null ? 1 : (map.get(id) +1));

                }
            });                    
        }

        TreeMap<Integer, List<Integer>> countToId = new TreeMap<>(Collections.reverseOrder());
        
        map.keySet().forEach(id -> {
            if(countToId.get(id) == null){
                List<Integer> l = new LinkedList<>();
                l.add(id);
                countToId.put(map.get(id), l);
            }else{
                countToId.get(map.get(id)).add(id);
            }            
        });
        
        countToId.keySet().forEach(countof -> {
            countToId.get(countof).forEach(c -> System.out.print(c+" "));
        });
        
    }   
}

/*breakfast beach citycenter location metro view staff price
5
1
This hotel has a nice view of the citycenter. The location is perfect.
2
The breakfast is ok. Regarding location, it is quite far from citycenter but price is cheap so it is worth.
1
Location is excellent, 5 minutes from citycenter. There is also a metro station very close to the hotel.
1
They said I couldn't take my dog and there were other guests with dogs! That is not fair.
2
Very friendly staff and good cost-benefit ratio. Its location is a bit far from citycenter.*/