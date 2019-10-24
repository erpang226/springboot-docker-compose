package com.syc.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/10/21
 * author: song yong chang
 */
@RestController
@Slf4j
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping(value = "/")
    public String index(){
        log.info("=========={}",applicationName);
        return applicationName;
    }


    @PostMapping(value = "/agent")
    public String agent(
            String callDateFrom,
           String callDateTo,
            @RequestBody List<Map<String,String>> userIds ,@RequestHeader Long userId) {
        log.info("按坐席分类统计{} {} {}", callDateFrom, callDateTo, userIds);

        return "OK";
    }


}
