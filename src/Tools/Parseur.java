package Tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import DNA.Fragment;
import Info.Info;

public class Parseur {
	// First version of Parseur
	public Parseur(){}

	public ArrayList<Fragment> readFile(String fileName)
	{
		ArrayList<Fragment> parse = new ArrayList<Fragment>();
		Info.getInstance();
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		
			String line = br.readLine();
			String fragment = "";
			boolean debut = true; // Sinon on oublie le premier element, ce qui était problématique (à changer eventuellement)
			// en faite je sais pas pk mais si on met pas ça, il prend la première ligne >machinmachin....
			while(line != null)
			{
				if(debut){
					debut = false;
				} else
				if(line.startsWith(">")&& (fragment != "") ){
					Fragment frag = new Fragment(fragment,Info.getNextId());
					fragment = "";
					parse.add(frag);
				}else{
					fragment = fragment + line;
				}
				line = br.readLine();
			}
			br.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/O exception");
			e.printStackTrace();
		}
		
		return parse;
	}

}
