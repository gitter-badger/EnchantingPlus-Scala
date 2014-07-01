package com.aesireanempire.eplus

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event._

@Mod(name = EnchantingPlus.MODNAME, modid = EnchantingPlus.MODID, modLanguage = "scala")
object EnchantingPlus {
  final val MODNAME = "Enchanting Plus"
  final val MODID = "eplus"

  @Mod.EventHandler
  def preInit(event: FMLPreInitializationEvent) {
    //Config
    //Register Items
    //Register Blocks
  }

  @Mod.EventHandler
  def init(event: FMLInitializationEvent) {
    //Register WorldGen
    //Register Recipes
    //Register Events
  }

  @Mod.EventHandler
  def postInit(event: FMLPostInitializationEvent) {

  }
}
