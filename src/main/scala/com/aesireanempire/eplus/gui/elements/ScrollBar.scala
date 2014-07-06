package com.aesireanempire.eplus.gui.elements

import com.aesireanempire.eplus.GUIAdvEnchantment
import net.minecraft.util.ResourceLocation

class ScrollBar(posX: Int, posY: Int, width: Int, height: Int, texture: ResourceLocation,
                screen: GUIAdvEnchantment) extends GuiElement(posX,
    posY, width, height, 48, texture, screen) {

    private var element: ListBox = null
    private var pages: Int = 0

    private var movementInterval: Int = 0

    def linkElement(element: ListBox) = {
        this.element = element
    }

    def getNumberOfElementsLinked: Int = {
        element.data.length
    }

    private var scrollPosition = 0

    override def drawExtras() {
        screen.mc.renderEngine.bindTexture(screen.TEXTURE)
        drawTexturedModalRect(posX, posY + scrollPosition, 0, 182, 12, 15)
    }

    override def update() = {
        pages = Math.floor(element.getNumberOfItems / 10D).toInt

        if (pages != 0) {
            movementInterval = Math.ceil((height - 16) / pages.toDouble).toInt
        } else {
            movementInterval = 0
        }

        if (pages == 0) {
            scrollPosition = 0
        }
    }

    override def mouseMoved(x: Int, y: Int): Unit = {
        val newPosition = y - posY - 6
        move(newPosition)
    }

    def move(position: Int, interval: Double = movementInterval) {
        var newPosition = position

        if (interval == 0) {
            return
        }

        if (position % interval != 0) {
            val movement = position % interval
            newPosition = position - movement.toInt
        }

        if (newPosition < 0) newPosition = 0

        if (newPosition + 16 >= height) newPosition = height - 16

        if(interval != movementInterval ) {
            element.setPage(newPosition / (interval * 5))
        } else {
            element.setPage(newPosition / interval)
        }

        scrollPosition = newPosition
    }

    override def isVisible: Boolean = true

    override def handleMouseInput(mouseEvent: Int, mouseX: Int, mouseY: Int) = {
        if (mouseEvent != 0) {
            val sign = if (mouseEvent < 0) 1 else -1
            move(scrollPosition + sign * movementInterval)
        } else {
            if (isDragging) {
                move(mouseY - posY - 6, movementInterval)
            }
        }
    }
}