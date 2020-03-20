
package eg.edu.alexu.csd.datastructure.linkedList.cs01_cs10;

public class DNode {
    private Object data;
    private DNode next;
    private DNode prev;
    public DNode(Object data)
    {
        this.data = data;
    }
    public void setData(Object data)
    {
        this.data = data;
    }
    public void setNext(DNode next)
    {
        this.next = next;
    }
    public void setPrev(DNode prev)
    {
        this.prev = prev;
    }
    public Object getData()
    {
        return data;
    }
    public DNode getNext()
    {
        return next;
    }
    public DNode getPrev()
    {
        return prev;
    }
}
