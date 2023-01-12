public class Producer extends Thread {
    public Buffer b;
    int N;
    
    int size ;
    int counter=0;
    
    public Producer(Buffer b,int N,int size){
        
    	this.b = b;
        this.N = N;
        this.size=size;
        Thread t = new Thread(this, "producer");
        
        t.start();
//		t.run();
    }
    
    boolean isPrime(int i){
        int counter = 0;
        
        for(int j=1;j<=i;j++){
            
        	if(i%j==0){
                counter++;
            }
        }
        
        if(counter==2) return true;
        else return false;
    }

    public void run() {
        int i = 0;
        while(true){
            try {
                //System.out.println("i= "+i);
                if(counter == size){

                    i=i;
                    counter = -1;
                }
                else{
                    i++;
                }
                
                if(isPrime(i) && i<=N){
                    //System.out.println("counter= "+counter);

                    b.put(i,N);

                    counter++;
                }


            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

        // TODO Auto-generated method stub

    }

}
