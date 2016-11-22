package Graph;

import DNA.Fragment;
/**
 * 
 * @author Maxime
 * Arc de type Compl�mentaire & invers� -> Normal
 */
public class ArcCINorm extends Arc {

	public ArcCINorm(int poid,Fragment start, Fragment end) {
		super(poid, start,end);
	}
	
	public String ArcType(){
		return "CINorm";
	}

	
	public boolean IsFollowing(Arc arc) {
		if (arc instanceof ArcNormCI || arc instanceof ArcNormNorm)
			return true;
		else
			return false;
	}

}
