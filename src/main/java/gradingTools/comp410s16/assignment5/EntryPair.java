package gradingTools.comp410s16.assignment5;

/**
 * COMP 410
 *
 * this is complete code, with
 * getters and setters for the fields in an element
 * you should not need to add any code to it
 */
public class EntryPair {
    public String value;
    public int priority;

    public EntryPair(String aValue, int aPriority) {
        // variables set when object is created
        value=aValue;
        priority=aPriority;
    }
    public EntryPair() {
        // default constructor, must set variables
        // after creating object using setters or directly
    }

    public String getValue() { return value; }
    public int getPriority() { return priority; }
    public void setValue(String aValue) { value=aValue; }
    public void setPriority(int aPriority) { priority=aPriority; }

    @Override
    public String toString() {
        return priority+"";
    }
}