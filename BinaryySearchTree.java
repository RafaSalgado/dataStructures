import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BinaryySearchTree {


  public static void main(String[] args) throws NumberFormatException, IOException {

	int numCase,numComand, id,dataA,dataB;
	String comando[], str;

	BinarySearchTree<Integer> t= new BinarySearchTree<>();
	BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

	numCase=Integer.parseInt(br.readLine());

	for (int i = 0; i < numCase; i++) {

	  numComand=Integer.parseInt(br.readLine());
	  System.out.println("Caso #"+(i+1)+":");


	  for (int j = 0; j < numComand; j++) {

		str=br.readLine();
		comando=str.split("\\s+");

		switch (comando[0]){

		case("agregar"):
		  id=Integer.parseInt(comando[1]);
		  t.insert(id);
		  break;

		case("distancia"):
		  dataA=Integer.parseInt(comando[1]);
		  dataB=Integer.parseInt(comando[2]);
		  System.out.println("distancia: "+t.distance(dataA, dataB));
		  break;

		case("ancestro"):
		  dataA=Integer.parseInt(comando[1]);
		  dataB=Integer.parseInt(comando[2]);

		  if( !(t.contains(dataA) && t.contains(dataB)) )
	         System.out.println("ancestro: " + -1);
		  else{
			System.out.println("ancestro: " +t.ancestorMin(dataA, dataB));

		  }


		}
	  }
	  t.makeEmpty();

	}
  }






  public static class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
  {

     public BinarySearchTree( )
     {
         root = null;
     }


     public void insert( AnyType x )
     {
         root = insert( x, root );
     }


     public void remove( AnyType x )
     {
         root = remove( x, root );
     }


     public AnyType findMin( ) throws Exception
     {
         if( isEmpty( ) )
             throw new Exception( );
         return findMin( root ).element;
     }


     public AnyType findMax( ) throws Exception
     {
         if( isEmpty( ) )
             throw new Exception( );
         return findMax( root ).element;
     }


     public boolean contains( AnyType x )
     {
         return contains( x, root );
     }


     public void makeEmpty( )
     {
         root = null;
     }


     public boolean isEmpty( )
     {
         return root == null;
     }


     public void printTree( )
     {
         if( isEmpty( ) )
             System.out.println( "Empty tree" );
         else
             printTree( root );
     }



     /**
      * Internal method to insert into a subtree.
      * @param x the item to insert.
      * @param t the node that roots the subtree.
      * @return the new root of the subtree.
      */
     private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
     {
         if( t == null )
             return new BinaryNode<>( x, null, null );

         int compareResult = x.compareTo( t.element );

         if( compareResult < 0 )
             t.left = insert( x, t.left );
         else if( compareResult > 0 )
             t.right = insert( x, t.right );
         else
             ;  // Duplicate; do nothing
         return t;
     }

     /**
      * Internal method to remove from a subtree.
      * @param x the item to remove.
      * @param t the node that roots the subtree.
      * @return the new root of the subtree.
      */
     private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
     {
         if( t == null )
             return t;   // Item not found; do nothing

         int compareResult = x.compareTo( t.element );

         if( compareResult < 0 )
             t.left = remove( x, t.left );
         else if( compareResult > 0 )
             t.right = remove( x, t.right );
         else if( t.left != null && t.right != null ) // Two children
         {
             t.element = findMin( t.right ).element;
             t.right = remove( t.element, t.right );
         }
         else
             t = ( t.left != null ) ? t.left : t.right;
         return t;
     }

     /**
      * Internal method to find the smallest item in a subtree.
      * @param t the node that roots the subtree.
      * @return node containing the smallest item.
      */
     private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
     {
         if( t == null )
             return null;
         else if( t.left == null )
             return t;
         return findMin( t.left );
     }

     /**
      * Internal method to find the largest item in a subtree.
      * @param t the node that roots the subtree.
      * @return node containing the largest item.
      */
     private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
     {
         if( t != null )
             while( t.right != null )
                 t = t.right;

         return t;
     }

     /**
      * Internal method to find an item in a subtree.
      * @param x is item to search for.
      * @param t the node that roots the subtree.
      * @return node containing the matched item.
      */
     private boolean contains( AnyType x, BinaryNode<AnyType> t )
     {
         if( t == null )
             return false;

         int compareResult = x.compareTo( t.element );

         if( compareResult < 0 )
             return contains( x, t.left );
         else if( compareResult > 0 )
             return contains( x, t.right );
         else
             return true;
     }



     private int distancia ( AnyType x, BinaryNode<AnyType> t )
     {

         if( t == null )
             return -1;

         int compareResult = x.compareTo( t.element );

         if( compareResult < 0 ){
           count++;
           return distancia( x, t.left );
         }
         else if( compareResult > 0 ){
           count++;
           return distancia( x, t.right );
         }
         else
             return count;
     }


     public int distance(AnyType x, AnyType y){
       return distance(x,y,root);
     }


     private int distance (AnyType x, AnyType y, BinaryNode<AnyType> t ){
       int a=0;
       int b=0;
       int c=0;
       count=0;

       if( (contains(x) && contains(y))==false )
         return -1;

       int compareResult1 = x.compareTo( t.element );
       int compareResult2 = y.compareTo( t.element );



       if( compareResult1 < 0 && compareResult2 < 0 ){
    	 	return distance( x,y, t.left );
       }

       if( compareResult1 > 0 && compareResult2 > 0 ){
    	 	return distance( x,y, t.right );
       }


       if( compareResult1 < 0 && compareResult2 > 0 ){
         	a++;
         	b++;
    	 	a+=  distancia( x, t.left );
    	 	count=0;
    	 	b+= distancia(y,t.right);
    	 	c=a+b;
    	 	return c;
       }

       if( compareResult1 > 0 && compareResult2 < 0 ){
         	a++;
         	b++;
    	 	a+= distancia( x, t.right );
    	 	count=0;
    	 	b+=distancia(y,t.left);
    	 	c=a+b;
    	 	return c;
       }

       if(compareResult1 == 0 && compareResult2 < 0 ){
         	a=distancia(y, t.left);
         	a++;
    	 	return a;
       }
       if(compareResult1 <0 && compareResult2 ==0 ){
    	 	a=distancia(x, t.left);
    	 	a++;
    	 	return a;
       }

       if(compareResult1 == 0 && compareResult2 > 0 ){
    	 	a=distancia(y, t.right);
    	 	a++;
    	 	return a;
       }
       if(compareResult1 >0 && compareResult2 ==0 ){
    	 	a=distancia(x, t.right);
    	 	a++;
    	 	return a;
       }


       else
           return a;

     }

     public AnyType ancestorMin (AnyType x, AnyType y){
         return ancestorMin(x,y,root);
     }

     private AnyType ancestorMin (AnyType x, AnyType y, BinaryNode<AnyType> t ){

       int compareResult1 = x.compareTo( t.element );
       int compareResult2 = y.compareTo( t.element );

       if( compareResult1 < 0 && compareResult2 < 0 ){
 	 	return ancestorMin( x,y, t.left );
       }

       if( compareResult1 > 0 && compareResult2 > 0 ){
 	 	return ancestorMin( x,y, t.right );
       }
       else
    	 return t.element;


   }



     /**
      * Internal method to print a subtree in sorted order.
      * @param t the node that roots the subtree.
      */
     private void printTree( BinaryNode<AnyType> t )
     {
         if( t != null )
         {
             printTree( t.left );
             System.out.println( t.element );
             printTree( t.right );
         }
     }


     private void preorden(BinaryNode<AnyType> x){
       if(x!=null){
    	 System.out.println(x.element);
    	 preorden(x.left);
    	 preorden(x.right);
       }
     }


     private void postorden(BinaryNode<AnyType>x){
       if(x!=null){
    	 postorden(x.left);
    	 postorden(x.right);
    	 System.out.println(x.element);
       }
     }


     /**
      * Internal method to compute height of a subtree.
      * @param t the node that roots the subtree.
      */
     private int height( BinaryNode<AnyType> t )
     {
         if( t == null )
             return -1;
         else
             return 1 + Math.max( height( t.left ), height( t.right ) );
     }

     // Basic node stored in unbalanced binary search trees
     private static class BinaryNode<AnyType>
     {
             // Constructors
         BinaryNode( AnyType theElement )
         {
             this( theElement, null, null );
         }

         BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
         {
             element  = theElement;
             left     = lt;
             right    = rt;
         }

         AnyType element;            // The data in the node
         BinaryNode<AnyType> left;   // Left child
         BinaryNode<AnyType> right;  // Right child
     }


       /** The tree root. */
     private BinaryNode<AnyType> root;
     private int count =0;



  }



}
