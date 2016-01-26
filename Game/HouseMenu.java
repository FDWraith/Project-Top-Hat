import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class HouseMenu extends JFrame implements ActionListener,ItemListener{
    private Container pane;
    private Player player;
    public boolean done;
    private int houseCounts;
    private int hotelCounts;
    private Property propSelected;
    private Property[]monopoly;
    private String[]monoPropNames;
    private String operation;

    //JFrame things
    private JComboBox c1;
    
    //properties of any properties
    private JLabel pp1 = new JLabel();
    private JLabel pp2 = new JLabel();
    private JLabel pp3 = new JLabel();
    private JLabel pp4 = new JLabel();
    private JLabel pp5 = new JLabel();
    
    public HouseMenu(Player entry,String op){
	this.player = entry;
	this.done = false;
	houseCountOP();
	this.operation = op;
	this.monopoly = updateMonopoly();
	this.monoPropNames = getMonoPropNames();
	
	this.setTitle("Player "+(player.getIndex()+1)+" welcome to Property Management!");
        this.setSize(400,400);
	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));

	if(operation.equals("buy") && monopoly.length == 0){
	    JOptionPane.showMessageDialog(null,"You do not have a monopoly, and thereby cannot build houses.","Warning",JOptionPane.INFORMATION_MESSAGE);
	    this.terminate();
	}


	//From this point onward, we can assume that player either has a monooply or has houses to sell.
	JPanel p1 = new JPanel();
	p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
	p1.setAlignmentX(Component.CENTER_ALIGNMENT);

	JLabel l1;
	if(op.equals("buy")){ 
	    l1 = new JLabel("Properties you can build houses on:");
	}else if(op.equals("sell")){
	    l1 = new JLabel("All your Properties that have houses");
	}else{
	    l1 = new JLabel();
	}
	
	if(op.equals("buy")){
	    c1 = new JComboBox(monoPropNames);
	    propSelected = monopoly[0];
	}else if(op.equals("sell")){
	    //fill in later...
	}else{
	    c1 = new JComboBox();
	}
	c1.addItemListener(this);

	p1.add(l1);
	p1.add(c1);
	
	JPanel p2 = new JPanel();
	p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
	p2.setAlignmentX(Component.CENTER_ALIGNMENT);

	p2.add(pp1);
	p2.add(pp2);
	p2.add(pp3);
	p2.add(pp4);
	p2.add(pp5);
	
	JPanel p3 = new JPanel();
	p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
	p3.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	JButton b1;
	if(op.equals("buy")){
	    if(propSelected.getHouseCount() == 4){
		b1 = new JButton("Build Hotel");
	    }else{
		b1 = new JButton("Build House");
	    }
	    b1.setActionCommand("Build");
	}else if(op.equals("sell")){
	    b1 = new JButton("Sell House");
	    b1.setActionCommand("Sell");
	}else{
	    b1 = new JButton();
	}
	b1.addActionListener(this);
	
	JButton b2 = new JButton("Quit");
	b2.addActionListener(this);
	b2.setActionCommand("Quit"); 

	p3.add(b1);
	p3.add(b2);

	JLabel l2 = new JLabel("Money: "+player.getMoney());
	l2.setAlignmentX(Component.RIGHT_ALIGNMENT);

	pane.add(p1);
	pane.add(p2);
	pane.add(l2);
	pane.add(p3);	
	
    }
    
    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("Quit")){
	    this.terminate();
	}else if(event.equals("Build")){
	    Property choice = monopoly[c1.getSelectedIndex()];
	    if(choice.getHouseCount()==5){
		JOptionPane.showMessageDialog(null,"You cannot build any more things on this property.","Max Capacity",JOptionPane.INFORMATION_MESSAGE);
		this.terminate();
	    }else if(choice.getHouseCount()==4){
		if(hotelCounts >= 8){
		    JOptionPane.showMessageDialog(null,"You cannot build a hotel on this property because the Bank has run out of Hotels","Out of Stock",JOptionPane.INFORMATION_MESSAGE);
		    this.terminate();
		}else{
		    int res = JOptionPane.showConfirmDialog(null,"Are you sure you want to build a hotel on "+choice.getName()+"?","Confirm your choice",JOptionPane.YES_NO_OPTION);
		    if(res == JOptionPane.YES_OPTION){
			choice.buyHotel();
			this.terminate();
		    }else{
			//do nothing.
		    }
		}
	    }else{
		if(houseCounts >= 32){
		    JOptionPane.showMessageDialog(null,"You cannot build a house on this property because the Bank has run out of Houses","Out of Stock",JOptionPane.INFORMATION_MESSAGE);
		    this.terminate();
		}else{
		    int res = JOptionPane.showConfirmDialog(null,"Are you sure you want to build a house on "+choice.getName()+"?","Confirm your choice",JOptionPane.YES_NO_OPTION);
		    if(res == JOptionPane.YES_OPTION){
			choice.buyHouse();
			this.terminate();
		    }else{
			//do nothing.
		    }
		}
	    }
	}else if(event.equals("Sell")){
	    //things happen.
	}
    }

    public void itemStateChanged(ItemEvent e){
	if(e.getStateChange() == ItemEvent.SELECTED){
	    if(operation.equals("buy")){
		Property choice = monopoly[c1.getSelectedIndex()];
		pp1.setText("House Price: "+choice.getHousePrice());
		pp2.setText("Hotel Price: "+choice.getHotelPrice());
		pp3.setText("Current House Count: "+choice.getHouseCount());
		pp4.setText("Current Rent: "+choice.getRentPrice());	
		if(choice.getHouseCount() == 5){
		    pp5.setText("");
		}else{
		    pp5.setText("New Rent: "+choice.housePrices[choice.getHouseCount()+1]);
		}
	    }else if(operation.equals("sell")){
		
	    }else{
		return;
	    }
	}
    }

    public void houseCountOP(){
	int count1 = 0;
	int count2 = 0;
	for(int i =0; i<Game.SlotsList.length;i++){
	    if(Game.SlotsList[i] instanceof Property){
		if ( ((Property)(Game.SlotsList[i])).getHouseCount() == 5){
		    count2 += 1;
		}else{
		    count1 +=  ((Property)(Game.SlotsList[i])).getHouseCount();
		}
	    }
	}
	this.houseCounts = count1;
	this.hotelCounts = count2;
    }

    public Property[] updateMonopoly(){
        ArrayList<Property>props = new ArrayList<Property>();
	for(int i =0; i<player.getProperties().size();i++){
	    if(((Property)(Game.SlotsList[(player.getProperties().get(i))])).getMonopolized()){
		props.add((Property)(Game.SlotsList[(player.getProperties().get(i))]));
	    }
	}
        Property[]exit = new Property[props.size()];
	return props.toArray(exit);
    }

    public String[] getMonoPropNames(){
	String[]exit = new String[monopoly.length];
	for(int i=0; i<monopoly.length;i++){
	    exit[i] = monopoly[i].getName();
	}
	return exit;
    }
    
    public void terminate(){
	this.done = true;
    }
}
