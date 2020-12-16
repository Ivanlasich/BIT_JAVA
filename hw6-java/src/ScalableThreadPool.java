import java.util.ArrayDeque;
import java.util.ArrayList;

public class ScalableThreadPool implements ThreadPool {
    volatile boolean stopAll = true;
    private int min;
    private int max;
    private int length;
    private int current = 0;

    private final ArrayDeque<Runnable> tasks = new ArrayDeque<>();
    private final ArrayList<Thread> threads = new ArrayList<>();

    ScalableThreadPool(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void createTask(int n) {
        for (int i = 0; i < n; i++) {
            this.execute(new Task(i));
        }
    }

    public void killAll() {
        while (stopAll) {
            if (tasks.isEmpty()) {
                for (Thread thread : threads) {
                    thread.interrupt();
                }
                stopAll = false;
            }
        }
    }

    @Override
    public void start() {
        for (int i = 0; i < min; i++) {
            length++;
            Thread tread = new Thread(new Handler("Handler " + i), "Handler " + length);
            threads.add(tread);
            tread.start();
        }
    }

    private synchronized void inc() {
        current++;
    }

    private synchronized void dec() {
        current--;
    }

    private synchronized int getCurrent() {
        return current;
    }

    private synchronized void createThread() {
        if (length < max) {
            length++;
            Thread tread = new Thread(new Handler("Handler " + length), "Handler " + length);
            tread.start();
            threads.add(tread);

        }
    }

    private synchronized void kiillThread() {
        if (length > min) {
            threads.remove(length - 1).interrupt();
            length--;
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (tasks) {
            tasks.notify();
            tasks.add(runnable);
            if (getCurrent() == 0) {
                createThread();
            }
            if (getCurrent() > 0) {
                kiillThread();
            }
        }
    }

    private class Handler implements Runnable {
        private String name;

        Handler(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable runnable;
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            inc();
                            tasks.wait();
                            dec();
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                            return;
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
