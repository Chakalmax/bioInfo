package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
			Fragment frag= new Fragment(fragment,Info.getNextId());
			parse.add(frag);
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
	
	public void writeFile(String outPutName, String frag, int numberCollection){
		try( BufferedWriter buff = new BufferedWriter(new FileWriter(outPutName))){
			buff.append(">Groupe-6 Collection");
			buff.append(Integer.toString(numberCollection));
			buff.append(" Longueur ");
			buff.append((Integer.toString((frag.length()))));
			buff.append("\n");
			for (int i = 0; i < frag.length(); i+=80) {
	            for(int j = 0; j < 80; ++j){ //80 caract per line.(convention)
	                if(i+j < frag.length()) {
	                    buff.append(frag.charAt(i + j));
	                    if(i+j>frag.length()){
	                    	buff.append("\n");
	                    	break;
	                    }
	                }
	            }
	            buff.append("\n");
			}
	        buff.flush();
	        buff.close();
	        
		} catch(IOException e) {
			System.out.println("I/O exception");
			e.printStackTrace();
		}
		
	}

}
