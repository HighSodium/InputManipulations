//ANTHONY PALLITTA

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class DuplicateCounter {
	
	private HashMap<String, Integer> wordCounter = new HashMap<String, Integer>();
	
	public void count(String dataFile) {
		
		File data = new File(dataFile);
		Scanner inScan = null;
		
		//input problem2.txt
		try {
			inScan = new Scanner(data);		
		} catch (FileNotFoundException e) {
			System.out.println("Sir, we found another one!");
			e.printStackTrace();
		}
		
		//Use a Map to count the occurrence of each word in a file
		while(inScan.hasNext()) 
		{			
			String temp = inScan.next().replaceAll("[!*,.?;\"]", "");
			temp = temp.toUpperCase();			
			if(wordCounter.putIfAbsent(temp, 1) != null) 
			{
				int n = wordCounter.get(temp);
				wordCounter.replace(temp,n+1);
			}	
		}				
		//System.out.println(wordCounter);	
		//output to unique_word_counts.txt
		inScan.close();	
	}
	
	//Write to output file FANCILY
	public void write (String outputFile) 
	{
		File myOutFile = new File(outputFile);		
		try {
			if(!myOutFile.exists()) { //If there is no file, create it
				myOutFile.createNewFile();
				System.out.println(">File '" + myOutFile.getName() + "' created...");
			}
			FileOutputStream streamOut = new FileOutputStream(myOutFile, false);
            PrintWriter out = new PrintWriter(streamOut);

            System.out.print("->Output printed to file [" + myOutFile.getName() + "]");
            
            for(String key : wordCounter.keySet()) { 
            	out.println(key + " = " + wordCounter.get(key)); 
            }
            
            out.flush();
            streamOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
