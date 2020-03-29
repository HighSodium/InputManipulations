//Anthony Pallitta

public class Application {

	public static void main(String[] args) 
	{		
	
		DuplicateRemover dupe = new DuplicateRemover();
		dupe.remove("problem1.txt");
		dupe.write("unique_words.txt");

	}

}
