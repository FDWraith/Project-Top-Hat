import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class TokenSet extends JFrame implements ActionListener, ItemListener{

    public boolean waitnext = true;
    private JPanel p = new JPanel();
    JComboBox options = new JComboBox(Game.AvailableTokens.toArray());
    private JButton b = new JButton("Confirm Token");
    private String Token;
    private int Saveplayer;
    private ImageIcon icon;
    private JLabel tv; 
    
    public TokenSet(int player){
	
	super("Select the token for Player" + (player));
	setSize(500,120);
	setResizable(true);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	Saveplayer = player;

	b.addActionListener(this);
	b.setActionCommand("token");

	
	p.add(options);
	p.add(b);
	options.addItemListener(this);		

	icon = new ImageIcon(Game.wdr + "images/tokens/monopoly_token_"+ options.getSelectedItem().toString() + ".png");
	Image img = icon.getImage();
	Image newimg = img.getScaledInstance(80,80, java.awt.Image.SCALE_SMOOTH);
	ImageIcon newicon = new ImageIcon(newimg);

	
	tv = new JLabel();
	tv.setIcon(newicon);
	
	p.add(tv);
	
	add(p);

	setVisible(true);
    }

    public String getToken(){
	return Token;
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("token")){
	    String save = (options.getSelectedItem()).toString();
	    Game.AvailableTokens.remove(save);
	    Game.TokenList[Saveplayer - 1] = save;
	    waitnext = false;
	    dispose();
	    System.out.println(Token);
	}
    }

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
