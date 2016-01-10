import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TokenSet extends JFrame implements ActionListener{

    public boolean waitnext = true;
    private JPanel p = new JPanel();
    JComboBox options = new JComboBox(Game.AvailableTokens.toArray());
    private JButton b = new JButton("Confirm Token");
    private String Token;
    
    public TokenSet(int player){
	
	
	super("Select the token for Player" + (player + 1));
	setSize(300,150);
	setResizable(true);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	b.addActionListener(this);
	b.setActionCommand("token");

	p.add(options);
	p.add(b);
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
	    Token = save;
	    waitnext = false;
	    dispose();
	}
    }
}
