
android 内 buildTypes下面
```

    //配置自定义打包名称
    applicationVariants.all { variant ->
        variant.outputs.each { output ->
            def outputFile = output.outputFile
            def fileName
            if (outputFile != null && outputFile.name.endsWith('.apk')) {
                if (variant.buildType.name.equals('release')) {
                    variant.mergedFlavor.versionName = getVersionName()
                    //fileName = "XXXX_${variant.mergedFlavor.versionName}_release.apk"
                    fileName = "GasVerv100r001b171010.apk"//   让图片放开 改ip 关闭log与内存卡的log
                    //  fileName = "app-release.apk"// 内测版 让图片放开 改ip 关闭log与内存卡的log
                } else if (variant.buildType.name.equals('debug')) {
                    //variant.mergedFlavor.versionName = getVersionName()+"."+releaseTime()
                    variant.mergedFlavor.versionName = getVersionName()
                    fileName = "XXXX_${variant.mergedFlavor.versionName}_debug.apk"
                }
                output.outputFile = new File(outputFile.parent, fileName)
            }
        }
    } //配置自定义打包名称
```