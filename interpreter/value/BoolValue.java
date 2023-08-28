package interpreter.value;

public class BoolValue extends Value<Boolean> {

    private Boolean value;

    public BoolValue(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean value() {
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
        } else if (obj instanceof BoolValue) {
            return this.value.booleanValue() == ((BoolValue) obj).value.booleanValue();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}
