package com.aesireanempire.eplus.gui.elements

import com.aesireanempire.eplus.GUIAdvEnchantment
import net.minecraft.client.gui.GuiButton
import net.minecraft.util.ResourceLocation

class ListBoxInfo(posX: Int, posY: Int, width: Int, height: Int, texture: ResourceLocation,
                  screen: GUIAdvEnchantment) extends ListBox[String](posX, posY, width, height,
    texture, screen) {

    override def setData(data: Array[String]): Unit = {
        if(data == null) {
            this.data = Array.empty[ListItem[String]]
        } else {
            this.data = Array.empty[ListItem[String]]
            for(string <- data) {
                this.data = this.data :+ new ListItemString(string, posX, posY + 14 * data.indexOf(string), width, 14,
                    this).asInstanceOf[ListItem[String]]
            }
        }
    }

    override def filterData(): Unit = {}

    override def actionPerformed(button: GuiButton): Unit = {}
}
