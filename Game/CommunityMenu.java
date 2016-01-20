import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CommunityMenu extends JFrame implements ActionListener{
    public boolean done;
    private Container pane;
    private Player player;
    private String action;
    private int actionID;

    public CommunityMenu(Player entry, String action,int actionID){
	this.player = entry;
	this.action = action;
	this.actionID = actionID
	this.done = false;
	
	this.setTitle("Player "+(player.getIndex()+1)+", you have landed on Community Chest!");
	this.setSize(400,200);
	this.setDefualtCloseOperation(DO_NOTHING_ON_CLOSE);
	
	pane = this.setContentPane();
	pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));

	JLabel l1 = new JLabel(action);
	l1.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	JButton b1 = new JButton("OK");
	b1.addActionListener(this);
	b1.setActionCommand("OK");
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);

	pane.add(l1);
	pane.add(b1);
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("OK")){
	    executeAction(this.actionID);
	    terminate();
	}
	terminate();
    }

    public void executeAction(int perform){
	if (perform == 0){
	    if(player.getLocation() > 0){
		player.changeMoney(200);
	    }
	    player.changeLocation(0);
	}else if(perform == 1){
	    player.changeMoney(200);
	}else if(perform == 2){
	    player.changeMoney(-1 * 50);
	}else if(perform == 3){
	    player.changeMoney(50);
	}else if(perform == 4){
	    //get out of jail free
	}else if(perform == 5){
	    player.changeLocation(10);
	}else if(perform == 6){
	    for(int i = 0; i < Game.PlayerList.size(); i++){
		Game.PlayerList.get(i).changeMoney(-1 * 50);
	    }
	    player.changeMoney(50 * Game.PlayerList.size());
	}else if(perform == 7){

	}else if(perform == 8){

	}else if(perform == 9){

	}else if(perform == 10){

	}else if(perform == 11){

	}else if(perform == 12){

	}else if(perform == 13){

	}else if(perform == 14){

	}else if(perform == 15){

	}else if(perform == 16){

	}else{
	    //obligatory else, but nothing happens :P   
	}
    }

    public void terminate(){
	done = true;
    }
}