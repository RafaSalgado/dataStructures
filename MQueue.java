
public class MQueue {


  public static void main(String[] args) {

	MyQueue<Integer> s=new MyQueue<>();
	s.offer(5);
	s.offer(6);
	s.offer(7);
	s.offer(8);
	s.offer(9);
	System.out.println("va a salir "+ s.peek());
	MyQueue.print(s);
	MyQueue.enderezar(s);
	System.out.println("va a salir "+ s.peek());


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

      private  class Node<AnyType>
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
      private static boolean a=false;


  }


}
