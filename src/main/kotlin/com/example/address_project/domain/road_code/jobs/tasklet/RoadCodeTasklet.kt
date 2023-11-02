package com.example.address_project.domain.road_code.jobs.tasklet

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

class RoadCodeTasklet() :Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        // 다른 service에서 파싱한 것을 저장하는 tasklet에 한다
        return RepeatStatus.FINISHED;
    }
}