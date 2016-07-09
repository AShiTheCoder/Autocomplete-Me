import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Term[] a, Term key, Comparator<Term> comparator) {
    	if (a == null || key == null || comparator == null){
    		throw new NullPointerException();
    	}
    	
    	int first = 0, last = a.length - 1;  	
    	while (first <= last){
    		int middle = (first + last) / 2;
    		if (comparator.compare(key, a[middle]) > 0){
    			first = middle + 1;
    		} else if (comparator.compare(key, a[middle]) < 0){
    			last = middle - 1;
    		} else {
    			for (int i = first; i <= last; i++){
    				if (comparator.compare(key, a[i]) == 0) return i;
    			}
    		}
    	}
    	return -1;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Term[] a, Term key, Comparator<Term> comparator) {
    	if (a == null || key == null || comparator == null){
    		throw new NullPointerException();
    	}
    	
    	int first = 0, last = a.length - 1;  	
    	while (first <= last){
    		int middle = (first + last) / 2;
    		if (comparator.compare(key, a[middle]) > 0){
    			first = middle + 1;
    		} else if (comparator.compare(key, a[middle]) < 0){
    			last = middle - 1;
    		} else {
    			for (int i = last; i >= first; i--){
    				if (comparator.compare(key, a[i]) == 0) return i;
    			}
    		}
    	}
    	return -1;
    }

    // unit testing (required)
    public static void main(String[] args) {
    	
    }
}