
public class MyQueueSL {

  public static void main(String[] args) {



	MyQueue<Integer> s=new MyQueue<>();
	s.offer(5);
	s.offer(6);
	s.offer(7);
	s.offer(8);
	s.offer(9);
	System.out.println("va a salir "+ s.peek());
	s.offer(20);
	s.offer(30);
	s.poll();
	System.out.println("va a salir "+ s.peek());
	MyQueue.print(s);
	MyQueue.enderezar(s);
	System.out.println("va a salir "+ s.peek());
	MyQueue.print(s);

  }




public static class MyQueue<AnyType>
{
  public static <E> void enderezar (MyQueue<E> s)
	{
	  E elem;
	  if (!s.isEmpty ()) {
		elem = s.poll ();
		MyQueue.<E>enderezar (s);
		s.offer (elem);

	  }
	}


	public static <E> void print (MyQueue<E> s)
	{
	  E elem;


	  if (!s.isEmpty ()) {
		elem = s.poll ();
		System.out.println (elem);
		MyQueue.<E>print (s);
		s.offer (elem);
	  }
	}


   public MyQueue( )
   {
       doClear( );
   }

   public void doClear( )
   {
       beginMarker = new Node<>( null,null );
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


   public boolean offer( AnyType x )
   {
       add( size( ), x );
       return true;
   }


   private void add( int idx, AnyType x )
   {
       addBefore( getNodeAnt( idx, 0, size( ) ), x );
   }


   private void addBefore( Node<AnyType> p, AnyType x )
   {
   	Node<AnyType> aux=p.next;
       Node<AnyType> newNode = new Node<>( x, aux );
       newNode.next=aux;
       p.next=newNode;
       theSize++;
       modCount++;
   }


   public AnyType peek(  )
   {
       return getNode( 0 ).data;
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

   	Node<AnyType> aux;
   	aux=beginMarker;
       p = beginMarker.next;
       for( int i = 0; i < idx; i++ ){
         p = p.next;
         aux=aux.next;

       }
       return p;
   }



   private Node<AnyType> getNodeAnt( int idx )
   {
       return getNodeAnt( idx, 0, size( ) - 1 );
   }

   private Node<AnyType> getNodeAnt(int idx, int lower, int upper)
   {


       if( idx < lower || idx > upper )
           throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );

       Node<AnyType> aux;
   	   aux=beginMarker;

       for( int i = 0; i < idx; i++ ){
         aux=aux.next;
       }
       return aux;

   }



     public AnyType poll(  )
   {
       return remove( getNodeAnt( 0 ) );
   }


   private AnyType remove( Node<AnyType> p )
   {   Node<AnyType>b;
   	b=p.next;
       p.next = b.next;
       theSize--;
       modCount++;
       return b.data;
   }



   private class Node <AnyType>
   {
       public Node( AnyType d, Node<AnyType> n )
       {
           data = d; next = n;
       }

       public AnyType data;
       public Node<AnyType>   next;
   }

   private int theSize;
   private int modCount = 0;
   private Node<AnyType> beginMarker;

}











}
