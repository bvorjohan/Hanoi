package com.vorjohan.hanoi;
import CS2114.*;

import java.util.*;

import stack.*;

import java.util.EmptyStackException;
import java.util.Random;


import java.awt.Color;
import java.math.*;
public class LinkedStack<T> implements StackInterface<T>{

	private int size=0;
	
	private Node<T> topNode=null;
	
	public LinkedStack(){
		//hi!
		System.out.println("LinkedStack Constructor...");
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
		Node<T> temp=this.topNode;
		StringBuilder string=new StringBuilder();
		string.append("[");
		while(temp != null){
			string.append(temp.data.toString());
			temp=temp.next;
		}
		string.append("]");
		return string.toString();
		
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
		Node<T> newNode= new Node<T>(topNode,arg0);
		this.topNode=newNode;
		System.out.println("Pushing item...");
		size++;
	}
	
	public int size(){
		System.out.println("Returning Size...");
		return this.size;
	}
	
	private class Node<T>{
		private Node<T> next;
		private T data;
		
		public Node(T data){
			this.next=null;
			this.data=data;
		}
		
		public Node(Node<T> nextNode, T data){
			this.next=nextNode;
			this.data=data;
		}
		
		public Node<T> getNextNode(){
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
