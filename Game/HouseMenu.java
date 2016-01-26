import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class HouseMenu extends JFrame implements ActionListener,ItemListener{
    private Container pane;
    private Player player;
    public boolean done;
    private int houseCounts;
    private Property propSelected;
    private Property[]monopoly;
    
    
    public HouseMenu(Player entry,String op){
	this.player = entry;
	this.done = false;
	this.houseCounts = houseCountOP();

	this.setTitle("Player "+(player.getIndex()+1)+" welcome to Property Management!");
        this.setSize(400,400);
	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));

	JPanel p1 = new JPanel();

	JLabel l1;
	if(op.equals("buy")){
	    monopoly = updateMonopoly();
	    l1 = new JLabel("Properties you can build houses on:");
	}else if(op.equals("sell")){
	    l1 = new JLabel("All your Properties that have houses");
	}

	
	JComboBox c1;
	if(op.equals("buy")){
	    c1 = new JComboBox(monoplizedProperties());
	    propSelected = monopoly[0];
	}
	c1.setItemListener(this);

	JPanel p2 = new JPanel();
	
	JButton b1;
	if(op.equals("buy")){
	    if(propSelected.getHouseCount() == 4){
		b1 = new JButton("Build Hotel");
	    }else{
		b1 = new JButton("Build House");
	    }
	}else if(op.equals("sell")){
	    b1 = new JButton("Sell House");
	}
	
	JButton b2 = new JButton("Quit");
    }
    
    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
    }

    public void itemStateChanged(ItemEvent e){
	if(e.getStateChange() == ItemEvent.SELECTED){
	    
	}
    }

    public int houseCountOP(){
	int count = 0;
	for(int i =0; i<Game.SlotsList.length;i++){
	    if(Game.SlotsList[i] instanceof Property){
		count += ((Property)(Game.SlotsList[i])).getHouseCount();
	    }
	}
	return count;
    }

    public Property[] updateMonopoly(){
        ArrayList<Property>props = new ArrayList<Property>();
	for(int i =0; i<player.getProperties().size();i++){
	    if(player.getProperties().get(i).getMonopolized()){
		props.add(player.getProperties().get(i));
	    }
	}
	return (Property[])(prop.toArray());
    }
    
}
