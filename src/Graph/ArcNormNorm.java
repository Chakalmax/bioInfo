package Graph;

import DNA.Fragment;
/**
 * 
 * @author Maxime
 * Arc de type Normal -> Normal
 */
public class ArcNormNorm extends Arc {

	public ArcNormNorm(int poid, Fragment frag) {
		super(poid, frag);
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
