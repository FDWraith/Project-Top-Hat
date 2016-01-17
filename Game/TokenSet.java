import java.util.*;
import java.io.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class TokenSet extends JFrame implements ActionListener, ItemListener{

    public boolean waitnext = true;//Signals for the next GUI to open when turned false
    private JPanel p = new JPanel();
    JComboBox options = new JComboBox(Game.AvailableTokens.toArray());//Create a combobox filled with options with availabe tokens
    private JButton b = new JButton("Confirm Token");
    private int Saveplayer;//Stores the player number
    private ImageIcon icon;//Loads the token image to be displayed
    private JLabel tv; //Label that holds ImageIcon icon
    
    public TokenSet(int player){

	//Creates the container
	super("Select the token for Player" + (player));
	setSize(500,120);
	setResizable(true);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	Saveplayer = player;

	//Adds the actionListener to button to signal when the close the GUI
	b.addActionListener(this);
	b.setActionCommand("token");

	//Add all the JComponenets
	p.add(options);
	p.add(b);
	options.addItemListener(this);		

	//Load the image onto icon and scale it to a proper size
	icon = new ImageIcon(Game.wdr + "images/tokens/monopoly_token_"+ options.getSelectedItem().toString() + ".png");
	Image img = icon.getImage();
	Image newimg = img.getScaledInstance(80,80, java.awt.Image.SCALE_SMOOTH);
	ImageIcon newicon = new ImageIcon(newimg);

	//Loads the image onto the label and load the label
	tv = new JLabel();
	tv.setIcon(newicon);
	
	p.add(tv);
	
	add(p);

	setVisible(true);
    }


    //Listens to when the player press the confirm button, alters token and signal for the GUI to close
    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("token")){
	    String save = (options.getSelectedItem()).toString();
	    Game.AvailableTokens.remove(save);
	    Game.TokenList[Saveplayer - 1] = save;
	    waitnext = false;
	    dispose();
	}
    }

    //Listen to when the player changes the selected option in ComboBox and alters the iamge
    public void itemStateChanged(ItemEvent e){
	if(e.getStateChange() == ItemEvent.SELECTED){
	    icon = new ImageIcon(Game.wdr + "images/tokens/monopoly_token_"+ options.getSelectedItem().toString() + ".png");	    
	    Image img = icon.getImage();
	    Image newimg = img.getScaledInstance(80,80, java.awt.Image.SCALE_SMOOTH);
	    ImageIcon newicon = new ImageIcon(newimg);
	    tv.setIcon(newicon);
	}
    }
}
