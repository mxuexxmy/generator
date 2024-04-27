package xyz.xmue.generator.controller;

import xyz.xmue.generator.utils.SnowFlakeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ID 生成器控制成
 * @author mxuexxmy
 */
@RequestMapping
@Controller
public class IdController {

    @GetMapping
    public String home(ModelMap map) {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(11, 11);
        map.put("id", snowFlakeUtil.nextId());
        return "index";
    }

    @GetMapping("/number")
    public String idOfNumber(ModelMap map) {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(11, 11);
        map.put("id", snowFlakeUtil.nextId());
        return "index";
    }

}
