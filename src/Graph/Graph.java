package Graph;

import java.util.ArrayList;
import java.util.HashMap;

import Alignement.Alignement;
import DNA.Fragment;

public class Graph {

	//private ArrayList<Node> nodeList;
	private ArrayList<Arc> arcList;
	private ArrayList<Fragment> frag;
	
	public Graph(ArrayList<Fragment> frag){
		this.frag=frag;
		int maxArc = frag.size()*(frag.size()-1)*4;
		int actualArc =0;
		arcList = new ArrayList<Arc>(maxArc);//this may cause problem if frag is empty
		short score;
		short score2;
		Alignement al1;
		Alignement al2;
		for(int i=0; i<frag.size();i++){
			short fragment1= frag.get(i).getId();
			for(int j=i+1; j<frag.size();j++){
				short fragment2= frag.get(j).getId();
				//for test...
				//score = 0;
				//score2 = 1;
				//calculer les 4 alignements et en faire 4 arc qui vers frag2.
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
				//Le score inverse/inverse est le même que normal/normal.

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
			//System.gc ();
			//System.runFinalization ();
			System.out.println("Done: " + ((float)actualArc/(float)maxArc)*100 +" %");
		}
		System.out.println("Graph is done");
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
	
}
