package com.example.address_project.infrastructure.batch

import com.example.address_project.domain.road_addrss.service.FileReaderRoadAddressService
import com.example.address_project.domain.road_code.service.FileReaderRoadCodeEnglishService
import com.example.address_project.domain.road_code.service.FileReaderRoadCodeService
import com.example.address_project.domain.street_number.service.FileReaderStreetNumberService
import com.example.address_project.infrastructure.batch.tasklet.SaveAddressFileTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SaveAddressFileConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val fileReaderStreetNumberService: FileReaderStreetNumberService,
    private val fileReaderRoadCodeService: FileReaderRoadCodeService,
    private val fileReaderRoadCodeEnglishService: FileReaderRoadCodeEnglishService,
    private val fileReaderRoadAddressService: FileReaderRoadAddressService,

    @Value("\${file.kor-address-path}")
    val korAddressPath: String,

    @Value("\${file.eng-address-path}")
    val engAddressPath: String,
) {

    @Bean
    fun saveAddressFileJob(): Job {
        return jobBuilderFactory.get("saveAddressFileJob")
            .start(saveAddressFileStep())
            .build()
    }

    @Bean
    fun saveAddressFileStep(): Step {
        return stepBuilderFactory.get("saveAddressFileStep")
            .tasklet(SaveAddressFileTasklet(
                fileReaderRoadAddressService,
                fileReaderRoadCodeService,
                fileReaderRoadCodeEnglishService,
                fileReaderStreetNumberService,
                korAddressPath,
                engAddressPath,
            )).build()
    }
}