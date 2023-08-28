package interpreter.expr;

import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.ListValue;
import interpreter.value.NumberValue;
import interpreter.value.MapValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class FunctionExpr extends Expr {

    private FunctionOp op;
    private Expr expr;

    private static Scanner input = new Scanner(System.in);

    public FunctionExpr(int line, FunctionOp op, Expr expr) {
        super(line);

        this.op = op;
        this.expr = expr;
    }

    @Override
    public Value<?> expr() {
        Value<?> v = expr.expr();

        switch (op) {
            case READ:
                return readOp(v);
            case RANDOM:
                return randomOp(v);
            case LENGTH:
                return lengthOp(v);
            case KEYS:
                return keysOp(v);
            case VALUES:
                return valuesOp(v);
            case TOBOOL:
                return toBoolOp(v);
            case TOINT:
                return toIntOp(v);
            case TOSTR:
                return toStrOp(v);
            default:
                Utils.abort(super.getLine());
                return null;
        }
    }

    private TextValue readOp(Value<?> v) {
        System.out.print(v);

        String text = input.nextLine().trim();
        return text.isEmpty() ? null : new TextValue(text);
    }

    private NumberValue randomOp(Value<?> v) {
        if(!(v instanceof NumberValue))
            Utils.abort(super.getLine());
        
        NumberValue nv = (NumberValue) v;

        return new NumberValue(new Random().nextInt(nv.value()));
    }

    private NumberValue lengthOp(Value<?> v) { // Contar a quantidade de elementos de uma lista; Gerar um erro em tempo de execução caso não seja uma lista
        if(!(v instanceof ListValue))
            Utils.abort(super.getLine());
        
        return new NumberValue(((ListValue) v).value().size());
    }

    private ListValue keysOp(Value<?> v) { // Obter uma lista com todas as chaves do mapa; Caso contrário, gerar erro
        List<Value<?>> lv = new ArrayList<Value<?>>();
        if(!(v instanceof MapValue))
            Utils.abort(super.getLine());
        
        MapValue mv = (MapValue) v;
        Map<Value<?>, Value<?>> m = mv.value();
        
        for(Value<?> value : m.keySet()) {
            lv.add(value);
        }

        return new ListValue(lv);
    }

    private ListValue valuesOp(Value<?> v) { // Gerar uma lista com todos os valores do mapa. Caso contrário, gerar erro
        List<Value<?>> lv = new ArrayList<Value<?>>();
        if(!(v instanceof MapValue))
            Utils.abort(super.getLine());
        
        MapValue mv = (MapValue) v;
        Map<Value<?>, Value<?>> m = (Map<Value<?>, Value<?>>) v.value();

        for(Value<?> value : m.values()) {
            lv.add(value);
        }

        return new ListValue(lv);
    }

    private BoolValue toBoolOp(Value<?> v) {
        boolean b;
        if(v == null)
            b = false;
        else if(v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            b = bv.value();
        }
        else if(v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            b = (nv.value() != 0) ? true : false;
        }
        else if(v instanceof TextValue) {
            TextValue tv = (TextValue) v;
            b = (tv.value() == "") ? false : true;
        }
        else if(v instanceof ListValue) {
            ListValue lv = (ListValue) v;
            List<Value<?>> listValues = lv.value();

            if(listValues == null || listValues.size() == 0)
                b = false;
            else
                b = true;
        }
        else if(v instanceof MapValue) {
            MapValue mv = (MapValue) v;
            Map<Value<?>, Value<?>> m = mv.value();

            if(m == null || m.size() == 0)
                b = false;
            else
                b = true;
        }
        else 
            b = false;
        
        BoolValue bv = new BoolValue(b);
        return bv;
    }

    private NumberValue toIntOp(Value<?> v) {
        int n;
        if (v == null) {
            n = 0;
        } else if (v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();

            n = b ? 1 : 0;
        } else if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            n = nv.value();
        } else if (v instanceof TextValue) {
            TextValue sv = (TextValue) v;
            String s = sv.value();

            try {
                n = Integer.parseInt(s);
            } catch (Exception e) {
                n = 0;
            }
        } else {
            n = 0;
        }

        return new NumberValue(n);
    }

    private TextValue toStrOp(Value<?> v) {
        String s = v == null ? "null" : v.toString();
        return new TextValue(s);
    }

}
