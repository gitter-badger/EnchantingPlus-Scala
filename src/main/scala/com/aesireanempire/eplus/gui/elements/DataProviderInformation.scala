package com.aesireanempire.eplus.gui.elements

class DataProviderInformation extends DataProvider[String] {
  override var dataSet: Array[String] = Array.empty[String]

  def setInfoAt(index: Int, info: String) = {
    if (dataSet.length <= index) {
      dataSet = dataSet :+ info
    } else {
      dataSet(index) = info
    }
    hasUpdated = true
  }
}
