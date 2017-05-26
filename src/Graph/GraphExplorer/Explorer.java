package Graph.GraphExplorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import DNA.Fragment;
import Graph.*;

public class Explorer {

	public Explorer(){}

	// n nombre de noeud.
	public LinkedList<Arc> Greedy(Graph graph){
		ArrayList<Short> frag_id = new ArrayList<Short>();;
		int n = graph.getFrag().size();
		boolean []in= new boolean[n];
		boolean []out= new boolean[n];
		// could be replace by an hashmap?
		short [] keySet = new short[n];
		Set [] valueSet = new Set[n];
		for(int i=0;i<n;i++){
			in[i]=false;
			out[i]=false;
			frag_id.add(graph.getFrag().get(i).getId());
			keySet[i] = graph.getFrag().get(i).getId();
			valueSet[i]=new Set();
			// refaire le set de telle manière que:
			// on y accède pas avec un indice de chiasse...
			// on ait accès au premier et dernier elem de ce set
			// meilleur moyen : HashMap
		}
		ArrayList<Arc> arcList=graph.getArcList();
		arcList = sortByWeight(arcList);
		int nbIter=n-1;
		short endreferenced=0;
		for(Arc arc: arcList){
			short f=arc.getStart();
			short g=arc.getEnd();
			if(in[g]==false && out[f]==false && keySet[f]!=keySet[g]){
				short f2 = keySet[f];
				short g2 = keySet[g];
				Set sf2= valueSet[f2];
				Set sg2 = valueSet[g2];
				if((sf2.isEmtpy()&& sg2.isEmtpy())||(sf2.isEmtpy()&&!sg2.isEmtpy()&&sg2.getSet().getFirst().IsFollowing(arc))
						||(sg2.isEmtpy() && !sf2.isEmtpy() && arc.IsFollowing(sf2.getSet().getLast())
								||(!sg2.isEmtpy()&&!sf2.isEmtpy()&&arc.IsFollowing(sf2.getSet().getLast())&&sg2.getSet().getFirst().IsFollowing(arc)))){
				in[g]=true;
				out[f]=true;
				
				valueSet[f2].getSet().addLast(arc);
				valueSet[f2].union(valueSet[g2]);
				for(int i=0;i<keySet.length;i++)
					if(keySet[i] == g2)
						keySet[i]=keySet[f];
				valueSet[g2] = null;
				endreferenced = f2;
				nbIter--;
				if(nbIter==0){
					break;
				}
				}
				
				
				
			}
		}

		return valueSet[endreferenced].getSet();
	}

	private static ArrayList<Arc> sortByWeight(ArrayList<Arc> arcList) {
		
		Collections.sort(arcList,Collections.reverseOrder());		
		return arcList;
	}
	
	public class Set{
		LinkedList<Arc> set=new LinkedList<Arc>();
		public Set(){
		}
		
		
		public LinkedList<Arc> getSet() {
			return this.set;
		}

		public void setSet(LinkedList<Arc> arcset) {
			this.set = arcset;
		}
		/*
		 * Unifie deux set (effet de bord!)
		 */
		public void union(Set set){
			for(Arc arc: set.getSet()){
				this.set.add(arc);
			}
		}
		public boolean isEmtpy(){
			return this.set.isEmpty();
		}
		public boolean contain(Arc arc){
			for(Arc arc0: this.set){
				if(arc0==arc){
					return true;
				}
			}
			return false;
		}
		
	}
}

