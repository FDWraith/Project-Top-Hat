import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


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
    javax.swing.Timer timer = new javax.swing.Timer(1000,this);


    private JLabel pr1 = new JLabel ();
    private JLabel pr2 = new JLabel ();
    private JLabel pr3 = new JLabel ();
    private JLabel pr4 = new JLabel ();
    private JLabel pr5 = new JLabel ();

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


	
	Image img = icon.getImage();
	Image newimg = img.getScaledInstance(80,80, java.awt.Image.SCALE_SMOOTH);
	ImageIcon newicon = new ImageIcon(newimg);
	timg = new JLabel(newicon);

	
	propertydisplay.addItemListener(this);

	p.add(timg);
	p.add(propertydisplay);
	p.add(moneyLabel);

	p.add(pr1);
	p.add(pr2);
	p.add(pr3);
	p.add(pr4);
	p.add(pr5);
	p.add(mortgage);
	p.add(jcards);
	
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
	}
    }

    public void itemStateChanged(ItemEvent e){
	if(e.getStateChange() == ItemEvent.SELECTED){
	    Slot pobject;
	    pobject = Game.SlotsList[x.getProperties().get(propertydisplay.getSelectedIndex())];
	    if(pobject instanceof Property){
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
