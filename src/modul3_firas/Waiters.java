package modul3_firas;

public class Waiters implements Runnable{
    int orderQty, customerID;
    int foodPrice = 25000;

    public Waiters(int customerID, int orderQty){
        this.customerID = customerID;
        this.orderQty = orderQty;
    }

    @Override
    public void run() {
        try {
            getFood();
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("terdapat kesalahan dalam mengambil makanan");
        }
    }

    public void orderinfo(){
        System.out.println("Customer ID :" + customerID);
        System.out.println("Number of Food :" + orderQty);
        System.out.println("Total Price :" + foodPrice);
    }

    public void getFood(){
        synchronized(Restaurant.getLock()){

            System.out.println("Waiter: ");
            Restaurant food = new Restaurant();
            food.setWaitingForPickup();

            if (food.getFoodNumber() == this.orderQty + 1){
                orderinfo();
                System.exit(0);
            }
            food.getLock().notifyAll();
            System.out.println("done");

        }
    }
}

