public class CounterPrime extends Thread{
    Buffer b;
    CounterPrime(Buffer b){
        this.b=b;
        Thread t = new Thread(this, "CounterPrime");
        t.start();
    }
    static int i;
    @Override
    public void run() {
        while (true) {
            try {
                i=b.getPrimeCount();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        }

    }