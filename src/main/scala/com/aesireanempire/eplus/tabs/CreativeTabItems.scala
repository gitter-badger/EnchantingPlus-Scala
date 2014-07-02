package com.aesireanempire.eplus.tabs

import com.aesireanempire.eplus.items.ItemAdvTableUpgrade
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

object CreativeTabItems extends CreativeTabs("eplus.items") {
  override def getTabIconItem: Item = ItemAdvTableUpgrade
}
