package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.Value;

public class UnsafeVariable extends Variable {

    private boolean initialized;
    private Value<?> value;

    public UnsafeVariable(int line, String name, boolean constant) {
        super(line, name, constant);
        this.initialized = false;
        this.value = null;
    }

    public Value<?> expr() {
        if (!initialized)
            return null;
            //Utils.abort(super.getLine());

        return value;
    }

    public void setValue(Value<?> value) {
        if (initialized && super.isConstant())
            Utils.abort(super.getLine());

        this.value = value;
        this.initialized = true;
    }

}
