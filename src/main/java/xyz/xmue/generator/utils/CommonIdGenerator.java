package xyz.xmue.generator.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 常见ID生成器
 * 支持市面上常见的ID生成方式：UUID、雪花算法等
 *
 * @author mxuexxmy
 */
public class CommonIdGenerator {

    /**
     * ID生成类型枚举
     */
    public enum IdType {
        UUID,                    // UUID
        UUID_WITHOUT_DASH,       // UUID（无横线）
        SNOWFLAKE,              // 雪花算法
        SNOWFLAKE_STRING        // 雪花算法（字符串格式）
    }

    /**
     * 生成指定类型和数量的ID
     *
     * @param type  ID类型
     * @param count 生成数量
     * @return ID列表
     */
    public static List<String> generate(IdType type, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("生成数量必须大于0");
        }

        List<String> ids = new ArrayList<>();
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(11, 11);

        for (int i = 0; i < count; i++) {
            switch (type) {
                case UUID:
                    ids.add(UUID.randomUUID().toString());
                    break;
                case UUID_WITHOUT_DASH:
                    ids.add(UUID.randomUUID().toString().replace("-", ""));
                    break;
                case SNOWFLAKE:
                    ids.add(String.valueOf(snowFlakeUtil.nextId()));
                    break;
                case SNOWFLAKE_STRING:
                    ids.add(String.valueOf(snowFlakeUtil.nextId()));
                    break;
                default:
                    throw new IllegalArgumentException("不支持的ID类型: " + type);
            }
        }
        return ids;
    }
}

