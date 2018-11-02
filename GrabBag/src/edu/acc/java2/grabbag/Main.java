package edu.acc.java2.grabbag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        List<String> names = new ArrayList();
        names.add("JavaScript");
        names.add("Java");
        names.add("Python");
        names.add("Kotlin");
        
        String name = names.get(0);
        
        for (int i = 0; i < names.size(); i++)
            System.out.printf("%02d:%s\n", i, names.get(i));
        
        for (String s : names)
            System.out.println(s);
        
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s);
        }
        
        Set<String> cities = new HashSet<>();
        cities.add("Austin");
        cities.add("Seattle");
        cities.add("Helsinki");
        cities.add("Austin");
        
        System.out.println(cities.size());
        
        for (String city : cities) {
            System.out.println(city);
        }
        
        Iterator<String> it2 = cities.iterator();
        while (it2.hasNext())
            System.out.println(it2.next());
        
        Set<String> sortedCities = new TreeSet(cities);
        for (String city : sortedCities)
            System.out.println(city);
        
        Map<String, Integer> pops = new HashMap<>();
        pops.put("Austin", 969000);
        pops.put("Seattle", 881000);
        pops.put("Helsinki", 1050000);
        
        for (String key : pops.keySet())
            System.out.printf("%s:%s\n", key, pops.get(key));
        
        Iterator<String> keys = pops.keySet().iterator();
        while (keys.hasNext()) {
            if (keys.next().equals("Seattle"))
                System.out.println("Seattle is in the map");
        }
    }
}
