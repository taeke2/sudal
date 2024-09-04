package com.shop.sudal.domain.test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TestController {
    private final TestService testService;

    @PostMapping
    public void saveTest(@RequestBody TestEntity testEntity) {
        testService.inputData(testEntity);
    }
}
