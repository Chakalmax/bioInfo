package Graph;

import DNA.Fragment;
/**
 * 
 * @author Maxime
 * Arc de type Complémentaire & inversé -> Complémentaire & inversé
 */
public class ArcCICI extends Arc {

	public ArcCICI(int poid, Fragment frag) {
		super(poid, frag);
	}
	
	public String ArcType(){
		return "CICI";
	}

	
	public boolean IsFollowing(Arc arc) {
		if (arc instanceof ArcCINorm || arc instanceof ArcCICI)
			return true;
		else
			return false;
	}
}
