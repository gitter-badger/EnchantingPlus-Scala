package com.aesireanempire.eplus.network

import com.aesireanempire.eplus.EnchantingPlus
import com.aesireanempire.eplus.blocks.BlockAdvEnchantmentTable
import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.items.ItemAdvTableUpgrade
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.fml.client.registry.ClientRegistry

class ClientProxy extends CommonProxy {
    override def init() {
        super.init()

        val modelMesher = Minecraft.getMinecraft.getRenderItem.getItemModelMesher

        //blocks
        modelMesher.register(Item.getItemFromBlock(BlockAdvEnchantmentTable), 0,
            new ModelResourceLocation(EnchantingPlus.MODID + ":" + BlockAdvEnchantmentTable.NAME, "inventory"))

        //items
        modelMesher.register(ItemAdvTableUpgrade, 0,
            new ModelResourceLocation(EnchantingPlus.MODID + ":" + ItemAdvTableUpgrade.NAME, "inventory"))
    }
}
