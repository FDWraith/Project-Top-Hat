import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class ChanceMenu extends JFrame implements ActionListener{
    public boolean done;
    private Container pane;
    private Player player;
    private String action;

    public ChanceMenu(Player entry,String action){
	this.player = entry;
	this.action = action;
	this.done = false;
	
	this.setTitle("You have landed on a Chance Card!");
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
	}else if(perform.equals("Advance to Illinois Ave. - If you pass Go, collect $200")){

	}else if(perform.equals("Advance to St. Charles Place - If you pass Go, collect $200")){
	    
	}else if(perform.equals("Advance to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total of ten times the amount thrown.")){
	    
	}else if(perform.equals("Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.")){
	    
	}
    }

    public void terminate(){
	done = true;
	try{
	    TimeUnit.MILLISECONDS.sleep(200);
	}catch(InterruptedException e){
	    //nothing
	}
	this.dispose();
    }
    
}
