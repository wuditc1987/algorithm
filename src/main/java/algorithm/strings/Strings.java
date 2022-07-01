package algorithm.strings;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/22
 * @description TODO
 */
public class Strings {

    /**
     * 14. 最长公共前缀
     * https://leetcode.cn/problems/longest-common-prefix/
     * 最长公共子串
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        String first = strs[0];
        //第一个字符串中的前i个字符
        for (int i = 0; i < first.length(); i++){
            char c = first.charAt(i);
            // 从first的下一个字符串开始
            for (int j = 1; j < strs.length; j++){
                String str = strs[j];
                if (i == str.length() || c != str.charAt(i)){
                    return first.substring(0, i);
                }
            }
        }
        return first;
    }

    public static void main(String[] args) {
    }
}
