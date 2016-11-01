package Graph;

import DNA.Fragment;

public abstract class Arc {

	private Fragment frag;
	private int poid;

	public Arc(int poid, Fragment frag){
		this.poid = poid;
		this.frag = frag;
	}

	/**
	 * @return the frag
	 */
	public Fragment getFrag() {
		return frag;
	}

	/**
	 * @param frag the frag to set
	 */
	public void setFrag(Fragment frag) {
		this.frag = frag;
	}

	/**
	 * @return the poid
	 */
	public int getPoid() {
		return poid;
	}

	/**
	 * @param poid the poid to set
	 */
	public void setPoid(int poid) {
		this.poid = poid;
	}
	
	/*
	 * Permet de savoir si le current arc suit bien ce qu'il faut (si le précédent(en param) était
	 *  NormNorm ,alors celui-ci doit être NormNorm ou NormCI.
	 */
	public abstract boolean IsFollowing(Arc arc);
}
