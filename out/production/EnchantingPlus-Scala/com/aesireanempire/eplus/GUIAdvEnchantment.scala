package com.aesireanempire.eplus

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.gui.elements.{GuiElement, ListBoxEnchantments, ListBoxInfo, ScrollBar}
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.gui.{Gui, GuiButton, GuiScreen}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import org.lwjgl.input.Mouse
import org.lwjgl.opengl.GL11

class GUIAdvEnchantment(player: EntityPlayer, tile: TileEntityAdvEnchantmentTable) extends GuiContainer(new
        ContainerAdvEnchantment(player, tile)) {
    def drawToolTip(strings: Array[String], x: Int, y: Int): Unit = {
        val lines = new java.util.ArrayList[String]

        for (string <- strings) {
            val formatted = fontRendererObj.listFormattedStringToWidth(string.trim.capitalize, 200).asInstanceOf[java.util.List[String]]
            lines.addAll(formatted)
        }

        drawHoveringText(lines, x, y, fontRendererObj)
    }

    def drawToolTip(text: String, x: Int, y: Int) = {
        val lines = fontRendererObj.listFormattedStringToWidth(text, 200)

        drawHoveringText(lines, x, y, fontRendererObj)
    }

    final val TEXTURE: ResourceLocation = new ResourceLocation("eplus:textures/gui/enchant_classic.png")
    final val ELEMENTS_TEXTURE = new ResourceLocation("eplus:textures/gui/enchant_elements.png")

    private var elements = List.empty[GuiElement]

    private val debug = false

    override def initGui(): Unit = {
        xSize = 256
        ySize = 182

        super.initGui()

        val scrollBar = new ScrollBar(guiLeft + 231, guiTop + 16, 12, 72, ELEMENTS_TEXTURE, this)
        val listBox = new ListBoxEnchantments(guiLeft + 85, guiTop + 16, 140, 72, ELEMENTS_TEXTURE, this)
        val infoBox = new ListBoxInfo(guiLeft + 25, guiTop + 16, 36, 43, ELEMENTS_TEXTURE, this)
        infoBox.setDataProvider(getContainer.infoProvider)

        scrollBar.linkElement(listBox)

        listBox.setDataProvider(getContainer.dataProvider)

        elements = listBox :: scrollBar :: infoBox :: List.empty

        val enchantButton = new GuiButton(0, guiLeft + 62, guiTop + 40, 20, 20, "E")

        buttonList.asInstanceOf[java.util.List[GuiButton]].add(enchantButton)

        getContainer.dataProvider.hasUpdated = true
        getContainer.infoProvider.hasUpdated = true
    }


    override def actionPerformed(button: GuiButton): Unit = {
        if (button.displayString.equals("E")) {
            elements.head.actionPerformed(button)
        }
    }


    override def drawScreen(x: Int, y: Int, zDepth: Float): Unit = {
        super.drawScreen(x, y, zDepth)
        if (GuiScreen.isShiftKeyDown) {
            val element = getElementUnderMouse(x, y)
            if (element != null) element.handleToolTip(x, y)
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

        getContainer.setInformationCost(elements.head.asInstanceOf[ListBoxEnchantments].getData)

        elements.foreach(_.update())
        elements.foreach(_.draw())

        if (debug) {
            elements.foreach { e =>
                Gui.drawRect(e.posX, e.posY, e.posX + e.width, e.posY + e.height,
                    0x8800aa00)
            }
        }
    }

    override def mouseClickMove(x: Int, y: Int, clickedMouseButton: Int, timeSinceLastClick: Long) : Unit = {
        super.mouseClickMove(x, y, clickedMouseButton, timeSinceLastClick)

        val guiElements: List[GuiElement] = elements.filter(e => e.isDragging)
        if (guiElements.nonEmpty) {
            val element = guiElements(0)

            if (!element.isInstanceOf[ScrollBar]) {
                element.mouseMoved(x, y)
            }
        }
    }

    override def mouseReleased(x: Int, y: Int, state: Int): Unit = {
        super.mouseReleased(x, y, state)

        val guiElements: List[GuiElement] = elements.filter(e => e.isDragging)
        if (guiElements.nonEmpty) {
            val element = guiElements(0)

            if (!element.isInstanceOf[ScrollBar]) {
                element.mouseMoved(x, y)
            }

            element.setDragging(x, y, b = false)
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

    def getContainer: ContainerAdvEnchantment = {
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
