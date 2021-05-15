package sort;

public class Utils {

    public static void change(int[] array,int i,int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void sout(int[] array){
        for(int i=0;i<array.length;i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void biChange(int i,int j){
        System.out.println("i:"+i+",j:"+j);
        i = i ^ j;
        j = i ^ j;
        i = i ^ j;
        System.out.println("i:"+i+",j:"+j);
    }

    public static void main(String[] args) {
        Utils.biChange(2,4);
    }
}
