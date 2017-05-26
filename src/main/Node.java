package main;

public class Node {

	private Node next;
	private Node down;
	private Node up;
	private Node previous;
	private char value;
	public Node()
	{
		
	}
	public char getValue()
	{
		return value;
	}
	public void setValue(char c)
	{
		this.value = c;
	}
	public Node getNext()
	{
		return next;
	}
	public Node getDown()
	{
		return down;
	}
	public void setNext(Node next)
	{
		this.next = next;
		next.setPrevious(this);
	}
	public void setDown(Node down)
	{
		this.down = down;
		down.setUp(this);
	}
	public Node getPrevious()
	{
		return previous;
	}
	public Node getUp()
	{
		return up;
	}
	public void setPrevious(Node previous)
	{
		this.previous = previous;
	}
	public void setUp(Node up)
	{
		this.up = up;
	}
}
