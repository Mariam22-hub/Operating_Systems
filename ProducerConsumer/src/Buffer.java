import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


public class Buffer {

	int N;
    
	public static int counPrime = 0;
    
	public static ArrayList<String> w = new ArrayList<String>();
    
    boolean flagCount = false;
    private int value;
    public static int max=0;
    boolean flag = false;
    int i = 1;
    public int size = 0; // the buffer bound
    
    public Integer store[];
    
    semaphore elements = new semaphore(0);

    public Buffer(int N) {
        this.size = N;
        store = new Integer[N];
    }

    private int inptr = 0;
    private int outptr = 0;
    boolean flagMax = false;
    // int counter = 1;

    ArrayList<String> lines = new ArrayList<>();

    public static File file = new File("G:\\Operating Systems\\ProducerConsumer\\out.txt");


    public synchronized void put(int value, int N) throws InterruptedException {

        if (flag) {
            wait();
        }

        this.value = value;
        this.N=N;
        
        if (elements.value < size) {

            elements.V();
            
            flag = false;
            
            store[inptr] = value;
            max=store[inptr];
            
            inptr++;
            counPrime++;
            
            System.out.println("counter "+counPrime);
            System.out.println("max "+max);
            
            if (inptr == size) {
                inptr = 0;
            }
            notifyAll();
            
            if (value >= N - size) {
                max = store[inptr];
                flag = true;
                flagMax = true;
                flagCount = true;
                notifyAll();

            }
        } else {

            flag = true;
            notify();
        }


    }

    public synchronized void get() throws InterruptedException {

        while (!flag) {
            wait();
        }

        int counter = 0;
        while (elements.value <= size && elements.value >= 1) {
            counter++;
            flag = true;

            // this is the value to be put in the file
            System.out.println(store[outptr]);
            lines.add(Integer.toString(store[outptr]));

            try {
                Files.write(file.toPath(), lines);
            } catch (IOException e) {

                e.printStackTrace();
            }

            outptr++;

            if (outptr == size) {
                outptr = 0;
            }


            elements.P();

            notify();
        }

        if (counter == size) {
            flag = false;
            notify();
        }

        //return value;
    }
    public synchronized int getMax() throws InterruptedException {
        while (!flagMax) {
            wait();
        }
        return max;

    }

    public synchronized int getPrimeCount() throws InterruptedException {
        while(!flagCount){
            wait();
        }
        return counPrime;
    }

    }

