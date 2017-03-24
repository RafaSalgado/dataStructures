
public class stack {

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
	MyStack.<Integer>print(pila);


	if(pila.contains(4))
	  System.out.println("Yes");
	else
	  System.out.println("No");



  }



  public static class MyStack <AnyType>
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


	public boolean contains (AnyType x){
	  for (int i = (size()-1); i >=0; i--)
		if(theItems[i]==x)
		  return true;
	  return false;


	}



	public MyStack( )
	{
      doClear( );
	}

	public void clear( )
	{
	 doClear( );
	}

	private void doClear( )
	{
	  theSize = 0;
	  ensureCapacity( DEFAULT_CAPACITY );
	}

	public int size( )
	{
      return theSize;
	}



	public boolean isEmpty( )
	{
      return size( ) == 0;
	}


  public AnyType top( )
  {
      return theItems[ size()-1 ];
  }



  @SuppressWarnings("unchecked")
  public void ensureCapacity( int newCapacity )
  {
      if( newCapacity < theSize )
          return;

      AnyType [ ] old = theItems;
      theItems = (AnyType []) new Object[ newCapacity ];
      for( int i = 0; i < size( ); i++ )
          theItems[ i ] = old[ i ];
  }


  public boolean push( AnyType x )
  {
	add( size( ), x );
      return true;
  }

  public void add( int idx, AnyType x )
  {
      if( theItems.length == size( ) )
          ensureCapacity( size( ) * 2 + 1 );

      for( int i = theSize; i > idx; i-- )
          theItems[ i ] = theItems[ i - 1 ];

      theItems[ idx ] = x;
      theSize++;
  }


  public AnyType pop(  )
  {
      AnyType removedItem = theItems[ size()-1 ];
      theSize--;
      return removedItem;
  }


  private static final int DEFAULT_CAPACITY = 10;

  private AnyType [ ] theItems;
  private int theSize;
  private static int c;
}

}
