public class Button{
    private int xcor,ycor,height,width;
    public Button(int xcor, int ycor, int w, int h){
	this.xcor = xcor;
	this.ycor = ycor;
	height = h;
	width = w;
    }

    public int getXCor(){
	return xcor;
    }
    public int getYCor(){
	return ycor;
    }
    public int getHeight(){
	return height;
    }
    public int getWidth(){
	return width;
    }
}