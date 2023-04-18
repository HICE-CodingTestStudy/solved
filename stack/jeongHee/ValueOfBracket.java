package stack;

import java.util.Scanner;
import java.util.Stack;

public class ValueOfBracket {
    //괄호의 값
    //https://www.acmicpc.net/problem/2504
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Object> stack = new Stack<>();
        String bracket = sc.next();
        for (int i = 0; i < bracket.length(); i++) {
            //열린 괄호는 push
            if(bracket.charAt(i)=='('||bracket.charAt(i)=='['){
                stack.push(bracket.charAt(i));
                continue;
            }
            //)가 나왔을때
            if(bracket.charAt(i)==')'){
                //)인 형태
                if(stack.isEmpty()){
                    System.out.println(0);
                    return;
                }
                //()같은 형태
                if(stack.peek()==(Object) '('){
                    //열린 괄호 뻄
                    stack.pop();
                    //숫자 2 넣음
                    stack.push(2);
                    continue;
                //(숫자)의 형태
                } else if (stack.peek()!=(Object) '[') {
                    //(나올때까지 돌림
                    while (!stack.isEmpty()&&stack.peek()!=(Object) '('){
                        int num = (int) stack.pop();
                        //숫자) 인형태
                        if(stack.isEmpty()){
                            System.out.println(0);
                            return;
                        }
                        //[)의 형태
                        if(stack.peek()==(Object) '['){
                            System.out.println(0);
                            return;
                        }
                        //(숫자)의 형태
                        if(stack.peek()==(Object) '('){
                            stack.pop();
                            stack.push(num);
                            break;
                        }
                        //계속 숫자인 상태
                        stack.push(num+ (int) stack.pop());

                    }

                    stack.push(2*(int) stack.pop());
                    continue;
                }
                //[)의 형태
                System.out.println(0);
                return;
            }

            //]가 나왔을때
            if(bracket.charAt(i)==']'){
                //]인 형태
                if(stack.isEmpty()){
                    System.out.println(0);
                    return;
                }
                //[]같은 형태
                if(stack.peek()==(Object) '['){
                    //열린 괄호 뻄
                    stack.pop();
                    //숫자 3 넣음
                    stack.push(3);
                    continue;
                    //(숫자)의 형태
                } else if (stack.peek()!=(Object) '(') {
                    //[나올때까지 돌림
                    while (!stack.isEmpty()&&stack.peek()!=(Object) '['){
                        int num = (int) stack.pop();
                        //숫자) 인형태
                        if(stack.isEmpty()){
                            System.out.println(0);
                            return;
                        }
                        //(]의 형태
                        if(stack.peek()==(Object) '('){
                            System.out.println(0);
                            return;
                        }
                        //[숫자]의 형태
                        if(stack.peek()==(Object) '['){
                            stack.pop();
                            stack.push(num);
                            break;
                        }
                        //계속 숫자인 상태
                        stack.push(num+ (int) stack.pop());

                    }
                    stack.push(3*(int) stack.pop());
                    continue;
                }
                //(]의 형태
                System.out.println(0);
                return;
            }
            
        }

        // 괄호는 다깜, 나머지 숫자 계산 및 예외처리
        int ans=0;
        while (!stack.isEmpty()){
            if(stack.peek()!=(Object) '(' && stack.peek()!=(Object) '['){
                ans+=(int) stack.pop();
                continue;
            }
            System.out.println(0);
            return;
        }
        System.out.println(ans);
        
    }
}
