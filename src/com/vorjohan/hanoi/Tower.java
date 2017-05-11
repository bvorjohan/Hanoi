package com.vorjohan.hanoi;

public class Tower extends LinkedStack<Disc>{

	private Position position;
	
	public Tower(Position position){
		super();
		this.position=position;
	}
	
	public Position position(){
		return this.position;
	}
	
	@Override public void push(Disc disc){
		System.out.println("Performing Tower Push Operation...");
		if ((this.size()>0)&&(this.peek().compareTo(disc)<0)){
			throw new IllegalStateException();
		}
		super.push(disc);
		
		
		
	}
	
	public void clickedDisc(){
		System.out.println("Clicked on a Disc! Implemented in Tower");
	}
}
