package com.aesireanempire.eplus.network

import com.aesireanempire.eplus.EnchantingPlusMod
import com.aesireanempire.eplus.blocks.BlockAdvEnchantmentTable
import com.aesireanempire.eplus.items.ItemAdvTableUpgrade
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.model.ModelResourceLocation
import net.minecraft.item.Item

class ClientProxy extends CommonProxy {
    override def init() {
        super.init()

        val modelMesher = Minecraft.getMinecraft.getRenderItem.getItemModelMesher

        //blocks
        modelMesher.register(Item.getItemFromBlock(BlockAdvEnchantmentTable), 0,
            new ModelResourceLocation(EnchantingPlusMod.MODID + ":" + BlockAdvEnchantmentTable.NAME, "inventory"))

        //items
        modelMesher.register(ItemAdvTableUpgrade, 0,
            new ModelResourceLocation(EnchantingPlusMod.MODID + ":" + ItemAdvTableUpgrade.NAME, "inventory"))
    }
}
