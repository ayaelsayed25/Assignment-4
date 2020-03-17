package eg.edu.alexu.csd.datastructure.linkedList.cs01_cs10;
import eg.edu.alexu.csd.datastructure.linkedList.cs01_cs10.ILinkedList;

import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class LinkedList implements ILinkedList {
    Node head;
    int size = 0;
    public void add(int index, Object element) {
        Node node = new Node();
        node.data = element;
        node.next = null;
        if (index == 0){ //to add at the start
            node.next = head;
            head = node;
            size++;
        }
        else if(index > 0) { // a valid index
            Node n = head;
            for (int i = 0; i < index-1; i++){ //loop until u get the chosen index
                n = n.next;
            }
            node.next = n.next;
            n.next = node;
            size++;
        }

    }
    public void add(Object element) {
        Node node = new Node();
        node.data = element;
        node.next = null;
        if (head == null){ // for the first element in the list
            head = node;
            size++;
        }
        else {
            Node n = head;
            while (n.next != null){ //loop to the last place
                n = n.next;
            }
            n.next = node;
            size++;
        }
    }

    public Object get(int index) {
        Node n = head;
        for (int i = 0; i < index; i++){
            n = n.next;
        }
        return n.data;
    }

    public void set(int index, Object element) {
        Node n = head;
        for (int i = 0; i < index; i++){
            n = n.next;
        }
        n.data = element;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        if (head == null || size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void remove(int index) {
        if (index == 0){
            head = head.next;
            size--;
        }
        else {
            Node n = head;
            Node temp = null;
            for (int i = 0; i < index - 1; i++){
                n = n.next;
            }
            temp = n.next;
            n.next = temp.next;
            size--;
        }
    }

    public int size() {
        return size;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        Node n = head;
        int i = 0;
        LinkedList sub = new LinkedList();
        for (i = 0; i <= fromIndex; i++){
            n = n.next;
        }
        for (int j = i; j < toIndex; j++){
            sub.add(n.data);
            n = n.next;
        }

        return sub;
    }

    public boolean contains(Object o) {
        Node node = head;
        boolean flag = false;
        while (node != null){
            if (node.data == o){
                flag = true;
            }
            node = node.next;
        }
        if(flag){
            return true;
        }
        else {
            return false;
        }
    }

    public void  show(){
        if (head == null) return;
        Node node = head;
        while (node.next != null){
            System.out.println(node.data);
            node = node.next;
        }
        System.out.println(node.data);
    }
/**
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(new Point(5,4));
        list.add(6);
        list.add(8);
        list.add("dfsg");
        list.add(125);
        int [][] A = new int[150][2];
        Scanner readInput=new Scanner(System.in);
        readInput.useDelimiter("\\D");
        int termsCounter = 0;
        try {
            while (readInput.hasNextInt()){
                A[termsCounter][0] = readInput.nextInt();
                A[termsCounter][1] = readInput.nextInt();
                termsCounter++;
            }
        }
        catch (Exception e){
            System.out.println("Enter a valid input");
        }
        readInput.close();
        System.out.println(Arrays.deepToString(A));

    }
   * */
}

