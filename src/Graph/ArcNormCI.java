package Graph;

import DNA.Fragment;
/**
 * 
 * @author Maxime
 * Arc de type Norm -> Complémentaire & inversé
 */
public class ArcNormCI extends Arc {

	public ArcNormCI(short poid,short start, short end) {
		super(poid, start,end);
	}

	
	public String ArcType(){
		return "NormCI";
	}

	
	public boolean IsFollowing(Arc arc) {
		if (arc instanceof ArcNormNorm || arc instanceof ArcCINorm)
			return true;
		else
			return false;
	}
}
