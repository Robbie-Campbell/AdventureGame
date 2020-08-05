package com.company.KeyFunctions;

import java.util.ArrayList;

public class InventoryDisplay {
    // Converts The items in the inventory into a readable format
    public static String inventoryToString(ArrayList<String> arr){
        StringBuilder returnValue = new StringBuilder();
        String punctuation = ",";
        int index = 0;
        for (String s : arr) {
            index++;
            returnValue.append(" a ").append(s).append(punctuation);
            if (index == arr.size()-1)
            {
                returnValue.append(" and");
                punctuation = "";
            }
        }
        return returnValue.toString();
    }
}
