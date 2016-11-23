package Graph;

import DNA.Fragment;
/**
 * 
 * @author Maxime
 * Arc de type Normal -> Normal
 */
public class ArcNormNorm extends Arc {

	public ArcNormNorm(short poid,short start, short end) {
		super(poid, start,end);
	}
	
	public String ArcType(){
		return "NormNorm";
	}

	
	public boolean IsFollowing(Arc arc) {
		if (arc instanceof ArcCINorm || arc instanceof ArcNormNorm)
			return true;
		else
			return false;
	}
	

}
