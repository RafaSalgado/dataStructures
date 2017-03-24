import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class AvlTree {

  public static void main( String [ ] args ) throws Exception {


    AvlTree<Integer> t;

    MyLinkedList< AvlTree<Integer>> list= new MyLinkedList<>();
    MyLinkedList< Integer> idxL= new MyLinkedList<>();


    int numComand,idx, idy,dat, posx, posy,min,repeat;
    String str, comand[];
    boolean in,openA,openB;
    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    numComand=Integer.parseInt(br.readLine());

    for (int i = 0; i < numComand; i++) {
      str=br.readLine();
      comand=str.split("\\s+");
      in=true;
      openA=false;
  	  openB=false;
  	  int count=0;
  	  posx=0;
  	  posy=0;
  	  AvlTree<Integer> r;
  	  AvlTree<Integer> s;
      java.util.Iterator<Integer> it = idxL.iterator();

      switch(comand[0]){

      case "crear":

    	idx=Integer.parseInt(comand[1]);
    	dat=Integer.parseInt(comand[2]);
    	t = new AvlTree<>( );



    	while (it.hasNext()) {
		  Integer n= (Integer) it.next();
		  if(n==idx){
			in=false;
			break;
		  }
		}

    	if(in==true){
    	  t.insert(dat);
    	  list.add(t);
    	  idxL.add(idx);
    	}
    	break;

      case "insertar":

    	idx=Integer.parseInt(comand[1]);
    	dat=Integer.parseInt(comand[2]);

    	while (it.hasNext()) {
		  Integer n= (Integer) it.next();

		  if(n==idx){
			list.get(count).insert(dat);
			list.get(count).checkBalance();

			break;
		  }
		  count++;
		}
    	break;

      case "unir":
    	//System.out.println("empieza unir");


    	idx=Integer.parseInt(comand[1]);
    	idy=Integer.parseInt(comand[2]);

    	while (it.hasNext()) {
		  Integer n= (Integer) it.next();
		  if(n==idx){
			posx=count;
			openA=true;
		  }
		  else if(n==idy){
			posy=count;
			openB=true;
		  }
		  count++;
		}
//    	list.get(posx).printTree();
//    	System.out.println("el otro");
//    	list.get(posy).printTree();

    	 if((openA&&openB)==true){
 //   	 System.out.println("union "+idx +" "+ idy + " "+ (i+2));

    	  r=list.get(posy);
    	  s=list.get(posx);

    	 while (!r.isEmpty()) {
    	   min=r.findMin();
    	   repeat=r.cuenta(min);
    	   r.remove(min);
    	   for (int j = 0; j < repeat; j++) {
    		   s.insert(min);
		  }

    	 }

    	  list.remove(posy);
    	  idxL.remove(posy);
//    	  System.out.println("nuevo arbol");
//    	  s.printTree();
//    	  System.out.println("termina unir");
    	}

    	break;

      case "contiene":

    	idx=Integer.parseInt(comand[1]);
    	idy=Integer.parseInt(comand[2]);

    	while (it.hasNext()) {
		  Integer n= (Integer) it.next();
		  if(n==idx){
			posx=count;
			openA=true;
		  }
		  else if(n==idy){
			posy=count;
			openB=true;
		  }
		  count++;
		}

    	if((openA && openB)==true){
 //   	  System.out.println((i+2));
    	  r=list.get(posx);
    	  s=list.get(posy);
//    	  System.out.println("este es el grande");
//    	  r.printAnchura();
//    	  System.out.println(".....");
//    	  r.printTree();
//   	      System.out.println("pequeño");
//	      s.printAnchura();
//	      System.out.println(".....");
//	      s.printTree();
//    	  if(r.containsTree(s)==true)
//    		System.out.println("YES");
//    	  else
//    		System.out.println("No");

    	  if(r.containsTheTree(s)==true)
    		System.out.println("contiene: Yes");
    	  else
    		System.out.println("contiene: No");

    	}
    	//System.out.println("termina contiene");
    	break;

      case "ocurrencia":
    	idx=Integer.parseInt(comand[1]);
    	dat=Integer.parseInt(comand[2]);

    	while (it.hasNext()) {
		  Integer n= (Integer) it.next();
		  if(n==idx){
			posx=count;
			openA=true;
		  }
		  count++;
    	}

    	if(openA==true){
 //       System.out.println((i+2));
    	  r=list.get(posx);
    	  System.out.println("ocurrencia: " +r.cuenta(dat));
    	}
    	break;
      }


	}


  }





  public static class AvlTree<AnyType extends Comparable<? super AnyType>>
  {

	  public void printAnchura( )     {
	       if( isEmpty( ) )
	           System.out.println( "Empty tree" );
	       else
	           niveles( root );
	   }
	     private void niveles (AvlNode <AnyType> t){

	       MyQueue<AvlNode> c = new MyQueue<>();
	       AvlNode aux;
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


      public AvlTree( )
      {
          root = null;
          li.doClear();
      }


      public void insert( AnyType x )
      {
          root = insert( x, root );
      }


      public void remove( AnyType x )
      {
          root = remove( x, root );
      }


      private AvlNode<AnyType> remove( AnyType x, AvlNode<AnyType> t )
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
          return balance( t );
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



      public MyLinkedList<Integer> orden(MyLinkedList<Integer> lista){

  	   java.util.Iterator<Integer> itr = lista.iterator();
    	int min, aux;
		for (int j = 0; j < lista.size()-1; j++) {

		  min=j;
			for (int i = j+1; i < lista.size(); i++) {
			  if(lista.get(i)<lista.get(min))
				min=i;
			}

			if(j!=min){
			  aux=lista.get(j);
			  lista.add(j,lista.get(min));
			  lista.add(min,aux);
			}
		}
		return lista;


    }


      private static final int ALLOWED_IMBALANCE = 1;

      // Assume t is either balanced or within one of being balanced
      private AvlNode<AnyType> balance( AvlNode<AnyType> t )
      {
          if( t == null )
              return t;

          if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
              if( height( t.left.left ) >= height( t.left.right ) )
                  t = rotateWithLeftChild( t );
              else
                  t = doubleWithLeftChild( t );
          else
          if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
              if( height( t.right.right ) >= height( t.right.left ) )
                  t = rotateWithRightChild( t );
              else
                  t = doubleWithRightChild( t );

          t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
          return t;
      }

      public void checkBalance( )
      {
          checkBalance( root );
      }

      private int checkBalance( AvlNode<AnyType> t )
      {
          if( t == null )
              return -1;

          if( t != null )
          {
              int hl = checkBalance( t.left );
              int hr = checkBalance( t.right );
              if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                      height( t.left ) != hl || height( t.right ) != hr )
                  System.out.println( "OOPS!!" );
          }

          return height( t );
      }



      private AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t )
      {
          if( t == null ){
        	  li.add(x);
              return new AvlNode<>( x, null, null );

          }

          int compareResult = x.compareTo( t.element );

          if( compareResult < 0 )
              t.left = insert( x, t.left );
          else if( compareResult > 0 )
              t.right = insert( x, t.right );
          else{
              li.add(x);
              lii.add(x);
          }

          return balance( t );
      }



      public int cuenta (AnyType x){
    	int count=0;
    	for (int i = 0; i < li.size(); i++) {
    	  if(li.get(i)==x)
    		count++;
		}
    	return count;
      }


      private AvlNode<AnyType> findMin( AvlNode<AnyType> t )
      {
          if( t == null )
              return t;

          while( t.left != null )
              t = t.left;
          return t;
      }


      private AvlNode<AnyType> findMax( AvlNode<AnyType> t )
      {
          if( t == null )
              return t;

          while( t.right != null )
              t = t.right;
          return t;
      }


      private boolean contains( AnyType x, AvlNode<AnyType> t )
      {
          while( t != null )
          {
              int compareResult = x.compareTo( t.element );

              if( compareResult < 0 )
                  t = t.left;
              else if( compareResult > 0 )
                  t = t.right;
              else
                  return true;    // Match
          }

          return false;   // No match
      }

      public boolean containsTree(AvlTree x ){

    	return containsTree(root,x.root);
      }

      private boolean containsTree(AvlNode<AnyType> t, AvlNode<AnyType> x){

    	if (t == null && x== null) {
    	  return true;
    	}

    	while( t != null )
        {
    	  int a=(Integer)t.element;
    	  int b=(Integer)x.element;

          int compareResult = x.element.compareTo( t.element );

          if( compareResult < 0 )
             t = t.left;
          else if( compareResult > 0 )
              t = t.right;
          else
            return equalTree(t,x);
         }


    	return false;
      }

      private boolean equalTree(AvlNode<AnyType> t, AvlNode<AnyType> s){

    	if(t.height!=s.height)
    	  return false;
    	else{
    	MyLinkedList<AnyType>lt= out(t);
    	MyLinkedList<AnyType>ls=out(s);

    	for (int i = 0; i < lt.size(); i++) {
    	  if(lt.get(i)!=ls.get(i))
    		return false;
		}

    	  return true;
    	}
      }
    //verification Contains
      public boolean containsTheTree(AvlTree x ){

    	return containsTheTree(root,x.root);
      }

      private boolean containsTheTree(AvlNode<AnyType> t, AvlNode<AnyType> x){

    	if (t == null && x== null) {
    	  return true;
    	}

    	while( t != null )
        {
    	  int a=(Integer)t.element;
    	  int b=(Integer)x.element;

          int compareResult = x.element.compareTo( t.element );

          if( compareResult < 0 )
             t = t.left;
          else if( compareResult > 0 )
              t = t.right;
          else
            return equalTreeB(t,x);
         }


    	return false;
      }


      private boolean equalTreeB(AvlNode<AnyType> t, AvlNode<AnyType> s){

    	if(t==null && s== null){
    	  return true;
    	}

    	else if(t != null && s!= null){
    	  if(t.element==s.element){
    		if(equalTreeB(t.left,s.left)==true && equalTreeB(t.right,s.right)==true){
    		  return true;
    		}
    		else
    		  return false;
    	  }
    	}


    	return false;

      }





      private void printTree( AvlNode<AnyType> t )
      {
          if( t != null )
          {
              printTree( t.left );
              System.out.println( t.element );
              printTree( t.right );
          }
      }

      public MyLinkedList<AnyType> out(){

        return out( root );

    }

      private MyLinkedList<AnyType> out( AvlNode<AnyType> t )
      {
          if( t != null )
          {
              out( t.left );
              lii.add(t.element);
              out( t.right );
              return lii;
          }

          return lii;
      }


      private int height( AvlNode<AnyType> t )
      {
          return t == null ? -1 : t.height;
      }


      private AvlNode<AnyType> rotateWithLeftChild( AvlNode<AnyType> k2 )
      {
          AvlNode<AnyType> k1 = k2.left;
          k2.left = k1.right;
          k1.right = k2;
          k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
          k1.height = Math.max( height( k1.left ), k2.height ) + 1;
          return k1;
      }


      private AvlNode<AnyType> rotateWithRightChild( AvlNode<AnyType> k1 )
      {
          AvlNode<AnyType> k2 = k1.right;
          k1.right = k2.left;
          k2.left = k1;
          k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
          k2.height = Math.max( height( k2.right ), k1.height ) + 1;
          return k2;
      }


      private AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3 )
      {
          k3.left = rotateWithRightChild( k3.left );
          return rotateWithLeftChild( k3 );
      }


      private AvlNode<AnyType> doubleWithRightChild( AvlNode<AnyType> k1 )
      {
          k1.right = rotateWithLeftChild( k1.right );
          return rotateWithRightChild( k1 );
      }

      private static class AvlNode<AnyType>
      {
          AvlNode( AnyType theElement )
          {
              this( theElement, null, null );
          }

          AvlNode( AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt )
          {
              element  = theElement;
              left     = lt;
              right    = rt;
              height   = 0;
          }

          AnyType           element;      // The data in the node
          AvlNode<AnyType>  left;         // Left child
          AvlNode<AnyType>  right;        // Right child
          int               height;       // Height
      }


      private AvlNode<AnyType> root;
      private MyLinkedList<AnyType> li=new MyLinkedList<>();
      private MyLinkedList<AnyType> lii=new MyLinkedList<>();



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
