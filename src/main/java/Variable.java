import java.util.List;


// class for check: json compatible class
public class Variable implements java.io.Serializable {
    //k_copy.setValue(Integer.parseInt((jo.get("value").toString())));
    //        System.out.println(k_copy.getValue());
    private int value;

    /** Property "name", readable/writable. */
    private String name = null;

    /** No-arg constructor (takes no arguments). */
    public Variable() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    /**
     * Getter for property "name".
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property "name".
     *
     * @param value
     */
    public void setName(final String value) {
        this.name = value;
    }
}
