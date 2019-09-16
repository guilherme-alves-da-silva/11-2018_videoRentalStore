/*
 * using dbDesign9
 */

/**
 * this object doesn't have the emptyString flag because this object is not used in a comboBox,
 * which is the only place where the emptyString flag is used.
 */
public class PhoneNumberTable extends DBData {

    private final String phoneNumber;

    /**
     * flag to know if a given phone, inside the list of phones, is to be inserted in the DB (is a
     * new phone), or is not to be inserted in the DB (is not a new phone therefore it already was
     * in the DB).
     */
    private final boolean toInsert;

    /**
     * doesn't have an id because it will be used in the list of phones as being a new phone,
     * therefore it doesn't have a DB id yet.
     *
     * @param phoneNumber
     */
    public PhoneNumberTable(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.toInsert = true; //is to insert in the DB because it's a new phone.
    }

    /**
     * id is from the DB, therefore this constructor is used only for phones that came from the DB.
     *
     * @param id
     * @param phoneNumber
     */
    public PhoneNumberTable(
            int id,
            String phoneNumber
    ) {
        super(id);
        this.phoneNumber = phoneNumber;
        this.toInsert = false; //is not to insert in the DB because it's already in the DB.
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isToInsert() {
        return toInsert;
    }

    @Override
    public String toString() {
        return phoneNumber;
    }
}
