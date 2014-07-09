package com.aesireanempire.eplus.network

import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.{ChannelHandlerContext, SimpleChannelInboundHandler}

@Sharable
class PacketHandler extends SimpleChannelInboundHandler[EplusPacket]{
    override def channelRead0(ctx: ChannelHandlerContext, msg: EplusPacket): Unit = {

    }
}
