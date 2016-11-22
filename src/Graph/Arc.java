package Graph;

import DNA.Fragment;

public abstract class Arc implements Comparable<Arc> {

	private Fragment start;
	private Fragment end;
	private int poid;

	public Arc(int poid,Fragment start, Fragment end){
		this.poid = poid;
		this.start=start;
		this.end = end;
	}
	@Override
	public int compareTo(Arc arc){
		return (this.poid - arc.getPoid());
	}

	/**
	 * @return the frag
	 */
	public Fragment getEnd() {
		return end;
	}

	/**
	 * @param frag the frag to set
	 */
	public void setEnd(Fragment frag) {
		this.end = frag;
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

	/**
	 * @return the start
	 */
	public Fragment getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Fragment start) {
		this.start = start;
	}
	
	/*
	 * Permet de savoir si le current arc suit bien ce qu'il faut (si le précédent(en param) était
	 *  NormNorm ,alors celui-ci doit être NormNorm ou NormCI.
	 */
	public abstract boolean IsFollowing(Arc arc);
}
