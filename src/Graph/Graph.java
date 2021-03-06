package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

import Alignement.Alignement;
import DNA.Fragment;
import Info.Info;

public class Graph{

	private ArrayList<Arc> arcList;
	private ArrayList<Fragment> frag;
	int actualArc;
	int maxArc;
	int imax;
	int actuali;
	
	public Graph(ArrayList<Fragment> frag, ArrayList<GraphThread> graphList){
		this.frag= frag;
		arcList = new ArrayList<Arc>();
		for(GraphThread t : graphList){
			arcList.addAll(t.getArcList());
		}
	}

	public Graph(ArrayList<Fragment> frag,boolean multiThread){
		
		this.frag=frag;
		imax = frag.size();
		maxArc = frag.size()*(frag.size()-1)*4;
		actualArc =0;
		arcList = new ArrayList<Arc>(maxArc);//this may cause problem if frag is empty
		actuali=0;
		if(!multiThread){

			makeAllArc();
		}
		else{
			ArrayList<GraphThread> threadList = new ArrayList<GraphThread>();
			int nbDeThread = Runtime.getRuntime().availableProcessors();
			long debut = System.currentTimeMillis();
			for(int i=0;i<nbDeThread;i++){
				GraphThread gtx = new GraphThread("t"+i, frag, i, nbDeThread);
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
			
			for(GraphThread t : threadList){
				arcList.addAll(t.getArcList());
			}
			System.out.println("Graph is done in ");
			System.out.println("Time to make the graph: " + (System.currentTimeMillis()-debut) +"ms");
			
			
		}
	}
	
	public void makeAllArc(){
		while(actuali<imax){
			short fragment1= frag.get(actuali).getId();
			makeArc(actuali,fragment1);
			//System.gc ();
			//System.runFinalization ();
			System.out.println("Done: " + ((float)actualArc/(float)maxArc)*100 +" %");
			actuali++;
		}
		System.out.println("Graph is done");
	}
	

	public void makeArc(int i,short fragment1){
		short score;
		short score2;
		Alignement al1;
		Alignement al2;
		for(int j=i+1; j<frag.size();j++){
			short fragment2= frag.get(j).getId();

			//arcNormNorm
			al1 = new Alignement(frag.get(j).getNormAsString(),frag.get(i).getNormAsString());
			al1.calcul();
			score= al1.getScore();
			ArcNormNorm arc1_1= new ArcNormNorm(score,fragment1, fragment2);
			ArcNormNorm arc1_2= new ArcNormNorm(score,fragment2, fragment1);

			al2 = new Alignement(frag.get(j).getNormAsString(),frag.get(i).getCIAsString());
			al2.calcul();
			score2= al2.getScore();
			ArcNormCI arc2_1= new ArcNormCI(score2,fragment1,fragment2);
			ArcNormCI arc2_2= new ArcNormCI(score2,fragment2,fragment1);


			ArcCINorm arc3_1= new ArcCINorm(score2,fragment1,fragment2);
			ArcCINorm arc3_2= new ArcCINorm(score2,fragment2,fragment1);
			//Le score inverse/inverse est le m�me que normal/normal.

			ArcCICI arc4_1 = new ArcCICI(score,fragment1,fragment2);
			ArcCICI arc4_2 = new ArcCICI(score,fragment2,fragment1);
			
			arcList.add(arc1_1);
			arcList.add(arc2_1);
			arcList.add(arc3_1);
			arcList.add(arc4_1);
			arcList.add(arc1_2);
			arcList.add(arc2_2);
			arcList.add(arc3_2);
			arcList.add(arc4_2);
			actualArc = actualArc+8;
		}
	}
/**
	private ArrayList<Node> makeNodeList(ArrayList<Fragment> frag){
		ArrayList<Node> nodeL =new ArrayList<Node>();
		for(Fragment fragment: frag){
			nodeL.add(new Node(fragment));
		}
		return nodeL;
	}
	
	public ArrayList<Node> getNodeList() {
		return nodeList;
	}
*/

/**
 * @return the arclist
 */
public ArrayList<Arc> getArcList() {
	return arcList;
}
/**
 * @param arclist the arclist to set
 */
public void setArcList(ArrayList<Arc> arclist) {
	this.arcList = arclist;
}
/**
 * @return the frag
 */
public ArrayList<Fragment> getFrag() {
	return frag;
}
/**
 * @param frag the frag to set
 */
public void setFrag(ArrayList<Fragment> frag) {
	this.frag = frag;
}


	public int getImax() {
		return imax;
	}

	public void setImax(int imax) {
		this.imax = imax;
	}

}


	
