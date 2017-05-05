package com.vorjohan.hanoi;
import CS2114.*;

import java.util.*;

import stack.*;

import java.util.EmptyStackException;
import java.util.Random;


import java.awt.Color;
import java.math.*;
public class LinkedStack<T> implements StackInterface<T>{

	private int size;
	//private LinkedList<T> elements= new LinkedList<T>();
	private Node<T> topNode=null;
	
	public LinkedStack(){
		//hi!
	}

	public void clear() {
		// TODO Auto-generated method stub
		this.topNode=null;
		this.size=0;
	}

	public boolean isEmpty() {
		if (this.size()==0){
			return true;
		}
		return false;
	}
	
	public String toString(){
		super.toString();
		return "TO BE FILLED IN";
		
	}

	public T peek() {
		if(this.size<=0){
			throw new EmptyStackException();
		}
		return this.topNode.getData();
	}

	public T pop() {
		
		if(size<=0){
			throw new EmptyStackException();
		}
		
		Node<T> temp = this.topNode;
		this.topNode=temp.getNextNode();
		size--;
		return temp.getData();
	}

	public void push(T arg0) {
		Node<T> newNode= new Node(topNode,arg0);
		this.topNode=newNode;
		size++;
	}
	
	public int size(){
		return this.size();
	}
	
	private class Node<T>{
		private Node next;
		private T data;
		
		public Node(T data){
			this.next=null;
			this.data=data;
		}
		
		public Node(Node nextNode, T data){
			this.next=nextNode;
			this.data=data;
		}
		
		public Node getNextNode(){
			return this.next;
		}
		
		public T getData(){
			return this.data;
		}
		
		public void setNextNode(Node<T> newNext){
			this.next=newNext;
		}
		
	}
	
}
