package algorithm.numbers;

import java.util.List;

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

    /**
     * 231. 2 的幂  位运算
     * https://leetcode.cn/problems/power-of-two/
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 50. Pow(x, n)
     * https://leetcode.cn/problems/powx-n/
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        if (n == 0){
            return 1.0D;
        }
        if ((n & (n - 1)) == 0) return myPow(x * x, n / 2);

        return (n >0 ? x : 1 / x) * myPow(x * x, n / 2);
    }

    public static void main(String[] args) {
        System.out.println(myPow(2 , -2147483648));
    }

}
