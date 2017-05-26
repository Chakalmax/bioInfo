package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

import Alignement.Alignement;
import DNA.*;
import Graph.Arc;
import Graph.Graph;
import Graph.GraphThread;
import Graph.GraphExplorer.Explorer;
import Info.Info;
import Tools.Parseur;
public class main {

	public static void main(String[] args) {
		/*if (args.length<5)
		{

			System.out.println("Arguments manquant");
		}
		else
		{*/
		String chemin = args[0];
		String out1 ="";
		String out2 ="";
		for (int ind =0;ind < args.length;ind++)
		{
			if ((args[ind].equals("-out") || args[ind].equals("--out")) && args[ind+1] !=null)
				out1 = args[ind+1];
			if ((args[ind].equals("-out-ic") || args[ind].equals("--out-ic")) && args[ind+1] !=null)
				out2 = args[ind+1];
			
				
		}
		String num = chemin;
		boolean numero = false;
		String numberString = "";
		int number = 1;
		num =num.substring(0,num.length()-6);
		while(!numero && num.length()!=0)
		{
			if (num.charAt(num.length()-1) == 'n')
				numero = true;
			else
			{
				numberString += num.charAt(num.length()-1);
				num=num.substring(0,num.length()-1);
			}
		}
		try {
			new StringBuilder(numberString).reverse().toString();
			number = Integer.parseInt(numberString);
		}
		catch(Exception e)
		{}

		if (out1.equals(""))
			out1 = "cible"+Integer.toString(number)+".fasta";
		if (out2.equals(""))
			out2 = "cibleIC"+Integer.toString(number)+".fasta";
		Info.getInstance();
		ArrayList<Fragment> fragList = new ArrayList<Fragment>();
		Parseur parseur = new Parseur();
		//fragList = parseur.readFile("C://Users//Ewen//Desktop//Cours informatique//Master 1//BioInfo//Projet//Collections//Collections//10000//collection1.fasta");
		fragList = parseur.readFile(chemin);
		System.out.println("nombre de fragments :" +fragList.size());
		/*
		for(Fragment frag: fragList){
			System.out.println(frag);
		}*/
		
		//System.out.println(fragList.get(0));
		Graph graph = new Graph(fragList,true);

		//System.out.println(4*fragList.size()*(fragList.size()-1));
		//System.out.println(graph.getArcList().size());

		Explorer explo = new Explorer();
		LinkedList<Arc> path = explo.Greedy(graph);
		//System.out.println(path);
		String res = Assemblage.getAlignement3(fragList,path);
		
		System.out.println("Ecriture dans les fichiers "+ out1+" et " +out2);
		Parseur parse = new Parseur();
		//parse.writeFile("output.fasta",res,1);
		parse.writeFile(out1,res,number);
		
		Fragment e = new Fragment(res,(short)2000);
	    res = e.getCIAsString();
	   
		//parse.writeFile("outputInverse.fasta",res,1);
	    parse.writeFile(out2,res,number);

		System.out.println("Fin");
		}
		
	
	


}