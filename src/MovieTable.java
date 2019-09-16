/*
 * using dbDesign9
 */

/**
 * this object doesn't have a toInsert flag because this object is not used in a circumstance where
 * there's a list of this object in and some of them are to be inserted in the DB, and some are not,
 * therefore there's no use for the flag.
 */
public class MovieTable extends DBData {

    private final String name;
    private final String yearOfRelease;
    private final long dateOfAcquisition;
    private final String description;
    private final int available;
    private final String notes;
    private final String kindOfMedia;
    private final int damaged;
    private final int active;
    private final int tbPriceCategoryId;

    /**
     * flag to know which object is the first empty string in the comboBox, there will be only one
     * emptyString object in each comboBox.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs.
     */
    private final boolean emptyString;

    /**
     * Using this just to create the first empty string in the comboBox, this will be used only once
     * for each comboBox of movies.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs. this
     * constructor is initializing the final attributes to empty\default values.
     */
    public MovieTable() {
        this.name = "";
        this.yearOfRelease = "";
        this.dateOfAcquisition = 0L;
        this.description = "";
        this.available = 0;
        this.notes = "";
        this.kindOfMedia = "";
        this.damaged = 0;
        this.active = 0;
        this.tbPriceCategoryId = 0;
        this.emptyString = true;
    }

    /**
     *
     * @param id
     * @param name
     * @param yearOfRelease
     * @param dateOfAcquisition
     * @param description
     * @param available
     * @param notes
     * @param kindOfMedia
     * @param damaged
     * @param active
     * @param tbPriceCategoryId
     */
    public MovieTable(
            int id,
            String name,
            String yearOfRelease,
            long dateOfAcquisition,
            String description,
            int available,
            String notes,
            String kindOfMedia,
            int damaged,
            int active,
            int tbPriceCategoryId
    ) {
        super(id);
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.dateOfAcquisition = dateOfAcquisition;
        this.description = description;
        this.available = available;
        this.notes = notes;
        this.kindOfMedia = kindOfMedia;
        this.damaged = damaged;
        this.active = active;
        this.tbPriceCategoryId = tbPriceCategoryId;
        this.emptyString = false;
    }

    public String getName() {
        return name;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public long getDateOfAcquisition() {
        return dateOfAcquisition;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailable() {
        return available;
    }

    public String getNotes() {
        return notes;
    }

    public String getKindOfMedia() {
        return kindOfMedia;
    }

    public int getDamaged() {
        return damaged;
    }

    public int getActive() {
        return active;
    }

    public int getTbPriceCategoryId() {
        return tbPriceCategoryId;
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
        String values = name
                + Separator.STANDARD.getValue()
                + yearOfRelease
                + Separator.STANDARD.getValue()
                + kindOfMedia
                + Separator.STANDARD.getValue()
                + (available == 1 ? "DISPONÍVEL" : "NÃO DISPONÍVEL")
                + Separator.STANDARD.getValue()
                + "Estado físico: "
                + (damaged == 1 ? "Danificado" : "Bom estado")
                + Separator.STANDARD.getValue()
                + description;

        return emptyString ? "" : values;
    }
}
