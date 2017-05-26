package Graph;

import java.util.ArrayList;

import Alignement.Alignement;
import DNA.Fragment;
import Info.Info;

public class GraphThread extends Thread {
	
	private ArrayList<Arc> arcList;
	private ArrayList<Fragment> frag;
	int actualArc;
	int maxArc;
	int nbDeThread;
	int imax;
	int initialI;
	
	
	
	
	   private Thread t;
	   private String threadName;
	   
	   
	public GraphThread(ArrayList<Fragment> frag){
		this.frag=frag;
	}
	public GraphThread(String threadName,ArrayList<Fragment> frag,int initialI,int nbDeThread){
		this.frag=frag;
		this.initialI=initialI;
		this.nbDeThread=nbDeThread;
		this.threadName=threadName;
	}

	@Override
	public void run() {
		//System.out.println("Running " +  threadName );
		imax = frag.size();
		maxArc = frag.size()*(frag.size()-1)*4;
		arcList = new ArrayList<Arc>(maxArc);//this may cause problem if frag is empty
		makeAllArc();
			//System.out.println("Thread " +  threadName + " exiting.");
		
	}
	
	
	public void makeAllArc(){
		for(int i=initialI;i<frag.size();i=i+nbDeThread){
			short fragment1= frag.get(i).getId();
			makeArc(i,fragment1);
			if(initialI==0)
			System.out.println("Done: " + ((float)Info.getActualArc()/(float)maxArc)*100 +" %");
		}

	}
	

	public void makeArc(int i,short fragment1){
		short score;
		short score2;
		short score3;
		short score4;
		short score5;
		short score6;
		short score7;
		short score8;
		Alignement al1;
		Alignement al2;
		Alignement al3;
		Alignement al4;
		Alignement al5;
		Alignement al6;
		Alignement al7;
		Alignement al8;
		boolean include;
		boolean include2;
		boolean include3;
		boolean include4;
		
		for(int j=i+1; j<frag.size();j++){
			short fragment2= frag.get(j).getId();

			al1 = new Alignement(frag.get(i).getNormAsString(),frag.get(j).getNormAsString());
			al1.calcul();
			al1.reverse();
			score= al1.getScore();
			ArcNormNorm arc1_1= new ArcNormNorm(score,fragment1, fragment2);
			include = al1.isInclude();
			
			
			
			al3 = new Alignement(frag.get(j).getNormAsString(),frag.get(i).getNormAsString());
			al3.calcul();
			al3.reverse();
			score3= al3.getScore();
			ArcNormNorm arc1_2= new ArcNormNorm(score3,fragment2, fragment1);
			include3 = al3.isInclude();

			al2 = new Alignement(frag.get(i).getNormAsString(),frag.get(j).getCIAsString());
			al2.calcul();
			al2.reverse();
			score2= al2.getScore();
			ArcNormCI arc2_1= new ArcNormCI(score2,fragment1,fragment2);
			include2= al2.isInclude();
			
			al4 = new Alignement(frag.get(j).getCIAsString(),frag.get(i).getNormAsString());
			al4.calcul();
			al4.reverse();
			score4= al4.getScore();
			ArcCINorm arc3_2= new ArcCINorm(score4,fragment2,fragment1);
			include4 = al4.isInclude();
			ArcNormCI arc2_2;
			if(!include2){
				arc2_2= new ArcNormCI(score2,fragment2,fragment1);
			}else{
				al4 = new Alignement(frag.get(j).getNormAsString(),frag.get(i).getCIAsString());
				al4.calcul();
				al4.reverse();
				score4= al4.getScore();
				arc2_2= new ArcNormCI(score4,fragment2,fragment1);
			}
			ArcCINorm arc3_1;
			if(!include4){
				arc3_1= new ArcCINorm(score4,fragment1,fragment2);
			}else{
				al5 = new Alignement(frag.get(i).getCIAsString(),frag.get(j).getNormAsString());
				al5.calcul();
				al5.reverse();
				score5= al5.getScore();
				arc3_1= new ArcCINorm(score5,fragment1,fragment2);
			}

			//Le score inverse/inverse est le même que normal/normal.
			ArcCICI arc4_1;
			if(!include3){
			arc4_1 = new ArcCICI(score3,fragment1,fragment2);
			}else{
				al7 = new Alignement(frag.get(i).getCIAsString(),frag.get(j).getCIAsString());
				al7.calcul();
				al7.reverse();
				score7= al7.getScore();
				arc4_1 = new ArcCICI(score7,fragment1,fragment2);
			}
			ArcCICI arc4_2;
			if(!include){
			arc4_2 = new ArcCICI(score,fragment2,fragment1);
			}else{
				al8 = new Alignement(frag.get(j).getCIAsString(),frag.get(i).getCIAsString());
				al8.calcul();
				al8.reverse();
				score8= al8.getScore();
				arc4_2 = new ArcCICI(score8,fragment2,fragment1);
			}
			
			
			
			arcList.add(arc2_1);
			arcList.add(arc3_1);
			arcList.add(arc4_1);
			arcList.add(arc2_2);
			arcList.add(arc3_2);
			arcList.add(arc4_2);
			
			arcList.add(arc1_1);			
			arcList.add(arc1_2);
			Info.AddArc(8);;
			
		}
	}
	
	public ArrayList<Arc> getArcList() {
		return arcList;
	}
	/**
	 * @param arclist the arclist to set
	 */
	public void setArcList(ArrayList<Arc> arclist) {
		this.arcList = arclist;
	}
	/**
	 * @return the frag
	 */
	public ArrayList<Fragment> getFrag() {
		return frag;
	}
	/**
	 * @param frag the frag to set
	 */
	public void setFrag(ArrayList<Fragment> frag) {
		this.frag = frag;
	}


		public int getImax() {
			return imax;
		}

		public void setImax(int imax) {
			this.imax = imax;
		}

}
