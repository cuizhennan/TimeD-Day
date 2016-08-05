package mkcoding.services.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by mx on 16/7/28.
 */
public class HelloServer {

    /**
     * 服务端监听的端口地址
     */
    private static final int portNumber = 7878;

    public static void main(String[] args) throws InterruptedException {

        //父线程
        final NioEventLoopGroup parentBosser = new NioEventLoopGroup();
        //子线程
        final NioEventLoopGroup childWorker = new NioEventLoopGroup();
        try {

            final ServerBootstrap bootstrap = new ServerBootstrap();
            //设置线程组
            bootstrap.group(parentBosser, childWorker);
            //设置channel类型
            bootstrap.channel(NioServerSocketChannel.class);

            bootstrap.childHandler(new HelloServerInitializer());

            ChannelFuture f = bootstrap.bind(portNumber).sync();
            f.channel().closeFuture().sync();
        } finally {
            parentBosser.shutdownGracefully();
            childWorker.shutdownGracefully();
        }

//        bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
//            @Override
//            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
//                try {
//                    //绑定并监听端口
//                    ChannelFuture future = bootstrap.bind(portNumber).sync();
//                    //等待关闭事件
//                    future.channel().closeFuture().sync();
//                } catch (InterruptedException ex) {
//                    throw new Exception("");
//                } finally {
//                    parentBosser.shutdownGracefully();
//                    childWorker.shutdownGracefully();
//                }
//            }
//        });
    }
}
