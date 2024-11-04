package cn.cc.easyboot.controller.common;

import io.github.luochangcheng.ccbootstarterfile.resp.FileResp;
import io.github.luochangcheng.ccbootstarterfile.service.IFileService;
import io.github.luochangcheng.ccbootstarterredisson.common.annotation.DistributedLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/common/test")
@Api(tags = "公共-测试")
public class TestController {

    @Autowired
    private IFileService fileService;

    @ApiOperation("分布式锁")
    @GetMapping("/hello")
    @DistributedLock(key = "'cn.cc.test.controller.common.TestController.hello[name='+#name+']'", releaseTime = 10, waitTime = 3)
    public String hello(String name) throws InterruptedException {
        Thread.sleep(15000);
        return "success";
    }

    @ApiOperation("文件上传")
    @PostMapping("/file")
    public FileResp file(@RequestParam MultipartFile file) throws IOException {
        return fileService.upload(file);
    }

}
