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
	this.actionID = actionID;
	this.done = false;
	
	this.setTitle("Player "+(player.getIndex()+1)+", you have landed on Community Chest!");
	this.setSize(400,200);
	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	
	pane = this.getContentPane();
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
	    player.setXY(750,750);
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
	    player.setXY(50,750);
	    player.changeJailTime(3);
	}else if(perform == 6){
	    for(int i = 0; i < Game.PlayerList.length; i++){
		Game.PlayerList[i].changeMoney(-1 * 50);
	    }
	    player.changeMoney(50 * Game.PlayerList.length);
	}else if(perform == 7){
	    player.changeMoney(100);
	}else if(perform == 8){
	    player.changeMoney(20);
	}else if(perform == 9){
	    for(int i =0; i < Game.PlayerList.length;i++){
		Game.PlayerList[i].changeMoney(-1 * 10);
	    }
	    player.changeMoney(10 * Game.PlayerList.length);
	}else if(perform == 10){
	    player.changeMoney(100);
	}else if(perform == 11){
	    player.changeMoney(-1 * 100);
	}else if(perform == 12){
	    player.changeMoney(-1 * 150);
	}else if(perform == 13){
	    player.changeMoney(25);
	}else if(perform == 14){
	    int[]houseCounts = new int[player.getProperties().size()];
	    for(int i = 0; i<player.getProperties().size();i++){
		if(Game.SlotsList[i] instanceof Property){
		    houseCounts[i] = ((Property)(Game.SlotsList[i])).getHouseCount();
		}
	    }
	    int sum = 0;
	    for(int i =0; i<houseCounts.length;i++){
		if(houseCounts[i] == 5){
		    sum += 115;
		}else{
		    sum += houseCounts[i] * 40;
		}
	    }
	    player.changeMoney(-1 * sum);
	}else if(perform == 15){
	    player.changeMoney(10);
	}else if(perform == 16){
	    player.changeMoney(100);
	}else{
	    //obligatory else, but nothing happens :P   
	}
    }

    public void terminate(){
	done = true;
    }
}
