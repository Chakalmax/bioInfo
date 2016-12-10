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
		System.out.println(this);
		this.frag=frag;
		this.initialI=initialI;
		this.nbDeThread=nbDeThread;
		//start();
		this.threadName=threadName;
	}

	@Override
	public void run() {
		System.out.println("Running " +  threadName );
		imax = frag.size();
		maxArc = frag.size()*(frag.size()-1)*4;
		arcList = new ArrayList<Arc>(maxArc);//this may cause problem if frag is empty
		makeAllArc();
		
      System.out.println("Thread " +  threadName + " exiting.");
		
	}
	
	
	public void makeAllArc(){
		for(int i=initialI;i<frag.size();i=i+nbDeThread){
			short fragment1= frag.get(i).getId();
			makeArc(i,fragment1);
			//System.gc ();
			//System.runFinalization ();
			System.out.println("Done: " + ((float)Info.getActualArc()/(float)maxArc)*100 +" %");
		}

	}
	

	public void makeArc(int i,short fragment1){
		short score;
		short score2;
		Alignement al1;
		Alignement al2;
		for(int j=i+1; j<frag.size();j++){
			short fragment2= frag.get(j).getId();

			//arcNormNorm
			al1 = new Alignement(frag.get(j).getNormAsString(),frag.get(i).getNormAsString());
			al1.calcul();
			score= al1.getScore();
			ArcNormNorm arc1_1= new ArcNormNorm(score,fragment1, fragment2);
			ArcNormNorm arc1_2= new ArcNormNorm(score,fragment2, fragment1);

			al2 = new Alignement(frag.get(j).getNormAsString(),frag.get(i).getCIAsString());
			al2.calcul();
			score2= al2.getScore();
			ArcNormCI arc2_1= new ArcNormCI(score2,fragment1,fragment2);
			ArcNormCI arc2_2= new ArcNormCI(score2,fragment2,fragment1);


			ArcCINorm arc3_1= new ArcCINorm(score2,fragment1,fragment2);
			ArcCINorm arc3_2= new ArcCINorm(score2,fragment2,fragment1);
			//Le score inverse/inverse est le même que normal/normal.

			ArcCICI arc4_1 = new ArcCICI(score,fragment1,fragment2);
			ArcCICI arc4_2 = new ArcCICI(score,fragment2,fragment1);
			
			arcList.add(arc1_1);
			arcList.add(arc2_1);
			arcList.add(arc3_1);
			arcList.add(arc4_1);
			arcList.add(arc1_2);
			arcList.add(arc2_2);
			arcList.add(arc3_2);
			arcList.add(arc4_2);
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
