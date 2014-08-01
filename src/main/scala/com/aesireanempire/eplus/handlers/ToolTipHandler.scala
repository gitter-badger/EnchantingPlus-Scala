package com.aesireanempire.eplus.handlers

import java.io.File
import java.net.URL

import net.minecraft.enchantment.Enchantment

import scala.collection.mutable
import scala.io.Source
import scala.sys.process._

object ToolTipHandler {
    private val upstreamLocation = "https://raw.githubusercontent.com/odininon/EnchantingPlus-Scala/develop/eplus_tooltips.txt"

    private var toolTips = Map.empty[Enchantment, String]

    private def needsUpdating(file: File): Boolean = {
        Source.fromURL(upstreamLocation).mkString != Source.fromFile(file).mkString
    }

    private def downloadFromStream(file: File): Unit = {
        new URL(upstreamLocation) #> file !!
    }

    def init(directory: File): Unit = {
        val toolTipFile = new File(directory, "eplus_tooltips.txt")
        def createFile(): Unit = {
            if (toolTipFile.exists()) {
                if (!needsUpdating(toolTipFile)) {
                    return
                }
            }
            downloadFromStream(toolTipFile)
        }
        createFile()

        toolTips = createToolTipHashFromFile(toolTipFile)
    }

    def getToolTip(enchantment: Enchantment): Option[String] = toolTips.get(enchantment)

    private def createToolTipHashFromFile(file: File): Map[Enchantment, String] = {
        val toolTips = mutable.Map.empty[Enchantment, String]

        val lines: Iterator[String] = Source.fromFile(file).getLines()
        for (line <- lines) {
            val tokens = line.split("=")
            val enchantment = getEnchantmentByName(tokens(0))

            if (enchantment.isDefined) {
                toolTips += enchantment.get -> tokens(1)
            }
        }
        toolTips.toMap
    }

    private def getEnchantmentByName(name: String): Option[Enchantment] = {
        for (enchantment <- Enchantment.enchantmentsList.filter(_ != null)) {
            if (enchantment.getName.equals(name)) {
                return Some(enchantment)
            }
        }
        None
    }
}
