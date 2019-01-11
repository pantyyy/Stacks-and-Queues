public class ArrayQueue<E> implements Queue<E> {


    //底层维护的数组
    private Array<E> array;

    //构造方法
    public ArrayQueue(int capacity){array = new Array<>(capacity);}
    public ArrayQueue(){array = new Array<>();}

    @Override
    public int getSzie() {
        //返回数组中维护size即可
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        //判断数组是否为空
        return array.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        //向数组尾插入元素
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        //删除数组首元素
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        //获取数组中下标为0的元素
        return array.getFirst();
    }



    @Override
    public String toString(){
        //动态字符串 , 方便拼接
        StringBuilder res = new StringBuilder();
        res.append("Queue ");
        res.append("front [");

        //遍历队列
        for (int i = 0 ; i < array.getSize() ; i++){
            //能进入循环 , 代表队列中有元素 , 所以先添加再说
            res.append(array.get(i));
            //判断是否是最后一个元素
            if (i != array.getSize() - 1) {
                //如果把这行代码加在if里面 , 那么最后一个元素就无法添加进入res
                //res.append(array.get(i));
                res.append(",");
            }
        }

        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }


}
