/*
 * using dbDesign9
 */

/**
 * this object doesn't have a toInsert flag because this object is not used in a circumstance where
 * there's a list of this object in and some of them are to be inserted in the DB, and some are not,
 * therefore there's no use for the flag.
 */
public class UserTypeTable extends DBData {

    private final String description;
    private final String notes;
    private final int active;

    /**
     * flag to know which object is the first empty string in the comboBox, there will be only one
     * emptyString object in each comboBox.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs.
     */
    private final boolean emptyString;

    /**
     * Using this just to create the first empty string in the comboBox, this will be used only once
     * for each comboBox of userTypes.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs. this
     * constructor is initializing the final attributes to empty\default values.
     */
    public UserTypeTable() {
        this.description = "";
        this.notes = "";
        this.active = 0;
        this.emptyString = true;
    }

    /**
     *
     * @param id
     * @param description
     * @param notes
     * @param active
     */
    public UserTypeTable(
            int id,
            String description,
            String notes,
            int active
    ) {
        super(id);
        this.description = description;
        this.notes = notes;
        this.active = active;
        this.emptyString = false;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    public int getActive() {
        return active;
    }

    public boolean isEmptyString() {
        return emptyString;
    }

    /*
     * First line of the comboBox needs to have an empty string, therefore if the flag emptyString
     * is true this custom toString() will return an empty string, otherwise will show the values.
     *
     * @return
     */
    @Override
    public String toString() {
        String values = description;

        return emptyString ? "" : values;
    }
}
