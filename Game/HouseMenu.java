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
	    l1 = new JLabel("Properties you can build houses on:");
	}else if(op.equals("sell")){
	    l1 = new JLabel("All your Properties that have houses");
	}

	
	JComboBox c1 = new JComboBox();
	c1.setItemListener(this);

	JPanel p2 = new JPanel();
	
	JButton b1;
	if(op.equals("buy")){
	    if(propSelected.getHouseCount() == 4){
		b1 = new JButton("Build Hotel");
	    }else{
		b1 = new JButton("Build House");
	    }
	}
	
	JButton b2 = new JButton();
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
		((Property)(Game.SlotsList[i])).getHouseCount();
	    }
	}
    }
    
}
