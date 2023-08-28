package interpreter.expr;

import interpreter.util.Utils;

import interpreter.value.Value;
import interpreter.value.MapValue;
import java.util.Map;

import java.util.List;

import interpreter.value.ListValue;
import interpreter.value.NumberValue;

public class AccessExpr extends SetExpr {
    private SetExpr base;
    private Expr index;

    public AccessExpr(int line, SetExpr base, Expr index) {
        super(line);
        this.base = base;
        this.index = index;
    }

    @Override
    public Value<?> expr() {
        //return base.expr();
        //throw new RuntimeException("metodo expr da classe AcessExpr");

        Value<?> bvalue = base.expr();
        if (bvalue instanceof ListValue) {
            List<Value<?>> lv = (List<Value<?>>) bvalue.value();

            if(!(this.index.expr() instanceof NumberValue))
                Utils.abort(super.getLine(), "O índice deve ser um número");

            NumberValue nv = (NumberValue) this.index.expr();
            Integer i = nv.value();

            if(i > ((ListValue) bvalue).maxIndex())
                return null;
                //Utils.abort(super.getLine());

            return lv.get(i);

        } else if (bvalue instanceof MapValue) {
            MapValue mv = (MapValue) bvalue;
            Map<Value<?>, Value<?>> map = mv.value();

            Value<?> ivalue = index.expr();
            
            if (ivalue == null) {
                Utils.abort(super.getLine());
            }

            return map.get(ivalue);
        } else {
            Utils.abort(super.getLine());
        }

        return null; 
    }

    @Override
    public void setValue(Value<?> value) {
        if(base.expr() instanceof ListValue) {
            if(((NumberValue) this.index.expr()).value() > ((ListValue) base.expr()).maxIndex()) {
                Utils.abort(super.getLine());
            }
            ((ListValue) base.expr()).value().set(((NumberValue) this.index.expr()).value(), value);
        }
        else if(base.expr() instanceof MapValue) {
            Value<?> valueIndex = this.index.expr();
            MapValue baseSet = (MapValue) this.base.expr();

            if(this.index.expr() == null)
                Utils.abort(super.getLine());

            if(!((baseSet.value().containsKey(valueIndex)))) {
                baseSet.value().put(valueIndex, value);
            }
            else{
                baseSet.value().replace(valueIndex, value);
            }

            //baseSet.value().replace(valueIndex, value);
            //((HashMap)(this.base.expr().value())).replace((this.index.expr()).value(), value.value());
        }
        else
            Utils.abort(super.getLine());

        // TODO: AccessExpr
        //throw new RuntimeException("metodo setValue da classe AcessExpr"); // Verificar index válido
    }

    public Value<?> getBase() {
        return this.base.expr();
    }

    public Value<?> getIndex() {
        return this.index.expr();
    }
    
}
