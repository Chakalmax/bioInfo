package Graph;

import DNA.Fragment;
/**
 * 
 * @author Maxime
 * Arc de type Norm -> Compl�mentaire & invers�
 */
public class ArcNormCI extends Arc {

	public ArcNormCI(int poid,Fragment start, Fragment end) {
		super(poid, start,end);
	}

	
	public String ArcType(){
		return "NormCI";
	}

	
	public boolean IsFollowing(Arc arc) {
		if (arc instanceof ArcCINorm || arc instanceof ArcCINorm)
			return true;
		else
			return false;
	}
}
