package com.aesireanempire.eplus

import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation

abstract class GuiElement(val posX: Int, val posY: Int, var width: Int, var height: Int, u: Int,
                          texture: ResourceLocation) extends Gui {
    def setDimension(newWidth: Int, newHeight: Int): Unit = {
        width = if (newWidth >= 30) newWidth else 30
        height = if (newHeight >= 30) newHeight else 30
    }

    var dragging: Boolean = false


    def draw()

    def drawTop() {
        drawTexturedModalRect(posX, posY, u, 0, if (width >= 15) 15 else width, 15)
        if (width > 30) {
            for (x <- posX + 15 to posX + width - 15 by 15) {
                drawTexturedModalRect(x, posY, u + 15, 0, 15, 15)
            }
        }

        if (width >= 30) {
            drawTexturedModalRect(posX + width - 15, posY, u + 30, 0, 15, 15)
        }
    }

    def drawMiddle() {
        if (height >= 30) {
            for (y <- posY + 15 to posY + height - 15 by 15) {
                drawTexturedModalRect(posX, y, u, 15, if (width >= 15) 15 else width, 15)

                if (width > 30) {
                    for (x <- posX + 15 to posX + width - 15 by 15) {
                        drawTexturedModalRect(x, y, u + 15, 15, 15, 15)
                    }
                }
                if (width >= 30) {
                    drawTexturedModalRect(posX + width - 15, y, u + 30, 15, 15, 15)
                }
            }
        }
    }

    def drawBottom() {
        val y: Int = posY + height - 15
        drawTexturedModalRect(posX, y, u, 30, if (width >= 15) 15 else width, 15)
        if (width > 30) {
            for (x <- posX + 15 to posX + width - 15 by 15) {
                drawTexturedModalRect(x, y, u + 15, 30, 15, 15)
            }
        }

        if (width >= 30) {
            drawTexturedModalRect(posX + width - 15, y, u + 30, 30, 15, 15)
        }
    }
}
