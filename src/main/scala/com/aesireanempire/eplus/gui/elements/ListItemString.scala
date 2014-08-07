package com.aesireanempire.eplus.gui.elements

class ListItemString(data: String, x: Int, y: Int, width: Int, height: Int, box: ListBoxInfo) extends
ListItem(data, x, y, width, height, box) {

  override def getString: String = data

  override def update(): Unit = {}

  override def drawExtras(): Unit = {}

  override def handleMouseInput(mouseEvent: Int, mouseX: Int, MouseY: Int): Unit = {}

  override def handleToolTip(x: Int, y: Int): Unit = {}
}
