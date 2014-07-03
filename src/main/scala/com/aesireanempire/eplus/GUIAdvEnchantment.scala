package com.aesireanempire.eplus

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11


class GUIAdvEnchantment(player: EntityPlayer, tile: TileEntityAdvEnchantmentTable) extends GuiContainer(new
        ContainerAdvEnchantment(player, tile)) {

    private final val TEXTURE: ResourceLocation = new ResourceLocation("eplus:textures/gui/enchant_classic.png")
    private final val ELEMENTS_TEXTURE = new ResourceLocation("eplus:textures/gui/enchant_elements.png")

    private var elements = List.empty[GuiElement]

    private val debug = false

    override def initGui(): Unit = {
        xSize = 235
        ySize = 182

        super.initGui()

        val scrollBar = new ScrollBar(guiLeft + 206, guiTop + 16, 12, 72, ELEMENTS_TEXTURE)
        val listBox = new ListBox(guiLeft + 61, guiTop + 16, 144, 72, ELEMENTS_TEXTURE)

        elements = scrollBar :: listBox :: List.empty

    }

    override def drawGuiContainerBackgroundLayer(f: Float, x: Int, y: Int): Unit = {
        drawTextureBackground()

        elements.foreach(e => e.draw())

        if (debug) {
            for (element <- elements) {
                Gui.drawRect(element.posX, element.posY, element.posX + element.width, element.posY + element.height,
                    0x8800aa00)
            }
        }
    }


    override def mouseMovedOrUp(x: Int, y: Int, event: Int): Unit = {
        super.mouseMovedOrUp(x, y, event)

        if (debug) {
            val guiElements: List[GuiElement] = elements.filter(e => e.dragging)
            if (guiElements.nonEmpty) {
                val element = guiElements(0)

                element.setDimension(x - element.posX, y - element.posY)

                if (event != -1) {
                    element.dragging = false
                }
            }
        }
    }

    override def mouseClicked(x: Int, y: Int, z: Int): Unit = {
        super.mouseClicked(x, y, z)

        if(debug) {
            val guiElements: List[GuiElement] = elements.filter(e => e.dragging)
            if (guiElements.isEmpty) {

                val elementClicked = getElementUnderClick(x, y)

                if (elementClicked != null) {
                    elementClicked.dragging = true
                }
            }
        }
    }

    def getContainer: ContainerAdvEnchantment = {
        inventorySlots.asInstanceOf[ContainerAdvEnchantment]
    }

    private def getElementUnderClick(x: Int, y: Int): GuiElement = {
        for (element <- elements) {
            if (element.posX <= x && x <= element.posX + element.width) {
                if (element.posY <= y && y <= element.posY + element.height)
                    return element
            }
        }
        null
    }

    private def drawTextureBackground() {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        mc.renderEngine.bindTexture(TEXTURE)
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize)
    }

    class ScrollBar(posX: Int, posY: Int, width: Int, height: Int, texture: ResourceLocation) extends GuiElement(posX,
        posY, width, height, 48, texture) {

       override def drawExtras() {
            mc.renderEngine.bindTexture(TEXTURE)
            drawTexturedModalRect(posX, posY, 0, 182, 12, 15)
        }
    }

    class ListBox(posX: Int, posY: Int, width: Int, height: Int, texture: ResourceLocation) extends GuiElement(posX,
        posY, width, height, 0, texture) {

        override def drawExtras() = {}
    }
}
