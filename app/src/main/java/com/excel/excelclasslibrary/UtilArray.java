package com.excel.excelclasslibrary;

public class UtilArray {
    public static String[] sortLexicographically(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[i]) < 0) {
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static String[] removeDuplicates(String[] arr) {
        String[] temp = new String[arr.length];
        String[] arr2 = sortLexicographically(arr);
        int counter = 0;
        int i = 0;
        while (i < arr2.length) {
            int j = i + 1;
            while (true) {
                if (j < arr2.length) {
                    if (arr2[j].charAt(0) != arr2[i].charAt(0)) {
                        temp[counter] = arr2[i];
                        counter++;
                        i = j;
                        break;
                    } else if (!arr2[j].equals(arr2[i])) {
                        temp[counter] = arr2[i];
                        counter++;
                        i = j;
                        break;
                    } else {
                        j++;
                    }
                } else {
                    break;
                }
            }
            i++;
        }
        int i2 = 0;
        while (temp[i2] != null) {
            i2++;
        }
        String[] removed_duplicates = new String[i2];
        for (int i3 = 0; i3 < removed_duplicates.length; i3++) {
            removed_duplicates[i3] = temp[i3];
        }
        return removed_duplicates;
    }
}
