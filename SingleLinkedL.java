import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SingleLinkedL {


  public static void main(String[] args) throws NumberFormatException, IOException
	{


	 MySingleLinkedList<Integer> lstLinked = new MySingleLinkedList<>( );

     for( int i = 0; i < 10; i++ )
             lstLinked.add( i );
     for( int i = 20; i < 30; i++ )
             lstLinked.add( 0, i );

     lstLinked.remove( 0 );
     lstLinked.remove( lstLinked.size( ) - 1 );

     System.out.println( lstLinked );


     MySingleLinkedList<Integer> lst = new MySingleLinkedList<>( );
     for( int i = 0; i < 10; i++ )
             lst.add( i );
     for( int i = 20; i < 30; i++ )
             lst.add( 0, i );
     lst.remove( 0 );
     lst.remove( lst.size( ) - 1 );
     System.out.println( lst );
     java.util.Iterator<Integer> itr = lst.iterator( );
     while( itr.hasNext( ) ) {
             itr.next( );
             itr.remove( );
             System.out.println( lst );
     }



//
//	  int numCase, numComand, data,pos, countA, countS;
//	  String comand="";
//	  String [] spl;
//	  boolean flg=true;
//	  countA=0;
//	  countS=0;
//
//	  MySingleLinkedList<Integer> lsta= new MySingleLinkedList<> ();
//
//
//	  BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
//	  numCase= Integer.parseInt(bf.readLine());
//
//	  for (int l = 0; l < numCase; l++) {
//
//		int caso=l+1;
//		System.out.println("Caso #" + caso+":");
//		numComand=Integer.parseInt(bf.readLine());
//
//
//		for (int i = 0; i < numComand; i++)
//		{
//		  comand=bf.readLine();
//		  spl=comand.split(" ");
//		  flg=true;
//
//		  switch (spl[0])
//		  {
//		    case "insertar":
//		      data=Integer.parseInt(spl[1]);
//			  pos=Integer.parseInt(spl[2]);
//		      if(pos<0 || pos>lsta.size())
//		    	System.out.println("insertar: posicion invalida");
//
//		      else {
//		    	lsta.add(pos, data);
//
//		    	for (int j = (lsta.size()-1); j > pos; j--)
//		    	  countA++;
//
//		    	for (int j = 0; j < pos; j++)
//		    	  countS++;
//
//		    	System.out.println("insertar: posicion valida");
//		      }
//		      break;
//
//
//		    case "consultar":
//			  pos=Integer.parseInt(spl[1]);
//		      if(pos>=0 && pos<lsta.size())
//		      {
//		    	System.out.println("consulta: " + lsta.get(pos));
//		    	for (int j = 0; j < pos; j++)
//		    	  countS++;
//		      }
//		      else
//		    	System.out.println("consulta: no encontrado");
//		      break;
//
//
//
//		    case "eliminar":
//		      pos=Integer.parseInt(spl[1]);
//
//		      if(pos>=0 && pos<lsta.size())
//		      {
//		    	for (int j = (lsta.size()-1); j > pos; j--)
//		    	  countA++;
//		    	for (int j = 0; j < pos; j++) {
//		    	  countS++;
//				}
//
//		    	lsta.remove(pos);
//		    	System.out.println("eliminar: posicion valida");
//
//		      }
//		      else
//		    	System.out.println("eliminar: posicion invalida");
//		      break;
//
//		    case "buscar":
//		      data=Integer.parseInt(spl[1]);
//
//		      for (int j = 0; j < lsta.size(); j++) {
//		    	if (lsta.get(j)==data){
//		    	  countA ++;
//		    	  countS++;
//		    	  System.out.println("buscar: " + j);
//		    	  flg=false;
//		    	  break;
//		    	}
//		    	countA++;
//		    	countS++;
//				}
//
//
//		      if(flg==true)
//		    	System.out.println("no existe numero");
//		      break;
//
//		    case "costo":
//		      System.out.println("costo: " +countA + " " + countS);
//		      break;
//
//		     default:
//		       numComand--;
//		       break;
//		   }
//		}
//		countA=0;
//		countS=0;
//		lsta.doClear();
//
//	  }
//
//


	}





public static class MySingleLinkedList<AnyType> implements Iterable<AnyType>
{

   public MySingleLinkedList( )
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


   public boolean add( AnyType x )
   {
       add( size( ), x );
       return true;
   }


   public void add( int idx, AnyType x )
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



     public AnyType remove( int idx )
   {
       return remove( getNodeAnt( idx ) );
   }


   private AnyType remove( Node<AnyType> p )
   {   Node<AnyType>b;
   	b=p.next;
       p.next = b.next;
       theSize--;
       modCount++;
       return b.data;
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
   	private Node<AnyType> aux=beginMarker;
       private int expectedModCount = modCount;
       private boolean okToRemove = false;


       public boolean hasNext( )
       {
           return current != null;
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

           aux.next.next=current;

           MySingleLinkedList.this.remove( aux );
           expectedModCount++;
           okToRemove = false;
       }
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
