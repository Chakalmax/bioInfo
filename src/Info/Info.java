package Info;

import Graph.Graph;

public class Info {
	
	/** Constructeur privé */
	private Info()
	{
		
	}

	private static Info INSTANCE = new Info();
	private static short id;

	public static Info getInstance()
	{	return INSTANCE;
	}
	
	public static void reset(){
		id = 0;
	}
	
	public static short getNextId(){
		return id++;
	}


	
	
}
