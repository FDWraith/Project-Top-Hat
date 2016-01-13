import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PlayerSet extends JFrame{

    //Stores the selected number of players.
    public int NumPlayer;

    /*
     *Creates a JOptionPane that requests the user to select the number of ply
     *The X button is not disabled
     *It will reopen the same GUI if the user press the X button
     */
    public PlayerSet(){
	String [] options = {"2 Players","3 Players", "4 players"};

	NumPlayer = JOptionPane.showOptionDialog(null,"Select the Number of Players","Players Selection",0,JOptionPane.QUESTION_MESSAGE,null,options,0)+2;

	while(NumPlayer == 1){
	    NumPlayer = JOptionPane.showOptionDialog(null,"Select the Number of Players","Players Selection",0,JOptionPane.QUESTION_MESSAGE,null,options,0)+2;
	}
	
    }
}
