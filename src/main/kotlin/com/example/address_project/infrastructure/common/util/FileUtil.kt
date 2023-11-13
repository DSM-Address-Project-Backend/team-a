package com.example.address_project.infrastructure.common.util

import java.io.File
import java.util.zip.ZipFile

class FileUtil {

    fun unzip(zipFile: File, unzipFileTargetDirectory: File) {
        ZipFile(zipFile).use { zip ->
            for (entry in zip.entries()) {
                val entryFileOutputStream = File(unzipFileTargetDirectory, entry.name).outputStream()
                entryFileOutputStream.use { out ->
                    zip.getInputStream(entry).use { it.copyTo(out) }
                }
            }
        }
    }
}