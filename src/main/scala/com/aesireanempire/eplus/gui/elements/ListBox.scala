package com.aesireanempire.eplus.gui.elements

import com.aesireanempire.eplus.{ContainerAdvEnchantment, GUIAdvEnchantment}
import net.minecraft.enchantment.EnchantmentData
import net.minecraft.util.ResourceLocation

class ListBox(posX: Int, posY: Int, width: Int, height: Int, texture: ResourceLocation,
              screen: GUIAdvEnchantment) extends GuiElement(posX, posY, width, height, 0, texture, screen) {
    var data = Array.empty[listItem]
    private var dataProvider: ContainerAdvEnchantment = null

    def getNumberOfItems = data.length


    override def isDragging: Boolean = data.filter(_.isDragging).length > 0

    override def setDragging(mouseX: Int, mouseY: Int, b: Boolean): Unit = {
        val item = getListItem(mouseX, mouseY)

        if (item != null)
            item.setDragging(mouseX, mouseY, b)
    }

    def setData(enchantments: Array[EnchantmentData]) = {

        if (enchantments.isEmpty) {
            data = Array.empty[listItem]
        }
        else {
            if (data.isEmpty) {
                for (enchantment <- enchantments) {
                    data = new listItem(enchantment, posX, posY + 14 * enchantments.indexOf(enchantment), width, 14,
                        this) +: data
                }
            }
        }
    }

    def setPage(page: Double) = {
        handleMovementChange(page)
    }

    def setDataProvider(container: ContainerAdvEnchantment) = {
        dataProvider = container
    }

    override def drawExtras() = {
        data.filter(_.isVisible).foreach(_.draw())
    }

    override def update() = {
        if (dataProvider != null && dataProvider.hasUpdated) {
            setData(dataProvider.dataSet)
            dataProvider.hasUpdated = false
        }

        val newEnchants = data.filter(_.getLevel > 0)

        if(newEnchants.isEmpty) data.foreach(_.setActive())

        for (newEnchant <- newEnchants;
             enchantment <- data
        ) {
            if(enchantment.getEnchantment.canApplyTogether(newEnchant.getEnchantment)){
                enchantment.setActive()
            } else {
                if(!newEnchants.contains(enchantment)){
                    enchantment.setDeactive()
                }
            }
        }

        data.foreach(_.update())
    }

    private def getListItem(x: Int, y: Int): listItem = {
        for (list <- data) {
            if (list.isUnderMouse(x, y)) {
                return list
            }
        }
        null
    }

    override def mouseMoved(x: Int, y: Int): Unit = {
        val list = getListItem(x, y)

        if (list != null) {
            list.mouseMoved(x, y)
        }
    }

    override def handleMovementChange(dY: Double) = {
        data.foreach(_.handleMovementChange(dY))
    }

    override def isVisible: Boolean = true

    override def handleMouseInput(mouseEvent: Int, mouseX: Int, mouseY: Int) = {
        val item = getListItem(mouseX, mouseY)

        if (item != null) {
            item.handleMouseInput(mouseEvent, mouseX, mouseY)
        }
    }
}

