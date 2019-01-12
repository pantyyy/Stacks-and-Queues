public class LoopQueue<E> implements Queue<E> {

    //维护的数组
    private E[] data;
    private int front , tail;
    private int size;

    public LoopQueue(int capacity){
        //加1的原因 : 循环队列需要使用一个额外的空间来区分满和空的状态
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    //返回的是减去浪费空间的容积
    public int getCapacity(){
        //因为有一个空间是固定浪费的 , 所以不进入计算
        return data.length - 1;
    }

    @Override
    public int getSzie() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return tail == front;
    }

    //入队
    @Override
    public void enqueue(E e) {
        //判断队列是否为满 , 需要扩容
        //队列为满的条件 : (tail + 1)%数组长度 = front
        if(front == (tail+1) % data.length)
            resize(getCapacity() * 2);

        //在tail的位置添加元素
        data[tail] = e;
        //改变tail
        tail = (tail + 1) % data.length;
        //有效元素加1
        size++;
    }

    @Override
    public E dequeue() {
        //判断是否为空
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        E ret = data[front];
        data[front] = null;
        //更新front
        front = (front + 1) % data.length;
        size--;

        //判断是否需要缩容
        //缩容条件 : 当有效元素的个数 等于 容量的四分之一的时候
        //且 , 缩容不能缩容为0
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            //缩容一半
            //注意getCapacity()不能为1 , 因为会出现缩容为0的情况
            resize(getCapacity() / 2);

        return ret;
    }

    //获取队首元素
    @Override
    public E getFront() {
        //判断队列是否为空
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }


    //改变容积
    private void resize(int newCapacity){
        //创建一个新的数组 , 加上浪费的那个空间
        E[] newData = (E[])new Object[newCapacity + 1];

        //原数组中的值赋值给新数组
        for (int i = 0 ; i < size ; i++){
            //赋值
            newData[i] = data[(i+front) % data.length];
        }

        //队列中维护的数组指向新建的数组
        data = newData;
        //改变维护的front和tail
        front = 0;
        tail = size;

    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");

        //遍历循环队列
        //循环的次数为size , 队列中的有效元素个数

        //遍历循环队列的第一种方法:
        /*
        for(int i = 0 ; i < size ; i++){
            //通过front指针的移动 , 循环获取队列中的元素
            res.append(data[(i + front) % data.length]);
            //如何判断遍历到了最后一个元素?
            //如果到了最后一个元素 , 那么front指针在移动一次 , 就会和tail指针重合
            if ((i + front + 1) % data.length != tail)
                res.append(",");
        }
        */


        //遍历循环队列的第二种方法:
        for(int i = front ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            if( (i + 1) % data.length != tail)
                res.append(",");
        }



        res.append("] tail");
        return res.toString();
    }



    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>();
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
