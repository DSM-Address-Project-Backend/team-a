package com.example.address_project.infrastructure.scheduler

import com.example.address_project.infrastructure.batch.SaveAddressFileConfiguration
import com.example.address_project.infrastructure.common.feign.dto.request.UnZipFileRequest
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import com.example.address_project.infrastructure.common.util.AddressConstants.ENG_LANGUAGE
import com.example.address_project.infrastructure.common.util.AddressConstants.KOR_LANGUAGE
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import java.time.LocalDate
import java.util.UUID

class AddressFileScheduler(
    private val addressZipFileService: AddressZipFileService,
    private val jobLauncher: JobLauncher,
    private val saveAddressFileConfiguration: SaveAddressFileConfiguration,

    @Value("\${file.kor-address-path}")
    val korAddressPath: String,

    @Value("\${file.eng-address-path}")
    val engAddressPath: String,

    @Value("\${file.zip-path}")
    val zipPath: String,
) {

    @Scheduled(cron = "0 0 0 0 * *", zone = "Asia/Seoul")
    fun saveAddressFileScheduler() {
        // TODO 날짜 변경 필요(다음 달 껄 전 달에 미리 처리하도록 변경)
        val year = LocalDate.now().year
        val month = LocalDate.now().month

        val korRequest = UnZipFileRequest(
            KOR_LANGUAGE,
            year.toString(),
            month.toString(),
            "",
            "",
            zipPath,
            korAddressPath
        )

        val engRequest = UnZipFileRequest(
            ENG_LANGUAGE,
            year.toString(),
            month.toString(),
            "",
            "",
            zipPath,
            engAddressPath
        )

        addressZipFileService.saveAddressFile(korRequest)
        addressZipFileService.saveAddressFile(engRequest)

        jobLauncher.run(
            saveAddressFileConfiguration.saveAddressFileJob(),
            JobParametersBuilder()
                .addString(
                saveAddressFileConfiguration.saveAddressFileJob().toString(),
                UUID.randomUUID().toString())
                .toJobParameters()
        )
    }
}