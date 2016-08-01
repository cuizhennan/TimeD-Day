package mkcoding.services.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

/**
 * Created by mx on 16/8/1.
 */
public class HelloServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " Say: " + msg);

        //返回客户端消息
        ctx.writeAndFlush("Received your message !\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RemoteAddress :" + ctx.channel().remoteAddress() + " active !");

        ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }
}
