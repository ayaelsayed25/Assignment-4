
package eg.edu.alexu.csd.datastructure.linkedList.cs01_cs;

public class DoublyLinkedList implements ILinkedList {

    DNode head;
    DNode tail;
    int size = 0;
    
    public void insertHead(Object element)
    {
        DNode n= new DNode(element); 
        if(isEmpty())
        {
            tail = n;
        }
        else
        {
            head.setPrev(n);
        }
        n.setNext(head);
        head = n;
        size++;
    }
    
    
    @Override
    public void add(int index, Object element) {
        if(index == 0)
            insertHead(element);
        else
        {
            DNode mynode = new DNode(element);
            DNode n = head;
            for(int i=0; i<index; i++)
            {
                n = n.getNext();
            }
            DNode d= n.getPrev();
            d.setNext(mynode);
            n.setPrev(mynode);
            mynode.setNext(n);
            mynode.setPrev(d);
            size++;
            
        }
    }

    @Override
    public void add(Object element) {
        DNode n = new DNode(element);
        if(isEmpty())
        {
            head = n;
        }
        else
        {
            tail.setNext(n);
            n.setPrev(tail);
        }
        tail = n;
        size++;
    }

    @Override
    public Object get(int index) {
        DNode d = head;
        for(int i =0; i<index; i++)
        {
            d = d.getNext();
        }
        Object value = d.getData();
        return value;
    }

    @Override
    public void set(int index, Object element) {
        DNode d = head;
        for(int i=0; i<index; i++)
        {
            d = d.getNext();
        }
        d.setData(element);
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        if(head == null)
            return true;
        else
            return false;
    }

    @Override
    public void remove(int index) {
        if(index == size -1)
        {
            DNode d = tail.getPrev();
            tail = d;
            tail.setNext(null);
        }
        else if(index == 0)
        {
            DNode n = head.getNext();
            head = n;
            head.setPrev(null);
        }
        else{
        DNode n = head;
        for (int i =0; i<index; i++)
        {
            n = n.getNext();
        }
        DNode d = n.getPrev();
        DNode d2 = n.getNext();
        d.setNext(d2);
        d2.setPrev(d);
        n = null;
        }
        size--;
        
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        ILinkedList list = new DoublyLinkedList();
        DNode n = head;
        int i =0;
        for( i= 0; i<fromIndex; i++)
        {
            n = n.getNext();
        }
        for(int j= i; j<toIndex; j++)
        {
            list.add(n.getData());
            n = n.getNext();
        }
        list.add(n.getData());
        return list;
        
    }

    @Override
    public boolean contains(Object o) {
        DNode n = head;
        while(n != null)
        {
            if(n.getData() == o)
            {
                return true;
            }
            n = n.getNext();
        }
        return false;
    }
    public void show()
    {
        if(isEmpty())
        {
            System.out.println("Nothing");
        }
        else{
        DNode n = head;
        while(n != null)
        {
            System.out.println(n.getData());
            n = n.getNext();
        }
        }
    }
}
