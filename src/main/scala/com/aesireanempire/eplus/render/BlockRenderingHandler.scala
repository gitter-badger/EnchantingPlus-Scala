package com.aesireanempire.eplus.render

import com.aesireanempire.eplus.blocks.BlockAdvEnchantmentTable
import cpw.mods.fml.client.registry.{ISimpleBlockRenderingHandler, RenderingRegistry}
import net.minecraft.block.Block
import net.minecraft.client.renderer.RenderBlocks
import net.minecraft.world.IBlockAccess

object BlockRenderingHandler extends ISimpleBlockRenderingHandler {
  val renderID = RenderingRegistry.getNextAvailableRenderId

  override def renderInventoryBlock(block: Block, metadata: Int, modelId: Int, renderer: RenderBlocks) {
    if (block.eq(BlockAdvEnchantmentTable)) {
      RenderAdvTable.renderItem()
    }
  }

  override def getRenderId: Int = renderID

  override def shouldRender3DInInventory(modelId: Int): Boolean = true

  override def renderWorldBlock(world: IBlockAccess, x: Int, y: Int, z: Int, block: Block, modelId: Int, renderer: RenderBlocks): Boolean = false
}
