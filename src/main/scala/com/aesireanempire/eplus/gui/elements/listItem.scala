package com.aesireanempire.eplus.gui.elements

import net.minecraft.client.gui.Gui
import net.minecraft.enchantment.{Enchantment, EnchantmentData}

class listItem(enchantmentData: EnchantmentData, x: Int, y: Int, width: Int,
               height: Int, box: ListBox) extends GuiElement(x, y, width, height, 0, null, box.screen) {

    def isVisible: Boolean = {
        if (box.posX <= posX && posX + width <= box.posX + box.width) {
            if (box.posY <= posY && posY + height <= box.height + box.posY)
                return true
        }

        false
    }

    override def draw() {
        Gui.drawRect(posX + 1, posY + 1, posX + width - 1, posY + 14,
            0xff444444)
        Gui.drawRect(posX + 3, posY + 3, posX + width - 3, posY + 12,
            0xffaaaaaa)

        box.screen.drawString(getTranslatedName(enchantmentData.enchantmentobj, enchantmentData.enchantmentLevel),
            posX + 5, 4 + posY, 0xffffffff)
    }

    override def drawExtras() {

    }

    override def update() {

    }

    private def getTranslatedName(enchantment: Enchantment, level: Int): String = {
        var ret = enchantment.getTranslatedName(level)

        if (level == 0) {
            ret = if (ret.lastIndexOf(" ") == -1) enchantment.getName else ret.substring(0, ret.lastIndexOf(" "))
        }

        ret
    }

    override def mouseMoved(x: Int, y: Int): Unit = {}
}

