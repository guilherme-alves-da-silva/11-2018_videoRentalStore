/*
 * using dbDesign9
 */

/**
 * this class has 1 more field than the TB_CITY, it already has the state name stored here.
 *
 * this object doesn't have a toInsert flag because this object is not used in a circumstance where
 * there's a list of this object in and some of them are to be inserted in the DB, and some are not,
 * therefore there's no use for the flag.
 */
public class CityTable extends DBData {

    private final String cityName;
    private final int tbStateId;

    /**
     * is not final only because this value will come from the DB right after the object creation,
     * but it does not change after it has been set.
     */
    private String stateName;

    /**
     * flag to know which object is the first empty string in the comboBox, there will be only one
     * emptyString object in each comboBox.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs.
     */
    private final boolean emptyString;

    /**
     * Using this just to create the first empty string in the comboBox, this will be used only once
     * for each comboBox of cities.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs. this
     * constructor is initializing the final attributes to empty\default values.
     */
    public CityTable() {
        this.cityName = "";
        this.tbStateId = 0;
        this.emptyString = true;
    }

    /**
     *
     * @param id
     * @param cityName
     * @param tbStateId
     */
    public CityTable(
            int id,
            String cityName,
            int tbStateId
    ) {
        super(id);
        this.cityName = cityName;
        this.tbStateId = tbStateId;
        this.emptyString = false;
    }

    public String getCityName() {
        return cityName;
    }

    public int getTbStateId() {
        return tbStateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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
        String values = cityName
                + Separator.STANDARD.getValue()
                + stateName;

        return emptyString ? "" : values;
    }
}
