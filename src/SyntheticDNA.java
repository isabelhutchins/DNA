import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class SyntheticDNA {
	/*this is the implementation for writing strands to the "SB" files which
	 * are used for testing StringBuilderStrand and LinkStrand. 
	 * The comments in green indicate what the code would say if we wanted to 
	 * write to the "SS" files which are used for testing StringStrand
	 */
	public static void main(String[] args) {
		
		/**
		 * these are the calls for creating strands for the "SB" files
		*/
		writeToFile(6);
		writeToFile(42);
		writeToFile(316); 
		writeToFile(4996); 
		writeToFile(183996);
		
		
		/**
		 * these are the calls for creating the strands
		 * for the "SS" files to test StringStrand
		*/
		/*
		writeToFile(76);
		writeToFile(246);
		writeToFile(436); 
		writeToFile(1756); 
		writeToFile(43996);*/
}
	
	public static void writeToFile(int length){
/*I looked online to learn how to write to a file. This is the resource I used:
 *https://www.mkyong.com/java/how-to-append-content-to-file-in-java/ 
*/
    	try{
    		File file =new File(fileNamer(length));

    		if(!file.exists()){
    			System.out.println("file does not exist");
    			return;
    		}

    			FileWriter fw = new FileWriter(file,false);
    	        BufferedWriter bw = new BufferedWriter(fw);
    	        bw.write(makeStrand(length));
    	        bw.close();

	        System.out.println("file written");

    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}
	
	public static String fileNamer(int length){
		String name="";
		
		//if(length=76) 
		if (length==6){
			//name ="data/SS76between.txt" 
			name = "data/SB6between.txt";
		}
		
		//if(length=246)
		if (length==42){
			//name ="data/SS246between.txt" 
			name = "data/SB42between.txt";
		}
		
		//if(length=436) 
		if (length==316){
			//name ="data/SS436between.txt"
			name = "data/SB316between.txt";
		}
		
		//if(length=1756) 
		if (length==4996){
			//name ="data/SS1756between.txt" 
			name = "data/SB4996between.txt";
		}
		
		//if(length=43996) 
		if (length==183996){
			//name ="data/SS43996between.txt" 
			name = "data/SB183996between.txt";
		}
		return name;
	}
	
	public static String makeStrand(int length){
		
		StringBuilder inbetween = new StringBuilder(); 
		StringBuilder strand = new StringBuilder();
		StringBuilder enzyme = new StringBuilder("gaat");
		
		for (int i=0; i<length; i++){
			inbetween.append("c");
		}
	
		
		// i<220000
		for (int i=0; i<4600000/(length+4); i++){
			strand.append(inbetween);
			strand.append(enzyme);
			}
		
		System.out.println(strand.length()); //to double check the length is right
		return strand.toString();
	}
	
}
