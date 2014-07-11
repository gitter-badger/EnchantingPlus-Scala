package com.aesireanempire.eplus.gui.elements

abstract class DataProvider[T] {
    var dataSet: Array[T]

    var hasUpdated: Boolean = false

}
