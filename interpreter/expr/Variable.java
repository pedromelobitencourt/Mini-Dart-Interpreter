package interpreter.expr;

import interpreter.value.Value;

public abstract class Variable extends SetExpr {

    private String name;
    private boolean constant;

    protected Variable(int line, String name, boolean constant) {
        super(line);
        this.name = name;
        this.constant = constant;
    }

    public String getName() {
        return name;
    }

    public boolean isConstant() {
        return constant;
    }

    public abstract Value<?> expr();
    public abstract void setValue(Value<?> value);
    
}
