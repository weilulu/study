package string;

/**
 * 判断两个字符串是否互为旋转词
 * 一个字符串str，把字符串str前面任意部分移到后面形成的字符串叫作str的旋转词
 * 如：
 * a="cdab",b="abcd" 返回true
 * a="1ab2",b="ab12" 返回false
 *
 * 思路：
 * 1，将字符串b进行叠加形成b2
 * 2，判断b2是否包含a
 */
public class IsRotation {
    public boolean isRotation(String a,String b){
        if(a== null || b == null ||a.length()!=b.length()){
            return false;
        }
        String b2 = b + b;
        return getIndexOf(b2,a) != -1;//getIndexOf kmp算法
    }
    public int getIndexOf(String s,String m){
        if(s == null || m == null || m.length()<1||s.length()<m.length()){
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si <ss.length && mi<ms.length){
            if(ss[si] == ms[mi]){
                si++;
                mi++;
            }else if(next[mi] == -1){
                si++;
            }else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si-mi : -1;
    }

    public int[] getNextArray(char[] ms){
        if(ms.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length){
            if(ms[pos-1] == ms[cn]){
                next[pos++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else {
                next[pos++] = 0;
            }
        }
        return next;
    }
}
