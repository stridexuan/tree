package com.tree.common.config

import java.io.File

import com.typesafe.config.{ConfigFactory, Config}

/**
  * Created by hasee on 2016/11/5.
  */
trait CommonConfig {
    var config: Config = null

    protected var configHome = "../config"

    def setConfigFiles(files:String*) = {

        config = files.toList.map(load).reduce((a,b) => a.withFallback(b))
    }

    protected def load(file: String):Config = {
        val resourceFile = file
        val configFile = new File(makePath(file))
        if (configFile.exists()){
            ConfigFactory.parseFile(configFile).withFallback(ConfigFactory.load(resourceFile))
        }
        else ConfigFactory.load(resourceFile)
    }
    protected def makePath(fileName:String):String = {
        val newDir = configHome.trim.replaceAll("""\\""","/")
        if (newDir.endsWith("/")) configHome + fileName
        else configHome + "/" + fileName
    }
}

