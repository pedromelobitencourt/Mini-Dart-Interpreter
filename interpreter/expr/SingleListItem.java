package interpreter.expr;

import java.util.ArrayList;
import java.util.List;

import interpreter.value.Value;

public class SingleListItem extends ListItem {
    private Expr expr;

    public SingleListItem(int line, Expr expr) {
        super(line);
        this.expr = expr;
    }

    public Expr expr() {
        return this.expr;
    }

    @Override
    public List<Value<?>> items() {
        Value<?> v = this.expr.expr();

        List<Value<?>> list = new ArrayList<Value<?>>();
        list.add(v);

        return list;
    }
}
