package mkcoding.services.threads;

import mkcoding.services.httpclient.HttpClientUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;

/**
 * Created by mx on 16/9/5.
 */
public class CompletionServerTest {

    private final static Logger logger = LoggerFactory.getLogger(CompletionServerTest.class.getCanonicalName());

    private final static String img_stdout_path = "/Users/mx/tmp";

    private final ExecutorService executorService;

    public CompletionServerTest(ExecutorService executorService) {
        this.executorService = executorService;
    }


    public void downloadHtml() {
        String content = HttpClientUtil.get("https://moment.douban.com/post/141773/?douban_rec=1");
//        Elements elements = Jsoup.parse(content).select("div.post-body");
        downloadImgs(content);
    }

    public void downloadImgs(String content) {
        if (StringUtils.isNotEmpty(content)) {
            Elements imgs = Jsoup.parse(content).select("div.post-body img");

            CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);

            for (final Element img : imgs) {
                completionService.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        String sourceUrl = img.attr("src");
                        logger.info("source url {}", sourceUrl);
                        //download img
                        URL imageUrl = new URL(sourceUrl);
                        String newPath = img_stdout_path + File.separator + FilenameUtils.getName(sourceUrl);
                        try (InputStream imageReader = new BufferedInputStream(imageUrl.openStream());
                             OutputStream imageWriter = new BufferedOutputStream(
                                     new FileOutputStream(newPath)
                             )) {
                            int readByte;
                            while ((readByte = imageReader.read()) != -1) {
                                imageWriter.write(readByte);
                            }
                        }

                        return newPath;
                    }
                });
            }

            for (int t = 0, n = imgs.size(); t < n; t++) {
                try {
                    Future<String> f = completionService.take();
                    String imgPath = f.get();
                    System.out.println(t + " ==> " + imgPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            System.out.println(imgs);
        }

    }

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        new CompletionServerTest(executorService).downloadHtml();

        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
    }
}
