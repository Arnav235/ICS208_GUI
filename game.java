import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.Arrays;
import java.util.Arrays.*;

public class game extends JPanel implements ActionListener {

    JPanel whiteDisplaced;
    JPanel blackDisplaced;
    
    // Declaring JButtons for game board
    JButton boardSpaces[][][] = new JButton[9][9][9];
    JLabel currentStatus; // declaring current status JLabel

    // Array made of integers
    int boardSpacesInt[][][] = new int[9][9][9];

    // Declaring chosenarrays
    int chosenArray[][] = new int[3][3];
    int chosenArrayAddIndex = 0;

    boolean spaceAlreadySelected;

    // variable which stores array of the piece which was currently chosen
    int coordinateArray[] = new int[3];

    int whitePoints = 0;
    int blackPoints = 0;
    int turn = 1;

    // -----------------------------------------------------------------
    public game(){
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        GridBagConstraints consts = new GridBagConstraints();

        // back to menu JButton
        JButton backToMenu = new JButton("<– to Menu");
        backToMenu.addActionListener(this);
        backToMenu.setActionCommand("MENU");

        backToMenu.setFont(new Font("Avenir", Font.PLAIN,20));
        backToMenu.setBackground(Color.WHITE);
        backToMenu.setForeground(Color.BLACK);

        consts.gridx = 0;
        consts.gridy = 0;
        consts.weighty = 0.1;
        consts.weightx = 0.1;

        add(backToMenu, consts);

        // current status JLabel
        currentStatus = new JLabel ("It's black's move");

        currentStatus.setForeground(Color.WHITE);
        currentStatus.setFont(new Font("Avenir", Font.PLAIN, 20));

        consts.weightx = 0.3;
        consts.gridx = 1;

        add(currentStatus, consts);

        // black display JPanel
        // ------------------------------------------------------------
        blackDisplaced = new JPanel();
        blackDisplaced.setBackground(Color.BLACK);
        blackDisplaced.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel black = new JLabel("Black displaced");
        //blackDisplaced.add(black);

        consts.fill = GridBagConstraints.BOTH;
        consts.gridx = 0;
        consts.gridy = 1;
        consts.weightx = 0.1;
        consts.weighty = 0.1;
        add(blackDisplaced, consts);


        // white display JPanel
        // ---------------------------------------------------------------
        whiteDisplaced = new JPanel();
        whiteDisplaced.setBackground(Color.BLACK);
        whiteDisplaced.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel white = new JLabel("White displaced");
        //whiteDisplaced.add(white);

        consts.fill = GridBagConstraints.BOTH;
        consts.gridx = 2;
        consts.gridy = 1;
        consts.weightx = 0.1;
        consts.weighty = 0.1;
        add(whiteDisplaced, consts);

        // main gameBoard
        // -----------------------------------------------------------
        JPanel board = new JPanel();

        board.setLayout(new GridBagLayout());
        GridBagConstraints boardconsts = new GridBagConstraints();
        board.setBackground(new Color(111,27,3));
        board.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // building additional JPanels
        JPanel boardPanels[] = new JPanel[9];

        // initializing boardconsts variable

        boardconsts.gridx = 0;
        boardconsts.gridy = 0;
        boardconsts.weighty = 0;

        for (int panelnum = 0; panelnum < 9; panelnum++){
            boardPanels[panelnum] = new JPanel();
            boardPanels[panelnum].setBackground(new Color(111,27,3));
            board.add(boardPanels[panelnum], boardconsts);

            boardconsts.gridy += 1;
        }
        
        // initializing variable to build JButtons
        int x;
        int y;
        int limit = 5;
        int charsAdded;

        // Initializing weights and grid variables for GBL
        boardconsts.fill = GridBagConstraints.BOTH;
        int gridy = 0;
        boardconsts.gridwidth = 2;
        
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

                if (z == 0 || z == 1){
                    boardSpaces[z][x][y] = new JButton(createImageIcon("images/black.jpg"));
                    boardSpacesInt[z][x][y] = 1;
                }
                else if (z == 2 && (x > 3 && x < 7)){
                    boardSpaces[z][x][y] = new JButton(createImageIcon("images/black.jpg"));
                    boardSpacesInt[z][x][y] = 1;
                }
                else if (z == 8 || z == 7){
                    boardSpaces[z][x][y] = new JButton(createImageIcon("images/white.jpg"));
                    boardSpacesInt[z][x][y] = -1;
                }
                else if (z == 6 && (x > 1 && x < 5)){
                    boardSpaces[z][x][y] = new JButton(createImageIcon("images/white.jpg"));
                    boardSpacesInt[z][x][y] = -1;
                }
                else
                    boardSpaces[z][x][y] = new JButton(createImageIcon("images/emptySpace.png"));
                
                boardSpaces[z][x][y].addActionListener(this);
                boardSpaces[z][x][y].setActionCommand(""+z+x+y);

                boardSpaces[z][x][y].setBorderPainted(false);
                charsAdded++;

                boardPanels[gridy].add(boardSpaces[z][x][y]);
                
                if (charsAdded < limit){
                    x++;
                    y--;
                }
                else
                    break;
            }
    
            // if z is greater than 4, then the limit will be increased by 1
            if (z>4)
                limit++;
            else
                limit--;
            gridy += 1;
        }

        consts.fill = GridBagConstraints.BOTH;
        consts.gridx = 1;
        consts.gridy = 1;
        consts.weightx = 0.5;
        consts.weighty = 0.2;

        add(board, consts);

        // arrows to control where marbles move
        // -----------------------------------------------------------
        JPanel arrows = new JPanel();
        arrows.setBackground(Color.BLACK);
        arrows.setLayout(new GridBagLayout());
        GridBagConstraints arrowsconsts = new GridBagConstraints();

        // Adding arrow buttons
        JButton rightArrow = new JButton(createImageIcon("images/right.jpg"));
        rightArrow.addActionListener(this);
        rightArrow.setActionCommand("rightArrow");
        arrowsconsts.gridx = 3;
        arrowsconsts.gridy = 1;
        arrows.add(rightArrow, arrowsconsts);

        JButton upRight = new JButton(createImageIcon("images/upRight.jpg"));
        upRight.addActionListener(this);
        upRight.setActionCommand("upRightArrow");
        arrowsconsts.gridx = 2;
        arrowsconsts.gridy = 0;
        arrows.add(upRight, arrowsconsts);

        JButton downRight = new JButton(createImageIcon("images/downRight.jpg"));
        downRight.addActionListener(this);
        downRight.setActionCommand("downRightArrow");
        arrowsconsts.gridx = 2;
        arrowsconsts.gridy = 2;
        arrows.add(downRight, arrowsconsts);

        JButton upLeft = new JButton(createImageIcon("images/upLeft.jpg"));
        upLeft.addActionListener(this);
        upLeft.setActionCommand("upLeftArrow");
        arrowsconsts.gridx = 1;
        arrowsconsts.gridy = 0;
        arrows.add(upLeft, arrowsconsts);

        JButton leftArrow = new JButton(createImageIcon("images/left.jpg"));
        leftArrow.addActionListener(this);
        leftArrow.setActionCommand("leftArrow");
        arrowsconsts.gridx = 0;
        arrowsconsts.gridy = 1;
        arrows.add(leftArrow, arrowsconsts);

        JButton downLeft = new JButton(createImageIcon("images/downLeft.jpg"));
        downLeft.addActionListener(this);
        downLeft.setActionCommand("downLeftArrow");
        arrowsconsts.gridx = 1;
        arrowsconsts.gridy = 2;
        arrows.add(downLeft, arrowsconsts);

        // Updating GirdBagConstraints for arrows JPanel
        consts.fill = GridBagConstraints.BOTH;
        consts.gridx = 1;
        consts.gridy = 2;
        consts.weightx = 0.1;
        consts.weighty = 0.7;

        add(arrows, consts);
    }

    protected static ImageIcon createImageIcon(String path){
        java.net.URL imgURL = main.class.getResource( path);
        if (imgURL != null){
            return new ImageIcon (imgURL);
        } else {
            System.err.println("Couldn't find file" + path);
            return null;
        }
    }

    public void actionPerformed (ActionEvent e){
        System.out.println(e.getActionCommand());
        
        if (e.getActionCommand().equals("MENU")){
            main.cd.show(main.window.getContentPane(), "MENU");
        }
        // ------------------------------------- START UpRightArrow
        else if (e.getActionCommand().equals("upRightArrow")){

            // getting the final array (which excludes zero arrays)
            int finalChosenArray[][] = takeOutEmptyElements(chosenArray);

            int movementArr[] = {1,0,-1};
            // if the length of the array is more than one, than we check whether the marbles are adjacent to each other
            if (finalChosenArray.length > 1){
                finalChosenArray = marblesAreAdjacent(finalChosenArray);

                System.out.println("Final sorted array");
                System.out.println(Arrays.deepToString(finalChosenArray));

                // if the marbles are adjacent to each other, continue, else change the current status to tell the player that the marbles are not adjacent
                if (finalChosenArray.length > 0){
                    // resetting the board image of the marbles
                    resetBoardSpaces(finalChosenArray);

                    if (finalChosenArray[0][1] != finalChosenArray[1][1]){
                        broadSide(finalChosenArray, movementArr);
                    }
                    else {
                        move(finalChosenArray, movementArr);
                    }

                    chosenArray = new int[3][3];
                    chosenArrayAddIndex = 0;
                }
                else{
                    currentStatus.setText("The marbles you have selected are not adjacent to each other");
                }
            }
            else if (finalChosenArray.length == 1){
                // reseting boardSpaces
                resetBoardSpaces(finalChosenArray);
                
                broadSide(finalChosenArray, movementArr);

                chosenArray = new int[3][3];
                chosenArrayAddIndex = 0;
            }
        } // ---------------------------------------------- END UpRightArrow


        // ------------------------------------- START rightArrow
        else if (e.getActionCommand().equals("rightArrow")){
            // getting the final array (which excludes zero arrays)
            int finalChosenArray[][] = takeOutEmptyElements(chosenArray);

            int movementArr[] = {0,1,-1};
            // if the length of the array is more than one, than we check whether the marbles are adjacent to each other
            if (finalChosenArray.length > 1){
                finalChosenArray = marblesAreAdjacent(finalChosenArray);

                System.out.println("Final sorted array");
                System.out.println(Arrays.deepToString(finalChosenArray));

                // if the marbles are adjacent to each other, continue, else change the current status to tell the player that the marbles are not adjacent
                if (finalChosenArray.length > 0){
                    // resetting the board image of the marbles
                    resetBoardSpaces(finalChosenArray);

                    if (finalChosenArray[0][0] != finalChosenArray[1][0]){
                        broadSide(finalChosenArray, movementArr);
                    }
                    else {
                        move(finalChosenArray, movementArr);
                    }

                    chosenArray = new int[3][3];
                    chosenArrayAddIndex = 0;
                }
                else{
                    currentStatus.setText("The marbles you have selected are not adjacent to each other");
                }
            }
            else if (finalChosenArray.length == 1){
                // reseting boardSpaces
                resetBoardSpaces(finalChosenArray);
                
                broadSide(finalChosenArray, movementArr);

                chosenArray = new int[3][3];
                chosenArrayAddIndex = 0;
            }
        } // ---------------------------------------------- END rightArrow

        // ------------------------------------- START downRightArrow
        else if (e.getActionCommand().equals("downRightArrow")){
            // getting the final array (which excludes zero arrays)
            int finalChosenArray[][] = takeOutEmptyElements(chosenArray);

            int movementArr[] = {-1,1,0};
            // if the length of the array is more than one, than we check whether the marbles are adjacent to each other
            if (finalChosenArray.length > 1){
                finalChosenArray = marblesAreAdjacent(finalChosenArray);

                System.out.println("Final sorted array");
                System.out.println(Arrays.deepToString(finalChosenArray));

                // if the marbles are adjacent to each other, continue, else change the current status to tell the player that the marbles are not adjacent
                if (finalChosenArray.length > 0){
                    // resetting the board image of the marbles
                    resetBoardSpaces(finalChosenArray);

                    if (finalChosenArray[0][2] != finalChosenArray[1][2]){
                        broadSide(finalChosenArray, movementArr);
                    }
                    else {
                        move(finalChosenArray, movementArr);
                    }

                    chosenArray = new int[3][3];
                    chosenArrayAddIndex = 0;
                }
                else{
                    currentStatus.setText("The marbles you have selected are not adjacent to each other");
                }
            }
            else if (finalChosenArray.length == 1){
                // reseting boardSpaces
                resetBoardSpaces(finalChosenArray);
                
                broadSide(finalChosenArray, movementArr);

                chosenArray = new int[3][3];
                chosenArrayAddIndex = 0;
            }
        } // ---------------------------------------------- END downRightArrow


        // ------------------------------------- START upLeftArrow
        else if (e.getActionCommand().equals("upLeftArrow")){
            // getting the final array (which excludes zero arrays)
            int finalChosenArray[][] = takeOutEmptyElements(chosenArray);

            int movementArr[] = {1,-1,0};
            // if the length of the array is more than one, than we check whether the marbles are adjacent to each other
            if (finalChosenArray.length > 1){
                finalChosenArray = marblesAreAdjacent(finalChosenArray);

                System.out.println("Final sorted array");
                System.out.println(Arrays.deepToString(finalChosenArray));

                // if the marbles are adjacent to each other, continue, else change the current status to tell the player that the marbles are not adjacent
                if (finalChosenArray.length > 0){
                    // resetting the board image of the marbles
                    resetBoardSpaces(finalChosenArray);

                    if (finalChosenArray[0][2] != finalChosenArray[1][2]){
                        broadSide(finalChosenArray, movementArr);
                    }
                    else {
                        move(finalChosenArray, movementArr);
                    }

                    chosenArray = new int[3][3];
                    chosenArrayAddIndex = 0;
                }
                else{
                    currentStatus.setText("The marbles you have selected are not adjacent to each other");
                }
            }
            else if (finalChosenArray.length == 1){
                // reseting boardSpaces
                resetBoardSpaces(finalChosenArray);
                
                broadSide(finalChosenArray, movementArr);

                chosenArray = new int[3][3];
                chosenArrayAddIndex = 0;
            }
        } // ---------------------------------------------- END upLeftArrow

        // ------------------------------------- START leftArrow
        else if (e.getActionCommand().equals("leftArrow")){
            // getting the final array (which excludes zero arrays)
            int finalChosenArray[][] = takeOutEmptyElements(chosenArray);

            int movementArr[] = {0,-1,1};
            // if the length of the array is more than one, than we check whether the marbles are adjacent to each other
            if (finalChosenArray.length > 1){
                finalChosenArray = marblesAreAdjacent(finalChosenArray);

                System.out.println("Final sorted array");
                System.out.println(Arrays.deepToString(finalChosenArray));

                // if the marbles are adjacent to each other, continue, else change the current status to tell the player that the marbles are not adjacent
                if (finalChosenArray.length > 0){
                    // resetting the board image of the marbles
                    resetBoardSpaces(finalChosenArray);

                    if (finalChosenArray[0][0] != finalChosenArray[1][0]){
                        broadSide(finalChosenArray, movementArr);
                    }
                    else {
                        move(finalChosenArray, movementArr);
                    }

                    chosenArray = new int[3][3];
                    chosenArrayAddIndex = 0;
                }
                else{
                    currentStatus.setText("The marbles you have selected are not adjacent to each other");
                }
            }
            else if (finalChosenArray.length == 1){
                // reseting boardSpaces
                resetBoardSpaces(finalChosenArray);
                
                broadSide(finalChosenArray, movementArr);

                chosenArray = new int[3][3];
                chosenArrayAddIndex = 0;
            }
        } // ---------------------------------------------- END leftArrow


        // ------------------------------------- START downLeftArrow
        else if (e.getActionCommand().equals("downLeftArrow")){
            // getting the final array (which excludes zero arrays)
            int finalChosenArray[][] = takeOutEmptyElements(chosenArray);

            int movementArr[] = {-1,0,1};
            // if the length of the array is more than one, than we check whether the marbles are adjacent to each other
            if (finalChosenArray.length > 1){
                finalChosenArray = marblesAreAdjacent(finalChosenArray);

                System.out.println("Final sorted array");
                System.out.println(Arrays.deepToString(finalChosenArray));

                // if the marbles are adjacent to each other, continue, else change the current status to tell the player that the marbles are not adjacent
                if (finalChosenArray.length > 0){
                    // resetting the board image of the marbles
                    resetBoardSpaces(finalChosenArray);

                    if (finalChosenArray[0][1] != finalChosenArray[1][1]){
                        broadSide(finalChosenArray, movementArr);
                    }
                    else {
                        move(finalChosenArray, movementArr);
                    }

                    chosenArray = new int[3][3];
                    chosenArrayAddIndex = 0;
                }
                else{
                    currentStatus.setText("The marbles you have selected are not adjacent to each other");
                }
            }
            else if (finalChosenArray.length == 1){
                // reseting boardSpaces
                resetBoardSpaces(finalChosenArray);
                
                broadSide(finalChosenArray, movementArr);

                chosenArray = new int[3][3];
                chosenArrayAddIndex = 0;
            }
        } // ---------------------------------------------- END downLeftArrow

        // ------------------------------------------------------------------------------------------
        // handle clicking and unclicking of marbles
        else{
            coordinateArray = stringToArray(e.getActionCommand());
            spaceAlreadySelected = false;

            // Checking whether the coordinate selected is valid depending on who's turn it is
            if (boardSpacesInt[coordinateArray[0]][coordinateArray[1]][coordinateArray[2]] == turn){
                
                // checking whether coordinate is already in array
                for (int a = 0; a < 3; a ++){
                    if (Arrays.equals(coordinateArray, chosenArray[a])){

                        spaceAlreadySelected = true;

                        // setting the image to unselected white or black depending on who's turn it is
                        if (turn == 1){
                            boardSpaces[coordinateArray[0]][coordinateArray[1]][coordinateArray[2]].setIcon(createImageIcon("images/black.jpg"));
                        }
                        else{
                            boardSpaces[coordinateArray[0]][coordinateArray[1]][coordinateArray[2]].setIcon(createImageIcon("images/white.jpg"));
                        }

                        while (a < 2){
                            chosenArray[a] = chosenArray[a+1];
                            a += 1;
                        }
                        chosenArray[2] = new int[3];
                        chosenArrayAddIndex -= 1;
                        break;
                    }
                }

                if (!spaceAlreadySelected){
                    
                    // setting the image to selected white or black depending on who's turn it is
                    if (turn == 1){
                        boardSpaces[coordinateArray[0]][coordinateArray[1]][coordinateArray[2]].setIcon(createImageIcon("images/blackSelected.jpg"));
                    }
                    else{
                        boardSpaces[coordinateArray[0]][coordinateArray[1]][coordinateArray[2]].setIcon(createImageIcon("images/whiteSelected.jpg"));
                    }

                    // if the array is full, then the program shifts all the elements one index lower, and adds the new element to chosenArray[2]
                    if (chosenArrayAddIndex == 3){

                        // setting the first element in the array to a non selected image
                        if (turn == 1){
                            boardSpaces[chosenArray[0][0]][chosenArray[0][1]][chosenArray[0][2]].setIcon(createImageIcon("images/black.jpg"));
                        }
                        else{
                            boardSpaces[chosenArray[0][0]][chosenArray[0][1]][chosenArray[0][2]].setIcon(createImageIcon("images/white.jpg"));
                        }

                        int a = 0;
                        while (a < 2){
                            chosenArray[a] = chosenArray[a+1];
                            a += 1;
                        }
                        chosenArray[2] = coordinateArray;
                    }

                    // otherwise the new element is added to the chosenArrayAddIndex, and chosenArrayAddIndex is incremeented by one
                    else{
                        chosenArray[chosenArrayAddIndex] = coordinateArray;
                        chosenArrayAddIndex += 1;
                    }

                }

            }
        }
    }

    public int[] stringToArray(String stringArr){
        int coordinateInt = Integer.parseInt(stringArr);
        int arr[] = new int[3];
        arr[0] = coordinateInt/100;
        arr[1] = (coordinateInt/10) % 10;
        arr[2] = coordinateInt % 10;

        return arr;
    }

    // ---------------------------------------------------------
    // takes in an array, and outputs an array without any empty arrays
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
            currentStatus.setText("You have not selected any marbles");
            return new int[0][0];
        }
    }

    // -------------------------------------------------------------------
    // returns sorted array if marbles are adjacent, and empty array if they aren't
    public int[][] marblesAreAdjacent(int array[][]){
        if (array.length == 2){
            // checking whether the X co-ordinate of the 2 elements is the same
            if (array[0][1] == array[1][1]){
                if (Math.abs(array[0][2] - array[1][2]) == 1){ // if the 2 elements are next to each other
                    if (array[0][0] < array[1][0]){
                        int temp[] = array[0];
                        array[0] = array[1];
                        array[1] = temp;
                    }
                    return array;
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
                    }
                    return array;
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
                    }
                    return array;
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
                }
            }
            // if the X co-ordinate is the same
            else if (array[0][1] == array[1][1] && array[1][1] == array[2][1]){
                // checking if the first element in the array is smaller than the last element, if it is then switching the elements
                if (array[0][0] < array[2][0]){
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
                }
            }
            else{
                System.out.println("3 elements - none of the coordinates were the same");
            }
        }
        return new int[0][0];
    }

    // takes in an array, and resets those spaces to unselected pieces
    public void resetBoardSpaces(int arr[][]){
        for (int i = 0; i < arr.length; i++){
            if (turn == 1){
                boardSpaces[arr[i][0]][arr[i][1]][arr[i][2]].setIcon(createImageIcon("images/black.jpg"));
            }
            else{
                boardSpaces[arr[i][0]][arr[i][1]][arr[i][2]].setIcon(createImageIcon("images/white.jpg"));
            }
        }
    }

    // -------------------------------------------------------------------------
    // if the player plays a broadSide move
    public void broadSide(int arr[][], int movementArr[]){
        boolean EmptySpace = true;
        boolean moveOffBoard = false;

        // checking whether the marbles will go off the board
        for (int i = 0; i < arr.length; i++){
            if (arr[i][0] + movementArr[0] > 8 || arr[i][0] + movementArr[0] < 0 || arr[i][1] + movementArr[1] > 8 || arr[i][1] + movementArr[1] < 0 || arr[i][2] + movementArr[2] > 8 || arr[i][2] + movementArr[2] < 0){
                moveOffBoard = true;
                currentStatus.setText("That is an illegal move, your marbles will go off the board!");
            }
        }

        // checking whether the marbles are free to move
        if (moveOffBoard == false){
            for (int b = 0; b < arr.length; b++){
                if (boardSpacesInt[arr[b][0] + movementArr[0]] [arr[b][1] + movementArr[1]] [arr[b][2] + movementArr[2]] != 0){
                    EmptySpace = false;
                    currentStatus.setText("You are playing a broadside move, however other marbles are in the way");
                }
            }
        }

        // if the move is legal, then we update the board and boardSpacesInt
        if (EmptySpace == true && moveOffBoard == false){
            for (int a = 0; a < arr.length; a++){

                // if it is black's turn
                if (turn == 1){
                    boardSpaces[arr[a][0]] [arr[a][1]] [arr[a][2]].setIcon(createImageIcon("images/emptySpace.png"));
                    boardSpaces[arr[a][0] + movementArr[0]] [arr[a][1] + movementArr[1]] [arr[a][2] + movementArr[2]].setIcon(createImageIcon("images/black.jpg"));

                    boardSpacesInt[arr[a][0]] [arr[a][1]] [arr[a][2]] = 0;
                    boardSpacesInt[arr[a][0] + movementArr[0]] [arr[a][1] + movementArr[1]] [arr[a][2] + movementArr[2]] = 1;
                }
                // if it is white's turn
                else{
                    boardSpaces[arr[a][0]] [arr[a][1]] [arr[a][2]].setIcon(createImageIcon("images/emptySpace.png"));
                    boardSpaces[arr[a][0] + movementArr[0]] [arr[a][1] + movementArr[1]] [arr[a][2] + movementArr[2]].setIcon(createImageIcon("images/white.jpg"));

                    boardSpacesInt[arr[a][0]] [arr[a][1]] [arr[a][2]] = 0;
                    boardSpacesInt[arr[a][0] + movementArr[0]] [arr[a][1] + movementArr[1]] [arr[a][2] + movementArr[2]] = -1;
                }
            }
            System.out.println("Succesful broadside move");
            turn *= -1;
            if (turn == 1)
                currentStatus.setText("Now it is black's turn" );
            else
                currentStatus.setText("Now it is white's turn" );
        }
    }

    // --------------------------------------------------------------------
    // if player makees a normal move
    public void move(int arr[][], int movementArr[]){
        boolean illegalMove = false;

        // finding which marble is at the front of motion
        int frontOfMovement;
        if (movementArr[1] == 1){
            frontOfMovement = 0;
        }
        else if (movementArr[1] == 0 && movementArr[0] == 1){
            frontOfMovement = 0;
        }
        else{
            frontOfMovement = arr.length-1;
        }

        // checking if the marbles go off the board
        if (arr[frontOfMovement][0] + movementArr[0] > 8 || arr[frontOfMovement][0] + movementArr[0] < 0 || arr[frontOfMovement][1] + movementArr[1] > 8 || arr[frontOfMovement][1] + movementArr[1] < 0 || arr[frontOfMovement][2] + movementArr[2] > 8 || arr[frontOfMovement][2] + movementArr[2] < 0){
            illegalMove = true;
            currentStatus.setText("That is an illegal move, your marbles will go off the board");
        }

        int spacesAway = 1;
        int oppMarbles = 0;
        boolean marbleDisplace = false;
        boolean validMove = true;
        // checking if there are less of the opponent's marbles
        if (illegalMove == false) {
            while (true){

                // check whether the place being checked is off the board
                if (arr[frontOfMovement][0] + movementArr[0] * spacesAway > 8 || arr[frontOfMovement][0] + movementArr[0] * spacesAway < 0 || arr[frontOfMovement][1] + movementArr[1] * spacesAway > 8 || arr[frontOfMovement][1] + movementArr[1] * spacesAway < 0 || arr[frontOfMovement][2] + movementArr[2] * spacesAway > 8 || arr[frontOfMovement][2] + movementArr[2] * spacesAway < 0){
                    marbleDisplace = true;

                    if (turn == -1){
                        whitePoints += 1;
                        blackDisplaced.add(new JLabel(createImageIcon("images/black.png")));
                    }
                    else{
                        blackPoints += 1;
                        whiteDisplaced.add(new JLabel(createImageIcon("images/white.png")));
                    }
                    break;
                }

                // checking if there are an equal number of the opponent's marbles
                if (oppMarbles == arr.length){
                    System.out.println("Opponent marbles --"+ oppMarbles);
                    System.out.println(arr.length-1);
                    currentStatus.setText("You don't have enough marbles to move your opponent's marbles");
                    validMove = false;
                    break;
                }

                // checking whether the current player's own marbles are in the way
                else if (boardSpacesInt[arr[frontOfMovement][0] + movementArr[0] * spacesAway] [arr[frontOfMovement][1] + movementArr[1] * spacesAway] [arr[frontOfMovement][2] + movementArr[2] * spacesAway] == turn){
                    validMove = false;
                    currentStatus.setText("One of your own marbles is in the way");
                    break;
                }

                // checking if the next space is empty
                if (boardSpacesInt[arr[frontOfMovement][0] + movementArr[0] * spacesAway] [arr[frontOfMovement][1] + movementArr[1] * spacesAway] [arr[frontOfMovement][2] + movementArr[2] * spacesAway] == 0){
                    break;
                }            

                // if an opponent's marble is encountered, then increment oppMarbles and spacesAway
                else if (boardSpacesInt[arr[frontOfMovement][0] + movementArr[0] * spacesAway] [arr[frontOfMovement][1] + movementArr[1] * spacesAway] [arr[frontOfMovement][2] + movementArr[2] * spacesAway] == turn * -1){
                    oppMarbles += 1;
                    spacesAway += 1; 
                }
            }
            // if a marble was displaced
            if (validMove == true && marbleDisplace == true){

                System.out.println("Succesful displacement of piece");

                // set the space in front of the marble at the front of motion equal to the current player's marble
                // if it is black's turn
                if (turn == 1){
                    boardSpaces[arr[frontOfMovement][0] + movementArr[0]] [arr[frontOfMovement][1] + movementArr[1]] [arr[frontOfMovement][2] + movementArr[2]].setIcon(createImageIcon("images/black.jpg"));
                    boardSpacesInt[arr[frontOfMovement][0] + movementArr[0]] [arr[frontOfMovement][1] + movementArr[1]] [arr[frontOfMovement][2] + movementArr[2]] = 1;
                }
                // if it is white's turn
                else{
                    boardSpaces[arr[frontOfMovement][0] + movementArr[0]] [arr[frontOfMovement][1] + movementArr[1]] [arr[frontOfMovement][2] + movementArr[2]].setIcon(createImageIcon("images/white.jpg"));
                    boardSpacesInt[arr[frontOfMovement][0] + movementArr[0]] [arr[frontOfMovement][1] + movementArr[1]] [arr[frontOfMovement][2] + movementArr[2]] = -1;
                }

                // setting the marble furthest from the direction of motion equal to an empty space
                boardSpaces[arr[frontOfMovement][0] - movementArr[0] * (arr.length-1)] [arr[frontOfMovement][1] - movementArr[1] * (arr.length-1)] [arr[frontOfMovement][2] - movementArr[2] * (arr.length-1)].setIcon(createImageIcon("images/emptySpace.png"));
                boardSpacesInt[arr[frontOfMovement][0] - movementArr[0] * (arr.length-1)] [arr[frontOfMovement][1] - movementArr[1] * (arr.length-1)] [arr[frontOfMovement][2] - movementArr[2] * (arr.length-1)] = 0;
                
                if (turn == 1){
                    if (blackPoints == 6){
                        System.out.println("BLACK WON");
                        main.winnerLabel.setText("Black Won!");
                        main.cd.show(main.window.getContentPane(), "WINNINGSCREEN");
                    }
                }
                else{
                    if (whitePoints == 6){
                        System.out.println("WHITE WON");
                        main.winnerLabel.setText("White Won!");
                        main.cd.show(main.window.getContentPane(), "WINNINGSCREEN");
                    }
                }

                // changing turn, and changing currentStatus according to who's turn it is
                turn *= -1;
                if (turn == 1)
                    currentStatus.setText("Now it is black's turn" );
                else
                    currentStatus.setText("Now it is white's turn" );
            
            }
            else if (validMove == true){

                System.out.println("Succesful move of more than 1 marbles");

                // setting the empty space in front of the direction of motion equal to the first marble in the moving coloumn
                if (boardSpacesInt[arr[frontOfMovement][0] + movementArr[0] * oppMarbles] [arr[frontOfMovement][1] + movementArr[1] * oppMarbles] [arr[frontOfMovement][2] + movementArr[2] * oppMarbles] == 1){
                    boardSpaces[arr[frontOfMovement][0] + movementArr[0] * spacesAway] [arr[frontOfMovement][1] + movementArr[1] * spacesAway] [arr[frontOfMovement][2] + movementArr[2] * spacesAway].setIcon(createImageIcon("images/black.jpg"));
                    boardSpacesInt[arr[frontOfMovement][0] + movementArr[0] * spacesAway] [arr[frontOfMovement][1] + movementArr[1] * spacesAway] [arr[frontOfMovement][2] + movementArr[2] * spacesAway] = 1;
                }
                else{
                    boardSpaces[arr[frontOfMovement][0] + movementArr[0] * spacesAway] [arr[frontOfMovement][1] + movementArr[1] * spacesAway] [arr[frontOfMovement][2] + movementArr[2] * spacesAway].setIcon(createImageIcon("images/white.jpg"));
                    boardSpacesInt[arr[frontOfMovement][0] + movementArr[0] * spacesAway] [arr[frontOfMovement][1] + movementArr[1] * spacesAway] [arr[frontOfMovement][2] + movementArr[2] * spacesAway] = -1;
                }


                // set the space in front of the marble at the front of motion equal to the current player's marble
                // if it is black's turn
                if (turn == 1){
                    boardSpaces[arr[frontOfMovement][0] + movementArr[0]] [arr[frontOfMovement][1] + movementArr[1]] [arr[frontOfMovement][2] + movementArr[2]].setIcon(createImageIcon("images/black.jpg"));
                    boardSpacesInt[arr[frontOfMovement][0] + movementArr[0]] [arr[frontOfMovement][1] + movementArr[1]] [arr[frontOfMovement][2] + movementArr[2]] = 1;
                }
                // if it is white's turn
                else{
                    boardSpaces[arr[frontOfMovement][0] + movementArr[0]] [arr[frontOfMovement][1] + movementArr[1]] [arr[frontOfMovement][2] + movementArr[2]].setIcon(createImageIcon("images/white.jpg"));
                    boardSpacesInt[arr[frontOfMovement][0] + movementArr[0]] [arr[frontOfMovement][1] + movementArr[1]] [arr[frontOfMovement][2] + movementArr[2]] = -1;
                }

                // setting the marble furthest from the direction of motion equal to an empty space
                boardSpaces[arr[frontOfMovement][0] - movementArr[0] * (arr.length-1)] [arr[frontOfMovement][1] - movementArr[1] * (arr.length-1)] [arr[frontOfMovement][2] - movementArr[2] * (arr.length-1)].setIcon(createImageIcon("images/emptySpace.png"));
                boardSpacesInt[arr[frontOfMovement][0] - movementArr[0] * (arr.length-1)] [arr[frontOfMovement][1] - movementArr[1] * (arr.length-1)] [arr[frontOfMovement][2] - movementArr[2] * (arr.length-1)] = 0;
            
                // changing turn, and changing currentStatus according to who's turn it is
                turn *= -1;
                if (turn == 1)
                    currentStatus.setText("Now it is black's turn" );
                else
                    currentStatus.setText("Now it is white's turn" );
            
            }
        }
    }

        /*
        Chosen array is a String
        If user clicks on marble, check in chosen array whether marble has been clicked or not
        If clicked: unclick it, and update chosen array
        If not clicked, click it, and update chosen array

        If user clicks on more than 3 spaces, unclick the last space (move values up to 0th index)

        ----------------------------

        [General]
        if user clicks arrow keys
        transfer chosen values to an array (discarding 0 values) [2d array]

            Count number of 0's in String array
            IF array LENGTH = 1
            IF array LENGTH = 0

            Else: value closest to direction of motion first, and furthest from direction of motion at end of array (index 2) [NOT GENERAL]
        get length of array (3-0's)
        check whether chosen spaces are adjescent to each other
            check if any of the 3 dimensions are the same
            and if the other dimensions are different by 1 (if [x dimension]Math.abs(space/10 - space2/10) ==1 && Math.abs(space2/10 - space3/10) ==1 || [y dimension]Math.abs(space/10 - space2/10) ==1 && Math.abs(space2/10 - space3/10) ==1)
        [General]

        Check whether move is valid
            Marbles do not go off of board
                chosen marbles + direction of move is between 8 and 0
            
            Marbles are more than opponent's marbles
                Take marble with coordinate (z,x,y) closer to direction of motion, and count towards direction of motion
                If it encounters a marble of the same color, break;
                If there are equal marbles of the opponent as length of array: status= not enough marbles to move

                If space ==0: record last marble
            Check whether any marble has fallen off of board, if it has, add a marble to the side and update score
                Take last marble and add direction of motion


            Make move. 
                Change value of 
        */
}