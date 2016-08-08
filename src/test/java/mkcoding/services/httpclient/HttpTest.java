package mkcoding.services.httpclient;

import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mx on 16/8/4.
 */
public class HttpTest {
//    public static void main(String[] args) {
//        // URL列表数组
//        String[] urisToGet = {
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//                "http://blog.csdn.net/u012147290/article/details/50618606",
//        };
//
//        long start = System.currentTimeMillis();
//        try {
////            int pagecount = urisToGet.length;
//            int pagecount = 500;
////            ExecutorService executors = Executors.newFixedThreadPool(pagecount);
//            ExecutorService executors = Executors.newCachedThreadPool();
//            CountDownLatch countDownLatch = new CountDownLatch(pagecount);
//            for (int i = 0; i < pagecount; i++) {
//                // 启动线程抓取
//                executors.execute(new GetRunable("http://blog.csdn.net/u012147290/article/details/50618606", countDownLatch, i));
//            }
//
//            countDownLatch.await();
//            executors.shutdown();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("线程" + Thread.currentThread().getName() + "," + System.currentTimeMillis() + ", 所有线程已完成，开始进入下一步！");
//        }
//
//        long end = System.currentTimeMillis();
//        System.out.println("consume -> " + (end - start));
//    }

    public static void main(String[] args) {
        System.out.println(HttpClientUtil.get("http://fashion.self.com.cn/selfstyle/pic_174429cf15df8c8c.html"));
    }

    static class GetRunable implements Runnable {
        private CountDownLatch countDownLatch;
        private String url;
        private int index;

        public GetRunable(String url, CountDownLatch countDownLatch, int index) {
            this.url = url;
            this.countDownLatch = countDownLatch;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                System.out.println(Jsoup.parse(HttpClientUtil.get(url)).select("div.article_title").text() + "==> " + index);
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}
