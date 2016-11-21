package main;

import DNA.*;

public class main {

	public static void main(String[] args) {
		Nucleotide nuc1 = new Nucleotide('a');
		//System.out.println(nuc1);
		String str1 = "aaattttggggcccc";
		//System.out.println(str1.length());
		Fragment frag1 = new Fragment("aaaattttggggcccc", 0);
		System.out.println(frag1);

	}

}
