package interpreter.expr;

import java.util.List;
import interpreter.value.Value;

public abstract class ListItem {
    private int line;

    protected ListItem(int line) {
        this.line = line;
    }

    public int getLine() {
        return this.line;
    }

    public abstract List<Value<?>> items();
}
