package DNA;

public class Nucleotide {
	byte value;
	
	public Nucleotide(char carac){
		switch(carac){
		case 'a':
			this.value=0;
			break;
		case 'c':
			this.value=1;
			break;
		case 'g':
			this.value=2;
			break;
		case 't':
			this.value=3;
			break;
		case '-':
			this.value=4;
			break;
		default: // At the moment, assume that every other char is a gap.(we could throw an error instead)
			this.value=4;
			break;
		}
	}
	public Nucleotide(byte value){
		if(value>4 || value<0){
			this.value=4;
		}else{this.value=value;}
	}
	
	/**
	 * @return the value
	 */
	public byte getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(byte value) {
		this.value = value;
	}
	public Nucleotide getComplementary(){
		switch(this.value){
		case 0: return new Nucleotide((byte)3);
		case 1 : return new Nucleotide((byte)2);
		case 2 : return new Nucleotide((byte)1);
		case 3 : return new Nucleotide((byte)0);
		default: return this;
		}
	}
	public char getAsChar(){
		switch(this.value){
			case 0: return 'a';
			case 1 : return 'c';
			case 2 : return 'g';
			case 3 : return 't';
			default: return '-';
		}
	}

	public String toString(){
		return ""+ this.value +"";
	}
	
	

		
	
}
