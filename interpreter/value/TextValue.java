package interpreter.value;

public class TextValue extends Value<String> {

    private String value;

    public TextValue(String value) {
        value = value.replaceAll("\\\\n", "\n");
        value = value.replaceAll("\\\\r", "\r");
        value = value.replaceAll("\\\\t", "\t");

        this.value = value;
    }

    @Override
    public String value() {
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
        } else if (obj instanceof TextValue) {
            return this.value.equals(((TextValue) obj).value);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}
