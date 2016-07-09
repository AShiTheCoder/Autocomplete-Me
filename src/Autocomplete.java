import java.util.Comparator;
import java.util.Scanner;
import java.io.*;

public class Autocomplete {
	private Term[] terms;
	
	public static void main(String[] args) throws IOException {

	    // read in the terms from a file
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the name of the data file:");
		String filename = reader.nextLine();
		System.out.println("Enter the number of autocompleted queries to be found:");
		int k = Integer.parseInt(reader.nextLine());
		System.out.println("Enter the prefix to be autocompleted:");
		String prefix = reader.nextLine();
		reader.close();
		
	    String filePath = new File("").getAbsolutePath().concat("/src/" + filename);
	    Scanner in = new Scanner(new FileInputStream(filePath));
	    int N = in.nextInt();
	    Term[] terms = new Term[N];
	    for (int i = 0; i < N; i++) {
	        long weight = in.nextLong();           // read the next weight
	        String query = "";
	        while (!in.hasNextLong() && in.hasNext()){ // read the next query
	        	query += in.next() + " ";
	        }
	        //query = query.substring(1);
	        terms[i] = new Term(query, weight);    // construct the term
	    }
	    in.close();
	    
	    // read in queries from standard input and print out the top k matching terms
	    Autocomplete autocomplete = new Autocomplete(terms);
	    
	    Term[] results = autocomplete.allMatches(prefix);
	    for (int i = 0; i < Math.min(k, results.length); i++){
	    	System.out.println(results[i]);
	    }
	}

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms){
    	if (terms == null) throw new NullPointerException();
    	for (Term t : terms){
    		if (t == null) throw new NullPointerException();
    	}
    	this.terms = terms;
    	
    	Utility.quicksort(this.terms, Term.byLexi());
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix){
    	if (prefix == null) throw new NullPointerException();
    	Term[] result = getMatches(prefix);
    	Utility.quicksort(result, Term.byReverseWeightOrder());
    	return result;
    }

    // Returns the number of terms that start with the given prefix.
    public Term[] getMatches(String prefix){
    	if (prefix == null) throw new NullPointerException();
    	
		Comparator<Term> lexi = Term.byPrefixOrder(prefix.length());
    	int start = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), lexi);
    	int end = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), lexi);
    	
    	Term[] result = new Term[end - start + 1];
    	for (int i = start; i <= end; i++){
    		result[i - start] = terms[i];
    	}
    	return result;
    }
    
    
}