//==================================================================
// Solution to Assn 2
// D. Stotts, 2/5/2016
//==================================================================

//==== client code =================================================
import java.util.*;
public class BridgeQueueListDemo {

  public static void main( String[] args ) {
    System.out.println("the args: " + Arrays.toString(args));
    // create front end objects, of type abstract Queue
    // but with different back end implementation objects
    
    Queue qL = new Queue("links"); // one implemented with linked cells
    Queue qA = new Queue("array"); // one implemented with array
    
    // speak the same interface "language" to each
    // show the results of each are the same
    
    // take data from the comman line args
    // take ops to perform from the assignment writeup
 
    // (1) take the first command line arg and add it to both queues
      String item = null;
    try {
         item= args[0];
    } catch (Exception e) {
        e.printStackTrace();
    }
    qL.enq(item);
    qA.enq(item);
    // (2) print the size of each queue
    System.out.println(qL.size());
    System.out.println(qA.size());
    // (3) print the item on front each queue
    System.out.println(qL.front());
    System.out.println(qA.front());
    // (4) remove an item from each queue
    qL.deq();
    qA.deq();
    // (5) print the size of each queue
    System.out.println(qL.size());
    System.out.println(qA.size());

    // now add all other command line args to each queue
    for (int i=1; i<args.length; i++) {
      qL.enq(args[i]); 
      qA.enq(args[i]);
    }
    for (int i=1; i<args.length; i++) {
      // (1) print front element on each queue
      System.out.println(qL.front());
      System.out.println(qA.front());
      // (2) do a deq on each queue
      qL.deq();
      qA.deq();
      // (5) print the size of each queue
      System.out.println(qL.size());
      System.out.println(qA.size());
    }
  } 
}

//=== Abstraction side ==================================
// this is the "front end"
// the client code talks to this abstract object
// the abstraction delegates executions of its operations 
// to a "back end" object that contains the implementation details

class Queue {   
   protected ListImp imp; 
       
   public Queue( String iType ) { 
     switch (iType) {
       case "links": imp = new ListImpLinks(); return;
       case "array": imp = new ListImpArray(); return;
       default: imp = new ListImpArray(); return;
     }
	}
   public Queue() { this( "array" ); }
   public void enq( String val ) { imp.ins( val, this.size() ); }
   public void deq() { imp.rem(0); }
	public String front() { return imp.get(0); }
   public int size() { return imp.size(); }
   public boolean empty() { return imp.empty(); }
}


//=== implementation side =======================================
// one of these is hooked into each front end object

interface ListImp {  
  void ins( String val, int loc ); 
  void rem(int loc);     
  String  get(int loc);            
  int  size(); 
  boolean empty();
}

class ListImpLinks implements ListImp { 
  private class Cell {
    String val=null;
    Cell next=null;
    Cell() { val = null; }
    Cell(String v) { val = v; }
  }
  
  private Cell first = null;
  private int count = 0;
  
  public void ins( String val, int loc ) {
    Cell p, c, nc = new Cell(val);
    if (loc==0) {
      nc.next = this.first;
      this.first = nc;
      this.count++;
      }
    else {
      p=this.first; c=this.first;
      for (int i=0; i<loc; i++) { p=c; c=c.next; }
      p.next = nc;
      nc.next = c;
      this.count++;
    }
  }
  
  public void rem(int loc) {
    // does nothing if Q is empty
    if (this.count==0) { return; }
    if (loc==0) { 
      this.first = this.first.next;
      this.count--;
      return;
    } 
    Cell p=this.first, c=this.first;
    for (int i=0; i<loc; i++) { p=c; c=c.next; }
    p.next = c.next;
    this.count--;
  }
  
  public String get(int loc) { 
    // return null string is Q is empty
    if (this.count==0) { return ""; }
    Cell p=this.first, c=this.first;
    for (int i=0; i<loc; i++) { p=c; c=c.next; }
    return c.val;
  } 
  public int size() { return this.count; }
  public boolean empty() { return (this.count==0); }
}


class ListImpArray implements ListImp { 
  private String[] elts = new String[2000]; 
  private int count = 0;
  public void ins( String val, int loc ) {  
    for (int i=count-1; i>loc; i--) { elts[i+1] = elts[i]; }
    elts[loc] = val;
    count++;
  }
  public void rem(int loc) { 
    for (int i=loc; i<count; i++) { elts[i] = elts[i+1]; }
    count--;
  }
  public String get(int loc) { return elts[loc]; }
  public int size() { return count; }
  public boolean empty() { return (count==0); }
}



