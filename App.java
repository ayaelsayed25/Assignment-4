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

    @Override
    public void setPolynomial(char myChar, int[][] myArr) {
        myChar = Character.toLowerCase(myChar);
        if(myChar == 'a')
        {
            int termNoA = termNo;
            listA = setList(myArr, termNoA);
            listA = CoeffSum (listA);
        }
        else if(myChar == 'b')
        {
            int termNoB = termNo;
            listB = setList(myArr, termNoB);
            listB = CoeffSum(listB);
        }
        else if(myChar == 'c')
        {
            int termNoC = termNo;
            listC = setList(myArr, termNoC);
            listC = CoeffSum(listC);
        }
        else{
            throw new RuntimeException("unavailable");
        }
    }

    @Override
    public String print(char poly) {
        String polynomial;
        poly = Character.toLowerCase(poly);
        if(poly == 'a' && listA.size != 0)
        {
            polynomial = getPoly(listA);
        }
        else if(poly == 'b' && listB.size != 0)
        {
            polynomial = getPoly(listB);
        }
        else if(poly == 'c' && listC.size != 0)
        {
            polynomial = getPoly(listC);
        }
        else if (poly == 'r' && listR.size != 0)
        {
            polynomial = getPoly(listR);
        }
        else {
            throw new RuntimeException("Empty polynomial");
        }
        return polynomial;
    }

    @Override
    public void clearPolynomial(char poly) {
        poly = Character.toLowerCase(poly);
        if(poly == 'a')
        {
            listA.clear();
            System.out.println("Polynomial A is empty now");
        }
        else if(poly == 'b')
        {
            listB.clear();
            System.out.println("Polynomial B is empty now");
        }
        else if(poly == 'r')
        {
            listR.clear();
            System.out.println("Polynomial R is empty now");
        }
        else
        {
            listC.clear();
            System.out.println("Polynomial C is empty now");
        }
    }

    @Override
    public float evaluatePolynomial(char poly, float value) { /*******Original method ( it calls another method : evaluate)********/
        float result = 0;
        char myChar = Character.toLowerCase(poly);
        if(myChar == 'a')
        {
            result = evaluate(listA, value);
        }
        else if(myChar == 'b')
        {
            result = evaluate(listB, value);
        }
        else if(myChar == 'c')
        {
            result = evaluate(listC, value);
        }
        else
        {
            result = evaluate(listR, value);
        }

        return result;
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        poly1 = Character.toLowerCase(poly1);
        poly2 = Character.toLowerCase(poly2);
        DoublyLinkedList list1 = new DoublyLinkedList();
        DoublyLinkedList list2 = new DoublyLinkedList();
        switch (poly1){
            case 'a': list1 = listA; break;
            case 'b': list1 = listB; break;
            case 'c': list1 = listC; break;
            case 'r': list1 = listR; break;
        }
        switch (poly2){
            case 'a': list2 = listA; break;
            case 'b': list2= listB; break;
            case 'c': list2 = listC; break;
            case 'r': list2 = listR; break;
        }
        addPoly(list1,list2,false);
        int[][] arr = listToArr(listR);
        return arr;
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        poly1 = Character.toLowerCase(poly1);
        poly2 = Character.toLowerCase(poly2);
        DoublyLinkedList list1 = new DoublyLinkedList();
        DoublyLinkedList list2 = new DoublyLinkedList();
        switch (poly1){
            case 'a': list1 = listA; break;
            case 'b': list1 = listB; break;
            case 'c': list1 = listC; break;
            case 'r': list1 = listR; break;
        }
        switch (poly2){
            case 'a': list2 = listA; break;
            case 'b': list2= listB; break;
            case 'c': list2 = listC; break;
            case 'r': list2 = listR; break;
        }
        addPoly(list1,list2,true);
        int[][] arr = listToArr(listR);
        return arr;
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        poly1 = Character.toLowerCase(poly1);
        poly2 = Character.toLowerCase(poly2);
        DoublyLinkedList list1 = new DoublyLinkedList();
        DoublyLinkedList list2 = new DoublyLinkedList();
        switch (poly1){
            case 'a': list1 = listA; break;
            case 'b': list1 = listB; break;
            case 'c': list1 = listC; break;
            case 'r': list1 = listR; break;
        }
        switch (poly2){
            case 'a': list2 = listA; break;
            case 'b': list2= listB; break;
            case 'c': list2 = listC; break;
            case 'r': list2 = listR; break;
        }
        multiplyPoly(list1,list2);
        int [][] arr = listToArr(listR);
        return arr;
    }

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
        if (list.head == null){
            throw new RuntimeException("You can not set a polynomial to null");
        }
        return list;
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

    public float evaluate(DoublyLinkedList list, float value)
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

    public void addPoly(DoublyLinkedList list1, DoublyLinkedList list2, boolean subtract)
    {
        DoublyLinkedList tempList = new DoublyLinkedList();
        int size1 =list1.size(), size2 = list2.size();
        if(subtract == true){
            list2 = changeSign(list2);
        }
        for(int i=0; i<size1; i++)
        {
            Point p1 = (Point)list1.get(i);
            int exp = (int) p1.getY();
            int existsIn2 = containsExp(exp, list2); int existsInTemp = containsExp(exp, tempList);
            if( existsIn2 ==-1  && existsInTemp == -1)
                tempList.add(new Point((int) p1.getX(), (int)p1.getY()) );
            else if( existsIn2 != -1  && existsInTemp == -1)
            {
                Point p2 = (Point) list2.get(existsIn2);
                int sum = (int) (p1.getX() + p2.getX());
                tempList.add(new Point(sum, exp));
            }
        }
        for(int i=0; i<size2; i++)
        {
            Point p1 = (Point)list2.get(i);
            int exp = (int) p1.getY();
            int existsIn1 = containsExp(exp, list1); int existsInTemp = containsExp(exp, tempList);
            if( existsIn1==-1  && existsInTemp == -1)
                tempList.add(new Point((int) p1.getX(), (int)p1.getY()) );
        }
        int [][] temp = listToArr(tempList);
        temp = sortArray(temp, temp.length);
        listR = setList(temp, temp.length);
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

    public DoublyLinkedList CoeffSum(DoublyLinkedList list)
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

    public void multiplyPoly(DoublyLinkedList list1, DoublyLinkedList list2){
        int size1 =list1.size(), size2 = list2.size();
        DoublyLinkedList tempList = new DoublyLinkedList();
        for (int i = 0; i < size1; i++){
            for (int j = 0; j < size2; j++){
                int coffR, expR;
                Point p1 = (Point) list1.get(i);
                Point p2 = (Point) list2.get(j);
                coffR = p1.x * p2.x;
                expR = p1.y + p2.y;
                tempList.add(new Point(coffR,expR));
            }
        }
        tempList = CoeffSum(tempList);
        int[][] temp = listToArr(tempList);
        sortArray(temp,temp.length);
        listR = setList(temp,temp.length);
    }

    public  boolean available(char myChar){ //to check the polynomial before use it in operations
        myChar = Character.toLowerCase(myChar);
        if (myChar == 'a' && listA.size != 0){
            return true;
        }
        else if (myChar == 'b' && listB.size != 0){
            return true;
        }
        else if (myChar == 'c' && listC.size != 0){
            return true;
        }
        else if (myChar == 'r' && listR.size != 0){
            return true;
        }
        else{
            throw new RuntimeException("Variable is not set");
        }
    }
}