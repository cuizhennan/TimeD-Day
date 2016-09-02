package mkcoding.services.queues;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mx on 16/9/2.
 */
public class BlockingQueueTest {

    public static class Basket {
        BlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);

        public void produce() throws InterruptedException {
            basket.put("An apple");
        }

        public String consume() throws InterruptedException {
            return basket.take();
        }
    }

    public static void testBasket(final AtomicInteger atomicInteger) {
        final CountDownLatch downLatch = new CountDownLatch(10000);
        final CountDownLatch start = new CountDownLatch(1);
        //一个篮子对象
        final Basket basket = new Basket();

        class Producer implements Runnable {
            @Override
            public void run() {
                try {
                    while (true) {
                        start.await();
                        System.out.println("生产者准备生产苹果: " + System.currentTimeMillis());
                        basket.produce();
                        System.out.println("produce size =>" + atomicInteger.toString());
                        System.out.println("生产者生产苹果完毕: " + System.currentTimeMillis());
                        atomicInteger.incrementAndGet();
                        downLatch.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        class Consumer implements Runnable {
            @Override
            public void run() {
                try {
                    while (true) {
                        start.await();
                        System.out.println("消费者准备消费苹果：" + System.currentTimeMillis());
                        System.out.println(basket.consume());
                        System.out.println("consume size =>" + atomicInteger.toString());
                        System.out.println("消费者消费苹果完毕：" + System.currentTimeMillis());
                        atomicInteger.incrementAndGet();
                        downLatch.countDown();
                    }
                } catch (InterruptedException ex) {
                }
            }
        }

        int a = Runtime.getRuntime().availableProcessors();
        System.out.println(a);
        ExecutorService service = Executors.newFixedThreadPool(33);
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        service.submit(producer);
        service.submit(consumer);

        try {
            System.out.println("awit .....");
            start.countDown();
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end .....");
        service.shutdownNow();
    }

    public static void main(String[] args) {
        final AtomicInteger atomicInteger = new AtomicInteger();
        BlockingQueueTest.testBasket(atomicInteger);

    }
}
