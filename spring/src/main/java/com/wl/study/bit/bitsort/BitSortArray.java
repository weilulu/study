package com.wl.study.bit.bitsort;

/**
 * @Author:weilu
 * @Date: 2019/4/12 11:48
 *
 * 使用位图进行排序
 * 待排序的数：4,7,2,5,3
 * 思路：
 * 1,待排序数列最大为7，所以构造8个bit位的数组
 * 2，初始化数组的每一位为0
 * 3，取出待排序的数字，根据数字大小把bit数组对应的下标置为1
 *     0  1  2  3  4  5  6 7
 *    ----------------------
 *    |0 |0 |0 |0 |1 |0 |0 |
 *    ----------------------
 *    对于第一个数字4，那对应的bit数组每4位会被置为1；依此操作对应的数字与数组
 *
 * 4,打印非0位的下标，会按顺序输出，这样就排序完成
 *
 * 最后：这个可以作用java.util.BitSet来实现
 */
public class BitSortArray {

    private int bitLength = 8;
    private byte[] bytes;
    public byte[] getBytes(){
        return bytes;
    }

    /**
     * 构建一定位数的数组
     * @param bitLength
     */
    public BitSortArray(int bitLength){
        this.bitLength = bitLength;
        int len = (int)Math.ceil((double)bitLength / 7);
        bytes = new byte[len];
    }

    /**
     * 指定位置为1
     * @param position
     */
    public void mark(int position){
        if(position > bitLength){
            return;
        }
        //4,7,2,5,14,3,8,12
        int arrIndex = position / 7;
        int bitIndex = position % 7;
        bytes[arrIndex] |=  (1 << (6 - bitIndex));
        //4 | (1 << 2);第2位 TODO....
        //1 | (1 << 6);第6位
        //2 | (1 << 2);
        //5 | (1 << 2);
        //2 | (1 << 2);
        //3 | (1 << 2);
        //1 | (1 << 2);
        //1 | (1 << 2);
    }


    /**
     * 指定位置为0
     * @param position
     */
    public void cleanMark(int position){
        if(position > bitLength){
            return;
        }
        int arrIndex = position / 7;
        int bitIndex = position % 7;
        bytes[arrIndex] &= ~(1 << (6 - bitIndex));
    }

    public void printAllBit(){
        for(byte bt : bytes){
            System.out.println(BitSortArray.byte2String(bt));
        }
    }

    public static String byte2String(byte bt){
        StringBuilder sb = new StringBuilder();
        for(int i=6;i>=0;i--){
            int j = (int)bt & (int)(Math.pow(2,(double)i));
            if(j > 0){
                sb.append("1");
            }else {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    public static int[] bitmapSort(int[] arr,int max){
        if(arr == null || arr.length <=0){
            return null;
        }
        BitSortArray bitSortArray = new BitSortArray(max + 1);
        for(int anArr : arr){
            bitSortArray.mark(anArr);
        }
        int[] result = new int[arr.length];
        byte[] bytes = bitSortArray.getBytes();
        int index = 0;
        for (int i=0;i<bytes.length;i++){
            for(int j=0;j<7;j++){
                byte temp = (byte)(1<<6-j);
                byte b = (byte)(bytes[i] & temp);
                if(b == temp){
                    result[index++] = i * 7 + j;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {4,7,2,5,14,3,8,12};
        int[] end = bitmapSort(a,14);
        for(int x :end){
            System.out.print(x+",");
        }
    }
}
