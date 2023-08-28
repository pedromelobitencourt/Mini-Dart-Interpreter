package interpreter.value;

public class NumberValue extends Value<Integer> {

    private Integer value;

    public NumberValue(Integer value) {
        this.value = value;
    }

    @Override
    public Integer value() {
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
        } else if (obj instanceof NumberValue) {
            return this.value.intValue() == ((NumberValue) obj).value.intValue();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public void setValue(int value) {
        this.value = value;
    }

}
