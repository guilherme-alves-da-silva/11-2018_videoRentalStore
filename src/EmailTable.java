/*
 * using dbDesign9
 */

/**
 * this object doesn't have the emptyString flag because this object is not used in a comboBox,
 * which is the only place where the emptyString flag is used.
 */
public class EmailTable extends DBData {

    private final String email;

    /**
     * flag to know if a given email, inside the list of emails, is to be inserted in the DB (is a
     * new email), or is not to be inserted in the DB (is not a new email therefore it already was
     * in the DB).
     */
    private final boolean toInsert;

    /**
     * doesn't have an id because it will be used in the list of emails as being a new email,
     * therefore it doesn't have a DB id yet.
     *
     * @param email
     */
    public EmailTable(String email) {
        this.email = email;
        this.toInsert = true; //is to insert in the DB because it's a new phone.
    }

    /**
     * id is from the DB, therefore this constructor is used only for emails that came from the DB.
     *
     * @param id
     * @param email
     */
    public EmailTable(
            int id,
            String email
    ) {
        super(id);
        this.email = email;
        this.toInsert = false; //is not to insert in the DB because it's already in the DB.
    }

    public String getEmail() {
        return email;
    }

    public boolean isToInsert() {
        return toInsert;
    }

    @Override
    public String toString() {
        return email;
    }
}
