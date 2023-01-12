public class Consumer extends Thread{
    private Buffer b;

    public Consumer(Buffer b){
        this.b = b;
        Thread t = new Thread(this,"consumer");

        t.start();

    }

    public void run() {
        while(true){
            try {
                b.get();
            } catch (InterruptedException e1) {

                e1.printStackTrace();
            }

        }
        // TODO Auto-generated method stub

    }

}
