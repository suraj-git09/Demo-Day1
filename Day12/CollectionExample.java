package Day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionExample {
    public static void main(String[] args) {
        // ArrayList example
        ArrayList<String> arrayList = new ArrayList<>();

        // Adding elements to the ArrayList
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");

        // Retrieving elements from the ArrayList
        System.out.println("ArrayList elements:");
        for (String fruit : arrayList) {
            System.out.println(fruit);
        }

        // HashMap example
        HashMap<String, Integer> hashMap = new HashMap<>();

        // Adding key-value pairs to the HashMap
        hashMap.put("Apple", 1);
        hashMap.put("Banana", 2);
        hashMap.put("Cherry", 3);

        // Retrieving values from the HashMap
        System.out.println("\nHashMap elements:");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // Example of getting a specific value
        String key = "Banana";
        if (hashMap.containsKey(key)) {
            System.out.println("\nThe value associated with '" + key + "' is: " + hashMap.get(key));
        } else {
            System.out.println("\nThe key '" + key + "' is not present in the HashMap.");
        }
    }
}
