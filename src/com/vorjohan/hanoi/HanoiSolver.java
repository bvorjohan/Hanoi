package com.vorjohan.hanoi;

import java.util.EmptyStackException;
import java.util.Observable;

public class HanoiSolver extends Observable{
	private Tower left;
	private Tower middle;
	private Tower right;
	private int numDiscs;
	
	public HanoiSolver(int numDiscs){
		this.left=new Tower(Position.LEFT);
		this.middle=new Tower(Position.MIDDLE);
		this.right=new Tower(Position.RIGHT);
		this.numDiscs=numDiscs;
		
		
	}
	
	public int discs(){
		return this.numDiscs;
	}
	
	
	public Tower getTower(Position position){
		switch(position){
		case LEFT: return this.left;
		case MIDDLE: return this.middle;
		case RIGHT: return this.right;
		default: return this.left;
		
		}
		
		
	}
	
	public String toString(){
		StringBuilder string = new StringBuilder("[");
		string.append(this.left.toString());
		string.append(this.middle.toString());
		string.append(this.right.toString());
		string.append("[");
		return string.toString();

		
	}
	
	
	
	private void move(Tower source, Tower destination){
		Disc disc=source.pop();
		destination.push(disc);		
		
		this.setChanged();
		this.notifyObservers(destination.position());
		
	}
	
	private void solveTowers(int currentDiscs, Tower startPole, Tower tempPole, Tower endPole){
		if (currentDiscs==1){
			this.move(startPole, endPole);
		}
		else if(currentDiscs>1){
			this.solveTowers(currentDiscs-1, startPole, endPole, tempPole);
			this.solveTowers(1, startPole, tempPole, endPole);
			this.solveTowers(currentDiscs-1, tempPole, startPole, endPole);
		}
		
	}
	
	public void solve(){
		this.solveTowers(this.numDiscs,this.left,this.middle,this.right);
	}
	
	private int getTopWidth(Tower tower){
		try{
			return tower.peek().getWidth();
		}catch (EmptyStackException e) {
			return 999999;
		}
	
	}
	
	private Tower otherTower(Tower tow1, Tower tow2){
		if((tow1==this.left && tow2==this.middle) || (tow1==this.middle && tow2==this.left)){
			return this.right;
		}
		else if((tow1==this.left && tow2==this.right) || (tow1==this.right && tow2==this.left)){
			return this.middle;
		}
		else{
			return this.left;
		}
		
	}
	
	public void reset(int widthDiff){
		Tower start;
		if ((getTopWidth(this.left)<getTopWidth(this.middle))&&(getTopWidth(this.left)<getTopWidth(this.right))){
				start=this.left;			
		}
		else if (getTopWidth(this.middle)<getTopWidth(this.right)){
			start=this.middle;
		}
		else{
			start=this.right;
		}
		
		int runningWidth=start.peek().getWidth();
		
		for(int i=0;i<this.numDiscs;i++){
			runningWidth+=widthDiff;
			if(getTopWidth(this.left)==runningWidth){
				solveTowers(i+1,start,otherTower(start,this.left),this.left);
				start=this.left;
			}
			else if(getTopWidth(this.middle)==runningWidth){
				solveTowers(i+1,start,otherTower(start,this.middle),this.middle);
				start=this.middle;
			}
			else if(getTopWidth(this.right)==runningWidth){
				solveTowers(i+1,start,otherTower(start,this.right),this.right);
				start=this.right;
			}
		}
		solveTowers(this.numDiscs,start,otherTower(start,this.left),this.left);
	}
	
	
	
}
