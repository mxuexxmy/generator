package xyz.xmue.generator.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机ID生成器
 * 根据指定的长度和数量生成随机ID
 *
 * @author mxuexxmy
 */
public class RandomIdGenerator {

    private static final String DIGITS = "0123456789";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHANUMERIC = DIGITS + LETTERS;
    private static final Random random = new Random();

    /**
     * 生成指定长度和数量的随机数字ID
     *
     * @param length ID长度
     * @param count  生成数量
     * @return ID列表
     */
    public static List<String> generateNumeric(int length, int count) {
        return generate(length, count, DIGITS);
    }

    /**
     * 生成指定长度和数量的随机字母ID
     *
     * @param length ID长度
     * @param count  生成数量
     * @return ID列表
     */
    public static List<String> generateAlphabetic(int length, int count) {
        return generate(length, count, LETTERS);
    }

    /**
     * 生成指定长度和数量的随机字母数字ID
     *
     * @param length ID长度
     * @param count  生成数量
     * @return ID列表
     */
    public static List<String> generateAlphanumeric(int length, int count) {
        return generate(length, count, ALPHANUMERIC);
    }

    /**
     * 根据字符集生成随机ID
     *
     * @param length   ID长度
     * @param count    生成数量
     * @param charSet  字符集
     * @return ID列表
     */
    private static List<String> generate(int length, int count, String charSet) {
        if (length <= 0) {
            throw new IllegalArgumentException("ID长度必须大于0");
        }
        if (count <= 0) {
            throw new IllegalArgumentException("生成数量必须大于0");
        }
        if (charSet == null || charSet.isEmpty()) {
            throw new IllegalArgumentException("字符集不能为空");
        }

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StringBuilder sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int index = random.nextInt(charSet.length());
                sb.append(charSet.charAt(index));
            }
            ids.add(sb.toString());
        }
        return ids;
    }
}

