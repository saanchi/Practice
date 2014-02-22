package edu.ufl.cise.codeval;

import java.util.Stack;
import java.util.StringTokenizer;

/**Performs infix computations with +,-,*,/
 * Your task: Add the grouping operators ().
 * The rules to apply for resolving the infix 
 * stacks with grouping:
 * while(true)
 * 0. If there is zero or one operator on the
 * operator stack then terminate loop.
 * 1. If 0 does not apply and if the top two 
 * operators on the operator stack are ) and ( 
 * respectively, remove them.
 * 2. If 0 and 1 do not apply and either of 
 * the top two operators on the operator stack 
 * are (, then terminate loop.
 * 3. If 0-2 do not apply and either the top 
 * operator has less priority (see note below)
 * than the operator beneath it or the top 
 * operator is ), 
 * 	a. pop 1st value v1
 * 	b. pop 2nd value v2
 * 	c. pop 1st operator op1
 * 	d. pop 2nd operator op2
 * 	e. push value v2 op2 v1
 * 	f. push operator op1
 *  4. If 0-3 do not apply, terminate loop.
 * Note: priority 1 has the highest priority, 
 * 2 is next highest and so forth
 * @author Jam Jenkins
 */
public class Infix
{

    public double infix(String expression)
    {
        //remove white space and add evaluation operator
        expression=expression.replaceAll("[\t\n ]", "")+"=";
        String operator="*/+-=";
        //split up the operators from the values
        StringTokenizer tokenizer=new StringTokenizer(expression, operator, true);
        Stack operatorStack=new Stack();
        Stack valueStack=new Stack();
        while(tokenizer.hasMoreTokens())
        {
            //add the next token to the proper stack
            String token=tokenizer.nextToken();
            if(operator.indexOf(token)<0)
                valueStack.push(token);
            else
                operatorStack.push(token);
            //perform any pending operations
            resolve(valueStack, operatorStack);
        }
        //return the top of the value stack
        String lastOne=(String)valueStack.pop();
        return Double.parseDouble(lastOne);   
    }
        
    public int getPriority(String op)
    {
        if(op.equals("*") || op.equals("/"))
            return 1;
        else if(op.equals("+") || op.equals("-"))
            return 2;
        else if(op.equals("="))
            return 3;
        else
            return Integer.MIN_VALUE;
    }
    
    public void resolve(Stack values, 
            Stack operators)
    {
        while(operators.size()>=2)
        {
            String first=(String)operators.pop();
            String second=(String)operators.pop();
            if(getPriority(first)<getPriority(second))
            {
                operators.push(second);
                operators.push(first);
                return;
            }
            else
            {
                String firstValue=(String)values.pop();
                String secondValue=(String)values.pop();
                values.push(getResults(secondValue, second, firstValue));
                operators.push(first);
            }
        }
    }
    
    public String getResults(String operand1, String operator, String operand2)
    {
        System.out.println("Performing "+
                operand1+operator+operand2);
        double op1=Double.parseDouble(operand1);
        double op2=Double.parseDouble(operand2);
        if(operator.equals("*"))
            return ""+(op1*op2);
        else if(operator.equals("/"))
            return ""+(op1/op2);
        else if(operator.equals("+"))
            return ""+(op1+op2);
        else if(operator.equals("-"))
            return ""+(op1-op2);
        else
            return null;
    }
    
    public static void main(String[] args)
    {
        Infix fix=new Infix();
        String expression="0+1-1";
        System.out.println(expression+"="+fix.infix(expression));
    }
}
