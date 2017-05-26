package Alignement;

import java.util.ArrayList;
import java.util.Collections;
public class Alignement{
	public Alignement(){
		
	}
	public Alignement(String s, String t)
	{
		this.s =s;
		this.t =t;
	}
	public boolean[][] test;
	protected int StartIndex = 0;
	public String[] res;
	public String s ="";
	public String t ="";
	protected int[][] a;
	protected short sim;
	protected int n= 0;
	protected int m=0;
	public boolean isInclude()
	{
		if (getRes()[1].length() <= getRes()[0].length())
			return true;
		else
			return false;
	}
	public int getStartIndex()
	{
		return StartIndex;
	}
	public short getScore(){
		return sim;
	}
	protected final int g =-2;
	public int p(char a,char b)
	{
		if (a ==b)
		{
			return 1;
		}
		return -1;
	}
	public int[][] prog_dynamic(String s,String t){
		int m= s.length();
		int n = t.length();
		int [][] a = new int[m+1][n+1];
		boolean [][] test = new boolean[m+1][n+1];
		for (int i=0;i<=m;i++)
		{
			a[i][0] = 0;
			test[i][0] = true;
		}
		for (int j=0;j<=n;j++)
		{
			a[0][j] = 0;
			test[0][j]= false;
		}
		test[0][0]=true;
		boolean start = true;
		for (int i =1;i<=m;i++)
		{
			for (int j =1;j<=n;j++)
			{
				a[i][j] = Math.max(a[i-1][j]+g, Math.max(a[i-1][j-1]+p(s.charAt(i-1),t.charAt(j-1)),a[i][j-1]+g));
				
				if (a[i][j] == a[i-1][j]+g)
					{
						test[i][j] = test[i-1][j];
					}
				else if (a[i-1][j-1]+p(s.charAt(i-1),t.charAt(j-1)) == a[i][j])
						test[i][j] = test[i-1][j-1];
				else if (a[i][j] == a[i][j-1]+g)
						test[i][j] = test[i][j-1];
				
			}
		}
		/*
		for (int i=0;i < a.length;i++)
		{
			for (int j=0; j< a[0].length;j++)
			{
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}*/
		this.test =test;
		return a;
	}
	public void calcul()
	{
		this.a =prog_dynamic(this.s,this.t);
		//this.sim = a[s.length()][t.length()];
		short max = -5000;
		this.n = 0;
		this.m = 0;
		for (int i=1; i<t.length();i++)
		{
			if (a[s.length()][i] >= max && test[s.length()][i] == true)
			{
				
				this.n = i;
				this.m = s.length();
				max = (short)a[s.length()][i];
				//System.out.println("ici"+ s.length()  + i);
			}
		}
		for (int j=1; j<=s.length();j++)
		{
			if (a[j][t.length()] > max && test[j][t.length()]== true)
			{
				// System.out.println("here");
				this.m = j;
				this.n = t.length();
				max = (short)a[j][t.length()];
			}
		}
		
		this.sim = max;
		/*for( int i=0;i <a.length;i++)
		{
			for (int j = 0;j<a[0].length;j++)
			{
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}*/
	}
	public void reverse()
	{
		ArrayList<Integer> c = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		c.add(s.length());
		b.add(t.length());
		ArrayList<ArrayList> resM = new ArrayList<ArrayList>();
		ArrayList<ArrayList> resN = new ArrayList<ArrayList>();
		reverseRecc(this.m,this.n,c,b,resM,resN);
		/*for (int i =0;i<resM.size();i++)
		{
			Collections.reverse(resM.get(i));
			Collections.reverse(resN.get(i));
		}
		System.out.println(resM.get(0) + " " +resN.get(0));
		System.out.println(resM.get(1) + " " +resN.get(1));
		System.out.println(resM.get(2) + " " +resN.get(2));*/
		for(int i =0; i<resM.size();i++)
		{
			
			Collections.reverse(resM.get(i));
			Collections.reverse(resN.get(i));
			String[] res = new String[2];
			res = strAlignement(resM.get(i),resN.get(i));
			for (int u = ((Integer)resN.get(0).get(resN.get(0).size()-1)); u< t.length();u++)
			{
				res[1] += t.charAt(u);
			}
			/*
			System.out.println(res[0]);
			System.out.println(res[1]);
			System.out.println("___________________________________");
			*/
			this.setRes(totalRes(res));
			int u = 0;
			while (getRes()[1].charAt((getRes()[1].length()-1)-u) == '-')
			{
				u++;
			}
			if (u !=0)
				this.res[0] =this.res[0].substring(0,this.res[0].length()-1-u)+'g'+this.res[0].substring(this.res[0].length()-1-u+1);
		}
	}
	public int ModifySize=0;
	public void reverseRecc(int m, int n,ArrayList cheminM, ArrayList cheminN,ArrayList<ArrayList> resM, ArrayList<ArrayList> resN)
	{
		boolean test = false;
		if (m == 0 || n == 0)
		{
			ArrayList[] res= new ArrayList[2];
			res[0] = cheminM;
			res[1] = cheminN;
			
			resM.add(res[0]);
			resN.add(res[1]);
			res[1].set(0,(Integer)(res[1].get(1))+1);
			test = true;
			this.StartIndex = m;
		}
		if (test == false){
		if (m>0 && a[m-1][n] +g == a[m][n])
		{
			ArrayList<Integer> cheminM2 = new ArrayList<Integer> (cheminM);
			ArrayList<Integer> cheminN2 = new ArrayList<Integer> (cheminN);
			cheminM2.add(m-1);
			cheminN2.add(n);
			ModifySize--;
			
			reverseRecc(m-1,n,cheminM2,cheminN2,resM,resN);
		}
		else if (m>0 && n > 0&&a[m-1][n-1] + p(s.charAt(m-1),t.charAt(n-1)) == a[m][n])
		{
			ArrayList<Integer> cheminM2 = new ArrayList<Integer> (cheminM);
			ArrayList<Integer> cheminN2 = new ArrayList<Integer> (cheminN);
			cheminM2.add(m-1);
			cheminN2.add(n-1);
			reverseRecc(m-1,n-1,cheminM2,cheminN2,resM,resN);
		}
		else if (n>0 && a[m][n-1] +g ==a[m][n])
		{
			ArrayList<Integer> cheminM2 = new ArrayList<Integer> (cheminM);
			ArrayList<Integer> cheminN2 = new ArrayList<Integer> (cheminN);
			cheminM2.add(m);
			cheminN2.add(n-1);
			ModifySize++;
			reverseRecc(m,n-1,cheminM2,cheminN2,resM,resN);
			
		}
	}
	}
	public String[] strAlignement(ArrayList<Integer> resM, ArrayList<Integer> resN)
	{
		String resS = "";
		String resT = "";
		for (int i =1; i<resM.size();i++)
		{
			if (resM.get(i) - resM.get(i-1) ==1){
				resS += this.s.charAt(resM.get(i)-1);
			}
			else
			{
				resS+= "-";
			}
		}
		for (int i =1; i<resN.size();i++)
		{
			if (resN.get(i) - resN.get(i-1) ==1){
				resT += this.t.charAt(resN.get(i)-1);
			}
			else
			{
				resT+= "-";
			}
		}
		String[] res = new String[2];
		
		res[0] = resS;
		res[1] = resT;
		return res;
	}
	public String[] totalRes(String[] res)
	{
		String[] totRes = new String[2];
		totRes[0]="";
		totRes[1]="";
		for(int i =0; i< this.StartIndex;i++)
		{
			totRes[0]= totRes[0]+s.charAt(i);
			totRes[1] = totRes[1]+"-";
		}
		totRes[0]+=res[0];
		totRes[1]+=res[1];
		for (int i =this.StartIndex-this.ModifySize+res[0].length();i<s.length();i++)
		{
			totRes[0]+=s.charAt(i);
			totRes[1]+= "-";
		}
		return totRes;
	}
	public String[] getRes() {
		return res;
	}
	public void setRes(String[] res) {
		this.res = res;
	}
}
