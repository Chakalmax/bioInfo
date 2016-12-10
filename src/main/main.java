package main;

import java.util.ArrayList;
import java.util.LinkedList;

import DNA.*;
import Graph.Arc;
import Graph.Graph;
import Graph.GraphThread;
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
		Graph graph = new Graph(fragList,true);

		System.out.println(4*fragList.size()*(fragList.size()-1));
		System.out.println(graph.getArcList().size());

		Explorer explo = new Explorer();
		LinkedList<Arc> path = explo.Greedy(graph);
		System.out.println(path);
		System.out.println("longueur du path: "+ path.size());
		System.out.println("End");
		
		//parseur.writeFile("output.fasta", "aaaaaaaaaattttttttttggggggggggatgatgatgcaaaaaaaaaattttttttttggggggggggatgatgatgcaaaaaaaaaattttttttttggggggggggatgatgatgcaaaaaaaaaattttttttttggggggggggatgatgatgcaaaaaaaaaattttttttttggggggggggatgatgatgcttaaccgg", 27);
/*
		GraphThread gt = new GraphThread("t1", fragList, 0, 2);
		gt.start();
		GraphThread gt2 = new GraphThread("t2", fragList, 1, 2);
		gt2.start();
		try {
			gt.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(gt.getArcList().size());
*/		
		/*
		ArrayList<GraphThread> threadList = new ArrayList<GraphThread>();
		int nbDeThread = Runtime.getRuntime().availableProcessors();
		long debut = System.currentTimeMillis();
		for(int i=0;i<nbDeThread;i++){
			GraphThread gtx = new GraphThread("t"+i, fragList, i, nbDeThread);
			threadList.add(gtx);
		}
		for(GraphThread t: threadList){
			t.start();
		}
		for(GraphThread t: threadList){
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Graph graph = new Graph(fragList,threadList);
		System.out.println(4*fragList.size()*(fragList.size()-1));
		System.out.println(graph.getArcList().size());
		System.out.println("Time: " + (System.currentTimeMillis()-debut) +"ms");
		Explorer explo = new Explorer();
		LinkedList<Arc> path = explo.Greedy(graph);
		System.out.println(path);
		System.out.println("longueur du path: "+ path.size());
		System.out.println("End");
		*/
	}
	


}
