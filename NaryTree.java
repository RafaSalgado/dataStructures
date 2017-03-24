

public class NATree {



  public static class naryTree<AnyType extends Comparable<? super AnyType>>
  {

     public naryTree( ) {
         root = null;
     }

     public void makeEmpty( )  {
         root = null;
     }

     public boolean isEmpty( )     {
         return root == null;
     }

     public naryNode<AnyType> darRaiz( )
     {
       return root;
     }


     public void definirRaiz( naryNode<AnyType> n )
     {
       root= n;
     }

     public AnyType buscar( AnyType x )
     {

//       if(root!=null)
//    	 return root.buscar(x);
//       else
//    	 return null;
       return ( root != null ) ? root.buscar(x): null;
     }



     private naryNode<AnyType> root;


  }

  public static class naryNode<AnyType>
  {
       AnyType element;
      private MyLinkedList<naryNode<AnyType>> child;

      naryNode( AnyType theElement )
      {
          element=theElement;
          child=null;
      }

      public void addChild( naryNode<AnyType> n )
      {
      if( child == null )
    	child = new MyLinkedList<naryNode<AnyType>>( );
      	child.add(n);
      }

      public AnyType get( )
      {
    	return element;
      }


      public MyLinkedList<naryNode<AnyType>> getChild( )
      {
    	return child;
      }

      public void setElem( AnyType x )
      {
    	element = x;
      }

      public boolean isLeaf( )
      {
    	return child == null;
      }

      public AnyType buscar( AnyType x )
      {
      if( x.equals( element ) )
    	return element;
      else if( isLeaf( ) )
    	return null;
      else
      {
      for( int i = 0; i < child.size(); i++ )
      {
    	AnyType aux = child.get(i).buscar(x);
    	if( aux != null )
    	  return aux;
      	}
      	return null;
      	}
      }




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

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }


    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }

    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
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

    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }

    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
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

    /**
     * This is the doubly-linked list node.
     */
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
