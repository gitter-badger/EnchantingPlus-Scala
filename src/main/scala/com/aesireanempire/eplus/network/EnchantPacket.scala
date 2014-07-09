package com.aesireanempire.eplus.network

import com.aesireanempire.eplus.ContainerAdvEnchantment
import io.netty.buffer.ByteBuf
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.player.EntityPlayer

class EnchantPacket(enchantments: Map[Enchantment, Int]) extends EplusPacket {

    var m_enchantments = collection.mutable.Map.empty[Int, Int]

    def this() = this(Map.empty[Enchantment, Int])

    override def readData(buf: ByteBuf): Unit = {
        val length = buf.readInt()

        for (i <- 0 until length) {
            val enchantment = buf.readInt()
            val level = buf.readInt()

            m_enchantments = m_enchantments.++=(Map(enchantment -> level))
        }
    }

    override def writeData(buf: ByteBuf): Unit = {
        val length = enchantments.size

        buf.writeInt(length)
        for (enchantment <- enchantments) {
            buf.writeInt(enchantment._1.effectId)
            buf.writeInt(enchantment._2)
        }
    }

    override def execute(player: EntityPlayer): Unit = {
        player.openContainer.asInstanceOf[ContainerAdvEnchantment].enchantItem(player, m_enchantments, 0)
    }
}
