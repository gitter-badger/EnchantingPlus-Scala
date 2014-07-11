package com.aesireanempire.eplus.gui.elements

import net.minecraft.client.gui.{Gui, GuiButton}

abstract class ListItem[T](val data: T, x: Int, y: Int, width: Int, height: Int,
                        box: ListBox[T]) extends GuiElement(x, y, width, height, 0, null, box.screen) {

    protected var active: Boolean = true
    protected var page: Double = 0
    protected var y1: Int = posY

    def activate() = active = true

    def deactivate() = active = false

    def getString: String

    def isVisible: Boolean = {
        if (box.posX <= posX && posX + width <= box.posX + box.width) {
            if (box.posY <= y1 && y1 + height < box.height + box.posY)
                return true
        }

        false
    }

    override def draw() {
        Gui.drawRect(posX + 1, y1 + 1, posX + width - 1, y1 + 14, 0xff444444)
        Gui.drawRect(posX + 3, y1 + 3, posX + width - 3, y1 + 12, if (active) 0xffaaaaaa else 0xff00aa00)

        box.screen.drawString(getString, posX + 5, 4 + y1, 0xffffffff)

        drawExtras()
    }

    override def handleMovementChange(dY: Double): Unit = {
        page = dY

        if (dY <= 0) page = 0
    }

    override def mouseMoved(x: Int, y: Int): Unit = {}

    override def isUnderMouse(x: Int, y: Int): Boolean = {
        if (posX <= x && x <= posX + width) {
            if (y1 <= y && y <= y1 + height) {
                return true
            }
        }
        false
    }


    override def actionPerformed(button: GuiButton): Unit = {}
}
