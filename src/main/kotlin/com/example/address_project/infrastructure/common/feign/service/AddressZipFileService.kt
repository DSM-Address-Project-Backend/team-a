package com.example.address_project.infrastructure.common.feign.service

import com.example.address_project.infrastructure.common.exception.FeignBadRequestException
import com.example.address_project.infrastructure.common.feign.client.AddressClient
import com.example.address_project.infrastructure.common.feign.dto.UnzipFile
import com.example.address_project.infrastructure.common.feign.dto.request.UnZipFileRequest
import com.example.address_project.infrastructure.common.feign.dto.response.FileNameElement
import com.example.address_project.infrastructure.common.util.AddressConstants.CTPRVN_CD
import com.example.address_project.infrastructure.common.util.AddressConstants.ENG_LANGUAGE
import com.example.address_project.infrastructure.common.util.AddressConstants.ENG_REAL_FILE_NAME
import com.example.address_project.infrastructure.common.util.AddressConstants.ENG_ZIP_NAME
import com.example.address_project.infrastructure.common.util.AddressConstants.INT_FILE_NO
import com.example.address_project.infrastructure.common.util.AddressConstants.INT_NUM
import com.example.address_project.infrastructure.common.util.AddressConstants.KOR_LANGUAGE
import com.example.address_project.infrastructure.common.util.AddressConstants.KOR_REAL_FILE_NAME
import com.example.address_project.infrastructure.common.util.AddressConstants.KOR_ZIP_NAME
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Month
import java.util.zip.ZipFile

@Service
class AddressZipFileService(
    private val addressClient: AddressClient
) {
    fun saveAddressFile(request: UnZipFileRequest) {
        val files = getAddressInfo(request.reqType, request.year, request.month)
        Files.write(Paths.get(request.zipPath), files)
        unzip(File(request.zipPath), File(request.unzipPath))
    }

    fun addressFileWriter(unZipFile: UnzipFile) {
        Files.write(
            Paths.get(unZipFile.zipFilePath), getAddressInfo(unZipFile.reqType, unZipFile.year, unZipFile.month)
        )
    }

    private fun getAddressInfo(language: String, year: String, month: String): ByteArray {
        val month = month.padStart(2, '0')

        val fileNameElement = buildFileName(language, year, month)

        return addressClient.getAddressInfo(
            reqType = language,
            regYmd = year,
            ctprvnCd = CTPRVN_CD,
            stdde = year + month,
            fileName = fileNameElement.fileName,
            intNum = INT_NUM,
            intFileNo = INT_FILE_NO,
            realFileName = fileNameElement.realFileName,
        ).body ?: throw FeignBadRequestException
    }

    private fun buildFileName(language: String, year: String, month: String): FileNameElement {
        val yyyyMM = year + month
        val yyMMZip = "${year.slice(2..3)}$month.zip"
        var fileName = ""
        var realFileName = ""

        when (language) {
            KOR_LANGUAGE -> {
                fileName = "$yyyyMM$KOR_ZIP_NAME"
                realFileName = "$KOR_REAL_FILE_NAME$yyMMZip"
            }

            ENG_LANGUAGE -> {
                fileName = "$yyyyMM$ENG_ZIP_NAME"
                realFileName = "$ENG_REAL_FILE_NAME$yyMMZip"
            }
        }

        return FileNameElement(
            fileName = fileName,
            realFileName = realFileName
        )
    }

    private fun unzip(zipFile: File, unzipFileTargetDirectory: File) {
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