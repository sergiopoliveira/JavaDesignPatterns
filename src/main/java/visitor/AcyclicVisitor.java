package visitor;

interface Visitor {} // marker interface

interface ExpressionVisitorAV extends Visitor {
    void visit(ExpressionAV obj);
}

interface DoubleExpressionVisitor extends Visitor{
    void visit(DoubleExpressionAV obj);
}

interface AdditionExpressionVisitor extends Visitor{
    void visit(AdditionExpressionAV obj);
}

abstract class ExpressionAV {

    public void accept(Visitor visitor){
        if(visitor instanceof ExpressionVisitorAV){
            ((ExpressionVisitorAV)visitor).visit(this);
        }
    }
}

class DoubleExpressionAV extends ExpressionAV {

    public double value;

    public DoubleExpressionAV(double value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor){
        if(visitor instanceof DoubleExpressionVisitor){
            ((DoubleExpressionVisitor)visitor).visit(this);
        }
    }
}

class AdditionExpressionAV extends ExpressionAV {

    public ExpressionAV left, right;

    @Override
    public void accept(Visitor visitor){
        if(visitor instanceof AdditionExpressionVisitor){
            ((AdditionExpressionVisitor)visitor).visit(this);
        }
    }

    public AdditionExpressionAV(ExpressionAV left, ExpressionAV right) {
        this.left = left;
        this.right = right;
    }
}

class ExpressionPrinterAV
        implements DoubleExpressionVisitor, AdditionExpressionVisitor {

    private StringBuilder sb = new StringBuilder();

    @Override
    public void visit(DoubleExpressionAV e) {
        sb.append(e.value);
    }

    @Override
    public void visit(AdditionExpressionAV e) {
        sb.append("(");
        e.left.accept(this);
        sb.append("+");
        e.right.accept(this);
        sb.append(")");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}


public class AcyclicVisitor {

    public static void main(String[] args) {
        // 1 + (2+3)
        AdditionExpressionAV e = new AdditionExpressionAV(
                new DoubleExpressionAV(1),
                new AdditionExpressionAV(
                        new DoubleExpressionAV(2),
                        new DoubleExpressionAV(3)
                )
        );
        ExpressionPrinterAV ep = new ExpressionPrinterAV();
        ep.visit(e);
        System.out.println(ep);

    }
}
