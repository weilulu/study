package dynamic_plan;

/**
 * 最长公共子串问题
 * 给定两个字符串str1和str2，返回两个字符串的最长公共子串。比如
 * str1="1AB2345CE";str2="12345EF".返回"2345"
 *
 */
public class C4 {
    /**
     * 生成动态规划表
     * @param str1
     * @param str2
     * @return
     */
    public int[][] getdp(char[] str1,char[]str2){
        int[][] dp = new int[str1.length][str2.length];
        //生成第一行
        for (int i=0;i<str1.length;i++){
            if(str1[i] == str2[0]){
                dp[i][0] = 1;
            }
        }
        //生成第一列
        for (int j=0;j<str2.length;j++){
            if(str2[j] == str1[0]){
                dp[0][j] = 1;
            }
        }
        //生成其它行与列
        /**
         * dp[i][j]的含义是把str1[i]和str2[j]当作公共子串最后一个字符情况下，
         * 公共子串最长能有多长。(注意，这里的str[i]与str2[j]的值必须要相等)
         * 在当作公共子串最后一个字符情况下，就相当于是这个字符向左能扩到最大的
         * 情况，也就是dp[i-1][j-1]
         */
        for (int i=1;i<str1.length;i++){
            for (int j=1;j<str2.length;j++){
                if(str1[i] == str2[j]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
            }
        }
        return dp;
    }

    /**
     * 1,生成动态规划表
     * 2，找出规划表里最大的值，及最大值所在的位置。这里最大值就是公共子串的长度
     * @param str1
     * @param str2
     * @return
     */
    public String lcst1(String str1,String str2){
        if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return null;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1,chs2);//生成动态规划表
        int end = 0;
        int max = 0;
        for (int i=0;i<chs1.length;i++){
            for (int j=0;j<chs2.length;j++){
                if(dp[i][j] > max){
                    end = i;//最大值在chs1里的位置
                    max = dp[i][j];//最大值
                }
            }
        }
        //根据最大值位置与子串长度进行截取
        return str1.substring(end - max + 1,end + 1);
    }
}
