public class ArrayStack<E> implements Stack<E> {

    //维护的数组
    Array<E> array;

    //构造函数

    //用户在创建栈的时候 , 指定了数组的容量
    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }


    public ArrayStack(){
        //默认创建数组的容量为10
        array = new Array<>();
    }

    @Override
    public int getSize() {
        //返回数组中的有效元素
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        //判断数组是否为空即可
        return array.isEmpty();
    }

    //入栈 , 等价于在数组的末尾添加元素
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    //出栈 , 等价于删除数组最后一个元素 , 并返回
    @Override
    public E pop() {
        return array.removeLast();
    }

    //获取栈顶元素 , 即是获取数组的最后一个元素
    @Override
    public E peek() {
        return array.getLast();
    }


    //重写toString方法 , 让打印出来的数据更加人性化
    @Override
    public String toString(){
        //创建动态字符串
        StringBuilder res = new StringBuilder();
        res.append("Stack:");
        res.append("[");

        //遍历栈中的元素
        for(int i = 0 ; i < array.getSize(); i++){
            //字符串拼接
            res.append(array.get(i));
            //判断是否是最后一个元素
            //当循环到了最后一个元素 , 不应该在后面添加逗号
            if (i != array.getSize() - 1)
                //每个元素以逗号分隔
                res.append(",");
        }

        //拼接上最后的括号
        res.append("] top");

        //返回最终的字符串
        return res.toString();
    }

    //获取栈的容积 , 即是数组的容积
    public int getCapacity(){
        return array.getCapacity();
    }



    //测试栈
    public static void main(String[] args) {

        ArrayStack<Integer> stack = new ArrayStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
