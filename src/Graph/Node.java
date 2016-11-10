package Graph;

import java.util.ArrayList;

import DNA.Fragment;

public class Node {

	Fragment DNAfrag;
	ArrayList<Arc> arcSortant;
	
	public Node(Fragment DNAfrag){
		this.DNAfrag=DNAfrag;		
	}
	
	public Node(Fragment DNAfrag,ArrayList<Arc>arc){
		this.DNAfrag=DNAfrag;
		this.arcSortant=arc;
	}

	/**
	 * @return the arcSortant
	 */
	public ArrayList<Arc> getArcSortant() {
		return arcSortant;
	}

	/**
	 * @param arcSortant the arcSortant to set
	 */
	public void setArcSortant(ArrayList<Arc> arcSortant) {
		this.arcSortant = arcSortant;
	}
		
	
}
