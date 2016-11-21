package Graph;

import java.util.ArrayList;
import java.util.HashMap;

import DNA.Fragment;

public class Graph {

	//private ArrayList<Node> nodeList;
	private ArrayList<Arc> arcList;
	private ArrayList<Fragment> frag;
	
	public Graph(ArrayList<Fragment> frag){
		this.frag=frag;
		arcList = new ArrayList<Arc>();
		
		for(int i=0; i<frag.size();i++){
			Fragment fragment1= frag.get(i);
			for(int j=i; j<frag.size();j++){
				Fragment fragment2= frag.get(j);
				//calculer les 4 alignements et en faire 4 arc qui vers frag2.
				//arcNormNorm
				//int score=Alignement.getScore(fragment1,fragment2);
				int score= 1;
				ArcNormNorm arc1_1= new ArcNormNorm(score,fragment1, fragment2);
				ArcNormNorm arc1_2= new ArcNormNorm(score,fragment2, fragment1);
				//int score = Alignement.getScore(fragment1.compAndReverse(),fragment2);
				score = 2;
				ArcNormCI arc2_1= new ArcNormCI(score,fragment1,fragment2);
				ArcNormCI arc2_2= new ArcNormCI(score,fragment2,fragment1);
				//int score = Alignement.getScore(fragment1,fragment2.compAndReverse());
				score =3;
				ArcCINorm arc3_1= new ArcCINorm(score,fragment1,fragment2);
				ArcCINorm arc3_2= new ArcCINorm(score,fragment2,fragment1);
				//Le score inverse/inverse est le même que normal/normal mais bon on testera pour être sur.
				score=4;
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
				
			}
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
	
}
