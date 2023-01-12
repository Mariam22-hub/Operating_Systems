public class max extends Thread{
    Buffer b;
    max(Buffer b){
        this.b=b;
        Thread t = new Thread(this, "producer");
        t.start();
    }
    static int i;
    @Override
    public void run() {
        i=b.max;

    }
}