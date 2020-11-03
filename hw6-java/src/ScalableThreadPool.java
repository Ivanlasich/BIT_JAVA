import java.util.ArrayDeque;
import java.util.ArrayList;

public class ScalableThreadPool implements ThreadPool {
    private int min;
    private int max;
    private int length;
    private int current = 0;

    private final ArrayDeque<Runnable> tasks = new ArrayDeque<>();
    private final ArrayList<Thread> threads = new ArrayList<>();

    ScalableThreadPool(int min, int max){
        this.min = min;
        this.max = max;
    }

    public void createTask(int n){
        for(int i=0; i < n; i++) {
            this.execute(new Task(i));
        }
    }


    public void killAll(){
        for(int i = 0; i < min; i++) {
            threads.get(i).interrupt();
        }
    }

    @Override
    public void start() {
        for(int i=0; i < min; i++) {
            length++;
            Thread tread = new Handler("Handler " + i);
            threads.add(tread);
            tread.start();
        }
    }

    private synchronized void Inc(){
        current++;
    }

    private synchronized void Dec(){
        current--;
    }

    private synchronized int getCurrent(){
        return current;
    }

    private synchronized void createThread() {
        if (length < max){
            length++;
            Thread tread = new Handler("Handler " + length);
            tread.start();
            threads.add(tread);

        }
    }

    private synchronized void kiillThread() {
        if (length > min){
            threads.remove(length-1).interrupt();
            length--;
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (tasks) {
            tasks.notify();
            tasks.add(runnable);
            if(getCurrent()==0){
                createThread();
            }
            if(getCurrent() > 0){
                kiillThread();
            }

        }
    }

    private class Handler extends Thread {

        Handler(String name){
            super(name);
        }

        @Override
        public void run() {
            while (!isInterrupted()){
                synchronized (tasks) {
                    while (tasks.isEmpty()){
                        try {
                            Inc();
                            tasks.wait();
                            Dec();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;

                        }
                    }

                    Runnable runnable = tasks.pop();
                    runnable.run();
                }
            }
        }

    }


    private class Task implements Runnable {
        private int n;
        Task(int n){
            this.n = n;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" - " + n + ": Run our task!");
        }
    }

}
