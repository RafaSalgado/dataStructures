

public class MyStackL {


  public static void main(String[] args) {
	MyStack<Integer> pila = new MyStack<>();
	MyStack<Integer> doble = new MyStack<>();
	pila.push(1);
	pila.push(2);
	pila.push(3);
	pila.push(4);
	System.out.println("elementos en la pila " + MyStack.countElements(pila));
	MyStack.<Integer>print(pila);
	pila.pop();

	System.out.println("elementos en la pila " + MyStack.countElements(pila));
	MyStack.<Integer>print(pila);
	MyStack.doble(pila, doble);

	System.out.println("la pila duplicada");
	MyStack.print(doble);


}




  public static class MyStack<AnyType>
  {

	public static <E> void print (MyStack<E> s)
	{
	  E elem;
	  if (!s.isEmpty ()) {
		elem = s.pop ();
		System.out.println (elem);
		MyStack.<E>print (s);
		s.push (elem);
	  }
	}


	public static <E> int countElements(MyStack<E> s)
	{
	  c=0;
	  return MyStack.<E> numElements(s);
	}

	private static <E> int numElements (MyStack<E> s)
	{
	  E elem;
	  if (!s.isEmpty ()) {
		elem = s.pop ();
		c++;
		MyStack.<E> numElements(s);
		s.push (elem);
	  }
	  return c;
	}


	public static <E> MyStack<E> doble (MyStack<E> s, MyStack<E> t){

	  E elem;
	  if (!s.isEmpty ()) {
		elem = s.pop ();
		MyStack.<E> doble(s,t);
		t.push(elem);
		s.push (elem);
	  }
	  return t;

	}

      public MyStack( )
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


      public boolean push( AnyType x )
      {
          add(0, x );
          return true;
      }


      private void add( int idx, AnyType x )
      {
          addBefore( getNodeAnt( 0, 0, size( ) ), x );
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


      public AnyType top( )
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


          p = beginMarker.next;
          for( int i = 0; i < idx; i++ ){
            p = p.next;


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

          for( int i = 0; i < idx; i++ )
            aux=aux.next;


          return aux;

      }



       public AnyType pop()
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
      private static int c=0;

  }

}
