
package eg.edu.alexu.csd.datastructure.linkedList.cs01_cs;
import java.awt.Point;
import java.io.*;
import java.lang.*;
import java.util.*;
public class App implements IPolynomialSolver {
    final int max = 150;
    int termNo = 0;
    DoublyLinkedList listA = new DoublyLinkedList();
    DoublyLinkedList listB = new DoublyLinkedList();
    DoublyLinkedList listC = new DoublyLinkedList();
    DoublyLinkedList listR = new DoublyLinkedList();
    
    public int[][] scanPolynomial()
    {
        System.out.println("Enter the terms of your polynomial in this order : Coefficient1, Exponent1 , Coefficient2, Exponent2, ...");
        System.out.println("When you finish writing your polynomial, Enter anything rather than integers ");
        int[][] arr = new int[max][2];
        Scanner readInput=new Scanner(System.in);
        readInput.useDelimiter("\\D");
        int termsCounter = 0;
        try {
            while (readInput.hasNextInt()){
                arr[termsCounter][0] = readInput.nextInt();
                arr[termsCounter][1] = readInput.nextInt();
                termsCounter++;
            }
        }
        catch (Exception e){
            System.out.println("Enter a valid input");
        }
        readInput.close();
        return arr;
    }
    public void showArr(int[][] arr) /*FOR TESTING IN MAIN*/
    {
        for(int i =0; i<termNo; i++ )
        {
            for(int j =0; j<2; j++)
            {
                System.out.println(arr[i][j]);
            }
            System.out.println(" ");
        }
    }
    public void showList() /*FOR TESTING IN MAIN*/
    {
        System.out.println("List A :");
        listA.show();
        System.out.println("List B :");
        listB.show();
        System.out.println("List C :");
        listC.show();
        
    }
    public void EnterPolynomial()
    {
        Scanner scanner = new Scanner(System.in);
        char myChar;
            do {   //scan the character as long as it is not between a and c
                 System.out.print("Choose a polynomial to set : A, B, or C \n"); myChar = scanner.next().charAt(0);
            } while (!Character.toString(myChar).matches("^[a-cA-C]*$" ) );
        int[][] myArr = scanPolynomial();
        setPolynomial(myChar, myArr);
    }

    @Override
    public void setPolynomial(char myChar, int[][] myArr) {
        if(myChar == 'A' || myChar == 'a')
        {
            int termNoA = termNo;
            listA = setList(myArr, termNoA);
        }
        else if(myChar == 'B' || myChar == 'b')
        {
            int termNoB = termNo;
            listB = setList(myArr, termNoB);
        }
        else
        {
            int termNoC = termNo;
            listC = setList(myArr, termNoC);
        }
        
    }
    
    public DoublyLinkedList setList (int[][] arr, int termNum)
    {
        
        DoublyLinkedList list = new DoublyLinkedList();
        Point p = new Point();
        for(int i=0; i<termNum; i++)
        {
            for(int j=0; j<2; j++)
            {
                if(j==0)
                {
                    p.x = arr[i][j];
                }
                else
                {
                    p.y = arr[i][j];
                }
            }
            list.add(new Point(p.x, p.y));
        }
        return list;
    }
    

    @Override
    public String print(char poly) {
        String polynomial;
        if(poly == 'A' || poly == 'a')
        {
            polynomial = getPoly(listA);
        }
        else if(poly == 'B' || poly == 'b')
        {
            polynomial = getPoly(listB);
        }
        else
        {
            polynomial = getPoly(listC);
        }
        return polynomial;
    }
    public String getPoly(DoublyLinkedList list)
    {
        String poly = " ";
        int size = list.size();
        for(int i=0; i<size; i++)
        {
            Point p = (Point) list.get(i);
            if(poly == " ")
            {
                poly = Integer.toString((int) p.getX()) + "X" + "^" + Integer.toString((int) p.getY());
            }
            else
            {
                poly = poly + " + " + Integer.toString((int) p.getX()) + "X" + "^" + Integer.toString((int) p.getY());
            }
        }
        return poly;
    }

    @Override
    public void clearPolynomial(char poly) {
        if(poly == 'A' || poly == 'a')
        {
            listA.clear();
        }
        else if(poly == 'B' || poly == 'b')
        {
            listB.clear();
        }
        else
        {
            listC.clear();
        }
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
