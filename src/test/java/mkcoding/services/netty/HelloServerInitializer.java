package mkcoding.services.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by mx on 16/8/1.
 */
public class HelloServerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        ChannelPipeline channelPipeline = nioSocketChannel.pipeline();

        channelPipeline.addLast("framer", new DelimiterBasedFrameDecoder(8219, Delimiters.lineDelimiter()));

        channelPipeline.addLast("decoder", new StringDecoder());
        channelPipeline.addLast("encoder", new StringEncoder());

        //自己的逻辑handler
        channelPipeline.addLast("handler", new HelloServerHandler());
    }
}
