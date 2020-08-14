package com.company.KeyFunctions;

import java.util.HashMap;

public class InventoryDisplay {
    // Converts The items in the inventory into a readable format, uses the hashmap, and/ or, what the HashMap int represents
    // And whether it is the item HashMap
    public static String inventoryToString(HashMap<String, Integer> arr, String connectorType, String outputType, Boolean items){
        StringBuilder returnValue = new StringBuilder();
        String punctuation = "";
        int index = 0;
        for (String i : arr.keySet()) {
            index++;
            if (items) {
                String item = String.format("%d %s%s", arr.get(i), i, punctuation);
                returnValue.append(item);
                punctuation = ",";
            }
            else
            {
                String item = String.format("%s: (%d %s", i, arr.get(i), outputType);
                returnValue.append(item);
            }
            if (index == arr.size()-1)
            {
                String connection = String.format(" %s ",connectorType);
                returnValue.append(connection);
                punctuation = "";
            }
        }
        return returnValue.toString();
    }
}
