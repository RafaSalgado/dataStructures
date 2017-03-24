import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class BinaryyTree {

  public static void main(String[] args) throws NumberFormatException, IOException {
	BinaryTree<Integer> t;
	MyLinkedList<BinaryNode<Integer>> list= new MyLinkedList<>();

	BinaryNode<Integer> n;

	int numCase,numNode,sum=0,max=0,rest;
	String nodes[],idNode[], str;
	BufferedReader br= new BufferedReader(new InputStreamReader(System.in));


	numCase=Integer.parseInt(br.readLine());

	for (int i = 0; i < numCase; i++) {
	  t= new BinaryTree<>();
	  numNode=Integer.parseInt(br.readLine());
	  str=br.readLine();
	  nodes=str.split("\\s+");
	  max=Integer.parseInt(nodes[0]);
	  t.sumClear();
	  sum=0;
	  System.out.println("Caso #" +(i+1)+ ":");

	  for (int j = 0; j < numNode; j++) {
		n=t.createN(Integer.parseInt(nodes[j]));
		sum+=Integer.parseInt(nodes[j]);
		if(Integer.parseInt(nodes[j])>max)
		  max=Integer.parseInt(nodes[j]);

		list.add(n);
	  }


	  for (int j = 0; j < numNode-1; j++) {
		str=br.readLine();
		idNode=str.split("\\s+");
		if(t.isEmpty()){
		  t.root(list.get(Integer.parseInt(idNode[0])-1));
		}
		t.enlazar(list.get(Integer.parseInt(idNode[0])-1), list.get(Integer.parseInt(idNode[1])-1));
	  }

	  	rest=Math.abs(sum-max);
		t.sumClear();
		System.out.println(t.cutTree(rest,sum));
		t.sumClear();
		list.doClear();

	}

	//NOOO
  }






  public static class BinaryTree<AnyType extends Comparable<? super AnyType>>
  {

     public BinaryTree( ) {
         root = null;
     }

     public void makeEmpty( )  {
         root = null;
     }

     public boolean isEmpty( )     {
         return root == null;
     }


     public void printTree( )     {
         if( isEmpty( ) )
             System.out.println( "Empty tree" );
         else
             printTree( root );
     }

     public BinaryNode<AnyType> createN( AnyType x )
     {
       return new BinaryNode<>( x, null, null );
     }

     public  void root (BinaryNode<AnyType> r){
       root=r;
     }

     public void enlazar(BinaryNode<AnyType> t, BinaryNode<AnyType> r){
       if(t.right==null){
    	 t.right=r;
       }
       else if(t.left==null){
    	 t.left=r;
       }
       else
    	 ;
     }

     private void printTree( BinaryNode<AnyType> t )
     {
         if( t != null )
         {
             printTree( t.left );
             System.out.println( t.element );
             printTree( t.right );
         }
     }

     public int sumTree(){
       return sumTree(root);
     }


     public void sumClear (){
       suma=0;
     }

     private int sumTree( BinaryNode<AnyType> t )   {


         if( t != null )  {
             sumTree( t.left );
             suma+=(Integer)t.element;
             sumTree( t.right );

         }
        return suma;
       }

     public int cutTree(Integer a,Integer b){
       return cutTree(a,b, root);
     }

     private int cutTree(Integer a,Integer x, BinaryNode<AnyType> t ){

       int y;
       sumClear();
       min=a;

       if( t != null )  {
    	 cutTree(min,x, t.left );
    	 minimum=min;
    	 y=sumTree(t);
    	 minimum= Math.abs(y-(x-y));

    	 if(minimum<min){
    	   min=minimum;
    	 }
         cutTree(min,x,t.right);
         return min;

       }
       return min;

     }


     public void printAnchura( )     {
       if( isEmpty( ) )
           System.out.println( "Empty tree" );
       else
           niveles( root );
   }
     private void niveles (BinaryNode <AnyType> t){

       MyQueue<BinaryNode> c = new MyQueue<>();
       BinaryNode<AnyType> aux;
       c.offer(t);
       while(!c.isEmpty()){
    	 aux = c.poll();

    	 System.out.println(aux.element);
    	 if(aux.left != null)
    	   c.offer(aux.left);

    	 if(aux.right != null)
    	   c.offer(aux.right);
       }
     }


     private BinaryNode<AnyType> root;
     private int suma=0;
     private int min=0;
     private int minimum=0;

  }

  public static class BinaryNode<AnyType>
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
      public BinaryNode() {
  	  // TODO Auto-generated constructor stub
  	}

      AnyType element;            // The data in the node
      BinaryNode<AnyType> left;   // Left child
      BinaryNode<AnyType> right;  // Right child
  }




  public static class MyLinkedList<AnyType> implements Iterable<AnyType>
  {


    public MyLinkedList( )
    {
        doClear( );
    }



    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        theSize = 0;
        modCount++;
    }


    public int size( )
    {
        return theSize;
    }

    public boolean isEmpty( )
    {
        return size( ) == 0;
    }


    public boolean add( AnyType x )
    {

        add( size( ), x );
        return true;
    }


    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }

    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }

    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }


    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
        if( idx < size( ) / 2 ){
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;
        }
        else{
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        }
        return p;
    }


    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }


    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }


    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }


    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }


    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext( )
        {
            return current != endMarker;
        }

        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( );

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );

            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;
        }
    }


    private  class Node<E>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }

        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

  }


  public static class MyQueue<AnyType>
  {


	public static <E> void print (MyQueue<E> s)
	{
	  E elem;
	  MyQueue<E> a = new MyQueue<E>();
	  if (!s.isEmpty ()) {
		elem = s.poll ();
		a.offer(elem);
		System.out.println (elem);
		MyQueue.<E>print (s);
		a.offer (elem);
	  }

	}

      public MyQueue( )
      {
          doClear( );
      }


      public void doClear( )
      {
          beginMarker = new Node<>( null, null, null );
          endMarker = new Node<>( null, beginMarker, null );
          beginMarker.next = endMarker;
          theSize = 0;
          modCount++;
      }


      private int size( )
      {
          return theSize;
      }

      public boolean isEmpty( )
      {
          return size( ) == 0;
      }


      public boolean offer( AnyType x )
      {
    	  addBefore( getNode( 0, 0, size( ) ), x );
          return true;
      }


      private void addBefore( Node<AnyType> p, AnyType x )
      {
          Node<AnyType> newNode = new Node<>( x, p.prev, p );
          newNode.prev.next = newNode;
          p.prev = newNode;
          theSize++;
          modCount++;
      }


      public AnyType peek( )
      {
          return getNode( size()-1 , 0, size()-1 ).data;
      }

      private Node<AnyType> getNode( int idx, int lower, int upper )
      {
          Node<AnyType> p;
          if( idx < lower || idx > upper )
              throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
          if( idx < size( ) / 2 ){
              p = beginMarker.next;
              for( int i = 0; i < idx; i++ )
                  p = p.next;
          }
          else{
              p = endMarker;
              for( int i = size( ); i > idx; i-- )
                  p = p.prev;
          }
          return p;
      }


      public AnyType poll( )
      {
          return remove( getNode( size()-1, 0, size( ) - 1 ) );
      }


      private AnyType remove( Node<AnyType> p )
      {
          p.next.prev = p.prev;
          p.prev.next = p.next;
          theSize--;
          modCount++;

          return p.data;
      }

      private  class Node<E>
      {
          public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
          {
              data = d; prev = p; next = n;
          }

          public AnyType data;
          public Node<AnyType>   prev;
          public Node<AnyType>   next;
      }

      private int theSize;
      private int modCount = 0;
      private Node<AnyType> beginMarker;
      private Node<AnyType> endMarker;
  }

}
