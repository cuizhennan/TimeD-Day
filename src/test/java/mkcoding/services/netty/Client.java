package mkcoding.services.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by mx on 16/8/1.
 */
public class Client {

    public static String host = "127.0.0.1";
    public static int port = 7878;

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new HelloClientInitializer());

            Channel ch = bootstrap.connect(host, port).sync().channel();

            //控制台输入
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            for (; ; ) {
                String line = in.readLine();
                if (line == null) {
                    continue;
                }
                /*
                  * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
                  * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
                  * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
                  * */
                ch.writeAndFlush(line + "\r\n");
            }
        } catch (Exception ex) {
            group.shutdownGracefully();
        }
    }
}
