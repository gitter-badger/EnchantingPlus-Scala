package com.aesireanempire.eplus.network

import net.minecraftforge.fml.common.network.{NetworkRegistry, FMLIndexedMessageToMessageCodec}
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.network.NetHandlerPlayServer

class EplusChannelHandler extends FMLIndexedMessageToMessageCodec[EplusPacket] {

    addDiscriminator(0, classOf[EnchantPacket])

    override def encodeInto(ctx: ChannelHandlerContext, msg: EplusPacket, target: ByteBuf): Unit = {
        msg.writeData(target)
    }

    override def decodeInto(ctx: ChannelHandlerContext, source: ByteBuf, msg: EplusPacket): Unit = {
        val player: EntityPlayer = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get().asInstanceOf[NetHandlerPlayServer].playerEntity
        msg.readData(source)

        msg.execute(player)
    }
}


