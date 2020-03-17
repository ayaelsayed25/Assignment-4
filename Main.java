
package eg.edu.alexu.csd.datastructure.linkedList.cs01_cs;
public class Main {
    public static void main(String[] args) {
        
        App obj = new App();
        obj.EnterPolynomial(); //Enter A here (for testing)
        obj.EnterPolynomial(); //Enter B 
        obj.clearPolynomial('a'); // remove a
        obj.showList(); //show me all polynomials I entered :(A is removed now)
        System.out.println("Polynomial : " + obj.print('b')); //print B 
    }
    
}
