import java.util.ArrayDeque;
import java.util.ArrayList;

public class FixedThreadPool implements ThreadPool {
    volatile boolean stopAll = true;
    private int n;
    private final ArrayDeque<Runnable> tasks = new ArrayDeque<>();
    private final ArrayList<Thread> threads = new ArrayList<>();

    FixedThreadPool(int n) {
        this.n = n;
    }

    public void createTask(int n) {
        for (int i = 0; i < n; i++) {
            this.execute(new Task(i));
        }
    }

    public void killAll() {
        while (stopAll) {
            if (tasks.isEmpty()) {
                for (int i = 0; i < n; i++) {
                    threads.get(i).interrupt();
                }
                stopAll = false;
            }
        }
    }

    @Override
    public void start() {
        for (int i = 0; i < n; i++) {
            Thread tread = new Thread(new Handler("Handler " + i), "Handler " + i);
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

    private class Handler implements Runnable {
        private String string;

        Handler(String name) {
            this.string = name;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable runnable;
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            return;
                            //e.printStackTrace();
                        }
                    }
                    runnable = tasks.pop();
                }
                runnable.run();
            }
        }
    }

    private class Task implements Runnable {
        private int n;

        Task(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " - " + n + ": Run our task!");
        }
    }

}
