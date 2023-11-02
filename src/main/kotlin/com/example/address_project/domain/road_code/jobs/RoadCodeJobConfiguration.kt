package com.example.address_project.domain.road_code.jobs

import com.example.address_project.domain.road_code.jobs.tasklet.RoadCodeTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RoadCodeJobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun roadCodeJob() : Job {
        return jobBuilderFactory.get("roadCodeJob")
            .start(roadCodeStep())
            .build()
    }
    
    @Bean
    fun roadCodeStep () : Step {
        return stepBuilderFactory.get("roadCodeStep")
            .tasklet(RoadCodeTasklet())
            .build()
    }
}