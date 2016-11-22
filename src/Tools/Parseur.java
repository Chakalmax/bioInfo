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

	public ArrayList<Fragment> readFile(String fileName)
	{
		ArrayList<Fragment> parse = new ArrayList<Fragment>();
		Info info = Info.getInstance();
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		
			String line = br.readLine();
			while(line != null)
			{
				
				if(line.startsWith(">")){
					line = br.readLine();
				}
				Fragment frag = new Fragment(line,info.getNextId());
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
