import java.util.*;
import java.util.concurrent.TimeUnit;

public class Chance extends Slot{
    private String[]cards = {"Advance to Go (Collect $200)","Advance to Illinois Ave. - If you pass Go, collect $200", "Advance to St. Charles Place - If you pass Go, collect $200", "Advance to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total of ten times the amount thrown.", "Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", "Bank pays you dividend of $50", "Get out of Jail Free - This card may be kept until needed, or traded/sold", "Go Back 3 Spaces", "Go to Jail - Go directly to Jail - Do not pass Go, do not collect $200", "Make general repairs on all your property - For each house pay $25 - For each hotel $100", "Pay poor tax of $15", "Take a trip to Reading Railroad", "Take a walk on the Boardwalk - Advance token to BoardWalk", "You have been elected Chairman of the Board - Pay each player $50", "Your building loan matures - Collect $150", "You have won a crossword competition - Collect $100" };

    public Chance(int ID){
	super(ID);
    }

    //Mandatory Method
    public void doAction(Player name){
	Random r = new Random();
	int x = r.nextInt(cards.length);
        ChanceMenu one = new ChanceMenu(name,cards[x]);
	one.setVisible(true);
	
	do{
	    try{
		TimeUnit.SECONDS.sleep(1);
	    }catch(InterruptedException e){
		//do nothing
	    }
	}while(one.done == false);
    }

}
