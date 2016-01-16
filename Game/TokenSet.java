import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TokenSet extends JFrame implements ActionListener, ItemListener{

    public boolean waitnext = true;
    private JPanel p = new JPanel();
    JComboBox options = new JComboBox(Game.AvailableTokens.toArray());
    private JButton b = new JButton("Confirm Token");
    private String Token;
    private int Saveplayer;
    private ImageIcon image;
    
    public TokenSet(int player){
	
	super("Select the token for Player" + (player));
	setSize(500,120);
	setResizable(true);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	Saveplayer = player;

	b.addActionListener(this);
	b.setActionCommand("token");

	image = new ImageIcon("images/tokens/monopoly_token_" + options.getSelectedITem().toString() +".png");
	
	p.add(options);
	p.add(b);
	p.add(image)
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
	
    }
}
