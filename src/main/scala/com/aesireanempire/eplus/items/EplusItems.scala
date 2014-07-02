package com.aesireanempire.eplus.items

import cpw.mods.fml.common.registry.GameRegistry

object EplusItems {

    /**
     * Creates any item objects that need to be initialized.
     */
    def preInit() = {
        GameRegistry.registerItem(ItemAdvTableUpgrade, ItemAdvTableUpgrade.getUnlocalizedName)

    }

    /**
     * Registers all recipes needed for items
     */
    def init() = {}

}
