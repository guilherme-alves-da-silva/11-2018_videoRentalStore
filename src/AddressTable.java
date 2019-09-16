/*
 * using dbDesign9
 */

/**
 * This class has 2 more fields than the address table, it already has the city name and state name
 * stored here.
 *
 * this object doesn't have the emptyString flag because this object is not used in a comboBox,
 * which is the only place where the emptyString flag is used.
 */
public class AddressTable extends DBData {

    private final String streetName;
    private final String houseNumber;
    private final String typeOfResidence;
    private final String neighborhood;
    private final int tbCityId;

    /**
     * is not final only because this value will come from the DB right after the object creation,
     * but it does not change after it has been set.
     */
    private String cityName;
    private String stateName;

    /**
     * flag to know if a given address, inside the list of addresses, is to be inserted in the DB
     * (is a new address), or is not to be inserted in the DB (is not a new address therefore it
     * already was in the DB).
     */
    private final boolean toInsert;

    /**
     * doesn't have an id because it will be used in the list of addresses as being a new address,
     * therefore it doesn't have a DB id yet.
     *
     * @param streetName
     * @param houseNumber
     * @param typeOfResidence
     * @param neighborhood
     * @param tbCityId
     * @param cityName
     * @param stateName
     */
    public AddressTable(
            String streetName,
            String houseNumber,
            String typeOfResidence,
            String neighborhood,
            int tbCityId,
            String cityName,
            String stateName
    ) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.typeOfResidence = typeOfResidence;
        this.neighborhood = neighborhood;
        this.tbCityId = tbCityId;
        this.cityName = cityName;
        this.stateName = stateName;
        this.toInsert = true; //is to insert in the DB because it's a new address
    }

    /**
     * id is from the DB, therefore this constructor is used only for addresses that came from the
     * DB.
     *
     * @param id
     * @param streetName
     * @param houseNumber
     * @param typeOfResidence
     * @param neighborhood
     * @param tbCityId
     */
    public AddressTable(
            int id,
            String streetName,
            String houseNumber,
            String typeOfResidence,
            String neighborhood,
            int tbCityId
    ) {
        super(id);
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.typeOfResidence = typeOfResidence;
        this.neighborhood = neighborhood;
        this.tbCityId = tbCityId;
        this.toInsert = false; //is not to insert in the DB because it's already in the DB.
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getTypeOfResidence() {
        return typeOfResidence;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public int getTbCityId() {
        return tbCityId;
    }

    public boolean isToInsert() {
        return toInsert;
    }

    @Override
    public String toString() {
        return "Rua: "
                + streetName
                + Separator.STANDARD.getValue()
                + "Número: "
                + houseNumber
                + Separator.STANDARD.getValue()
                + "Tipo de Residência: "
                + typeOfResidence
                + Separator.STANDARD.getValue()
                + "Bairro: "
                + neighborhood
                + Separator.STANDARD.getValue()
                + "Cidade: "
                + cityName
                + Separator.STANDARD.getValue()
                + "Estado: "
                + stateName;
    }
}
