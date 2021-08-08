package print;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/7
 * @description 阿拉伯数字转汉字
 */
public class NumbersChangeChinese {

    public static final String[] CHINESE_NUMBERS = new String[]{"零","一","二","三","四","五","六","七","八","九"};

    public static final String[] CHINESE_CARRY = new String[]{"拾","百","千","万"};

    public static final int TEN = 10;

    public static void main(String[] args) {
        int num = 211111111;
        System.out.println(chinese(num));
    }


    public static String chinese(int number){
        StringBuilder sb = new StringBuilder();
        int num = number;
        if(num == 0 || num / TEN == 0){
            return CHINESE_NUMBERS[num];
        }

        //进位标志
        int carry = -1;
        //进位次数标志
        int times = 0;
        //是否已加入万或亿字
        boolean flag = false;

        while (num != 0){
            if(CHINESE_CARRY.length <= carry){
                carry = 0;
                times ++;
                flag = false;
            }

            //对于中间有多个0的处理
            if(sb.length() != 0 && CHINESE_NUMBERS[0].charAt(0) == sb.charAt(0) && 0 == num % TEN){
                num /= TEN;
                carry ++;
                continue;
            }

            sb.insert(0,CHINESE_NUMBERS[num % 10]);

            if(carry >= 0 && num % 10 != 0){
                sb.insert(1,CHINESE_CARRY[carry]);
            }
            if(times >= 1 && !flag){
                if(times % 2 == 0){
                    sb.insert(2,"亿");
                }else {
                    sb.insert(2,CHINESE_CARRY[3]);
                }
                flag = true;
            }

            num /= TEN;
            carry ++ ;
        }
        return sb.toString();
    }

}
