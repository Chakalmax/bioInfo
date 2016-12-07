package Graph;

import DNA.Fragment;

public abstract class Arc implements Comparable<Arc> {

	private short start;
	private short end;
	private short poid;

	public Arc(short poid,short start, short end){
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
	public short getEnd() {
		return end;
	}

	/**
	 * @param frag the frag to set
	 */
	public void setEnd(short frag) {
		this.end = frag;
	}

	/**
	 * @return the poid
	 */
	public short getPoid() {
		return this.poid;
	}

	/**
	 * @param poid the poid to set
	 */
	public void setPoid(short poid) {
		this.poid = poid;
	}

	/**
	 * @return the start
	 */
	public short getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(short start) {
		this.start = start;
	}
	
	/*
	 * Permet de savoir si le current arc suit bien ce qu'il faut (si le précédent(en param) était
	 *  NormNorm ,alors celui-ci doit être NormNorm ou NormCI.
	 */
	public abstract boolean IsFollowing(Arc arc);
	@Override
	public String toString(){
		String str = "start: "+ this.start + " end: " + this.end + "poid: " + this.poid + "instance:" + this.getClass();
		return str;
	}
}
