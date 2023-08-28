package interpreter.expr;

import interpreter.value.Value;
import interpreter.value.ListValue;
import java.util.List;
import java.util.ArrayList;

public class ListExpr extends Expr {
    
    private List<ListItem> list;

    public ListExpr(int line) {
        super(line);
        this.list = new ArrayList<ListItem>();
    }

    public void addItem(ListItem item) {
        this.list.add(item);
    }

    @Override
    public Value<?> expr() {
        List<Value<?>> lvalues = new ArrayList<Value<?>>();

        for(ListItem li : this.list) {
            for (Value<?> v : li.items()) {
                lvalues.add(v);
            }
        }
        
        ListValue lv = new ListValue(lvalues);
        return lv;
    }
}
