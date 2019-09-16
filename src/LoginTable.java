/*
 * using dbDesign9
 */

/**
 * this object has 1 more attribute than the actual DB table, it already has the userTypeDescription
 * stored here.
 *
 * this should not go into a final product, i must use a safer implementation to protect the logins
 * and passwords.
 *
 * this object doesn't have a toInsert flag because this object is not used in a circumstance where
 * there's a list of this object in and some of them are to be inserted in the DB, and some are not,
 * therefore there's no use for the flag.
 */
public class LoginTable extends DBData {

    private final String login;
    private final String password;
    private final String notes;
    private final int active;
    private final int tbUserTypeId;

    /**
     * is not final only because this value will come from the DB right after the object creation,
     * but it does not change after it has been set.
     */
    private String userTypeDescription;

    /**
     * flag to know which object is the first empty string in the comboBox, there will be only one
     * emptyString object in each comboBox.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs.
     */
    private final boolean emptyString;

    /**
     * Using this just to create the first empty string in the comboBox, this will be used only once
     * for each comboBox of logins.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs. this
     * constructor is initializing the final attributes to empty\default values.
     */
    public LoginTable() {
        this.login = "";
        this.password = "";
        this.notes = "";
        this.active = 0;
        this.tbUserTypeId = 0;
        this.emptyString = true;
    }

    /**
     *
     * @param id
     * @param login
     * @param password
     * @param notes
     * @param active
     * @param tbUserTypeId
     */
    public LoginTable(
            int id,
            String login,
            String password,
            String notes,
            int active,
            int tbUserTypeId
    ) {
        super(id);
        this.login = login;
        this.password = password;
        this.notes = notes;
        this.active = active;
        this.tbUserTypeId = tbUserTypeId;
        this.emptyString = false;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNotes() {
        return notes;
    }

    public int getActive() {
        return active;
    }

    public int getTbUserTypeId() {
        return tbUserTypeId;
    }

    public String getUserTypeDescription() {
        return userTypeDescription;
    }

    public void setUserTypeDescription(String userTypeDescription) {
        this.userTypeDescription = userTypeDescription;
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
        String values = login
                + Separator.STANDARD.getValue()
                + (active == 1 ? "ATIVO" : "INATIVO");

        return emptyString ? "" : values;
    }
}
