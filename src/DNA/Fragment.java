package DNA;

public class Fragment implements Comparable<Fragment>{

	Nucleotide[] chaine;
	Nucleotide[] reverse;
	// on devra p-e garder l'inverser en mémoire (si on doit le calculer trop souvent)
	short id;
	
	public Fragment(String chaine, short id){
		int len = chaine.length();
		Nucleotide[] tmp = new Nucleotide[len];
		for(int i=0;i<len;i++)
		{
			tmp[i]= new Nucleotide(chaine.charAt(i));
		}
		this.chaine = tmp;
		this.id = id;
		this.reverse = compAndReverse(this.chaine);
	}
	
	public Fragment(Nucleotide[] chaine,short id){
		this.chaine = chaine;
		this.id = id;
	}

	public Nucleotide[] getChaine() {
		return chaine;
	}

	public void setChaine(Nucleotide[] chaine) {
		this.chaine = chaine;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}
	

	public String reverse(String str){
		String reverse = new StringBuffer(str).reverse().toString();
		return reverse;
	}

	public Nucleotide[] reverse(Nucleotide[] chaine){
		Nucleotide[] reverse = new Nucleotide[chaine.length];
		for(int i=0;i<reverse.length;i++)
		{
			reverse[i]= chaine[(chaine.length-i)-1];
		}
		return reverse;
	}
	
	public Nucleotide[] complementary(Nucleotide[] chaine){
		
		int len = chaine.length;
		Nucleotide[] comp = new Nucleotide[len];
		for(int i=0;i<len;i++)
		{
			comp[i]= chaine[i].getComplementary();;
		}
		return comp;
	}
	
	public Nucleotide[] compAndReverse(Nucleotide[] chaine)
	{
		Nucleotide[] comp = complementary(chaine);
		Nucleotide[] res = reverse(comp);
		int len = res.length;
		return res;
	}

	@Override
	public int compareTo(Fragment arg0) {
		return (this.id-arg0.getId());
	}

	
	public String toString(){
		String str ="Chaine(Norm)" + this.id + " : ";
		for(int i=0;i<this.chaine.length;i++){
			str = str+ chaine[i].getAsChar();
		}
		System.out.println();
		str = str + " Inversé : ";
		for(int i=0;i<this.reverse.length;i++){
			str = str+ reverse[i].getAsChar();
		}
		return str;
	}

	public String getNormAsString(){
		String str = "";
		for(int i=0;i<this.chaine.length;i++)
			str = str + chaine[i].getAsChar();
		return str;
	}
	
	public String getCIAsString(){
		String str = "";
		for(int i=0;i<this.reverse.length;i++)
			str = str + reverse[i].getAsChar();
		return str;
	}
	/**
	 * @return the reverse
	 */
	public Nucleotide[] getReverse() {
		return reverse;
	}

	/**
	 * @param reverse the reverse to set
	 */
	public void setReverse(Nucleotide[] reverse) {
		this.reverse = reverse;
	}
}
