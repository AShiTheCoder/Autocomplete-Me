import java.util.Comparator;

public class Utility{

	public static void quicksort(Comparable[] a, Comparator c) {
	    quick(a, 0, a.length-1, c);
	}
	    
	public static void quick(Comparable[] a, int left, int right, Comparator c) {
	    if (right > left) {
	        int pivotPos = partition(a, left, right, c);
	        quick(a, left, pivotPos-1, c);
	        quick(a, pivotPos+1, right, c);
	    }
	}
	    
	public static int partition(Comparable[] a, int left, int right, Comparator c) {
	    int splitPos = left;
	    for (int i = left; i < right; i++) {
	        if (c.compare(a[i], a[right]) < 0) {
	            swap(a, i, splitPos);
	            splitPos++;
	        }
	    }
	    swap(a,splitPos,right);
	    return splitPos;
	}

	public static void swap(Comparable[] a, int i, int j) {
	    Comparable temp = a[i];
	    a[i] = a[j];
	    a[j] = temp;
	}
}
