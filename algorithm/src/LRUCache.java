import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用双向链表
 * 这个实现的有点问题 todo
 * @param <V>
 */
public class LRUCache<V> {
    private int capacity = 1024;
    //get在无碰撞下时间复杂度为O(1)；发生了碰撞链表时间复杂度为O(n)、红黑树为O(logn)
    private Map<String,ListNode<String,V>> table = new ConcurrentHashMap<>();
    private ListNode<String,V> head;
    private ListNode<String,V> tail;
    public LRUCache(int capacity){
        this();
        this.capacity = capacity;
    }

    public LRUCache() {
        head = new ListNode<>();
        tail = new ListNode<>();
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
    }

    public V get(String key){
        ListNode<String,V> node = table.get(key);
        if(node == null){
            return null;
        }
        //断链
        node.prev.next = node.next;
        node.next.prev = node.prev;
        //移到表头
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
        //加到容器里
        table.put(key,node);
        return node.value;
    }

    public void put(String key,V value){
        ListNode<String, V> node = table.get(key);
        if(node == null){
            //超过容量，首先移除尾部的节点
            if(table.size() == capacity){
                table.remove(tail.prev.key);
                tail.prev = tail.next;
                tail.next = null;
                tail = tail.prev;
            }
            node = new ListNode<>();
            node.key = key;
            node.value = value;
            table.put(key,node);
        }
        //如果存在，需要移到头部
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache<ListNode> cache = new LRUCache<>(4);
        ListNode<String, Integer> node1 = new ListNode<>("key1", 1);
        ListNode<String, Integer> node2 = new ListNode<>("key2", 2);
        ListNode<String, Integer> node3 = new ListNode<>("key3", 3);
        ListNode<String, Integer> node4 = new ListNode<>("key4", 4);
        ListNode<String, Integer> node5 = new ListNode<>("key5", 5);
        cache.put("key1", node1);
        cache.put("key2", node2);
        cache.put("key3", node3);
        cache.put("key4", node4);
        cache.get("key2");
        cache.put("key5", node5);
        cache.get("key2");

        if(cache != null){
            Map<String, ListNode<String, ListNode>> table = cache.table;
            for(Map.Entry<String,ListNode<String,ListNode>> entry : table.entrySet()){
                String key = entry.getKey();
                System.out.println("key:"+key);
            }
        }
    }

    static class ListNode<K,V>{
        private K key;
        private V value;
        private ListNode<K,V> prev;
        private ListNode<K,V> next;

        public ListNode(){}
        public ListNode(K key,V value) {
            this.key = key;
            this.value = value;
        }
        public V getValue(K key){
            if(key == key){
                return value;
            }
            return null;
        }
    }
}





