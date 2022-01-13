package string;

/**
 * 字符串的统计字符串
 * 如：aabcadd
 * 返回a_2_b_1_c_1_a_1_d_2
 * str[i]==str[i-1]说明连续出现的字符str[i-1]还没有结束，令num++;如果不等了则说明结束
 */
public class StatisticsString {
    public String getCountString(String str){
        if(str == null || str.equals("")){
            return "";
        }
        char[] chs = str.toCharArray();
        String res = String.valueOf(chs[0]);
        int num = 1;
        for (int i=1;i<chs.length;i++){
            if(chs[i] != chs[i-1]){
                res = concat(res,String.valueOf(num),String.valueOf(chs[i]));
                num = 1;
            }else {
                num++;
            }
        }
        return concat(res,String.valueOf(num),"");
    }
    public String concat(String s1,String s2,String s3){
        return s1+"_"+s2+(s3.equals("")?s3:"_"+s3);
    }
}
