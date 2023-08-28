package interpreter.expr;

import interpreter.util.Utils;

import interpreter.value.Value;
import interpreter.value.ListValue;
import java.util.List;

public class SpreadListItem extends ListItem {
    private Expr expr;

    public SpreadListItem(int line, Expr expr) {
        super(line);
        this.expr = expr;
    }

    public List<Value<?>> items() {
        if(!(this.expr.expr() instanceof ListValue)) {
            Utils.abort(super.getLine());
        }
        return ((ListValue) this.expr.expr()).value();
        //throw new RuntimeException("metodo items da classe SpreadListItem");
    }
}
