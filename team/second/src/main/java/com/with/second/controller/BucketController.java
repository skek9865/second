package com.with.second.controller;



import com.with.second.dto.BucketDto;
import com.with.second.service.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/bucket")
public class BucketController {

    private final BucketService bucketService;

    @PostMapping("/register")
    public HttpStatus register(@RequestBody BucketDto dto){

        log.info("dto : " + dto);

        bucketService.register(dto);

        return HttpStatus.OK;
    }

    @DeleteMapping("/remove/{sno}")
    public HttpStatus remove(@PathVariable("sno") Long sno){

        log.info("sno : " + sno);

        bucketService.remove(sno);

        return HttpStatus.OK;
    }

    @GetMapping("/getList")
    public ResponseEntity<List<BucketDto>> getList(String id){

        List<BucketDto> list = bucketService.getList(id);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
