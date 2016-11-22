package Graph.GraphExplorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import DNA.Fragment;
import Graph.*;

public class Explorer {

	public static void main(String[] args) {
		
		Arc arc1 = new ArcNormNorm(57, null, null);
		Arc arc2 = new ArcNormNorm(3, null, null);
		
		ArrayList<Arc> arcList = new ArrayList<Arc>();
		arcList.add(arc1);
		arcList.add(arc2);
		//arcList = sortByWeight(arcList);
	}
	// n nombre de noeud.
	public LinkedList<Arc> Greedy(Graph graph){
		ArrayList<Fragment> fragList= graph.getFrag();
		int n = fragList.size();
		boolean []in= new boolean[n];
		boolean []out= new boolean[n];
		ArrayList<Set> setList = new ArrayList<Set>();
		for(int i=0;i<n;i++){
			in[i]=false;
			out[i]=false;
			fragList.get(i).setId((short)i);
			setList.add(new Set(graph.getFrag().get(i)));
			// refaire le set de telle manière que:
			// on y accède pas avec un indice de chiasse...
			// on ait accès au premier et dernier elem de ce set
			// meilleur moyen : HashMap
		}
		ArrayList<Arc> arcList=graph.getArcList();
		arcList = sortByWeight(arcList);
		int nbIter=n-1;
		int endreferenced=0;
		for(Arc arc: arcList){
			int f=arc.getStart().getId();
			int g=arc.getEnd().getId();
			if(in[g]==false && out[f]==false && setList.get(f)!=setList.get(g)// egalité des sets foireuse blblblblb
					&&arc.IsFollowing(setList.get(g).getArcset().getFirst()) // verifie si on l'arc qu'on veut ajouter peut être avant celui du set g
					&&setList.get(f).getArcset().getLast().IsFollowing(arc)){ //verifie si l'arc qu'on veut ajouter peut être après celui du set f
				endreferenced=f;// pour le retour (eventuel)
				
				in[g]=true;
				setList.get(g).getArcset().addLast(arc);
				out[f]=true;
				setList.get(f).getArcset().addFirst(arc);
				
				
				Set tmp= setList.get(f);
				
				
				//union
				setList.get(f).union(setList.get(g));
				setList.get(g).union(tmp);
				
				nbIter--;
				if(nbIter==0){
					break;
				}
				
				
				
				
			}
		}
		return setList.get(endreferenced).getArcset();
	}

	private static ArrayList<Arc> sortByWeight(ArrayList<Arc> arcList) {
		
		Collections.sort(arcList,Collections.reverseOrder());		
		return arcList;
	}
	
	public class Set{
		LinkedList<Fragment> set = new LinkedList<Fragment>();
		LinkedList<Arc> arcset=new LinkedList<Arc>();
		public Set(Fragment frag){
			set.add(frag);
		}
		
		public LinkedList<Fragment> getSet(){
			return this.set;
		}
		
		public LinkedList<Arc> getArcset() {
			return arcset;
		}

		public void setArcset(LinkedList<Arc> arcset) {
			this.arcset = arcset;
		}
		/*
		 * Unifie deux set, (SUR SES DEUX PARAMS)
		 */
		public void union(Set set){
			for(Fragment frag: set.getSet()){
				this.set.add(frag);
			}
			for(Arc arc: set.getArcset()){
				this.arcset.add(arc);
			}
		}
		public boolean contain(Fragment frag){
			for(Fragment fragment: this.set){
				if(fragment==frag){
					return true;
				}
			}
			return false;
		}
		
	}
}

