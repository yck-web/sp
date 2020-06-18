package com.tedu.sp02itemservice.item;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.service.ItemService;
import com.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Slf4j
@RestController

public class itemController {
    @Autowired
    private ItemService itemService;
    @Value("${server.port}")
    private int port;

    @RequestMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws InterruptedException {
        if (Math.random()<0.6){
            long i = new Random().nextInt(5000);
            log.info("111111111111"+i);
            Thread.sleep(i);
        }
        log.info("server.port="+port+", orderId="+orderId);
        List<Item> items = itemService.getItems(orderId);
        return JsonResult.ok(items).msg("port="+port);
    }

    @PostMapping("/decreaseNumber")
    public JsonResult decreaseNumber(@RequestBody List<Item> list){
        itemService.decreaseNumbers(list);
        return JsonResult.ok();
    }

}
