package TheTokenizer;

public class MultiplicationExpressionNode extends SequenceExpressionNode{
    public MultiplicationExpressionNode() {
        super();
    }

    public MultiplicationExpressionNode(ExpressionNode a,
                                        boolean positive) {
        super(a, positive);
    }

    public int getType() {
        return ExpressionNode.MULTIPLICATION_NODE;
    }

    public double getValue() {
        double prod = 1.0;
        for (Term t : terms) {
            if (t.positive)
                prod *= t.expression.getValue();
            else
                prod /= t.expression.getValue();
        }
        return prod;
    }

    public void accept(ExpressionNodeVisitor visitor) {
        visitor.visit(this);
        for (Term t: terms)
            t.expression.accept(visitor);
    }
}
