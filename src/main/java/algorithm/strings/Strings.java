package algorithm.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/22
 * @description TODO
 */
public class Strings {

    /**
     * 无重复字符的最长子串 leetcode 3
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     *
     * @param s
     *
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        //key 遇到的字符
        //value 最近一次遇到字符的位置
        Map<Character, Integer> map = new HashMap<>(s.length());
        //max为最大长度，left为遇到重复的字符最左边字符的位置
        int max = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, i);
            max = Math.max(max, i - left + 1);
        }

        return max;
    }

    /**
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
                if (i == strs[j].length() || c != strs[j].charAt(i)){
                    return first.substring(0, i);
                }
            }
        }
        return first;
    }

    public static void main(String[] args) {
        int l = lengthOfLongestSubstring(" ");
        System.out.println(l);

//        String[] strs = {"flower","flow","flight"};
//        System.out.println(longestCommonPrefix(strs));
    }
}
