package com.syc.demo1.controller;

import com.alibaba.fastjson.JSONObject;
import com.syc.demo1.entity.Person;
import com.syc.demo1.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Value("${count}")
    private int count = 5;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/")
    public String index() {
        log.info("=========={}", applicationName);
        return applicationName + ":" + count;
    }


    @PostMapping(value = "/agent")
    public String agent(
            String callDateFrom,
            String callDateTo,
            @RequestBody List<Map<String, String>> userIds, @RequestHeader Long userId) {
        log.info("按坐席分类统计{} {} {}", callDateFrom, callDateTo, userIds);
        return "OK";
    }


    @PutMapping(value = "/{key}/{value}")
    public String setRedis(@PathVariable String key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value, 60, TimeUnit.SECONDS);
        return "ok";
    }


    @GetMapping(value = "/{key}")
    public String getRedis(@PathVariable String key) {
        String value = redisTemplate.opsForValue().get(key);
        return value;
    }

    @GetMapping(value = "/insertPerson")
    public String insertPerson(@RequestParam String name, @RequestParam int age) {
        Person p = new Person();
        p.setName(name);
        p.setAge(age);
        p = personRepository.save(p);
        return JSONObject.toJSONString(p);
    }

}
