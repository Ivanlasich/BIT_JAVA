import java.util.ArrayDeque;
import java.util.Collection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool {
    private int n;
    private final ArrayDeque<Runnable> tasks = new ArrayDeque<>();
    private final ArrayList<Thread> threads = new ArrayList<>();

    FixedThreadPool(int n){
        this.n = n;
    }

    public void createTask(int n){
        for(int i=0; i < n; i++) {
            this.execute(new Task(i));
        }
    }


    public void killAll(){
        for(int i = 0; i < n; i++) {
            threads.get(i).interrupt();
        }
    }

    @Override
    public void start() {
        for(int i=0; i < n; i++) {
            Thread tread = new Handler("Handler " + i);
            threads.add(tread);
            tread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (tasks) {
            tasks.add(runnable);
            tasks.notify();
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
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            interrupt();
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
