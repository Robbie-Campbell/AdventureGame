package com.company.KeyFunctions;

import java.util.HashMap;

public class InventoryDisplay {
    // Converts The items in the inventory into a readable format
    public static String inventoryToString(HashMap<String, Integer> arr, String connectorType){
        StringBuilder returnValue = new StringBuilder();
        String punctuation = ",";
        int index = 0;
        for (String i : arr.keySet()) {
            index++;
            String item = String.format("%d %s%s ",arr.get(i), i, punctuation);
            returnValue.append(item);
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
