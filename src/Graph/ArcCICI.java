package Graph;

import DNA.Fragment;
/**
 * 
 * @author Maxime
 * Arc de type Compl�mentaire & invers� -> Compl�mentaire & invers�
 */
public class ArcCICI extends Arc {

	public ArcCICI(int poid,Fragment start, Fragment end) {
		super(poid, start,end);
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
