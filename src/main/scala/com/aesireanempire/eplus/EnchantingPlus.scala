package com.aesireanempire.eplus

import java.io.File

import com.aesireanempire.eplus.blocks.EplusBlocks
import com.aesireanempire.eplus.handlers.{ToolTipHandler, ConfigHandler, GUIHandler}
import com.aesireanempire.eplus.items.EplusItems
import com.aesireanempire.eplus.network.{CommonProxy, EplusChannelHandler, EplusPacket, PacketHandler}
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.{FMLLog, SidedProxy, Mod}
import net.minecraftforge.fml.common.event._
import net.minecraftforge.fml.common.network.{FMLOutboundHandler, FMLEmbeddedChannel, NetworkRegistry}
import net.minecraftforge.fml.relauncher.Side

import scala.collection.JavaConverters._


@Mod(name = EnchantingPlus.MODNAME, modid = EnchantingPlus.MODID, version = EnchantingPlus.VERSION, modLanguage = "scala")
object EnchantingPlus {
    final val MODNAME = "Enchanting Plus"
    final val MODID = "eplus"
    final val VERSION = "3.1.0a1"

    var channels = collection.mutable.Map.empty[Side, FMLEmbeddedChannel]

    @SidedProxy(clientSide = "com.aesireanempire.eplus.network.ClientProxy", serverSide = "com.aesireanempire.eplus.network.CommonProxy")
    var proxy: CommonProxy = null

    @EventHandler
    def preInit(event: FMLPreInitializationEvent) {
        ConfigHandler.init(event.getSuggestedConfigurationFile)

        EplusItems.preInit()
        EplusBlocks.preInit()

        ToolTipHandler.init(new File(event.getModConfigurationDirectory.toPath + File.separator + ".."))
    }

    @EventHandler
    def init(event: FMLInitializationEvent) {
        channels = NetworkRegistry.INSTANCE.newChannel("eplus", new EplusChannelHandler, new PacketHandler).asScala

        //Register WorldGen
        //Register Recipes

        EplusItems.init()
        EplusBlocks.init()

        //Register Events
        NetworkRegistry.INSTANCE.registerGuiHandler(this, GUIHandler)
        proxy.init()

        FMLLog.info("EnchantingPlus has been loaded!")
    }

    @EventHandler
    def postInit(event: FMLPostInitializationEvent) {

    }

    def sendToServer(packet: EplusPacket)= {
        channels.get(Side.CLIENT).get.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER)
        channels.get(Side.CLIENT).get.writeAndFlush(packet)
    }
}
