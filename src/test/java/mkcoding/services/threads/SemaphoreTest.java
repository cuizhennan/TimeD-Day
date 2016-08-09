package mkcoding.services.threads;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by mx on 16/8/8.
 */
public class SemaphoreTest<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public SemaphoreTest(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        this.sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded) sem.release();
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) sem.release();
        return wasRemoved;
    }

    public static void main(String[] args) throws InterruptedException {
        final SemaphoreTest<MySemaphore> semaphoreSemaphoreTest = new SemaphoreTest<MySemaphore>(10);

        final CountDownLatch startLatch = new CountDownLatch(3);

        int count = 20;
        final CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            final int no = i + 1;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startLatch.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        MySemaphore mySemaphore = new MySemaphore("Semaphore:" + no);
                        boolean wasAdded = semaphoreSemaphoreTest.add(mySemaphore);
                        System.out.println(mySemaphore.name + " add ==> " + wasAdded + " ==> " + semaphoreSemaphoreTest.set.size());

                        Thread.sleep((long) (Math.random() * 10000));
                        boolean wasRemoved = semaphoreSemaphoreTest.remove(mySemaphore);
                        System.out.println(mySemaphore.name + " remove ==> " + wasRemoved + " ==> " + semaphoreSemaphoreTest.set.size() + "\n");
                    } catch (InterruptedException ex) {
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });

            thread.start();
        }

        try {
            for (int j = 3; j > 0; j--) {
                Thread.sleep(1000);
                System.out.println("预备:" + j);
                startLatch.countDown();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Game Start");
        try {
            // 等待end变为0，即所有选手到达终点
            countDownLatch.await();
            System.out.println("Game Over");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

class MySemaphore {
    int id;
    String name;
    int age;
    String addr;

    public MySemaphore(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MySemaphore{" +
                "addr='" + addr + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}



