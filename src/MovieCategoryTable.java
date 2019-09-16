/*
 * using dbDesign9
 */

public class MovieCategoryTable extends DBData {

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
     * flag to know if a given movieCategory, inside the list of movieCategories, is to be inserted
     * in the DB (is a new movieCategory), or is not to be inserted in the DB (is not a new
     * movieCategory therefore it already was in the DB).
     */
    private final boolean toInsert;

    /**
     * Using this just to create the first empty string in the comboBox, this will be used only once
     * for each comboBox of priceCategories.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs. this
     * constructor is initializing the final attributes to empty\default values.
     */
    public MovieCategoryTable() {
        this.description = "";
        this.notes = "";
        this.active = 0;
        this.toInsert = false;
        this.emptyString = true;
    }

    /**
     *
     * @param id
     * @param description
     * @param notes
     * @param active
     * @param toInsert
     */
    public MovieCategoryTable(
            int id,
            String description,
            String notes,
            int active,
            boolean toInsert
    ) {
        super(id);
        this.description = description;
        this.notes = notes;
        this.active = active;
        this.toInsert = toInsert;
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

    public boolean isToInsert() {
        return toInsert;
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
