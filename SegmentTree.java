import java.io.BufferedReader;
import java.io.InputStreamReader;



public class SegmentTree {

	public static void main (String[] args) throws Exception  {


//	  SegmentTree st = new SegmentTree(0,8 );
//	  int[] initialValues = new int[]{-20, 19, 7, 4, -10, 5, 100, 1, 3};
//	  for(int i = 0; i < initialValues.length; i++)
//		st.set(i, initialValues[i]);
//	  System.out.println("max in [0,8]: " + st.getMax(0, 8));
//	  System.out.println("max in [1,7]: " + st.getMax(1, 7));
//	  System.out.println("max in [5,5]: " + st.getMax(5, 5));
//	  st.set(0, 8);
//	  System.out.println("max in [0,8]: " + st.getMax(0, 8));
//	  System.out.println("max in [7,8]: " + st.getMax(7, 8));
//	  st.set(7, 529);
//	  System.out.println("max in [7,8]: " + st.getMax(7, 8));
//	  System.out.println("max in [0,8]: " + st.getMax(0, 8));
//


}

	public static class  SegmentTree {
		int start, end;
		SegmentTree leftTree, rightTree;
		int maxValue;

		public SegmentTree(int start, int end) {
		  this.start = start;
		  this.end = end;

		  if(start == end) { // es una hoja
			leftTree = rightTree = null;
			return;
		}


		  int mid = (start + end) / 2;
		  leftTree = new SegmentTree(start, mid);
		  rightTree = new SegmentTree(mid + 1, end);
		}


		public void set(int pos, int value) {
		// es una hoja, CASO BASE
		  if(start == end) {
			maxValue = value;
			return;
		  }
		  int mid = (start + end) / 2;
		  if(pos <= mid)
			leftTree.set(pos, value);
		  else
			rightTree.set(pos, value);
		  	maxValue = max(leftTree.maxValue, rightTree.maxValue);
		}

		public int max(int a , int b){
		  if(a>b)
			return a;
		  else
			return b;
		}


		public int getMax(int low, int high) {
		// [low,high] = [start,end]
		  if(start == low && end == high)
			return maxValue;
		  int mid = (start + end) / 2;
		// [low,high] totalmente incluido en [start,mid]
		  if(high <= mid)
			return leftTree.getMax(low, high);
		// [low,high] totalmente incluido en [mid+1,end]
		  if(low > mid)
			return rightTree.getMax(low, high);
		// debemos dividir el intervalo en [low,mid] y [mid+1,high]
		  int leftMin = leftTree.getMax(low, mid);
		  int rightMin = rightTree.getMax(mid+1, high);
		  return max(leftMin, rightMin);
		}





	 }



}
