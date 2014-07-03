package com.aesireanempire.eplus.gui.elements

import com.aesireanempire.eplus.GUIAdvEnchantment
import net.minecraft.util.ResourceLocation

class ScrollBar(posX: Int, posY: Int, width: Int, height: Int, texture: ResourceLocation,
                screen: GUIAdvEnchantment) extends GuiElement(posX,
    posY, width, height, 48, texture, screen) {

    private var element: GuiElement = null

    def linkElement(element: GuiElement) = {
        this.element = element
    }

    private var scrollPosition = 0

    override def drawExtras() {
        screen.mc.renderEngine.bindTexture(screen.TEXTURE)
        drawTexturedModalRect(posX, posY + scrollPosition, 0, 182, 12, 15)
    }

    override def update() = {}

    override def mouseMoved(x: Int, y: Int): Unit = {
        var newPosition = y - posY - 6
        if (newPosition < 0) newPosition = 0

        if (newPosition + 12 >= height) newPosition = height - 12

        element.handleMovementChange(newPosition - scrollPosition)

        scrollPosition = newPosition
    }

    override def isVisible: Boolean = true
}