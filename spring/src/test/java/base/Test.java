package base;

public class Test {

    /**
     * 执行了main方法后，只会输出:
     * test start...
     * event happened
     * test end...
     * 并不会输出access test
     * @param args
     */
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println("test start...");
        test.testEvent(new Event() {
            @Override
            public void access() {
                System.out.println("access test");
            }
        });
        System.out.println("test end...");
    }
    public void testEvent(Event event){
        System.out.println("event happened");
    }
}

interface Event{
    void access();
}
