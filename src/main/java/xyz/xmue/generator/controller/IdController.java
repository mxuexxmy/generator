package xyz.xmue.generator.controller;

import xyz.xmue.generator.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ID 生成器控制器
 * @author mxuexxmy
 */
@RequestMapping
@Controller
public class IdController {

    @GetMapping
    public String home(ModelMap map) {
        // 默认显示雪花算法生成的ID
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(11, 11);
        // 转换为字符串以避免JavaScript精度丢失
        map.put("id", String.valueOf(snowFlakeUtil.nextId()));
        map.put("mode", "snowflake");
        return "index";
    }

    /**
     * 递增ID生成
     * 模式1：给定一个ID，然后选择生成多少个，累计增加生成
     */
    @PostMapping("/generate/incremental")
    @ResponseBody
    public List<String> generateIncremental(@RequestParam long startId, 
                                         @RequestParam int count) {
        return IncrementalIdGenerator.generate(startId, count);
    }

    /**
     * 随机ID生成
     * 模式2：选择生成长度和个数
     */
    @PostMapping("/generate/random")
    @ResponseBody
    public List<String> generateRandom(@RequestParam int length,
                                      @RequestParam int count,
                                      @RequestParam(defaultValue = "alphanumeric") String type) {
        switch (type.toLowerCase()) {
            case "numeric":
                return RandomIdGenerator.generateNumeric(length, count);
            case "alphabetic":
                return RandomIdGenerator.generateAlphabetic(length, count);
            case "alphanumeric":
            default:
                return RandomIdGenerator.generateAlphanumeric(length, count);
        }
    }

    /**
     * 常见ID生成
     * 模式3：目前市面上已有的ID生成（UUID、雪花算法等）
     */
    @PostMapping("/generate/common")
    @ResponseBody
    public List<String> generateCommon(@RequestParam String idType,
                                       @RequestParam int count) {
        CommonIdGenerator.IdType type;
        try {
            type = CommonIdGenerator.IdType.valueOf(idType.toUpperCase());
        } catch (IllegalArgumentException e) {
            type = CommonIdGenerator.IdType.UUID;
        }
        return CommonIdGenerator.generate(type, count);
    }

    /**
     * 统一生成接口（支持所有模式）
     */
    @PostMapping("/generate")
    @ResponseBody
    public Object generate(@RequestParam String mode,
                          @RequestParam(required = false) Long startId,
                          @RequestParam(required = false) Integer count,
                          @RequestParam(required = false) Integer length,
                          @RequestParam(required = false) String randomType,
                          @RequestParam(required = false) String idType) {
        if (count == null || count <= 0) {
            count = 1;
        }

        switch (mode.toLowerCase()) {
            case "incremental":
                if (startId == null) {
                    throw new IllegalArgumentException("递增模式需要提供起始ID");
                }
                return IncrementalIdGenerator.generate(startId, count);

            case "random":
                if (length == null || length <= 0) {
                    throw new IllegalArgumentException("随机模式需要提供ID长度");
                }
                String type = randomType != null ? randomType : "alphanumeric";
                switch (type.toLowerCase()) {
                    case "numeric":
                        return RandomIdGenerator.generateNumeric(length, count);
                    case "alphabetic":
                        return RandomIdGenerator.generateAlphabetic(length, count);
                    case "alphanumeric":
                    default:
                        return RandomIdGenerator.generateAlphanumeric(length, count);
                }

            case "common":
            case "uuid":
            case "snowflake":
                CommonIdGenerator.IdType commonType;
                if (idType != null) {
                    try {
                        commonType = CommonIdGenerator.IdType.valueOf(idType.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        commonType = CommonIdGenerator.IdType.UUID;
                    }
                } else if ("uuid".equals(mode.toLowerCase())) {
                    commonType = CommonIdGenerator.IdType.UUID;
                } else if ("snowflake".equals(mode.toLowerCase())) {
                    commonType = CommonIdGenerator.IdType.SNOWFLAKE;
                } else {
                    commonType = CommonIdGenerator.IdType.UUID;
                }
                return CommonIdGenerator.generate(commonType, count);

            default:
                // 默认使用雪花算法
                SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(11, 11);
                return String.valueOf(snowFlakeUtil.nextId());
        }
    }

}
