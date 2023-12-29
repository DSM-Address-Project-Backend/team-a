package com.example.address_project.infrastructure.batch.tasklet

import com.example.address_project.domain.road_addrss.service.FileReaderRoadAddressService
import com.example.address_project.domain.road_code.service.FileReaderRoadCodeEnglishService
import com.example.address_project.domain.road_code.service.FileReaderRoadCodeService
import com.example.address_project.domain.street_number.service.FileReaderStreetNumberService
import com.example.address_project.infrastructure.common.util.AddressConstants.JIBUN
import com.example.address_project.infrastructure.common.util.AddressConstants.RNADDRKOR
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import java.io.File

class SaveAddressFileTasklet(
    private val fileReaderRoadAddressService: FileReaderRoadAddressService,
    private val fileReaderRoadCodeService: FileReaderRoadCodeService,
    private val fileReaderRoadCodeEnglishService: FileReaderRoadCodeEnglishService,
    private val fileReaderStreetNumberService: FileReaderStreetNumberService,

    @Value("\${file.kor-address-path}")
    val korAddressPath: String,

    @Value("\${file.eng-address-path}")
    val engAddressPath: String,
):Tasklet {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        val korAddressFile = File(korAddressPath)
        val engAddressFile = File(engAddressPath)

        korAddressFile.walk().forEach {
            val fileNameList = it.name.split("_")

            if(fileNameList[0] == JIBUN) {
                fileReaderStreetNumberService.readerStreetNumberKorea(it)
            } else {
                fileReaderRoadAddressService.readerRoadAddressKorea(it)
                fileReaderRoadCodeService.readerRoadCodeKorea(it)
            }
        }

        engAddressFile.walk().forEach {
            val fileNameList = it.name.split("_")

            if((fileNameList[0] != JIBUN) && (fileNameList[1] != RNADDRKOR)) {
                fileReaderRoadCodeEnglishService.readerRoadCodeEnglish(it)
            }
        }

        return RepeatStatus.FINISHED
    }

}