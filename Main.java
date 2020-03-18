
package eg.edu.alexu.csd.datastructure.linkedList.cs01_cs10;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        App obj = new App();
        boolean flag2 = true;
        while (flag2){
            System.out.println("Please choose an action:\n" +
                    "------------------------\n" +
                    "1- Set a polynomial variable\n" +
                    "2- Print the value of a polynomial variable\n" +
                    "3- Add two polynomials\n" +
                    "4- Subtract two polynomials\n" +
                    "5- Multiply two polynomials\n" +
                    "6- Evaluate a polynomial" +
                    "7- Exit");
            Scanner userChoice = new Scanner(System.in);
            boolean flag1 = true;
            String choice = "";
            while (flag1){
                choice = userChoice.nextLine();
                if (isNumeric(choice) && Integer.parseInt(choice) > 0 && Integer.parseInt(choice) < 8){
                    flag1 = false;
                }
                else System.out.println("Enter a valid input!");
            }
            switch (choice){
                case "1":{
                    obj.EnterPolynomial();
                    break;
                }
                case "2":{
                    System.out.print("Choose a polynomial to print : A, B, C or R \n");
                    char myChar = chooseChar();
                    System.out.println(obj.print(myChar));
                    break;
                }
                case "3":{
                    System.out.println("Choose the first polynomial:");
                    char myChar1 = chooseChar();
                    System.out.println("Choose the second polynomial:");
                    char myChar2 = chooseChar();
                    System.out.println("Result set in R: " + Arrays.deepToString(obj.add(myChar1,myChar2)));
                    break;
                }
                case "4":{
                    System.out.println("Choose the first polynomial:");
                    char myChar1 = chooseChar();
                    System.out.println("Choose the second polynomial:");
                    char myChar2 = chooseChar();
                    obj.subtract(myChar1,myChar2);
                    System.out.println("Result set in R: " + Arrays.deepToString(obj.subtract(myChar1,myChar2)));
                    break;
                }
                case "5":{
                    System.out.println("not yet");
                    break;
                }
                case "6":{
                    System.out.println("no");
                    break;
                }
                case "7":{
                    return;
                }
            }
        }
    }
    public static char chooseChar(){
        Scanner scanner = new Scanner(System.in);
        char myChar;
        do {
            myChar = scanner.next().charAt(0);
        } while (!Character.toString(myChar).matches("^[a-cA-CrR]*$"));
        return myChar;
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
