package DNA;

public class Fragment {

	String chaine;
	// on devra p-e garder l'inverser en mémoire (si on doit le calculer trop souvent)
	int id;
	
	public Fragment(String chaine, int id){
		this.chaine = chaine;
		this.id = id;
	}

	public String getChaine() {
		return chaine;
	}

	public void setChaine(String chaine) {
		this.chaine = chaine;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String reverse(){
		String reverse = new StringBuffer(this.chaine).reverse().toString();
		return reverse;
	}
	public String reverse(String str){
		String reverse = new StringBuffer(str).reverse().toString();
		return reverse;
	}
	public String complementary(){
		String comp = "";
		int len = this.chaine.length();
		for(int i=0;i<len-1;i++)
		{
			char caract = chaine.charAt(i);
			switch(caract)
			{
			case ('a'):
				comp = comp+'t';
				break;
			case ('t'):
				comp = comp+'a';
				break;
			case('c'):
				comp = comp+'g';
				break;
			case('g'):
				comp = comp+'t';
				break;
			default:
				comp = comp+'-';
			
			}
		}
		return comp;
	}
	public String compAndReverse()
	{
		String comp = this.complementary();
		String res = reverse(comp);
		return res;
	}
}
