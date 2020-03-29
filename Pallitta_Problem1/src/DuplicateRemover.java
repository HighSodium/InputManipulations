import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//nooo don't look at these!!
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class DuplicateRemover {

	private Set<String> uniqueWords = new HashSet<String>();
	
	/**
	 * Removes blacklisted words from a text file
	 * @param dataFile - path to file to be edited
	 * @throws FileNotFoundException 
	 */
	public void remove (String dataFile)
	{		
		File data = new File(dataFile);
		Scanner inScan = null;
		
		try {
			inScan = new Scanner(data);
		} 
		catch (FileNotFoundException e) {
			friendly();	//user-friendly
			e.printStackTrace();
		}
		//Scan each token 
		while(inScan.hasNext()) {			
			String temp = inScan.next();		
			uniqueWords.add(temp.toUpperCase());	
		}
		
		//System.out.println(uniqueWords);
		inScan.close();
	}
	
	public void write (String outputFile) 
	{
		File myOutFile = new File(outputFile);
		
		try {
			if(!myOutFile.exists()) {
				myOutFile.createNewFile();
				System.out.println(">File '" + myOutFile.getName() + "' created...");
			}
			FileOutputStream streamOut = new FileOutputStream(myOutFile, false);
            PrintWriter out = new PrintWriter(streamOut);
            
            System.out.print("->Output printed to file [" + myOutFile.getName() + "]");
            out.print(uniqueWords);
            
            //end it all
            out.flush();
            streamOut.close();			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
	/**  : )*/
	private void friendly() {
		AudioInputStream hello = null;
		Clip clip = null;
		File audio = new File("friendly.wav");
		try {
			hello = AudioSystem.getAudioInputStream(audio);
			clip = AudioSystem.getClip();
			clip.open(hello);
			FloatControl vol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			vol.setValue(-13f); //I dont want to deafen the TA
			clip.start();	
		} 
		catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			System.out.println("[Alarms blaring]");			
			//if i dont comment this, it kinda ruins the immersion if you dont have the sound.
			//e.printStackTrace();
		}		
	
		try {
			System.out.println("AN EXCEPTION WAS FOUND IN THE SYSTEM!!");
			Thread.sleep(2500);
			System.out.println("LOCKDOWN ALL PRIMARY THREADS!");
			Thread.sleep(2500);
			System.err.print("DON'T LET IT ESCAPE!\n\n");	
			
					
			for(int i=0;i<6;i++) {
				Thread.sleep(250); 
				System.out.print("....");
			}			
			System.out.print("We got him!\n\n\n");	
			
			Thread.sleep(1000);
			System.out.println("We found him trying to hide the files, sir!");
			
			try { 
				if(audio.exists()) {
					clip.stop();
					hello.close(); 
					clip.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();			
		}	
	}	
}