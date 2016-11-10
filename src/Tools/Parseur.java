package Tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import DNA.Fragment;

public class Parseur {
	// First version of Parseur

	public ArrayList<Fragment> readFile(String fileName)
	{
		ArrayList<Fragment> parse = new ArrayList<Fragment>();
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		
			int id=0;
			String line = br.readLine();
			while(line != null)
			{
				
				if(line.startsWith(">")){
					id++;
					line = br.readLine();
				}
				Fragment frag = new Fragment(line,id);
				parse.add(frag);
				line = br.readLine();
			}
		
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
