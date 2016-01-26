import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.util.concurrent.TimeUnit;

public class Displayer extends JFrame implements ActionListener, ItemListener{
    private int playernum;
    private Player x;
    
    private JPanel p = new JPanel();
    private JLabel moneyLabel;
    private ImageIcon icon;
    private JLabel timg;
    private JLabel jcards;
    private JComboBox propertydisplay;
    private JButton mortgage;
    private JButton buyHouse,sellHouse,sellProperty;
    javax.swing.Timer timer = new javax.swing.Timer(1000,this);


    private JLabel pr1 = new JLabel ();
    private JLabel pr2 = new JLabel ();
    private JLabel pr3 = new JLabel ();
    private JLabel pr4 = new JLabel ();
    private JLabel pr5 = new JLabel ();

    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JPanel p5 = new JPanel();

    public Displayer(Player x, int num){
	super("Player " + num + " Information");
	playernum = num;
	this.x = x;

	setSize(400,400);
	setResizable(false);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	moneyLabel = new JLabel("Money Owned:" +x.getMoney()+"");
	jcards = new JLabel("Get out of Jail Cards Owned: " +x.getJailCards());
	
	propertydisplay = new JComboBox(getPropertyName());
	icon = new ImageIcon(Game.wdr + "images/tokens/monopoly_token_"+ x.getToken() + ".png");
	mortgage = new JButton("Manage Mortgage");
	mortgage.addActionListener(this);
	mortgage.setActionCommand("mortgage");
	
        buyHouse = new JButton("Buy a House");
	buyHouse.addActionListener(this);
	buyHouse.setActionCommand("buyHouse");

	sellHouse = new JButton("Sell a House");
	sellHouse.addActionListener(this);
	sellHouse.setActionCommand("sellHouse");

	sellProperty = new JButton("Sell this Property");
	sellProperty.addActionListener(this);
	sellProperty.setActionCommand("sellProperty");
	
	Image img = icon.getImage();
	Image newimg = img.getScaledInstance(80,80, java.awt.Image.SCALE_SMOOTH);
	ImageIcon newicon = new ImageIcon(newimg);
	timg = new JLabel(newicon);

	
	propertydisplay.addItemListener(this);

	p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
	p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
	p2.setAlignmentX(Component.RIGHT_ALIGNMENT);
	p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
	p3.setAlignmentX(Component.LEFT_ALIGNMENT);

	p2.add(timg);
	p3.add(moneyLabel);
	p3.add(jcards);

	p1.add(p2);
	p1.add(p3);

	p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
	p4.setAlignmentY(Component.CENTER_ALIGNMENT);

	p4.add(propertydisplay);
	p4.add(pr1);
	p4.add(pr2);
	p4.add(pr3);
	p4.add(pr4);
	p4.add(pr5);

	p5.setLayout(new BoxLayout(p5,BoxLayout.X_AXIS));	

	p5.add(mortgage);
	p5.add(buyHouse);
	p5.add(sellHouse);
	
	p.add(p1);
	p.add(p4);
	p.add(p5);

	add(p);
	setVisible(true);

	timer.setActionCommand("timing");
	timer.setInitialDelay(500);
	timer.start();
    }

    public String[] getPropertyName(){
	String [] save = new String[x.getProperties().size()];
	for(int i = 0; i < x.getProperties().size(); i++){
	    save[i] = (Game.SlotsList[x.getProperties().get(i)]).getName();
	}
	return save;
    }

    public void updateProperty(){
	propertydisplay.removeAllItems();
	for(String x: getPropertyName()){
	    propertydisplay.addItem(x);
	}
    }
    
    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	moneyLabel.setText("Money Owned:" + x.getMoney()+"");
	jcards.setText("Get out of Jail Cards Owned: " +x.getJailCards());	
	if(event.equals("mortgage") && (Game.SlotsList[x.getProperties().get(propertydisplay.getSelectedIndex())] instanceof Property)){
	    Slot pobject = Game.SlotsList[x.getProperties().get(propertydisplay.getSelectedIndex())];
	    if(pobject.getMortgage()){
		if(JOptionPane.showConfirmDialog(null,"Would you like to unmortgage your property for $" + pobject.getMortgageV()*1.10, "Mortgage Choice",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
		    pobject.endMortgage();
		}else{
		    
		}
	    }else{
		if(JOptionPane.showConfirmDialog(null,"Would you like to mortgage your property for $" + pobject.getMortgageV(), "Mortgage Choice",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
		    pobject.mortgageProperty();
		}else{
		    
		}
		
	    }
	    //WIP Code!
	}else if(event.equals("buyHouse")){
	    HouseMenu one = new HouseMenu(x,"buy");//pass along the player and what sort of thing the player wants to be doing
		one.setVisible(true);
	    do{
		try{
		    TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException a){
		    //nothing
		}
	    }while(one.done == false);
	    one.dispose();
	}else if(event.equals("sellHouse")){
	    HouseMenu one = new HouseMenu(x,"sell");
	    one.setVisible(true);
	    do{
		try{
		    TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException a){
		    //nothing
		}
	    }while(one.done == false);
	    one.dispose();
	}else{
	    Property choice =(Property)(Game.SlotsList[x.getProperties().get(propertydisplay.getSelectedIndex())]);
	    int res = JOptionPane.showConfirmDialog(null,"Are you sure you want to sell this property, along with any houses and hotels?","Confirm your choice",JOptionPane.YES_NO_OPTION);
	    if(res == JOptionPane.YES_OPTION){
		choice.sellProperty();
	    }else{
		//nothing
	    }
	}
    }

    public void itemStateChanged(ItemEvent e){
	if(e.getStateChange() == ItemEvent.SELECTED){
	    p5.add(mortgage);
	    Slot pobject;
	    pobject = Game.SlotsList[x.getProperties().get(propertydisplay.getSelectedIndex())];
	    if(pobject instanceof Property){
		((Property)(pobject)).monopolizeProperty(); 
		pr1.setText("Buy Price:" + pobject.getBuyPrice());
		pr2.setText("Rent Price:" + pobject.getRentPrice());
		pr3.setText("House Price:" + pobject.getHousePrice());
		pr4.setText("Hotel Price:" + pobject.getHotelPrice());
		pr5.setText("Mortgaged:" + pobject.getMortgage());
	    }else if(pobject instanceof Railroad){
		pobject.adjustRentPrice();
		pr1.setText("Buy Price:"+pobject.getBuyPrice());
		pr2.setText("Rent Price:"+pobject.getRentPrice());
		pr3.setText("");
		pr4.setText("");
		pr5.setText("");
		    
	    }else{
		pobject.adjustDiceFactor();
		pr1.setText("Buy Price:" + pobject.getBuyPrice());
		pr2.setText("Dice Factor:" + pobject.getDiceFactor());
		pr3.setText("");
		pr4.setText("");
		pr5.setText("");
	    }
		
	}
    }
}
