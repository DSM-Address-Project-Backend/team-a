package com.example.address_project.infrastructure.common.feign.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "AddressClient", url = "https://business.juso.go.kr")
interface AddressClient {

    @GetMapping("/addrlink/download.do")
    fun getAddressInfo(
        @RequestParam("reqType") reqType: String,
        @RequestParam("regYmd") regYmd: String,
        @RequestParam("ctprvnCd") ctprvnCd: String,
        @RequestParam("stdde") stdde: String,
        @RequestParam("fileName") fileName: String,
        @RequestParam("intNum") intNum: String,
        @RequestParam("intFileNo") intFileNo: String,
        @RequestParam("realFileName") realFileName: String,
    ): ResponseEntity<ByteArray>
}