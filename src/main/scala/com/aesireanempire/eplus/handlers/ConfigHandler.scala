package com.aesireanempire.eplus.handlers

import java.io.File

import net.minecraftforge.common.config.Configuration

/**
 * Utility object for manipulating configuration files
 */
object ConfigHandler {
    var allowDisenchanting : Boolean = false

    private var configuration: Configuration = null

    /**
     * Creates the configuration file, and loads all default values into it
     * @param file The file object pointing to where the new configuration should be kept on disk
     */
    def init(file: File): Unit = {
        configuration = new Configuration(file)

        configuration.load()

        loadDefaults()

        configuration.setCategoryRequiresMcRestart("Server", true)

        if (configuration.hasChanged) configuration.save()
    }

    private def loadDefaults() = {
        allowDisenchanting = configuration.getBoolean("AllowDisenchanting", "Server",
            true,
            "Setting this to false will not allow anyone to disenchant any items."
        )
    }
}
