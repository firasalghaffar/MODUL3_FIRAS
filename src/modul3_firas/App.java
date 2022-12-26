package modul3_firas;
import modul3_firas.Restaurant;
import modul3_firas.Waiters;

import java.util.Scanner;

import javax.swing.InputMap;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        Restaurant food = new Restaurant();
        int customerID, orderQty;
        try{
            System.out.println("Enter CustId : ");
            customerID = in.nextInt();

            System.out.println("Enter orderQty : ");
            orderQty = in.nextInt();

            int i = 0;
            while (i < orderQty){
                Thread t1 = new Thread(food);
                Thread t2 = new Thread(new Waiters(customerID, orderQty));

                t1.start();
                t2.start();
                t1.join();
                t2.join();

                i++;
            
            }
        } catch (Exception e) {
            System.out.println("Something is wrong.");
        }
    }
}
