import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;

public class testing {
    public static void main(String[] args) {
        new testing();
    }

    public testing() {

        int array[][] = {{4,5,3}, {3,6,3}};
        System.out.println(Arrays.deepToString(marblesAreAdjacent(array)));

        /*
        String boardSpaces[][][] = new String[9][9][9];

        int x;
        int y;
        int limit = 5;
        int charsAdded;
        for (int z = 8; z >= 0; z--){

            // Resetting parameters
            charsAdded = 0;
            if (z < 4){
                y = 8;
                x = 4 - z;
            }
           else{
               x = 0;
               y = 12-z;
           }

          // While loop iterates through to add JButtons in array
           while (true){
                boardSpaces[z][x][y] = ""+z+x+y+"";
                charsAdded++;

                if (charsAdded < limit){
                    x++;
                    y--;
                }
                else{
                    break;
                }
            }

          // if z is greater than 4, then the limit will be increased by 1
            if (z>4)
                limit++;
            else
                limit--;
        }

        System.out.println(Arrays.deepToString(boardSpaces));
        */

    }

    public void newFunc (){
        System.out.println("Entered function");
        int test = 5;
        System.out.println(test+1);
    }

    public int[][] takeOutEmptyElements(int arr[][]){
        int marblesChosen = 0;
        for (int i = 0; i < 3; i++){
            if (!Arrays.equals(arr[i], new int[3])){
                marblesChosen += 1;
            }
        }

        // if elements were selected, carries on with program
        if (marblesChosen != 0){

            // declaring new array which excludes zeroes
            int finalChosenArray[][] = new int [marblesChosen][3];

            for (int i = 0; i < marblesChosen; i++){
                finalChosenArray[i] = arr[i];
            }
            return finalChosenArray;
        }
        else{
            return new int[0][0];
        }
    }
    // -------------------------------------------------------------------
    // returns sorted array array if marbles are adjacent, and empty array if they aren't
    public int[][] marblesAreAdjacent(int array[][]){
        if (array.length == 2){
            // checking whether the X co-ordinate of the 2 elements is the same
            if (array[0][1] == array[1][1]){
                if (Math.abs(array[0][2] - array[1][2]) == 1){ // if the 2 elements are next to each other
                    if (array[0][0] < array[1][0]){
                        int temp[] = array[0];
                        array[0] = array[1];
                        array[1] = temp;
                        return array;
                    }
                }
                else{
                    System.out.println("2 element - same X coordinate, not beside each other");
                }
            }
            // checking whether the Y co-ordinate of the 2 elements is the same
            else if (array[0][2] == array[1][2]){
                if (Math.abs(array[0][1] - array[1][1]) == 1){ // if the 2 elements are next to each other
                    if (array[0][1] < array[1][1]){
                        int temp[] = array[0];
                        array[0] = array[1];
                        array[1] = temp;
                        return array;
                    }
                }
                else{
                    System.out.println("2 element - same Y coordinate, not beside each other");
                }
            }
            // checking whether the Z co-ordinate of the 2 elements is the same
            else if (array[0][0] == array[1][0]){
                if (Math.abs(array[0][1] - array[1][1]) == 1){ // if the 2 elements are next to each other
                    if (array[0][1] < array[1][1]){
                        int temp[] = array[0];
                        array[0] = array[1];
                        array[1] = temp;
                        return array;
                    }
                }
                else{
                    System.out.println("2 element - same Z coordinate, not beside each other");
                }
            }
            else{
                System.out.println("Array had 2 elements, but none of coordinates were the same");
            }
        }
        // if the length of array is 3
        // -------------------------------------------------------------------------
        else{
            // if the Z co-ordinate is the same
            if (array[0][0] == array[1][0] && array[1][0] == array[2][0]){
                // checking if the first element in the array is smaller than the last element, if it is then switching the elements
                if (array[0][1] < array[2][1]){
                    int temp[] = array[0];
                    array[0] = array[2];
                    array[2] = temp;
                }
                // checking if the first element in the array is smaller than the second element, if it is then switching the elements
                if (array[0][1] < array[1][1]){
                    int temp[] = array[0];
                    array[0] = array[1];
                    array[1] = temp;
                }
                // checking if the second element in the array is smaller than the third element, if it is then switching the elements
                if (array[1][1] < array[2][1]){
                    int temp[] = array[1];
                    array[1] = array[2];
                    array[2] = temp;
                }
                // checking whether the marbles are right beside each other
                if ((Math.abs(array[0][1] - array[1][1])) == 1 && (Math.abs(array[1][1] - array[2][1]) == 1)){
                    return array;
                }
                else{
                    System.out.println("3 elements - same Z coordinate, not beside");
                    return new int[0][0];
                }
            }
            // if the X co-ordinate is the same
            else if (array[0][1] == array[1][1] && array[1][1] == array[2][1]){
                // checking if the first element in the array is smaller than the last element, if it is then switching the elements
                if (array[0][1] < array[2][1]){
                    int temp[] = array[0];
                    array[0] = array[2];
                    array[2] = temp;
                }
                // checking if the first element in the array is smaller than the second element, if it is then switching the elements
                if (array[0][0] < array[1][0]){
                    int temp[] = array[0];
                    array[0] = array[1];
                    array[1] = temp;
                }
                // checking if the second element in the array is smaller than the third element, if it is then switching the elements
                if (array[1][0] < array[2][0]){
                    int temp[] = array[1];
                    array[1] = array[2];
                    array[2] = temp;
                }
                // checking whether the marbles are right beside each other
                if ((Math.abs(array[0][0] - array[1][0])) == 1 && (Math.abs(array[1][0] - array[2][0]) == 1)){
                    return array;
                }
                else{
                    System.out.println("3 elements - same X coordinate, not beside");
                    return new int[0][0];
                }
            }
            // if the Y co-ordinate is the same
            else if (array[0][2] == array[1][2] && array[1][2] == array[2][2]){
                // checking if the first element in the array is smaller than the last element, if it is then switching the elements
                if (array[0][1] < array[2][1]){
                    int temp[] = array[0];
                    array[0] = array[2];
                    array[2] = temp;
                }
                // checking if the first element in the array is smaller than the second element, if it is then switching the elements
                if (array[0][1] < array[1][1]){
                    int temp[] = array[0];
                    array[0] = array[1];
                    array[1] = temp;
                }
                // checking if the second element in the array is smaller than the third element, if it is then switching the elements
                if (array[1][1] < array[2][1]){
                    int temp[] = array[1];
                    array[1] = array[2];
                    array[2] = temp;
                }
                // checking whether the marbles are right beside each other
                if ((Math.abs(array[0][1] - array[1][1])) == 1 && (Math.abs(array[1][1] - array[2][1]) == 1)){
                    return array;
                }
                else{
                    System.out.println("3 elements - same Y coordinate, not beside");
                    return new int[0][0];
                }
            }
            else{
                System.out.println("3 elements - none of the coordinates were the same");
                return new int[0][0];
            }
        }
        return new int[0][0];
    }
}