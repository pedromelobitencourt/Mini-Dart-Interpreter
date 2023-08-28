package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.NumberValue;
import interpreter.value.MapValue;
import interpreter.value.ListValue;
import interpreter.value.Value;

public class UnaryExpr extends Expr {

    private Expr expr;
    private UnaryOp op;

    public UnaryExpr(int line, Expr expr, UnaryOp op) {
        super(line);
        this.expr = expr;
        this.op = op;
    }

    @Override
    public Value<?> expr() {
        switch (op) {
            case NEG:
                return negOp();
            case NOT:
                return notOp();
            case PRE_INC:
                return preIncOp();
            case POS_INC:
                return posIncOp();
            case PRE_DEC:
                return preDecOp();
            case POS_DEC:
                return posDecOp();
            default:
                Utils.abort(super.getLine());
                return null;
        }
    }

    private Value<?> negOp() {
        Value<?> v = expr.expr();
        if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int n = nv.value();
            int res = -n;
            NumberValue nres = new NumberValue(res);
            return nres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }
    
    private Value<?> notOp() {
        Value<?> v = expr.expr();
        if (v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();
            boolean res = !b;
            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }
    
    private Value<?> preIncOp() {
        Value<?> v = expr.expr();

        if(this.expr instanceof AccessExpr) {
            if(!(v instanceof NumberValue))
                Utils.abort(super.getLine());
            
            AccessExpr ae = (AccessExpr) this.expr;
            v = new NumberValue(((Integer) v.value()) + 1);
            int newIntegerValue = (Integer) v.value() + 1;
            NumberValue newNumberValue = new NumberValue(newIntegerValue);

            ae.setValue(newNumberValue);
            //((AccessExpr) this.expr).setValue(new NumberValue(((Integer) v.value()) + 1));
            return v;
        }

        if(v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int retorno = nv.value() + 1;

            nv.setValue(retorno);

            return new NumberValue(retorno);
        } 
        throw new RuntimeException("Me implemente na classe UnaryExpr no método PreInc");
    }
    
    private Value<?> posIncOp() {
        Value<?> v = expr.expr();

        if(this.expr instanceof AccessExpr) {
            if(!(v instanceof NumberValue))
                Utils.abort(super.getLine());
            
            AccessExpr ae = (AccessExpr) this.expr;
            int newIntegerValue = (Integer) v.value() + 1;
            NumberValue newNumberValue = new NumberValue(newIntegerValue);

            ae.setValue(newNumberValue);
            //((AccessExpr) this.expr).setValue(new NumberValue(((Integer) v.value()) + 1));
            return v;
        }

        if(v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int retorno = nv.value();

            nv.setValue(retorno + 1);

            return new NumberValue(retorno);
        }
        throw new RuntimeException("Me implemente na classe UnaryExpr no método PosInc");
    }
    
    private Value<?> preDecOp() {
        Value<?> v = expr.expr();

        if(this.expr instanceof AccessExpr) {
            if(!(v instanceof NumberValue))
                Utils.abort(super.getLine());
            
            AccessExpr ae = (AccessExpr) this.expr;
            v = new NumberValue(((Integer) v.value()) - 1);
            int newIntegerValue = (Integer) v.value() - 1;
            NumberValue newNumberValue = new NumberValue(newIntegerValue);

            ae.setValue(newNumberValue);
            //((AccessExpr) this.expr).setValue(new NumberValue(((Integer) v.value()) + 1));
            return v;
        }

        if(v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int retorno = nv.value() - 1;

            nv.setValue(retorno);

            return new NumberValue(retorno);
        }
        throw new RuntimeException("Me implemente na classe UnaryExpr no método preDec");
    }
    
    private Value<?> posDecOp() {
        Value<?> v = expr.expr();

        if(this.expr instanceof AccessExpr) {
            if(!(v instanceof NumberValue))
                Utils.abort(super.getLine());
            
            AccessExpr ae = (AccessExpr) this.expr;
            int newIntegerValue = (Integer) v.value() - 1;
            NumberValue newNumberValue = new NumberValue(newIntegerValue);

            ae.setValue(newNumberValue);
            //((AccessExpr) this.expr).setValue(new NumberValue(((Integer) v.value()) + 1));
            return v;
        }

        if(v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int retorno = nv.value();

            nv.setValue(retorno - 1);
            return new NumberValue(retorno);
        }
        else if(v instanceof MapValue) {
            MapValue mv = (MapValue) v;
        }
        throw new RuntimeException("Me implemente na classe UnaryExpr no método posDec!");
    }
    
}
