package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import Alignement.Alignement;
import DNA.Fragment;
import Graph.Arc;
import Graph.ArcCICI;
import Graph.ArcCINorm;
import Graph.ArcNormCI;
import Graph.ArcNormNorm;
import Graph.Graph;
import Graph.GraphExplorer.Explorer;
import Info.Info;
import Tools.Parseur;

public class Assemblage {
	
	public static String getAlignement3(ArrayList<Fragment> fragList, LinkedList<Arc> path)
	{
		Node start = new Node();
		Node earlyStart = start;
		Node current = new Node();
		Node futurStart = new Node();
		Object[] r;
		for (int i =0; i< path.size();i++)
		{
			
			Fragment a= fragList.get(path.get(i).getStart());
			Fragment b= fragList.get(path.get(i).getEnd());
			Alignement al = new Alignement();
			if (path.get(i) instanceof ArcCINorm)
			{
				al = new Alignement(a.getCIAsString(),b.getNormAsString());
			}
			if (path.get(i) instanceof ArcCICI)
			{
				al = new Alignement(a.getCIAsString(),b.getCIAsString());
			}
			if (path.get(i) instanceof ArcNormNorm)
			{
				al = new Alignement(a.getNormAsString(),b.getNormAsString());
			}
			if (path.get(i) instanceof ArcNormCI)
			{
				al = new Alignement(a.getNormAsString(),b.getCIAsString());
			}
			al.calcul();
			al.reverse();
			String fragA =al.getRes()[0];

			String fragB =al.getRes()[1];
			current = start;
			Node down = new Node();
			int startIndex =al.getStartIndex();
			int index = 0;
			int j = 0;
			if (i ==0)
			{
				for (j = 0;j < fragA.length();j++)
				{
					if (j!=0)
					{
						current.setNext(new Node());
						current = current.getNext();
					}
					current.setValue(fragA.charAt(j));
					if (index ==startIndex)
					{
						current.setDown(down);
						down.setValue(fragB.charAt(j));
						futurStart = down;
						
					}
					else if (index > startIndex)
					{
						down = current.getPrevious().getDown();
						down.setNext(new Node());
						down = down.getNext();
						current.setDown(down);
						down.setValue(fragB.charAt(j));
					}
					index++;
					
				}
				down = current.getDown();
				while (j<fragB.length())
				{
					
					down.setNext(new Node());
					down = down.getNext();
					down.setValue(fragB.charAt(j));
					j++;
				}
			}
			start = futurStart;
			current = start;
			if (i!=0)
			{
				for (j = 0;j < fragA.length();j++)
				{
					if (j ==0)
					{
						r= compare3(current,fragA.charAt(j),fragA,fragB,index,startIndex,j);
						current = (Node)r[0];
						fragA=(String)r[1];
						fragB=(String)r[2];
						startIndex=(int) r[3];
					}
					else if (j!=0)
					{
						if (current.getNext() != null)
						{
							current = current.getNext();

							r = compare3(current,fragA.charAt(j),fragA,fragB,index,startIndex,j);
							current = (Node)r[0];
							fragA=(String)r[1];
							fragB=(String)r[2];

							startIndex=(int) r[3];
							
						}
						else
						{
							System.out.println("jamais");
							current.setNext(new Node());
							current = current.getNext();
							current.setValue(fragA.charAt(j));
							r = compare3(current,fragA.charAt(j),fragA,fragB,index,startIndex,j);
							current = (Node)r[0];
							fragA=(String)r[1];
							fragB=(String)r[2];

							startIndex=(int) r[3];
						}
					}
					index++;
					
				}
				down=current.getDown();
				int z = j;
				while (z<fragB.length())
				{
					
					down.setNext(new Node());
					down = down.getNext();
					down.setValue(fragB.charAt(z));
					z++;
				}
				Node y = current;
				while(y.getNext() != null)
				{
					y = y.getNext();
				}
				while(y.getUp() != null && y.getUp().getNext() != null)
				{
					Node k = new Node();
					k.setValue('-');
					y.setNext(k);
					y.getUp().getNext().setDown(k);
					if (z!=j && y.getDown() != null && y.getDown().getNext() != null)
						k.setDown(y.getDown().getNext());
					z--;
					y=y.getNext();
					
				}
				//System.out.println(current.getPrevious().getPrevious().getPrevious().getDown().getNext());
				Node t = current.getDown();
				while(t.getPrevious() !=null)
				{
					t = t.getPrevious();
				}
				futurStart = t;
			
			
			}
			
		}
		repare(earlyStart);
		//print(earlyStart);
		String res = calculRec(earlyStart);
		return res;
		
	}
	public static void repare(Node start)
	{
		Node current = start;
		Node futurStart = new Node();
		boolean b = false;
		while(current != null)
		{
			if (current.getDown() != null && b == false)
			{
				futurStart = current.getDown();
				b = true;
			}
			if (current.getPrevious() != null && current.getPrevious().getUp() != null && current.getPrevious().getUp().getNext() !=null)
			{
				current.getPrevious().getUp().getNext().setDown(current);
			}
			if (current.getNext() != null)
				current = current.getNext();
			else
				break;
		}
		while (current.getUp() != null && current.getUp().getNext() != null)
		{
			Node f = new Node();
			f.setValue('-');
			
			current.setNext(f);
			current.getUp().getNext().setDown(f);
			if (current.getDown() != null && current.getDown().getNext() !=null)
				f.setDown(current.getDown().getNext());
			current = current.getNext();
		}
		if (b != false)
		{
			repare(futurStart);
		}
	}
	
	public static Object[] compare3(Node current,char valeur,String fragA,String fragB, int index,int startIndex, int j)
	{
		if (current.getValue() == valeur)
		{
			if(index == startIndex)
			{
				current.setDown(new Node());
				current.getDown().setValue(fragB.charAt(j));
			}
			else if (index > startIndex)
			{
				current.getPrevious().getDown().setNext(new Node());
				Node temp =current.getPrevious().getDown().getNext();
				temp.setValue(fragB.charAt(j));
				current.setDown(temp);
			}
			
		}
		else if (valeur == '-')
		{
			if (current.getUp() == null)
			{
				Node temp1 = new Node();
				temp1.setValue('-');
				current.getPrevious().setNext(temp1);
				temp1.setNext(current);
				
				temp1.setDown(new Node());
				temp1.getDown().setValue(fragB.charAt(j));
				temp1.getPrevious().getDown().setNext(temp1.getDown());
			}
			else
			{
				Node temp1 = new Node();
				temp1.setValue('-');
				current.getPrevious().setNext(temp1);
				temp1.setNext(current);
				
				temp1.setDown(new Node());
				temp1.getDown().setValue(fragB.charAt(j));
				temp1.getPrevious().getDown().setNext(temp1.getDown());
				Node val = new Node();
				val.setValue('-');
				current.getUp().getPrevious().setNext(val);
				val.setNext(current.getUp());
				val.setDown(temp1);
				rec(current);
				
			}
			current = current.getPrevious();
			if (current.getNext().getValue()!='a' &&current.getNext().getValue()!='t' &&current.getNext().getValue()!='c' &&current.getNext().getValue()!='g' &&current.getNext().getValue()!='-' )
				current.setNext(null);
		}
		else if (current.getValue() == '-')
		{
			
			String m = fragA;
			String[]u = m.split("");
			m="";
			int y=0;
			for(String str: u)
			{
				if(y==j)
					m+="-";
		        m+=str;
				y++;
			}
			fragA=m;
			m = fragB;
			u = m.split("");
			m="";
			y=0;
			for(String str: u)
			{
				if(y==j)
					m+="-";
		        m+=str;
				y++;
			}
			fragB=m;
			if (startIndex >= index)
				startIndex++;
			if(index == startIndex)
			{
				System.out.println("impo");
				current.setDown(new Node());
				current.getDown().setValue(fragB.charAt(j));
			}
			else if (index > startIndex)
			{
				current.getPrevious().getDown().setNext(new Node());
				Node temp =current.getPrevious().getDown().getNext();
				temp.setValue(fragB.charAt(j));
				current.setDown(temp);
			}
			
		}
		else
		{
			current.setValue(fragA.charAt(j));
			if(index == startIndex)
			{
				current.setDown(new Node());
				current.getDown().setValue(fragB.charAt(j));
			}
			else if (index > startIndex)
			{
				current.getPrevious().getDown().setNext(new Node());
				Node temp =current.getPrevious().getDown().getNext();
				temp.setValue(fragB.charAt(j));
				current.setDown(temp);
			}
		}
		Object[] r = new Object[4];
		r[0] = current;
		r[1]= fragA;
		r[2] = fragB;
		r[3] = startIndex;
		
		return r;
	}
	
	public static void print2(Node start)
	{
		Node current = start;
		while (start != null)
		{
			current = start;
			while (start!= null)
			{
				System.out.print(start.getValue());
				start = start.getDown();
			}
			start=current.getNext();
			while (current != null &&current.getNext() == null)
				{
					current = current.getDown();
				}
			if (current != null)
				start = current.getNext();
			else 
				start = null;
			
			System.out.println();
		}
		
	}
	public static void print(Node start)
	{
		printt(start,0);
		
	}
	public static void printt(Node start, int i)
	{
		Node temp = start;
		int j=0;
		while (j<i)
		{
			System.out.print("-");
			j++;
		}
		System.out.print(start.getValue());
		if (start.getDown() == null && start.getNext() == null)
		{
			System.out.println();
			return;
		}
		
		while(start.getNext()!= null)
		{
			
			start = start.getNext();
			System.out.print(start.getValue());
		}
		while(temp.getDown() == null)
		{
			if (temp.getNext() != null)
			{
				temp = temp.getNext();
				i++;
			}
			else
			{
				System.out.println();
				return;
			}
		}
		start =temp.getDown();
		System.out.println();
		printt(start,i);
		
		
	}
	public static void rec(Node current)
	{
		current =current.getUp();
		while (current.getUp() != null)
		{
			Node temp1 = current.getUp();
			Node Val = new Node();
			Val.setValue('-');
			if (temp1.getPrevious() != null)
			{
				Node temp2 = temp1.getPrevious();
				temp2.setNext(Val);
			}
			Val.setNext(temp1);
			if (temp1.getDown().getPrevious() != null)
				Val.setDown(temp1.getDown().getPrevious());
			
			current = temp1;
		}
	}
	public static Object[] compare(Node current, char value)
	{
		boolean b = false;
		Node dow = new Node();
		if (current.getValue() == 'r')
			current.setValue(value);
		else if (current.getValue()== value)
		{
			
		}
		else if(value == '-')
		{
			if (current.getUp() == null)
			{
				Node down = new Node();
				down.setValue('-');
				if(current.getPrevious() != null)
				{
					Node temp3 = current.getPrevious();
					temp3.setNext(down);
				}	
				down.setDown(current.getDown());
				down.setNext(current);
				current = current.getPrevious();
			}
			if (current.getUp() != null)
			{
				Node temp1 = current.getUp();
				
				Node Val = new Node();
				Node down = new Node();
				Val.setValue('-');
				down.setValue('-');
				if (temp1.getPrevious() != null)
				{
					Node temp2 = temp1.getPrevious();
					temp2.setNext(Val);
				}
				Val.setNext(temp1);
				Val.setDown(down);
				if(current.getPrevious() != null)
				{
					Node temp3 = current.getPrevious();
					temp3.setNext(down);
				}	
				if (current.getDown() != null)
					down.setDown(current.getDown());
				down.setNext(current);
				rec(temp1);
				current = current.getPrevious();
			}

		}
		else if (current.getValue() == '-')
		{
			Node temp1 = new Node();
			temp1.setValue('-');
			if (current.getPrevious() != null && current.getPrevious().getDown() != null)
			{
				current.getPrevious().getDown().setNext(temp1);
			}
			if (current.getDown() != null)
			{
				current.setDown(temp1);
			}
			dow = temp1;
				b= true;
			
			
			
			
		}
		Object[]  t= new Object[3];
		t[0] = current;
		t[1] = b;
		t[2] = dow;
		return t;
	}
	public static String calculRec(Node current)
	{
		String res = calcul(current, "");
	    
	    return res;
	}
	public static String calcul(Node current,String res)
	{
		int tab[] = new int[4];
		int i =0;
		while (current != null)
		{
			tab[0]=0;
			tab[1]=0;
			tab[2]=0;
			tab[3]=0;
			Node temp = current;
			while(current !=null)
			{
				if (current.getValue()== 'a')
				{
					tab[0]++;
				}
				if (current.getValue()== 'c')
				{
					tab[1]++;
				}
				if (current.getValue()== 'g')
				{
					tab[2]++;
				}
				if (current.getValue()== 't')
				{
					tab[3]++;
				}
				current = current.getDown();
				
			}
			if (tab[0] > tab[1] && tab[0] >tab[2] &&tab[0] > tab[3])
				res+="a";
			else if (tab[1] > tab[0] && tab[1] > tab[2] && tab[1] > tab[3])
				res+="c";
			else if (tab[2] > tab[0] && tab[2] > tab[1] && tab[2] > tab[3])
				res+="g";
			else if (tab[3] > tab[1] && tab[3] > tab[2] && tab[3] > tab[0])
				res+="t";
			
			while (temp != null &&temp.getNext() == null)
			{
				i++;
				temp = temp.getDown();
			}
			if (temp != null)
				current = temp.getNext();
			else 
				current = null;
		}

	return res;
	}
}
