
package com.aesireanempire.eplus.gui.elements

import net.minecraft.client.gui.Gui
import net.minecraft.enchantment.{Enchantment, EnchantmentData}

class listItem(enchantmentData: EnchantmentData, x: Int, y: Int, width: Int,
               height: Int, box: ListBox) extends GuiElement(x, y, width, height, 0, null, box.screen) {

    private var active: Boolean = true
    private var page: Double = 0
    private var y1: Int = posY

    private var level: Int = enchantmentData.enchantmentLevel

    def setActive() = active = true
    def setDeactive() = active = false
    def getEnchantment = enchantmentData.enchantmentobj

    def isVisible: Boolean = {
        if (box.posX <= posX && posX + width <= box.posX + box.width) {
            if (box.posY <= y1 && y1 + height < box.height + box.posY)
                return true
        }

        false
    }

    override def handleMovementChange(dY: Double): Unit = {
        page = dY

        if(dY <= 0) page = 0
    }

  def getLevel: Int = level

  override def draw() {
        Gui.drawRect(posX + 1, y1 + 1, posX + width - 1, y1 + 14,
            0xff444444)
        Gui.drawRect(posX + 3, y1 + 3, posX + width - 3, y1 + 12,
            if(active) 0xffaaaaaa else 0xff00aa00)

        box.screen.drawString(getTranslatedName(enchantmentData.enchantmentobj, level),
            posX + 5, 4 + y1, 0xffffffff)

        val selectorOffset = ((level / enchantmentData.enchantmentobj.getMaxLevel.toDouble) * (width - 6)).toInt

        Gui.drawRect(posX + 1 + selectorOffset, y1 + 1, posX + selectorOffset + 5, y1 + 14,
          0xff444444)

    }

    override def drawExtras() {

    }

    override def update() {
        y1 = posY - (14 * (page * 5).toInt)
    }

    private def getTranslatedName(enchantment: Enchantment, level: Int): String = {
        var ret = enchantment.getTranslatedName(level)

        if (level == 0) {
            ret = if (ret.lastIndexOf(" ") == -1) enchantment.getName else ret.substring(0, ret.lastIndexOf(" "))
        }

        ret
    }

    override def mouseMoved(x: Int, y: Int): Unit = {}

  def changeLevel(dLevel: Int): Unit = {
    level = dLevel

    if(level <= 0) level = 0

    val maxLevel = enchantmentData.enchantmentobj.getMaxLevel
    if(level >= maxLevel ) level = maxLevel
  }

  override def isUnderMouse(x: Int, y: Int): Boolean = {
    if (posX <= x && x <= posX + width) {
      if (y1 <= y && y <= y1 + height) {
        return true
      }
    }
    false
  }

  override def handleMouseInput(mouseEvent: Int, mouseX: Int, MouseY: Int): Unit = {
      if (active) {
          if (mouseEvent != 0) {
              val sign = if (mouseEvent > 0) 1 else -1
              changeLevel(level + sign)
          } else {
              if (isDragging) {
                  val level = (((mouseX - posX + 6) / width.toDouble) * enchantmentData.enchantmentobj.getMaxLevel).toInt
                  changeLevel(level)
              }
          }
      }
  }
}

