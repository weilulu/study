import java.util.HashMap;

/**
 * LRU：最近最少使用，也就是淘汰最长时间未使用
 * LFU：一段时间内最少使用
 * @param <K>
 * @param <V>
 * 新加入或get一次则移到尾部，达到容量后删除头部元素
 */
public class LRU<K,V> {
    private HashMap<K,Node<V>> keyNodeMap;
    private HashMap<Node<V>,K> nodeKeyMap;
    private NodeDoubleLinkedList<V> nodeList;
    private int capacity;
    public LRU(int capacity){
        if(capacity < 1){
            throw  new RuntimeException("容量值设置错误");
        }
        this.keyNodeMap = new HashMap<>();
        this.nodeKeyMap = new HashMap<>();
        this.nodeList = new NodeDoubleLinkedList<>();
        this.capacity = capacity;
    }

    public V get(K key){
        if(this.keyNodeMap.containsKey(key)){
            Node<V> node = this.keyNodeMap.get(key);
            this.nodeList.moveNodeToTail(node);
            return node.value;
        }
        return null;
    }
    public void set(K key,V value){
        if(this.keyNodeMap.containsKey(key)){
            Node<V> node = this.keyNodeMap.get(key);
            node.value = value;
            this.nodeList.moveNodeToTail(node);
        }else {
            Node<V> newNode = new Node<>(value);
            this.keyNodeMap.put(key,newNode);
            this.nodeKeyMap.put(newNode,key);
            this.nodeList.addNode(newNode);//加到尾节点
            if(this.keyNodeMap.size() == this.capacity + 1){
                this.removeMostUnusedCache();
            }
        }
    }

    /**
     * 删除头节点；并删除两个Map里的相关的内容
     */
    public void removeMostUnusedCache(){
        Node<V> removedNode = this.nodeList.removedHead();
        K removedKey = this.nodeKeyMap.get(removedNode);
        this.nodeKeyMap.remove(removedNode);
        this.keyNodeMap.remove(removedKey);
    }
}

/**
 * 需要实现三个基本功能：
 * 添加(添加节点到尾节点)
 * 移动节点至尾节点
 * 删除头节点
 *
 * @param <V>
 */
class NodeDoubleLinkedList<V>{
    private Node<V> head;
    private Node<V> tail;
    public NodeDoubleLinkedList(){
        this.head = null;
        this.tail = null;
    }

    /**
     * 将新节点添加到尾节点
     * @param newNode
     */
    public void addNode(Node<V> newNode){
        if(newNode == null){
            return;
        }
        //头节点不存在，直接将新节点当作头节点
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
        }else{
            this.tail.next = newNode;
            newNode.last = this.tail;
            this.tail = newNode;
        }

    }

    /**
     * 将某节点移动到尾节点
     * @param node
     */
    public void moveNodeToTail(Node<V> node){
        if(this.tail == node){
            return;
        }
        //设置新的头节点的前后指针
        if(this.head == node){
            this.head.next = node.next;
            this.head.last = null;//新的头节点上个节点设置为null
        }else{//断链
            node.last.next = node.next;
            node.next.last = node.last;
        }
        //设置尾节点
        node.last = this.tail;//当前节点上一节点指针指向原有尾节点
        node.next = null;//当前节点下一节点指针指向null
        this.tail.next = node;
        this.tail = node;
    }

    /**
     * 删除头节点
     * @return
     */
    public Node<V> removedHead(){
        if(this.head == null){
            return null;
        }
        Node<V> res = this.head;
        if(this.head == this.tail){
            this.head = null;
            this.tail = null;
        }else{
            this.head = res.next;
            res.next = null;
            this.head.last = null;
        }
        return res;
    }
}

/**
 * 定义节点
 * @param <V>
 */
class Node<V>{
    public V value;
    public Node<V> last;//上一节点
    public Node<V> next;//下一节点
    public Node(V value){
        this.value = value;
    }
}