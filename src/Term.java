import java.util.Comparator;

public class Term implements Comparable<Term> {
	private String query;
	private int weight;
	
    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) throws NullPointerException, IllegalArgumentException{
    	if (query == null){
    		throw new NullPointerException();
    	} else {
    		this.query = query;
    	}
    	
    	if (weight < 0){
    		throw new IllegalArgumentException();
    	} else {
    		this.weight = (int) weight;
    	}
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder(){
    	
    	class ReverseComparator implements Comparator<Term>{
			public int compare(Term o1, Term o2) {
				return (int) (o2.weight - o1.weight);
			}
    	}
    	
    	ReverseComparator r = new ReverseComparator();
    	return r;
    }

    // Compares the two terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r){
    	
    	class PrefixComparator implements Comparator<Term>{
    		public int compare(Term o1, Term o2) {
    			String s1 = o1.getQuery().substring(0, Math.min(o1.getQuery().length(), r));
    			String s2 = o2.getQuery().substring(0, Math.min(o2.getQuery().length(), r));
    			
    			return s1.compareTo(s2);
    		}
    	}
    	
    	PrefixComparator p = new PrefixComparator();
    	return p;
    }

    public static Comparator<Term> byLexi(){
    	
    	class LexiComparator implements Comparator<Term>{
    		public int compare(Term o1, Term o2) {
    			String s1 = o1.getQuery();
    			String s2 = o2.getQuery();
    			
    			return s1.compareTo(s2);
    		}
    	}
    	
    	LexiComparator l = new LexiComparator();
    	return l;
    }
    
    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that){
    	return this.getQuery().compareTo(that.getQuery());
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString(){
    	return weight + "   " + query;
    }

    public String getQuery(){
    	return query;
    }
    
    // unit testing (required)
    public static void main(String[] args){
    	
    }
}
