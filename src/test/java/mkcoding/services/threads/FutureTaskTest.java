package mkcoding.services.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by mx on 16/8/19.
 */
public class FutureTaskTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Task task = new Task();
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//        executor.submit(futureTask);
//        executor.shutdown();
//
//        Thread.sleep(1000);
//
//        System.out.println("主线程在执行任务");
//
//        System.out.println("task运行结果" + futureTask.get());
//
//        System.out.println("所有任务执行完毕");

        List<Animal> list = new ArrayList<>();

        Animal a = new Animal("a", "asdf", 12);
        Animal b = new Animal("b", "zxcvzx", 2);
        list.add(b);
        list.add(a);

        System.out.println(list);
    }

}

class Animal implements Comparable<Animal> {
    public String name;
    public int year_discovered;
    public String population;

    public Animal(String name, String population, int year_discovered) {
        this.name = name;
        this.population = population;
        this.year_discovered = year_discovered;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", year_discovered=" + year_discovered +
                ", population='" + population + '\'' +
                '}';
    }

    @Override
    public int compareTo(final Animal o) {
        return Integer.compare(this.year_discovered, o.year_discovered);
    }
}

