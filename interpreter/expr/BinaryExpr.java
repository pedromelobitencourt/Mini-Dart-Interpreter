package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.MapValue;
import interpreter.value.ListValue;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

public class BinaryExpr extends Expr {

    private Expr left;
    private BinaryOp op;
    private Expr right;

    public BinaryExpr(int line, Expr left, BinaryOp op, Expr right) {
        super(line);
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public Value<?> expr() {
        Value<?> v1 = left.expr();
        Value<?> v2 = right.expr();

        switch (op) {
            case IF_NULL:
                return ifNullOp(v1, v2);
            case AND:
                return andOp(v1, v2);
            case OR:
                return orOp(v1, v2);
            case EQUAL:
                return equalOp(v1, v2);
            case NOT_EQUAL:
                return notEqualOp(v1, v2);
            case LOWER_THAN:
                return lowerThanOp(v1, v2);
            case LOWER_EQUAL:
                return lowerEqualOp(v1, v2);
            case GREATER_THAN:
                return greaterThanOp(v1, v2);
            case GREATER_EQUAL:
                return greaterEqualOp(v1, v2);
            case ADD:
                return addOp(v1, v2);
            case SUB:
                return subOp(v1, v2);
            case MUL:
                return mulOp(v1, v2);
            case DIV:
                return divOp(v1, v2);
            case MOD:
                return modOp(v1, v2);
            default:
                Utils.abort(super.getLine(), "Não há essa operação binária");
                return null;
        }
    }

    private Value<?> ifNullOp(Value<?> v1, Value<?> v2) {
        if(v1 == null) {
            return v2;
        }
        return v1;
    }

    private Value<?> andOp(Value<?> v1, Value<?> v2) {
        if(v1 instanceof BoolValue && v2 instanceof BoolValue) {
            BoolValue bv1 = (BoolValue) v1;
            BoolValue bv2 = (BoolValue) v2;

            return new BoolValue((bv1.value()) && (bv2.value()));
        }
        else {
            return null;
        }
    }

    private Value<?> orOp(Value<?> v1, Value<?> v2) {
        if(v1 instanceof BoolValue && v2 instanceof BoolValue){
            BoolValue bv1 = (BoolValue) v1;
            BoolValue bv2 = (BoolValue) v2;

            return new BoolValue((bv1.value() || bv2.value()));
        }
        else {
            return null;
        }
    }

    private Value<?> equalOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = n1 == n2;

            BoolValue bres = new BoolValue(res);

            return bres;
        }
        /*else if((v1 instanceof NumberValue && v2 instanceof BoolValue)) { // || (v1 instanceof BoolValue && v2 instanceof NumberValue)
            NumberValue nv1 = (NumberValue) v1;
            BoolValue bv2 = (BoolValue) v2;

            int n1 = nv1.value();
            boolean b2 = bv2.value();

            boolean res = (n1 != 0 && b2 == true) || (n1 == 0 && b2 == false);
            
            BoolValue bres = new BoolValue(res);

            return bres;
        }

        else if((v1 instanceof BoolValue && v2 instanceof NumberValue)) { // || (v1 instanceof BoolValue && v2 instanceof NumberValue)
            BoolValue bv1 = (BoolValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            boolean b1 = bv1.value();
            int n2 = nv2.value();

            boolean res = (n2 != 0 && b1 == true) || (n2 == 0 && b1 == false);
            
            BoolValue bres = new BoolValue(res);

            return bres;
        }*/
        else if(v1 instanceof ListValue && v2 instanceof ListValue) {
            ListValue lv1 = (ListValue) v1;
            ListValue lv2 = (ListValue) v2;

            List<Value<?>> l1 = lv1.value();
            List<Value<?>> l2 = lv2.value();

            int size1 = l1.size();

            if(size1 != l2.size()) {
                BoolValue bres = new BoolValue(false);
                return bres;
            }

            for(int i = 0; i < size1; i++) {
                Value<?> value1 = l1.get(i);
                Value<?> value2 = l2.get(i);

                if(value1 instanceof NumberValue && value2 instanceof NumberValue) {
                    Value bres = this.equalOp(value1, value2);
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(!b) {
                        BoolValue bres2 = new BoolValue(false);
                        return bres2;
                    }
                }
                else if(value1 instanceof TextValue && value2 instanceof TextValue) {
                    Value bres = this.equalOp(value1, value2);
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(!b) {
                        BoolValue bres2 = new BoolValue(false);
                        return bres2;
                    }
                }
                else if(value1 instanceof BoolValue && value2 instanceof BoolValue) {
                    Value bres = this.equalOp(value1, value2);
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(!b) {
                        BoolValue bres2 = new BoolValue(false);
                        return bres2;
                    }
                }
                else if(value1 instanceof ListValue && value2 instanceof ListValue) {
                    Value bres = this.equalOp(value1, value2);
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(!b) {
                        BoolValue bres2 = new BoolValue(false);
                        return bres2;
                    }
                }
                else if(value1 instanceof MapValue && value2 instanceof MapValue) {
                    Value bres = this.equalOp(value1, value2);
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(!b) {
                        BoolValue bres2 = new BoolValue(false);
                        return bres2;
                    }
                }
            }

            BoolValue bres = new BoolValue(true);
            return bres;

        }
        else if(v1 instanceof MapValue && v2 instanceof MapValue) {
            MapValue mv1 = (MapValue) v1;
            MapValue mv2 = (MapValue) v2;

            Map<Value<?>, Value<?>> m1 = mv1.value();
            Map<Value<?>, Value<?>> m2 = mv2.value();

            int size1 = m1.size();

            if(m1.size() != m2.size()) {
                BoolValue bres = new BoolValue(false);
                return bres;
            }

            // Check the keySet and the values of each map

            Set<Value<?>> s1Keys = m1.keySet();
            Set<Value<?>> s2Keys = m2.keySet();

            for(int i = 0; i < size1; i++) {
                Value<?> value1 = (Value<?>) s1Keys.toArray()[i];

                if(m1.get(value1) instanceof ListValue) {
                    Value bres = this.equalOp(m1.get(value1), m2.get(value1));
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(!b) {
                        BoolValue bres2 = new BoolValue(false);
                        return bres2;
                    }
                }
                else if(m1.get(value1) instanceof NumberValue) {
                    Value bres = this.equalOp(m1.get(value1), m2.get(value1));
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(!b) {
                        BoolValue bres2 = new BoolValue(false);
                        return bres2;
                    }
                }
                else if(m1.get(value1) instanceof TextValue) {
                    Value bres = this.equalOp(m1.get(value1), m2.get(value1));
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(!b) {
                        BoolValue bres2 = new BoolValue(false);
                        return bres2;
                    }
                }
                else if(m1.get(value1) instanceof BoolValue) {
                    Value bres = this.equalOp(m1.get(value1), m2.get(value1));
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(!b) {
                        BoolValue bres2 = new BoolValue(false);
                        return bres2;
                    }
                }
                else if(m1.get(value1) instanceof MapValue) {
                    Value bres = this.equalOp(m1.get(value1), m2.get(value1));
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(!b) {
                        BoolValue bres2 = new BoolValue(false);
                        return bres2;
                    }
                }

            }

            //if((m1.keySet().containsAll(m2.keySet())) && (m1.values().containsAll(m2.values()))) {
             //   BoolValue bres = new BoolValue(true);
             //   return bres;
            //}

            BoolValue bres = new BoolValue(true);
            return bres;

        }
        else if(v1 instanceof BoolValue && v2 instanceof BoolValue){
            BoolValue bv1 = (BoolValue) v1;
            BoolValue bv2 = (BoolValue) v2;

            boolean b1 = bv1.value();
            boolean b2 = bv2.value();

            boolean res = (b1 == b2);
            BoolValue bres = new BoolValue(res);

            return bres;
        }
        else if(v1 instanceof TextValue && v2 instanceof TextValue) {
            TextValue tv1 = (TextValue) v1;
            TextValue tv2 = (TextValue) v2;

            String t1 = tv1.value();
            String t2 = tv2.value();

            boolean res = (t1.equals(t2));
            BoolValue bres = new BoolValue(res);

            return bres;
        }
        else if(v1 == null && v2 == null) {
            BoolValue bres = new BoolValue(true);

            return bres;
        }
        else if((v1 == null && v2 != null) || (v2 == null && v1 != null)) {
            BoolValue bres = new BoolValue(false);

            return bres;
        }
        else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> notEqualOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = n1 != n2;

            BoolValue bres = new BoolValue(res);

            return bres;
        }
        /*else if((v1 instanceof NumberValue && v2 instanceof BoolValue)) { // || (v1 instanceof BoolValue && v2 instanceof NumberValue)
            NumberValue nv1 = (NumberValue) v1;
            BoolValue bv2 = (BoolValue) v2;

            int n1 = nv1.value();
            boolean b2 = bv2.value();

            boolean res = (n1 != 0 && b2 == false) || (n1 == 0 && b2 == true);
            
            BoolValue bres = new BoolValue(res);

            return bres;
        }

        else if((v1 instanceof BoolValue && v2 instanceof NumberValue)) { // || (v1 instanceof BoolValue && v2 instanceof NumberValue)
            BoolValue bv1 = (BoolValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            boolean b1 = bv1.value();
            int n2 = nv2.value();

            boolean res = (n2 != 0 && b1 == false) || (n2 == 0 && b1 == true);
            
            BoolValue bres = new BoolValue(res);

            return bres;
        } */
        else if((v1 instanceof BoolValue && v2 instanceof BoolValue)){
            BoolValue bv1 = (BoolValue) v1;
            BoolValue bv2 = (BoolValue) v2;

            boolean b1 = bv1.value();
            boolean b2 = bv2.value();

            boolean res = (b1 != b2);
            BoolValue bres = new BoolValue(res);

            return bres;
        }
        else if(v1 instanceof TextValue && v2 instanceof TextValue) {
            TextValue tv1 = (TextValue) v1;
            TextValue tv2 = (TextValue) v2;

            String t1 = tv1.value();
            String t2 = tv2.value();

            boolean res = (!t1.equals(t2));
            BoolValue bres = new BoolValue(res);

            return bres;
        }
        else if(v1 instanceof ListValue && v2 instanceof ListValue) {
            ListValue lv1 = (ListValue) v1;
            ListValue lv2 = (ListValue) v2;

            List<Value<?>> l1 = lv1.value();
            List<Value<?>> l2 = lv2.value();

            int l1Size = l1.size();

            if(l1Size != l2.size()) {
                BoolValue bres = new BoolValue(true);
                return bres;
            }

            for(int i = 0; i < l1Size; i++) {
                Value<?> value1 = l1.get(i);
                Value<?> value2 = l2.get(i);

                if(value1 instanceof TextValue && value2 instanceof TextValue) {
                    Value bres = this.notEqualOp(value1, value2);
                    boolean b = ((BoolValue) bres).value();
                    if(b) { // Caso seja diferente
                        BoolValue bres1 = new BoolValue(true);
                        return bres1;
                    }
                }
                else if(value1 instanceof NumberValue && value2 instanceof NumberValue) {
                    Value bres = this.notEqualOp(value1, value2);

                    boolean b = ((BoolValue) bres).value();
                    if(b) { // Caso seja diferente
                        BoolValue bres1 = new BoolValue(true);
                        return bres1;
                    }

                }
                else if(value1 instanceof BoolValue && value2 instanceof BoolValue) {
                    Value bres = this.notEqualOp(value1, value2);

                    boolean b = ((BoolValue) bres).value();
                    if(b) { // Caso seja diferente
                        BoolValue bres1 = new BoolValue(true);
                        return bres1;
                    }

                }
                else if(value1 instanceof ListValue && value2 instanceof ListValue) {
                    Value bres = this.notEqualOp(value1, value2);

                    boolean b = ((BoolValue) bres).value();
                    if(b) { // Caso seja diferente
                        BoolValue bres1 = new BoolValue(true);
                        return bres1;
                    }
                }
                else if(value1 instanceof MapValue && value2 instanceof MapValue) {
                    Value bres = this.notEqualOp(value1, value2);

                    boolean b = ((BoolValue) bres).value();
                    if(b) { // Caso seja diferente
                        BoolValue bres1 = new BoolValue(true);
                        return bres1;
                    }
                }
            }
            BoolValue bres = new BoolValue(false);
            return bres;
        }
        else if(v1 instanceof MapValue && v2 instanceof MapValue) {
            MapValue mv1 = (MapValue) v1;
            MapValue mv2 = (MapValue) v2;

            Map<Value<?>, Value<?>> m1 = mv1.value();
            Map<Value<?>, Value<?>> m2 = mv2.value();

            int size1 = m1.size();

            if(m1.size() != m2.size()) {
                BoolValue bres = new BoolValue(true);
                return bres;
            }

            // Check the keySet and the values of each map

            Set<Value<?>> s1Keys = m1.keySet();
            Set<Value<?>> s2Keys = m2.keySet();

            for(int i = 0; i < size1; i++) {
                Value<?> value1 = (Value<?>) s1Keys.toArray()[i];

                if(m1.get(value1) instanceof ListValue) {
                    Value bres = this.notEqualOp(m1.get(value1), m2.get(value1));
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(b) {
                        BoolValue bres2 = new BoolValue(true);
                        return bres2;
                    }
                }
                else if(m1.get(value1) instanceof NumberValue) {
                    Value bres = this.notEqualOp(m1.get(value1), m2.get(value1));
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(b) {
                        BoolValue bres2 = new BoolValue(true);
                        return bres2;
                    }
                }
                else if(m1.get(value1) instanceof TextValue) {
                    Value bres = this.notEqualOp(m1.get(value1), m2.get(value1));
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(b) {
                        BoolValue bres2 = new BoolValue(true);
                        return bres2;
                    }
                }
                else if(m1.get(value1) instanceof BoolValue) {
                    Value bres = this.notEqualOp(m1.get(value1), m2.get(value1));
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(b) {
                        BoolValue bres2 = new BoolValue(true);
                        return bres2;
                    }
                }
                else if(m1.get(value1) instanceof MapValue) {
                    Value bres = this.notEqualOp(m1.get(value1), m2.get(value1));
                    BoolValue bres1 = (BoolValue) bres;
                    boolean b = bres1.value();
                    
                    if(b) {
                        BoolValue bres2 = new BoolValue(true);
                        return bres2;
                    }
                }

            }

            //if((m1.keySet().containsAll(m2.keySet())) && (m1.values().containsAll(m2.values()))) {
             //   BoolValue bres = new BoolValue(true);
             //   return bres;
            //}

            BoolValue bres = new BoolValue(false);
            return bres;
        }
        else{
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> lowerThanOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = n1 < n2;

            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> lowerEqualOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = n1 <= n2;

            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> greaterThanOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = n1 > n2;

            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> greaterEqualOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = n1 >= n2;

            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> addOp(Value<?> v1, Value<?> v2) {
        if(v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            return new NumberValue(nv1.value() + nv2.value());
        }
        else if(v1 instanceof TextValue && v2 instanceof TextValue) {
            TextValue tv1 = (TextValue) v1;
            TextValue tv2 = (TextValue) v2;

            return new TextValue(tv1.value() + tv2.value());
        }
        /*else if(v1 instanceof ListValue && !(v2 instanceof ListValue)) {
            ListValue lv = (ListValue) v1;
            List<Value<?>> l = lv.value();

            if(v2 == null)
                Utils.abort(super.getLine());
            
            l.add(v2);

            return lv;
        }*/
        else if(v1 instanceof ListValue && v2 instanceof ListValue) {
            ListValue lv1 = (ListValue) v1;
            ListValue lv2 = (ListValue) v2;

            ListValue returnList;
            List<Value<?>> returnListValues = new ArrayList<Value<?>>();

            if(v2 == null)
                Utils.abort(super.getLine());

            List<Value<?>> l1 = lv1.value();
            List<Value<?>> l2 = lv2.value();

            for(Value<?> value : l1) {
                returnListValues.add(value);
            }

            for(Value<?> value : l2) {
                returnListValues.add(value);
            }

            returnList = new ListValue(returnListValues);

            return returnList;

        }
        else if(v1 instanceof MapValue && v2 instanceof MapValue) {
            MapValue mv1 = (MapValue) v1;
            MapValue mv2 = (MapValue) v2;

            MapValue returnMapValue;
            Map<Value<?>, Value<?>> returnMap = new HashMap<Value<?>, Value<?>>();

            Map<Value<?>, Value<?>> m1 = mv1.value();
            Map<Value<?>, Value<?>> m2 = mv2.value();

            Set<Value<?>> keySet1 = m1.keySet();
            Set<Value<?>> keySet2 = m2.keySet();
            
            for(Value<?> value1 : keySet1) {
                returnMap.put(value1, m1.get(value1));
            }

            for(Value<?> value2 : keySet2) {
                returnMap.put(value2, m2.get(value2));
            }

            returnMapValue = new MapValue(returnMap);

            return returnMapValue;
        }
        else{
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> subOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            int res = n1 - n2;

            NumberValue nres = new NumberValue(res);
            return nres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> mulOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            int res = n1 * n2;

            NumberValue nres = new NumberValue(res);
            return nres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> divOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            if (n2 != 0) {
                int res = n1 / n2;

                NumberValue nres = new NumberValue(res);
                return nres;
            }
        }

        Utils.abort(super.getLine());
        return null;
    }

    private Value<?> modOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            if (n2 != 0) {
                int res = n1 % n2;

                NumberValue nres = new NumberValue(res);
                return nres;
            }
        }
        
        Utils.abort(super.getLine());
        return null;
    }
    
}
