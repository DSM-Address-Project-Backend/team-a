package com.example.address_project.infrastructure.common.feign.client

import com.example.address_project.infrastructure.common.feign.client.dto.response.AddressZipFileResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "AddressClient", url = "https://business.juso.go.kr")
interface AddressClient {

    @GetMapping("/download.do")
    fun getAddressZipFile(
        @RequestParam reqType: String,
        @RequestParam regYmd: Int,
        @RequestParam ctprvnCd: Int,
        @RequestParam stdde: Int,
        @RequestParam fileName: String,
        @RequestParam intNum: Int,
        @RequestParam intFileNo: Int,
        @RequestParam realFileName: String
    ) : AddressZipFileResponse
}