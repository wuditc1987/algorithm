package algorithm.numbers;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/7/1
 * @description 数字算法
 */
public class Numbers {

    /**
     * 9. 回文数
     * https://leetcode.cn/problems/palindrome-number/
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int temp = 0, num = x;
        while (num != 0){
            temp = temp * 10 + num % 10;
            num /= 10;
        }
        return temp == x;
    }
}
