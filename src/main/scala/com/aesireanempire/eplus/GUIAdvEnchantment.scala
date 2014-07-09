package com.aesireanempire.eplus

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.gui.elements.{GuiElement, ListBox, ScrollBar}
import net.minecraft.client.gui.{GuiButton, Gui}
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import org.lwjgl.input.Mouse
import org.lwjgl.opengl.GL11
import scala.collection.JavaConverters._

class GUIAdvEnchantment(player: EntityPlayer, tile: TileEntityAdvEnchantmentTable) extends GuiContainer(new
        ContainerAdvEnchantment(player, tile)) {

    final val TEXTURE: ResourceLocation = new ResourceLocation("eplus:textures/gui/enchant_classic.png")
    final val ELEMENTS_TEXTURE = new ResourceLocation("eplus:textures/gui/enchant_elements.png")

    private var elements = List.empty[GuiElement]

    private val debug = false

    override def initGui(): Unit = {
        xSize = 235
        ySize = 182

        super.initGui()

        val scrollBar = new ScrollBar(guiLeft + 206, guiTop + 16, 12, 72, ELEMENTS_TEXTURE, this)
        val listBox = new ListBox(guiLeft + 61, guiTop + 16, 144, 72, ELEMENTS_TEXTURE, this)

        scrollBar.linkElement(listBox)

        listBox.setDataProvider(getContainer)

        elements = listBox :: scrollBar :: List.empty

        val enchantButton = new GuiButton(0, guiLeft + 35, guiTop + 40, 20, 20, "E")

        buttonList.asInstanceOf[java.util.List[GuiButton]].add(enchantButton)
    }


    override def actionPerformed(button : GuiButton): Unit = {
        if(button.displayString.equals("E")) {
            elements.head.actionPerformed(button)
        }
        
    }

    override def handleMouseInput(): Unit = {
        super.handleMouseInput()

        val eventDWheel = Mouse.getEventDWheel
        val mouseX = Mouse.getEventX * width / mc.displayWidth
        val mouseY = height - Mouse.getEventY * height / mc.displayHeight - 1

        val element = getElementUnderMouse(mouseX, mouseY)

        if (element != null) {
            element.handleMouseInput(eventDWheel, mouseX, mouseY)
        }
    }

    override def drawGuiContainerBackgroundLayer(f: Float, x: Int, y: Int): Unit = {
        drawTextureBackground()

        elements.foreach(_.update())
        elements.foreach(_.draw())

        if (debug) {
            elements.foreach { e =>
                Gui.drawRect(e.posX, e.posY, e.posX + e.width, e.posY + e.height,
                    0x8800aa00)
            }
        }
    }


    override def mouseMovedOrUp(x: Int, y: Int, event: Int): Unit = {
        super.mouseMovedOrUp(x, y, event)

        val guiElements: List[GuiElement] = elements.filter(e => e.isDragging)
        if (guiElements.nonEmpty) {
            val element = guiElements(0)

            if (!element.isInstanceOf[ScrollBar]) {
                element.mouseMoved(x, y)
            }

            if (event != -1) {
                element.setDragging(x, y, b = false)
            }
        }
    }

    override def mouseClicked(x: Int, y: Int, z: Int): Unit = {
        super.mouseClicked(x, y, z)

        val guiElements: List[GuiElement] = elements.filter(e => e.isDragging)
        if (guiElements.isEmpty) {

            val elementClicked = getElementUnderMouse(x, y)

            if (elementClicked != null) {
                elementClicked.setDragging(x, y, b = true)
            }
        }
    }

    def drawString(text: String, x: Int, y: Int, color: Int) {
        fontRendererObj.drawString(text, x, y, color)
    }

    private def getContainer: ContainerAdvEnchantment = {
        inventorySlots.asInstanceOf[ContainerAdvEnchantment]
    }

    private def getElementUnderMouse(x: Int, y: Int): GuiElement = {
        for (element <- elements) {
            if (element.isUnderMouse(x, y)) {
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
}
