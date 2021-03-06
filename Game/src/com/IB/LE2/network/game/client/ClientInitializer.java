package com.IB.LE2.network.game.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

@Deprecated
public class ClientInitializer extends ChannelInitializer<SocketChannel>
{

	private final ClientHandler ch;
	
	public ClientInitializer(ClientHandler ch)
		{
			this.ch = ch;
		}

	@Override
	protected void initChannel(SocketChannel sc) throws Exception
		{
			ChannelPipeline pipeline = sc.pipeline();
			
			pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
			pipeline.addLast("decoder", new StringDecoder());
			pipeline.addLast("encoder", new StringEncoder());
			
			pipeline.addLast("handler", new ClientHandler());
		}

}
