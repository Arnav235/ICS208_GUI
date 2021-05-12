/*
Made by: Arnav Paruthi
Date: June 7 2019
Description: Abalone is a very interesting 2 player game. You can run the game where 
you'll find instructions on how to play it.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class main extends JPanel implements ActionListener{
    static CardLayout cd = new CardLayout();
    static JFrame window = new JFrame ("Abalone");
    static JLabel winnerLabel;

    public static void main (String[]args){
        main content = new main ();

        window.setContentPane(content);
        window.setSize(1200,900);
        window.setLocation(100,100);
        window.setVisible(true);
    }
    
    public main(){
        setLayout(cd);
        mainScreen();
        instructionsScreen();
        winningScreen();
        //add("game", new game());
    }

    public void mainScreen(){
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        menu.setBackground(Color.BLACK);

        GridBagConstraints consts = new GridBagConstraints();
        
        JLabel abaloneLogo = new JLabel(createImageIcon("images/logo.png"));

        consts.gridx = 0;
        consts.gridy = 0;
        consts.weighty = 1;

        menu.add(abaloneLogo,consts);

        // Adding play button

        JButton play = new JButton("Play");
        play.setFont(new Font("Avenir",Font.PLAIN, 40));
        play.setBackground(Color.white);
        play.setForeground(Color.BLACK);

        play.addActionListener(this);
        play.setActionCommand("PLAYGAME");

        consts.gridy = 3;
        consts.weighty = 0.1;

        menu.add(play,consts);

        // Adding instructions button

        JButton instructions = new JButton("Instructions");
        instructions.setFont(new Font("Avenir",Font.PLAIN, 40));
        instructions.setBackground(Color.white);
        instructions.setForeground(Color.BLACK);
        instructions.addActionListener(this);
        instructions.setActionCommand("INSTRUCTIONS");

        consts.gridy = 4;
        consts.weighty = 0.1;

        menu.add(instructions,consts);

        // adding abalone balls image

        JLabel abaloneBalls = new JLabel(createImageIcon("images/marbles.jpg"));

        consts.gridy = 5;
        consts.weighty = 0.7;
        consts.anchor = GridBagConstraints.PAGE_END;

        menu.add(abaloneBalls, consts);
        add("MENU", menu);
    }

    // -----------------------------------------------------------------------

    public void instructionsScreen(){
        JPanel instructions = new JPanel();
        instructions.setLayout(new GridBagLayout());
        instructions.setBackground(Color.BLACK);

        GridBagConstraints consts = new GridBagConstraints();

        // instructions JLabel

        JLabel title = new JLabel("Instructions");

        title.setFont(new Font("Avenir", Font.PLAIN, 100));
        title.setForeground(Color.WHITE);

        consts.gridx = 0;
        consts.gridy = 0;
        consts.weighty = 1;

        instructions.add(title,consts);

        // Instructions text

        JTextArea instructs = new JTextArea("Players take alternate turns. Each player can move either a single marble or a\n"+
        "column of marbles (that is, two or three marbles of the same color that are\n"+
        "adjacent and arranged in a straight line) one space at a time. Marbles can\n"+
        "be moved either forward or diagonally in any direction. The one exception\n"+
        "is that in a \"side-step\" move, in which marbles are moved sideways into\n"+
        "adjacent free spaces, they cannot be used to push an opponent's single marble\n"+
        "or column. The sidestep move is also known as a \"broadside\".\n\n" +
        "Essentially, you win the game by pushing the other player's marbles back,\n"+
        "sending them to unoccupied spaces and eventually off the board completely \n"+
        "to the rim, where they're no longer in play. The trick, though, is that a single\n"+
        "marble can't push an opponent's marble. To do that, you need numerical\n"+
        "superiority, a situation called a \"sumito.\" In that situation, a player's column\n"+
        "faces a lesser number of the opponent's marbles, giving him or her\n"+
        "advantage. A player can then use a column of three black or white marbles to push\n"+
        "one or two of the opponent's marbles one space, or a column of two to push\n"+
        "one of an opponent's marbles back a space. If the opponent has a column of\n"+
        "three marbles, it can't be pushed at all, even if a player has four marbles\n"+
        "in a row.\n\n"+
        "Select the marbles you wish to move by clicking on them, then click the arrow\n"+
        "keys corresponding to the direction you wish to move the marbles.");

        instructs.setFont(new Font("Avenir", Font.PLAIN, 15));
        instructs.setForeground(Color.BLACK);
        instructs.setBackground(Color.WHITE);
        instructs.setMargin( new Insets(20,50,100,10) );
        instructs.setOpaque(true);
        instructs.setEditable(false);

        consts.gridy = 1;
        consts.weighty = 0.8;


        instructions.add(instructs,consts);
        // Back to menu JButton

        JButton toMenu = new JButton("Menu");
        toMenu.addActionListener(this);
        toMenu.setActionCommand("MENU");

        toMenu.setFont(new Font("Avenir", Font.PLAIN, 40));
        toMenu.setBackground(Color.WHITE);
        toMenu.setForeground(Color.BLACK);
        toMenu.setHorizontalAlignment(toMenu.RIGHT);

        consts.gridy = 2;
        consts.weighty = 0.8;
        consts.ipadx = 0;
        consts.ipady = 5;

        instructions.add(toMenu,consts);
        add("INSTRUCTIONS", instructions);
    }

    // -----------------------------------------------------------

    public void winningScreen(){
        JPanel winningScreen = new JPanel();
        winningScreen.setLayout(new GridBagLayout());
        winningScreen.setBackground(Color.BLACK);

        GridBagConstraints consts = new GridBagConstraints();

        // Winner JLabel
        winnerLabel = new JLabel("");
        winnerLabel.setFont(new Font("Avenir", Font.PLAIN, 150));
        winnerLabel.setForeground(Color.WHITE);

        consts.gridx = 0;
        consts.gridy = 0;
        consts.weighty = 1;

        winningScreen.add(winnerLabel, consts);

        // Play Again Button
        JButton playAgain = new JButton("Play Again");
        playAgain.setFont(new Font("Avenir", Font.PLAIN, 40));
        playAgain.setForeground(Color.BLACK);
        playAgain.setBackground(Color.WHITE);

        playAgain.addActionListener(this);
        playAgain.setActionCommand("PLAYGAME");

        consts.gridy = 3;
        consts.weighty = 0.1;
        winningScreen.add(playAgain, consts);


        // Menu Button
        JButton toMenu = new JButton("Back To Menu");
        toMenu.setFont(new Font("Avenir", Font.PLAIN, 40));
        toMenu.setForeground(Color.BLACK);
        toMenu.setBackground(Color.WHITE);

        toMenu.addActionListener(this);
        toMenu.setActionCommand("MENU");

        consts.gridy = 4;
        consts.weighty = 0.7;
        winningScreen.add(toMenu, consts);

        add("WINNINGSCREEN", winningScreen);
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
        System.out.println("Action Command -" + e.getActionCommand());
        if (e.getActionCommand().equals("INSTRUCTIONS")){
            cd.show(this, "INSTRUCTIONS");
        }
        else if (e.getActionCommand().equals("PLAYGAME")){
            add("game", new game());
            cd.show(this,"game");
        }
        else if (e.getActionCommand().equals("MENU")){
            cd.show(this, "MENU");
        }

    }


}