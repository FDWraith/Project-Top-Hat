import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class ChanceMenu extends JFrame implements ActionListener{
    public boolean done;
    private Container pane;
    private Player player;
    private String action;

    public ChanceMenu(Player entry,String action){
	this.player = entry;
	this.action = action;
	this.done = false;
	
	this.setTitle("Player "+(player.getIndex()+1)+", you have landed on a Chance Card!");
	this.setSize(400,200);
	//this.setLocation(300,300);
	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));

	//JPanel p1 = new JPanel();
	//p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
	//p1.setAlignmentX(Component.CENTER_ALIGNMENT);

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
	    executeAction(this.action);
	    terminate();
	}
	terminate();
    }

    public void executeAction(String perform){
	if(perform.equals("Advance to Go (Collect $200)")){
	    player.changeLocation(0);
	    player.changeMoney(200);
	    player.setPhase(1);
	    Game.currentP-=1;
	}else if(perform.equals("Advance to Illinois Ave. - If you pass Go, collect $200")){
	    if(player.getLocation()>24){
		player.changeMoney(200);
		player.changeLocation(24);
		player.setPhase(1);
		Game.currentP-=1;
	    }else{
		player.changeLocation(24);
		player.setPhase(1);
		Game.currentP-=1;
	    }
	}else if(perform.equals("Advance to St. Charles Place - If you pass Go, collect $200")){
	    if(player.getLocation()>11){
		player.changeMoney(200);
		player.setPhase(1);
		Game.currentP-=1;
	    }else{
		player.changeLocation(11);
		player.setPhase(1);
		Game.currentP-=1;
	    }
	}else if(perform.equals("Advance to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total of ten times the amount thrown.")){
	    if(player.getLocation()<=12){
		player.changeLocation(12);
	    }else if(player.getLocation()<=28){
		player.changeLocation(28);
	    }else{
		player.changeLocation(12);
	    }
	    player.setPhase(1);
	    Game.currentP-=1;
	    if(Game.SlotsList[player.getLocation()].getOwned()){
		Random r = new Random();
		int sum = 10 * (r.nextInt(6)+1);
		player.changeMoney(-1*sum);
		Game.SlotsList[player.getLocation()].getOwner().changeMoney(sum);		
	    }else{
		this.dispose();
		UtilityMenu one = new UtilityMenu(player,(Utility)(Game.SlotsList[player.getLocation()]));
		one.setVisible(true);
		/*do{
		    try{
			TimeUnit.SECONDS.sleep(1);
		    }catch(InterruptedException e){
			//nothing here
		    }
		}while(one.done == false);
		one.dispose();*/
	    }
	}else if(perform.equals("Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.")){
	    if(player.getLocation()<=5){
	        player.changeLocation(5);
	    }else if(player.getLocation()<=15){
		player.changeLocation(15);
	    }else if(player.getLocation()<=25){
		player.changeLocation(25);
	    }else{
		player.changeLocation(5);
	    }
	    player.setPhase(1);
	    Game.currentP -= 1;
	    if(Game.SlotsList[player.getLocation()].getOwned()){
		Random r = new Random();
		int sum = 10 * (r.nextInt(6)+1);
		player.changeMoney(-1*sum);
		Game.SlotsList[player.getLocation()].getOwner().changeMoney(sum);		
	    }else{
		this.dispose();
		RailroadMenu one = new RailroadMenu(player,(Railroad)(Game.SlotsList[player.getLocation()]));
		one.setVisible(true);
		/*do{
		    try{
			TimeUnit.SECONDS.sleep(1);
		    }catch(InterruptedException e){
			//nothing
		    }
		}while(one.done == false);
		one.dispose();*/
		//while(one.done == false){
		    //wait?
		//}
		//one.dispose();
	    }
	}else if(perform.equals("Bank pays you dividend of $50")){
	    player.changeMoney(50);
	}else if(perform.equals("Get out of Jail Free - This card may be kept until needed, or traded/sold")){
	    player.changeJailCards(1); 
	}else if(perform.equals("Go Back 3 Spaces")){
	    player.changeLocation(player.getLocation()-3);
	    player.setPhase(1);
	    Game.currentP-=1;
	}else if(perform.equals("Go to Jail - Go directly to Jail - Do not pass Go, do not collect $200")){
	    player.changeLocation(10);
	    player.setXY(50,750);
	    player.changeJailTime(3);
	}else if(perform.equals("Make general repairs on all your property - For each house pay $25 - For each hotel $100")){
	    int count = 0;
	    ArrayList<Integer>PropertyIDs = player.getProperties();
	    for(int i =0; i<PropertyIDs.size();i++){
		if(Game.SlotsList[PropertyIDs.get(i)] instanceof Property){
		    int n = ((Property)(Game.SlotsList[PropertyIDs.get(i)])).getHouseCount();
		    if(n==5){
			n=8;
		    }
		    count += n;   
		}
	    }
	    player.changeMoney(-1 * 25 * count);
	}else if(perform.equals("Pay poor tax of $15")){
	    player.changeMoney(-1 * 15);    
	}else if(perform.equals("Take a trip to Reading Railroad")){
	    player.changeLocation(5);
	    player.setPhase(1);
	    Game.currentP-=1;
	}else if(perform.equals("Take a walk on the Boardwalk - Advance token to BoardWalk")){
	    player.changeLocation(39);
	    player.setPhase(1);
	    Game.currentP-=1;
	}else if(perform.equals("Your building loan matures - Collect $150")){
	    player.changeMoney(150);
	}else if(perform.equals("You have won a crossword competition - Collect $100")){
	    player.changeMoney(100);
	}else{
	    //Obligatory else, but does nothing :P
	}
    }

    public void terminate(){
	done = true;
	/*try{
	    TimeUnit.MILLISECONDS.sleep(200);
	}catch(InterruptedException e){
	    //nothing
	}
	this.dispose();*/
    }
    
}
