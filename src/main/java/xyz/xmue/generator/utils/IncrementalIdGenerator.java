package xyz.xmue.generator.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 递增ID生成器
 * 基于给定的起始ID，累计增加生成指定数量的ID
 *
 * @author mxuexxmy
 */
public class IncrementalIdGenerator {

    /**
     * 基于起始ID生成指定数量的递增ID
     * 返回字符串列表以避免JavaScript精度丢失问题
     *
     * @param startId 起始ID
     * @param count   生成数量
     * @return ID列表（字符串格式）
     */
    public static List<String> generate(long startId, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("生成数量必须大于0");
        }
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ids.add(String.valueOf(startId + i));
        }
        return ids;
    }
}

