

public class bst {


/**
* Implements an unbalanced binary search tree.
* Note that all "matching" is based on the compareTo method.
* @author Mark Allen Weiss
*/

  public static void main( String [ ] args ) throws Exception
  {
      BinarySearchTree<Integer> t = new BinarySearchTree<>( );
      BinarySearchTree<Integer> j = new BinarySearchTree<>( );
      t.insert(6);
      t.insert(5);
      t.insertByNode(5, 2);
      t.insertByNode(2, 4);
      t.insertByNode(6, 8);
      t.insertByNode(8, 10);
      t.insertByNode(4, 42);
      t.insertByNode(2, 25);
      t.insertByNode(5, 100);

      t.printTree();
      System.out.println("otro");
      j.insert(10);
      j.insert(18);
      j.insert(17);
      j.insert(8);
      j.insert(9);
      j.insert(5);

      j.printTree();




     // System.out.println("distancia " +t.distance(3, 30));


     /* if( !(t.contains(3) && t.contains(18)) )
        System.out.println("ancestro: " + -1);
	  else{
		System.out.println("ancestro");
	    System.out.println(t.ancestorMin(3, 19));
	  }

      System.out.println("este es el arbol");
      t.printTree();*/
  }

public static class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{


  public void insertByNode( AnyType x, AnyType y )
  {
      root = insertByNode( x, y, root );

  }

  private BinaryNode<AnyType> insertByNode( AnyType x, AnyType y,BinaryNode<AnyType> t )
  {
    if( t == null )
      return new BinaryNode<>( y, null, null );

    if(t.element==x && t.right==null){
 	 t.right=insertByNode(x,y,t.right);
    }
    else if(t.element==x && t.left==null){
 	 t.left=insertByNode(x,y,t.left);
    }

    else if(t.element!=x && t.right==null){
 	 insertByNode(x,y,t.left);
    }
    else if(t.element!=x && t.left==null){
 	  insertByNode(x,y,t.right);
    }
    else{
 	 if(contains(t.left.element))
 	   insertByNode(x,y,t.left);
 	 else
 	   insertByNode(x,y,t.right);
    }
    return t;

  }
   /**
    * Construct the tree.
    */
   public BinarySearchTree( )
   {
       root = null;
   }

   /**
    * Insert into the tree; duplicates are ignored.
    * @param x the item to insert.
    */
   public void insert( AnyType x )
   {
       root = insert( x, root );
   }

   /**
    * Remove from the tree. Nothing is done if x is not found.
    * @param x the item to remove.
    */
   public void remove( AnyType x )
   {
       root = remove( x, root );
   }

   /**
    * Find the smallest item in the tree.
    * @return smallest item or null if empty.
    */
   public AnyType findMin( ) throws Exception
   {
       if( isEmpty( ) )
           throw new Exception( );
       return findMin( root ).element;
   }

   /**
    * Find the largest item in the tree.
    * @return the largest item of null if empty.
    */
   public AnyType findMax( ) throws Exception
   {
       if( isEmpty( ) )
           throw new Exception( );
       return findMax( root ).element;
   }

   /**
    * Find an item in the tree.
    * @param x the item to search for.
    * @return true if not found.
    */
   public boolean contains( AnyType x )
   {
       return contains( x, root );
   }

   /**
    * Make the tree logically empty.
    */
   public void makeEmpty( )
   {
       root = null;
   }

   /**
    * Test if the tree is logically empty.
    * @return true if empty, false otherwise.
    */
   public boolean isEmpty( )
   {
       return root == null;
   }

   /**
    * Print the tree contents in sorted order.
    */
   public void printTree( )
   {
       if( isEmpty( ) )
           System.out.println( "Empty tree" );
       else
           printTree( root );
   }


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
    	 t.right = insert( x, t.right );
       return t;
   }

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


   public int distancia(AnyType x){
	 return distancia(x,root);
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
  	 return a;
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
           return true;    // Match
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
   private int count=0;



}


}
