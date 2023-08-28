package interpreter.value;

import java.util.Map;

public class MapValue extends Value<Map<Value<?>, Value<?>>> {

    private Map<Value<?>, Value<?>> value;

    public MapValue(Map<Value<?>, Value<?>> value) {
        this.value = value;
    }

    @Override
    public Map<Value<?>, Value<?>> value() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof MapValue) {
            return this.value == ((MapValue) obj).value;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{");

        for (Map.Entry<Value<?>, Value<?>> e : this.value.entrySet()) {
            Value<?> k = e.getKey();
            Value<?> v = e.getValue();

            sb.append(k.toString());
            sb.append(":");
            sb.append(v == null ? "null" : v.toString());
            sb.append(", ");
        }

        if (sb.length() > 1)
            sb.setLength(sb.length() - 2);

        sb.append("}");
        return sb.toString();
    }

}
