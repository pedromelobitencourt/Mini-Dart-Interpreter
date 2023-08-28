package interpreter.expr;

import interpreter.value.Value;
import interpreter.value.MapValue;

import interpreter.util.Utils;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MapExpr extends Expr {
    private List<MapItem> map;

    public MapExpr(int line) {
        super(line);
        this.map = new ArrayList<MapItem>();
    }

    public void addItem(MapItem item) {
        this.map.add(item);
    }

    @Override
    public Value<?> expr() {
        Map<Value<?>, Value<?>> m = new HashMap<Value<?>, Value<?>>();
        
        for (MapItem item : map) {
            Value<?> key = item.key.expr();
            if (key == null)
                Utils.abort(super.getLine());
                
            Value<?> value = item.value.expr();

            m.put(key, value);
        }

        MapValue mv = new MapValue(m);
        return mv;
    }
}
