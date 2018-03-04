public class Counter implements Runnable{

    private int counter;
    private Thread main;

    public Counter(Thread main) {
        this.main = main;
    }

    @Override
    public void run() {
        try{
            counter = 1;
            while (counter < 10){
                System.out.println(counter);
                counter++;
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
