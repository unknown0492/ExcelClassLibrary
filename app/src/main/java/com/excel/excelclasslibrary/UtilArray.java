package com.excel.excelclasslibrary;

public class UtilArray {

    public static String[] sortLexicographically( String arr[] ){
        String temp;
        for( int i = 0 ; i < arr.length ; i++ ){
            for( int j = i + 1 ; j < arr.length; j++ ){
                if( arr[ j ].compareTo( arr[ i ]) < 0 ){
                    temp = arr[ i ];
                    arr[ i ] = arr[ j ];
                    arr[ j ] = temp;
                }
            }
        }
        return arr;
    }

    public static String[] removeDuplicates( String arr[] ){
        String temp[] = new String[ arr.length ];
        arr = sortLexicographically( arr );
        int counter = 0;
        for( int i=0; i<arr.length ; i++ ){
            for( int j = i + 1; j<arr.length ; j++ ){
                if( arr[ j ].charAt( 0 ) == arr[ i ].charAt( 0 ) ){
                    // IF first characters of current and next is same, then check if the strings are same
                    if( ! arr[ j ].equals( arr[ i ] ) ){
                        temp[ counter ] = arr[ i ];
                        counter++;
                        i = j;
                        break;
                    }
                }
                else{
                    temp[ counter ] = arr[ i ];
                    counter++;
                    i = j;
                    break;
                }

            }
        }

        // Count the number of values in temp excluding null
        int i = 0;
        for( i = 0; temp[ i ] != null ; i++ ){

        }
        String[] removed_duplicates = new String[ i ];
        for( i = 0; i < removed_duplicates.length ; i++ ){
            removed_duplicates[ i ] = temp[ i ];
        }

        return removed_duplicates;
    }
}
