package com.jingluo.encryption;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;


public class Cck {

    private static final String E_STR = "cck";

    private static final String SPLIT_STR = ";";

    private static final int E_STR_LEN = E_STR.length();

    // 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163
    private static final int[] PN9_ARR = new int[]{101, 103, 107, 109, 113, 127, 131};

    // 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71
    private static final int[] PN1_ARR = new int[]{29, 31, 37, 41, 43, 47};

    private static final int EN_BASE = 34;

    private static final int EN_RANGE = 34;

    /**
     * 定向加密函数
     *
     * @param content 加密内容
     * @param keys    密钥列表
     * @param EN      加密数
     * @return 加密数据
     */
    private static String encryptionByEn(String content, String[] keys, int EN) {
        StringBuilder result = new StringBuilder();
        char[] contentArr = content.toCharArray();

        // 标注加密方式
        result.append(E_STR);
        // 首位为加密数
        result.append((char) EN);
        // 密钥加密并偏移随机加密数加字符底数
        for (String key : keys) {
            char[] keyArr = key.toCharArray();
            for (
                    int i = 0, j = 0, k = 0, l = 0; i < contentArr.length;
                    i++, j++, k++, l++,
                            j %= PN9_ARR.length, k %= keyArr.length, l %= PN1_ARR.length
            ) {
                contentArr[i] = (char) (
                        contentArr[i] - keyArr[k] % PN9_ARR[j] / PN1_ARR[l] +
                                keyArr[k] % EN / PN1_ARR[l]
                );
            }
        }
        result.append(contentArr);

        return result.toString();
    }

    /**
     * 加密函数
     *
     * @param content 加密内容
     * @param keys    密钥列表
     * @return 加密数据
     */
    public static String encryption(String content, String[] keys) {
        return encryptionByEn(content, keys, (int) (Math.random() * EN_RANGE + EN_BASE));
    }

    /**
     * 时效加密函数
     *
     * @param content 加密内容
     * @param keys    密钥列表
     * @param clock   时钟对象
     * @return 加密数据
     */
    public static String encryption(String content, String[] keys, Clock clock) {
        return encryptionByEn(
                content + SPLIT_STR + Instant.now(clock).toString(),
                keys,
                (int) (Math.random() * EN_RANGE + EN_BASE)
        );
    }

    /**
     * 解密函数
     *
     * @param content 加密内容
     * @param keys    密钥列表
     * @return 解密数据
     */
    public static String decryption(String content, String[] keys) {
        StringBuilder result = new StringBuilder();
        char[] contentArr = content.substring(E_STR_LEN + 1).toCharArray();

        int EN = content.charAt(E_STR_LEN);

        for (int si = keys.length - 1; si >= 0; si--) {
            char[] keyArr = keys[si].toCharArray();
            for (
                    int i = 0, j = 0, k = 0, l = 0; i < contentArr.length;
                    i++, j++, k++, l++,
                            j %= PN9_ARR.length, k %= keyArr.length, l %= PN1_ARR.length
            ) {
                contentArr[i] = (char) (
                        contentArr[i] + keyArr[k] % PN9_ARR[j] / PN1_ARR[l] -
                                keyArr[k] % EN / PN1_ARR[l]
                );
            }
        }
        result.append(contentArr);

        return result.toString();
    }

    /**
     * 检验函数
     *
     * @param content 加密数据
     * @param key     密钥
     * @return 解密内容 | Null
     */
    public static Long verify(String content, String[] key) {
        // 检验加密方式 & 检验加密数据长度
        if (!content.startsWith(E_STR) || content.length() <= E_STR_LEN + 31) return null;

        // 获取加密数
        int EN = content.charAt(E_STR_LEN);
        // 检验加密数范围
        if (EN < EN_BASE || EN >= EN_RANGE + EN_BASE) return null;

        try {
            // 进行解密
            return Long.parseLong(decryption(content, key));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 时效检验函数
     *
     * @param content 加密数据
     * @param key     密钥
     * @param clock   时钟对象
     * @return 解密内容 | Null
     */
    public static Long verify(String content, String[] key, Clock clock, Long validDuration) {
        // 检验加密方式 & 检验加密数据长度
        if (!content.startsWith(E_STR) || content.length() <= E_STR_LEN + 31) return null;

        // 获取加密数
        int EN = content.charAt(E_STR_LEN);
        // 检验加密数范围
        if (EN < EN_BASE || EN >= EN_RANGE + EN_BASE) return null;

        try {
            String[] dcArr = decryption(content, key).split(SPLIT_STR);
            String dContent = dcArr[0];
            String dIso = dcArr[1];

            Duration diff = Duration.between(
                    Instant.parse(dIso),
                    clock.instant()
            );
            long seconds = diff.getSeconds();
            if (seconds > validDuration) return null;

            // 进行解密
            return Long.parseLong(dContent);
        } catch (Exception e) {
            return null;
        }
    }

}
