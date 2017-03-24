

public class MyCircularArrayList {
  public static void main(String[] args) {

	MyQueue<Integer> a =new MyQueue<>();
	a.offer(5);
	a.offer(6);
	a.offer(7);
	a.offer(8);
	a.offer(9);
	a.offer(10);
	a.offer(11);
	a.offer(12);
	a.offer(13);
//	a.offer(19);
//	a.offer(14);
//	a.offer(14);


//	System.out.println(a.peek());
//	System.out.println(a.poll());
//	System.out.println(a.peek());

	MyQueue.print(a);
	System.out.println("...---------------");
	MyQueue.print(a);
	System.out.println("...---------------");
	MyQueue.print(a);
	System.out.println("...---------------");
	MyQueue.print(a);
	System.out.println("...---------------");
  }



  public static class MyQueue<AnyType>
  {

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

      public int size( )
      {
          return tamano;
      }

      public boolean isEmpty( )
      {
          return tamano == 0;
      }


      public AnyType peek(  )
      {
          return circular[ front ];
      }






      @SuppressWarnings("unchecked")
      public void ensureCapacity( int newCapacity )
      {
          if( newCapacity < tamano )
              return;

          AnyType [ ] old = circular;
          circular = (AnyType []) new Object[ newCapacity ];
          for( int i = 0; i < size( ); i++ )
              circular[ i ] = old[ i ];

          front = 0;
          back = tamano;

      }


      public boolean offer( AnyType x )
      {
    	add( size( ), x );
          return true;
      }

      private void add( int idx, AnyType x )
      {

    	if (size()==circular.length)
    	  ensureCapacity( size( ) * 2 + 1 );

        circular[ back ] = x;
        back=(back+1)%circular.length;
        tamano++;




      }

      public AnyType poll(  )
      {
    	 AnyType removedItem = circular[ front];
         front = (front+1) % circular.length;
         tamano--;
         return removedItem;
      }


      private void doClear( )
      {
    	  back=0;
    	  front=0;
          tamano = 0;
          ensureCapacity( DEFAULT_CAPACITY );

      }



      private static final int DEFAULT_CAPACITY = 9;

      private AnyType [ ] circular;
      private int tamano;
      private int back;
      private int front;
  }


}
