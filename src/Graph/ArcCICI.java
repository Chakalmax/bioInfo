package Graph;

import DNA.Fragment;
/**
 * 
 * @author Maxime
 * Arc de type Complémentaire & inversé -> Complémentaire & inversé
 */
public class ArcCICI extends Arc {

	public ArcCICI(short poid,short start, short end) {
		super(poid, start,end);
	}
	
	public String ArcType(){
		return "CICI";
	}

	
	public boolean IsFollowing(Arc arc) {
		if (arc instanceof ArcNormCI || arc instanceof ArcCICI)
			return true;
		else
			return false;
	}
}
