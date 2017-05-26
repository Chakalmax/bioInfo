package Info;

import Graph.Graph;
/**
 * 
 * @author Maxime
 *	This weird singleton is here to keep some information usefull for others class.
 *	For example, actualArc is the number of Arc already made. It's a way to get a safe number, shared by every Thread.
 */
public class Info {
	
	/** Constructeur privé */
	private Info()
	{
		
	}

	private static Info INSTANCE = new Info();
	private static short id;
	private static int actualArc;

	public static Info getInstance()
	{	return INSTANCE;
	}
	
	public static void reset(){
		id = 0;
	}
	
	public static short getNextId(){
		return id++;
	}

	public static void AddArc(int i){
		actualArc = actualArc+i;
	}
	public static void StartArc(){
		actualArc =0;
	}
	public static int getActualArc(){
		return actualArc;
	}

	
	
}