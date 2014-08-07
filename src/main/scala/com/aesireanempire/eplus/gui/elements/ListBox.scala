package com.aesireanempire.eplus.gui.elements

import com.aesireanempire.eplus.GUIAdvEnchantment
import net.minecraft.util.ResourceLocation

abstract class ListBox[T](posX: Int, posY: Int, width: Int, height: Int, texture: ResourceLocation,
                          screen: GUIAdvEnchantment) extends GuiElement(posX, posY, width, height, 0, texture, screen) {

  var data = Array.empty[ListItem[T]]
  private var dataProvider: DataProvider[T] = null

  def setData(data: Array[T])

  def filterData()

  def getNumberOfItems = data.length

  def setPage(page: Double) = {
    handleMovementChange(page)
  }

  def setDataProvider(dataProvider: DataProvider[T]) = {
    this.dataProvider = dataProvider
  }

  override def handleToolTip(x: Int, y: Int) = {
    getListItem(x, y).handleToolTip(x, y)
  }

  override def update() = {
    updateData()
    filterData()
    data.foreach(_.update())
  }

  override def drawExtras() = {
    data.filter(_.isVisible).foreach(_.draw())
  }

  override def isDragging: Boolean = data.filter(_.isDragging).length > 0

  override def setDragging(mouseX: Int, mouseY: Int, b: Boolean): Unit = {
    val item = getListItem(mouseX, mouseY)

    if (item != null)
      item.setDragging(mouseX, mouseY, b)
  }

  private def getListItem(x: Int, y: Int): ListItem[T] = {
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

  private def updateData() {
    if (dataProvider != null && dataProvider.hasUpdated) {
      setData(dataProvider.dataSet)
      dataProvider.hasUpdated = false
    }
  }
}
