package com.example.address_project.infrastructure.common.feign.service

import com.example.address_project.infrastructure.common.exception.FeignBadRequestException
import com.example.address_project.infrastructure.common.feign.client.AddressClient
import com.example.address_project.infrastructure.common.feign.dto.UnzipFile
import com.example.address_project.infrastructure.common.feign.dto.response.FileNameElement
import com.example.address_project.infrastructure.common.util.AddressConstans.CTPRVN_CD
import com.example.address_project.infrastructure.common.util.AddressConstans.ENG_LANGUAGE
import com.example.address_project.infrastructure.common.util.AddressConstans.ENG_REAL_FILE_NAME
import com.example.address_project.infrastructure.common.util.AddressConstans.ENG_ZIP_NAME
import com.example.address_project.infrastructure.common.util.AddressConstans.INT_FILE_NO
import com.example.address_project.infrastructure.common.util.AddressConstans.INT_NUM
import com.example.address_project.infrastructure.common.util.AddressConstans.KOR_LANGUAGE
import com.example.address_project.infrastructure.common.util.AddressConstans.KOR_REAL_FILE_NAME
import com.example.address_project.infrastructure.common.util.AddressConstans.KOR_ZIP_NAME
import com.example.address_project.infrastructure.common.util.AddressConstans.ZIP
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths

@Service
class AddressZipFileService(
    private val addressClient: AddressClient,
) {

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
        val yyMMZip = "${year.slice(2..3)}$month$ZIP"
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
}