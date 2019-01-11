import java.util.Stack;
//括号匹配
public class BracketMatch {

    //判断括号是否匹配
    public boolean isValid(String s){

        //创建栈
        Stack<Character> stack = new Stack<>();
        //遍历字符串
        for (int i = 0 ; i < s.length() ; i++){
            //获取每个字符
            char c = s.charAt(i);
            //如果是左括号就入栈
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                //括号没有入栈 , 表示需要和栈顶元素进行消除
                //此时 , 有三种情况
                //第一 , 栈为空 , 没有可以进行消除的元素 , 括号匹配失败
                //第二 , 栈顶有元素 , 但是和括号不匹配 , 返回false
                //第三 , 栈顶有元素 , 返回true

                //判断栈是否为空
                if(stack.isEmpty())
                    return false;

                //获取栈顶元素
                char topChar = stack.pop();
                //判断是否匹配
                if(c == ')' && topChar != '(')
                    return false;
                if(c == ']' && topChar != '[')
                    return false;
                if(c == '}' && topChar != '{')
                    return false;
            }
        }

        //循环结束 , 字符串遍历完毕
        //如果所有的括号匹配成功 ,栈则为空 , 返回true , 反之false
        return stack.isEmpty();
    }


    public static void main(String[] args) {

        System.out.println((new BracketMatch()).isValid("()[]{}"));
        System.out.println((new BracketMatch()).isValid("([)]"));
    }
}
