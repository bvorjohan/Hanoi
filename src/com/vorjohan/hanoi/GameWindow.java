package com.vorjohan.hanoi;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import CS2114.Button;
import CS2114.Shape;
import CS2114.Window;
import CS2114.WindowSide;

public class GameWindow implements Observer{

	private HanoiSolver game;
	private Shape left;
	private Shape middle;
	private Shape right;
	private Window window;	//how is the imported Window different than java's?
							//^same with Shape
	private final int DISK_GAP=5;
	private final int DISK_HEIGHT=5;
	
	private Button solveButton;
	
	

	
	
	





	public GameWindow(HanoiSolver game) {
		this.game=game;
		this.game.addObserver(this);
		this.window=new Window("Tower of Hanoi");
		this.left=new Shape(100, 1, new Color(1,1,1));
		this.middle=new Shape(100,100,new Color(1,1,1));
		this.right=new Shape(1,100,new Color(1,1,1));
		for (int i=0;i<this.game.discs();i++){
			this.game.getTower(Position.LEFT).push(new Disc(5*(this.game.discs()+1-i)));
			moveDisc(Position.LEFT);
		}
		
		this.window.addShape(this.left);
		this.window.addShape(this.middle);
		this.window.addShape(this.right);
		
		this.solveButton = new Button("Solve");
		this.window.addButton(solveButton, WindowSide.SOUTH);
		this.solveButton.onClick(this);
		
		
	}









	public void update(Observable o, Object arg) {
		if(arg.getClass()==Position.class){
			moveDisc((Position)arg);
			
		}
		sleep();
		
	}
	
	private void sleep() {
	    try {
	        Thread.sleep(500);
	    }
	    catch (Exception e) {
	    }
	}


	public void clickedSolve(Button button) {
	    button.disable();
	    new Thread() {
	        public void run() {
	            game.solve();
	        }
	    }.start();
	}
	
	private void moveDisc(Position position){
		Disc currentDisc=this.game.getTower(position).peek();
		Shape currentPole;
		switch (position){
		case LEFT:
			currentPole=this.left;
			break;
		case MIDDLE:
			currentPole=this.middle;
			break;
		case RIGHT:
			currentPole=this.right;
			break;
		default:
			currentPole=this.left;
			break;
		}
		int x=currentDisc.getX();
		int y=currentDisc.getY();
		//int width=currentDisc.getWidth();
		int height = currentDisc.getHeight();
		//int size = this.game.getTower(position).size();
		
		
		currentDisc.moveTo(x, y+height+this.DISK_GAP);
	}
	


}
