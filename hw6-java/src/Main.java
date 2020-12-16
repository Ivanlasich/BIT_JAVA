public class Main {
    public static void main(String[] args) throws InterruptedException {
        FixedThreadPool fixedThreadPool = new FixedThreadPool(3);
        fixedThreadPool.start();
        fixedThreadPool.createTask(1000);
        //Thread.sleep(2000);
        fixedThreadPool.killAll();

        /*
        ScalableThreadPool fixedThreadPool = new ScalableThreadPool(1,3);
        fixedThreadPool.start();
        fixedThreadPool.createTask(10000);
        fixedThreadPool.killAll();

         */
    }
}
