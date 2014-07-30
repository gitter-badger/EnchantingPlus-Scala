package com.aesireanempire.eplus.network

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.render.{BlockRenderingHandler, RenderAdvTable, FixedTechneModelLoader}
import cpw.mods.fml.client.registry.{RenderingRegistry, ClientRegistry}
import net.minecraftforge.client.model.AdvancedModelLoader

class ClientProxy extends CommonProxy {
    override def init() {
        super.init()
        AdvancedModelLoader.registerModelHandler(new FixedTechneModelLoader())

        RenderingRegistry.registerBlockHandler(BlockRenderingHandler)

        ClientRegistry.bindTileEntitySpecialRenderer(classOf[TileEntityAdvEnchantmentTable], RenderAdvTable)
    }
}
