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
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || "".equals(s.trim())){
            return 0;
        }
        Map<Character,Integer> map = new HashMap<>(s.length());
        //max为最大长度，left为遇到重复的字符最左边字符的位置
        int max = 0,left = 0;
        for (int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                left = Math.max(left,map.get(c) + 1);
            }
            map.put(c,i);
            max = Math.max(max,i - left + 1);
        }

        return max;
    }

    public static void main(String[] args) {
        Strings s = new Strings();
        int l = s.lengthOfLongestSubstring("aaaaa");
        System.out.println(l);
    }
}
