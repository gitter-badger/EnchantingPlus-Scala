package com.aesireanempire.eplus.items

import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack

object EplusItems {

    /**
     * Creates any item objects that need to be initialized.
     */
    def preInit() = {
        GameRegistry.registerItem(ItemAdvTableUpgrade, "advTableUpgrade")

    }

    /**
     * Registers all recipes needed for items
     */
    def init() = {
        GameRegistry.addShapedRecipe(
            new ItemStack(ItemAdvTableUpgrade),
            "gbg", "o o", "geg",
            'g'.asInstanceOf[Character], Items.gold_ingot, 'b'.asInstanceOf[Character], Items.writable_book,
            'o'.asInstanceOf[Character], Blocks.obsidian,
            'e'.asInstanceOf[Character], Items.ender_eye
        )
    }

}
