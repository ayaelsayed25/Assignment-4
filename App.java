
package eg.edu.alexu.csd.datastructure.linkedList.cs01_cs10;
import java.awt.Point;
import java.io.*;
import java.lang.*;
import static java.lang.Math.pow;
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
        System.out.println("Enter the terms of your polynomial in this way : Coefficient1, Exponent1 (Enter) Coefficient2, Exponent2 (Enter)  ...");
        System.out.println("When you finish writing your polynomial, Press Enter twice");
        int[][] arr = new int[max][2];
        Scanner readInput=new Scanner(System.in);
        readInput.useDelimiter("\\,|\\n");
        termNo = 0;
        try {
            while (readInput.hasNextInt()){
                arr[termNo][0] = readInput.nextInt();
                arr[termNo][1] = readInput.nextInt();
                termNo++;
            }
        }
        catch (Exception e){
            System.out.println("Enter a valid input");
        }
        return arr;
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
        myChar = Character.toLowerCase(myChar);
        if(myChar == 'a')
        {
            int termNoA = termNo;
            listA = setList(myArr, termNoA);
            listA = CoeffSum (listA);   /*********** terms with the same exponent are collected together before any operation ***********/
        }
        else if(myChar == 'b')
        {
            int termNoB = termNo;
            listB = setList(myArr, termNoB);
            listB = CoeffSum(listB);
        }
        else
        {
            int termNoC = termNo;
            listC = setList(myArr, termNoC);
            listC = CoeffSum(listC);
        }
        
    }
    public int[][] sortArray(int[][] arr, int termNum)
    {
        int[][] temp = new int[termNum][2];
        for (int i = 0; i < termNum; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                temp[i][j] = arr[i][j];
            }
        }
        Arrays.sort(temp, new Comparator<int[]>() {
            @Override
            public int compare(final int[] entry1, final int[] entry2) {
                if (entry1[1] < entry2[1])
                    return 1;
                else
                    return -1;
            }
        });
        return temp;
    }
    public DoublyLinkedList setList (int[][] arr, int termNum)
    {
        int[][] temp = sortArray(arr, termNum);
        System.out.println(Arrays.deepToString(temp));
        DoublyLinkedList list = new DoublyLinkedList();
        Point p = new Point();
        for(int i=0; i<termNum; i++)
        {
            for(int j=0; j<2; j++)
            {
                if(j==0)
                {
                    p.x = temp[i][j];
                }
                else
                {
                    p.y = temp[i][j];
                }
            }
            list.add(new Point(p.x, p.y));
        }
        return list;
    }
    

    @Override
    public String print(char poly) {
        String polynomial;
        poly = Character.toLowerCase(poly);
        if(poly == 'a')
        {
            polynomial = getPoly(listA);
        }
        else if(poly == 'b')
        {
            polynomial = getPoly(listB);
        }
        else if(poly == 'r')
        {
            polynomial = getPoly(listR);
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
        poly = Character.toLowerCase(poly);
        if(poly == 'a')
        {
            listA.clear();
        }
        else if(poly == 'b')
        {
            listB.clear();
        }
        else if(poly == 'r')
        {
            listR.clear();
        }
        else
        {
            listC.clear();
        }
    }

    @Override
    public float evaluatePolynomial(char poly, float value) { /*******Original method ( it calls another method : evaluate)********/
        float result = 0;
        char myChar = Character.toLowerCase(poly); 
        if(myChar == 'a')
        {
            if(listA.isEmpty())
                System.out.println("A is Empty; You can't evaluate it\n");
            else
                result = evaluate(listA, value);
        }
        else if(myChar == 'b')
        { 
            if(listB.isEmpty())
                System.out.println("B is Empty; You can't evaluate it\n");
            else
                result = evaluate(listB, value);
        }
        else if(myChar == 'c')
        {
            if(listC.isEmpty())
                System.out.println("C is Empty; You can't evaluate it\n");
            else
                result = evaluate(listC, value);
        }
        else
        {
            if(listR.isEmpty())
                System.out.println("R is Empty; You can't evaluate it\n");
            else
                result = evaluate(listR, value);
        }       
        return result; 
    }
    public float evaluate(DoublyLinkedList list, float value)  /**********Calculates the result***********/
    {
        float result = 0;
        for(int i=0; i<list.size(); i++)
        {
            Point p = (Point) list.get(i); int exp = (int)p.getY(); int coeff = (int)p.getX();
            result = result + (float) ((float)coeff * pow(value, (float)exp));  
        }
        return result;
        
    }
    
    public int[][] listToArr(DoublyLinkedList list)
    {
        int size = list.size();
        int[][] arr = new int[size][2];
        for(int i=0; i<size; i++)
        {
            Point p = (Point) list.get(i);
            int coeff = (int) p.getX();
            int exp = (int) p.getY();
            for(int j =0; j<2; j++)
            {
                if(j == 0)
                    arr[i][j] = coeff;
                else
                    arr[i][j] = exp;
            }
        }
        return arr;
        
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        poly1 = Character.toLowerCase(poly1);
        poly2 = Character.toLowerCase(poly2);
        if((poly1 == 'a' && poly2 == 'b') || (poly1 == 'b' && poly2 == 'a'))
            addPoly(listA, listB, false);
        else if((poly1 == 'a' && poly2 == 'c') || (poly1 == 'c' && poly2 == 'a'))
            addPoly(listA, listC, false);
        else
            addPoly(listB, listC, false);
        int[][] arr = listToArr(listR);
        listR = setList(arr, arr.length);
        int[][] temp = listToArr(listR);
        return temp;
    }
    
    public void addPoly(DoublyLinkedList list1, DoublyLinkedList list2, boolean subtract)
    {
        DoublyLinkedList listAfter = new DoublyLinkedList();
        int size1 =list1.size(), size2 = list2.size();
        if(subtract == true)
            listAfter = changeSign(list2);
        else
            listAfter = list2;
        for(int i=0; i<size1; i++)
        {
            Point p1 = (Point)list1.get(i);
            int exp = (int) p1.getY();
            int existsIn2 = containsExp(exp, listAfter); int existsInR = containsExp(exp, listR);
            if( existsIn2==-1  && existsInR == -1)
                listR.add(new Point((int) p1.getX(), (int)p1.getY()) );
            else if( existsIn2 != -1  && existsInR == -1) 
            {
                Point p2 = (Point) listAfter.get(existsIn2);
                int sum = (int) (p1.getX() + p2.getX());
                listR.add(new Point(sum, exp));
            }    
        }
        for(int i=0; i<size2; i++)
        {
            Point p1 = (Point)listAfter.get(i);
            int exp = (int) p1.getY();
            int existsIn1 = containsExp(exp, list1); int existsInR = containsExp(exp, listR);
            if( existsIn1==-1  && existsInR == -1)
                listR.add(new Point((int) p1.getX(), (int)p1.getY()) );
        }
    }
    
    public int containsExp(int num, DoublyLinkedList list)
    {
        int size = list.size();
        for(int i=0; i<size; i++)
        {
            Point p = (Point) list.get(i);
            int exp = (int) p.getY();
            if(exp == num)
                return i;
        }
        return -1;
    }
    
    @Override
    public int[][] subtract(char poly1, char poly2) {
        poly1 = Character.toLowerCase(poly1);
        poly2 = Character.toLowerCase(poly2);
        if(poly1 == 'a' && poly2 == 'b') 
            addPoly(listA, listB, true);
        else if(poly1 == 'b' && poly2 == 'a')
            addPoly(listB, listA,true);
        else if(poly1 == 'a' && poly2 == 'c')
            addPoly(listA, listC,true);
        else if(poly1 == 'c' && poly2 == 'a')
            addPoly(listC, listA,true);
        else if(poly1 == 'b' && poly2 == 'c')
            addPoly(listB, listC,true);
        else
            addPoly(listC, listB,true);
        int[][] arr = listToArr(listR);
        listR = setList(arr, arr.length);
        int[][] temp = listToArr(listR);
        return temp;        
    }
    
    public DoublyLinkedList changeSign(DoublyLinkedList list)
    {
        DoublyLinkedList listAfter = new DoublyLinkedList();
        int size = list.size();
        for(int i=0; i<size; i++)
        {
            Point p = (Point) list.get(i);
            int coeff = (int) p.getX();
            int exp = (int) p.getY();
            listAfter.add(new Point(-coeff, exp));
        }
        return listAfter;
    }
    
    public DoublyLinkedList CoeffSum(DoublyLinkedList list)   /**************will be used for multiplication list.R = coeffSum(list.R)****/
    {
        DoublyLinkedList newList = new DoublyLinkedList();
        int sum;
        for(int i=0; i<list.size(); i++)
        {
            Point pi = (Point) list.get(i); int expi = (int)pi.getY();
            sum = (int) pi.getX();
            if(containsExp(expi, newList) == -1)
            {
                for(int j =i+1; j<list.size(); j++)
                {
                    Point pj = (Point) list.get(j); int expj = (int)pj.getY();
                    if(expi == expj)
                    {
                        sum = sum + (int)pj.getX();
                    }
                }
                newList.add(new Point(sum, expi));
            }
            

        }
        return newList;
    }
    

    @Override
    public int[][] multiply(char poly1, char poly2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
