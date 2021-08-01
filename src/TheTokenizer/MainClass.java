package TheTokenizer;

import java.util.Scanner;


public class MainClass {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String exprstr = sc.nextLine();

        Parser parser = new Parser();
        try {
            ExpressionNode expr = parser.parse(exprstr);
            expr.accept(new SetVariable("a", Math.PI));
            System.out.println("The value of the expression is "+expr.getValue());
        }
        catch (ParserException e) {
            System.out.println(e.getMessage());
        }
        catch (EvaluationException e) {
            System.out.println(e.getMessage());
            System.out.println("Enter a variable: ");
            String var = sc.nextLine();
            System.out.println("Enter a value: ");
            int val = sc.nextInt();
            ExpressionNode expr = parser.parse(exprstr);
            expr.accept(new SetVariable(var, val));
            System.out.println("The value of the expression is "+expr.getValue());
        }
    }
}
