package com.aesireanempire.eplus

import java.io.File

import net.minecraftforge.common.config.Configuration

/**
 * Utility object for manipulating configuration files
 */
object ConfigHandler {
  private var configuration: Configuration = null

  /**
   * Creates the configuration file, and loads all default values into it
   * @param file The file object pointing to where the new configuration should be kept on disk
   */
  def init(file: File): Unit = {
    configuration = new Configuration(file)

    configuration.load()

    loadDefaults()

    if (configuration.hasChanged) configuration.save()
  }

  private def loadDefaults() = {}
}
