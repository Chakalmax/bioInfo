package main;

import java.util.ArrayList;
import java.util.LinkedList;

import DNA.*;
import Graph.Arc;
import Graph.Graph;
import Graph.GraphExplorer.Explorer;
import Info.Info;
import Tools.Parseur;
public class main {

	public static void main(String[] args) {
		//Nucleotide nuc1 = new Nucleotide('a');
		//System.out.println(nuc1);
		//String str1 = "aaattttggggcccc";
		//System.out.println(str1.length());
		Info.getInstance();
		Fragment frag1 = new Fragment("aaaattttggggcccc", (short)0);
		System.out.println(frag1);
		ArrayList<Fragment> fragList = new ArrayList<Fragment>();
		Parseur parseur = new Parseur();
		fragList = parseur.readFile("C://Users//Maxime//workspace//BioInfo//Bioinfo//Collections//Collections//10000//collection1.fasta");
		System.out.println("Correctement open");
		System.out.println("nb de frag :" +fragList.size());
		/*
		for(Fragment frag: fragList){
			System.out.println(frag);
		}*/
		System.out.println(fragList.get(0));
		Graph graph = new Graph(fragList);
		System.out.println(graph.getArcList().size());
		/*
		for(int i=0;i<graph.getArcList().size();i++)
			System.out.println(graph.getArcList().get(i));
			*/
		Explorer explo = new Explorer();
		LinkedList<Arc> path = explo.Greedy(graph);
		System.out.println(path);
		System.out.println("End");
		

	}

}
