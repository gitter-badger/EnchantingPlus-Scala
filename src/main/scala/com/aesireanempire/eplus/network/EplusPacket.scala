package com.aesireanempire.eplus.network

import io.netty.buffer.ByteBuf
import net.minecraft.entity.player.EntityPlayer

abstract class EplusPacket {
    def execute(player: EntityPlayer): Unit

    def readData(buf: ByteBuf): Unit

    def writeData(buf: ByteBuf): Unit
}
