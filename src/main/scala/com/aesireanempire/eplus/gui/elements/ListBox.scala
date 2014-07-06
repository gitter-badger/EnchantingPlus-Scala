package com.aesireanempire.eplus.gui.elements

import com.aesireanempire.eplus.{ContainerAdvEnchantment, GUIAdvEnchantment}
import net.minecraft.enchantment.EnchantmentData
import net.minecraft.util.ResourceLocation

class ListBox(posX: Int, posY: Int, width: Int, height: Int, texture: ResourceLocation,
              screen: GUIAdvEnchantment) extends GuiElement(posX, posY, width, height, 0, texture, screen) {
    var data = Array.empty[listItem]
    private var dataProvider: ContainerAdvEnchantment = null
    private var page: Int = 0

    def getNumberOfItems = data.length

    def setData(enchantments: Array[EnchantmentData]) = {

        if (enchantments.isEmpty) {
            data = Array.empty[listItem]
        }
        else {
            for (enchantment <- enchantments) {
                data = new listItem(enchantment, posX, posY + 14 * enchantments.indexOf(enchantment), width, 14,
                    this) +: data
            }
        }
    }

    def setPage(page: Int) = {
        handleMovementChange(page)
    }

    def setDataProvider(container: ContainerAdvEnchantment) = {
        dataProvider = container
    }

    override def drawExtras() = {
        for (item <- data) {
            if(item.isVisible) {
                item.draw()
            }
        }
    }

    override def update() = {
        if (dataProvider != null && dataProvider.hasUpdated) {
            setData(dataProvider.dataSet)
            dataProvider.hasUpdated = false
        }

        for (item <- data) {
            item.update()

        }
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

    override def handleMovementChange(dY: Int) = {
        for (list <- data) {
            list.handleMovementChange(dY)
        }
    }

    override def isVisible: Boolean = true

    override def handleMouseInput(mouseEvent: Int, mouseX: Int, mouseY: Int) = {
        val item = getListItem(mouseX, mouseY)

        if (item != null) {
            item.handleMouseInput(mouseEvent, mouseX, mouseY)
        }
    }
}

