package string;

/**
 * 判断两个字符串是否互为变形词
 * 给定两个字符串str1,str2，如果str1与str2中出现的字符种类一样且每种字符出现的次数也一样，那str1,str2互为变形词。
 * 举例：
 * str1="123",str2="231"，返回true
 * str1="123",str2="2331"，返回false
 * 思路：
 * 1，两个字符串长度必须相等
 * 2，申请一个长度为255的整形数组统计每个字符出现的频率（这里假设出现的字符编码值在0～255之间，当然也可以使用hashMap记录频率）
 */
public class IsDeformationString {
    public boolean isDeformation(String str1,String str2){
        if(str1 == null || str2 == null || str1.length() != str2.length()){
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        //这种做法与申请bit数组有点类似，具体的值做为数组的索引，数组值为出现次数
        int[] map = new int[256];
        for (int i=0;i<chars1.length;i++){
            map[chars1[i]]++;//统计字符串里每种字符频率
        }
        for (int j=0;j<chars2.length;j++){
            if(map[chars2[j]]-- == 0){//说明chars[i]至少有一次，而char[j]有两次或者一次也没有
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str1 = "bba";
        String str2 = "bbc";
        IsDeformationString isDeformationString = new IsDeformationString();
        boolean deformation = isDeformationString.isDeformation(str1, str2);
        System.out.println(deformation);
    }
}
