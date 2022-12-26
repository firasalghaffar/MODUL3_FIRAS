package modul3_firas;
import java.util.Random;

public class Restaurant implements Runnable{

    int foodnumber = 1;
    private boolean waitingForPickup;
    // Object lock;
    private static final Object lock = new Object();

    @Override
    public void run() {
        try {
            makeFood();
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("terdapat kesalahan dalam membuat makanan");
        }
    }

    public void setWaitingForPickup(){
        System.out.println("waiting for pickup food");
        this.waitingForPickup = false;
    }
    
    public int getFoodNumber(){
        return foodnumber;
    }

    public void makeFood(){
        synchronized(Restaurant.lock){
            if (this.waitingForPickup){
                try {
                    Restaurant.lock.wait();
                } catch (Exception e) {
                    System.out.println("tidak dapat membuat makanan");
                }
            }
            waitingForPickup = true;
            Restaurant.lock.notifyAll();
        }
        System.out.println("Making food");
        foodnumber = foodnumber++;
    }

    public static Object getLock(){
        return lock;
    }
}
