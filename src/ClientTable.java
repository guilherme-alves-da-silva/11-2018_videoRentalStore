/*
 * using dbDesign9
 */

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * this object doesn't have a toInsert flag because this object is not used in a circumstance where
 * there's a list of this object in and some of them are to be inserted in the DB, and some are not,
 * therefore there's no use for the flag.
 */
public class ClientTable extends DBData {

    private final String firstName;
    private final String lastName;
    private final String cpf;
    private final long dateOfBirth;
    private final long dateOfAdmission;
    private final int active;
    private final String notes;
    private final long debt;
    private final int totalAmountOfMoviesRented;

    /**
     * flag to know which object is the first empty string in the comboBox, there will be only one
     * emptyString object in each comboBox.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs.
     */
    private final boolean emptyString;

    /**
     * Using this just to create the first empty string in the comboBox, this will be used only once
     * for each comboBox of clients.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs. this
     * constructor is initializing the final attributes to empty\default values.
     */
    public ClientTable() {
        this.firstName = "";
        this.lastName = "";
        this.cpf = "";
        this.dateOfBirth = 0L;
        this.dateOfAdmission = 0L;
        this.active = 0;
        this.notes = "";
        this.debt = 0L;
        this.totalAmountOfMoviesRented = 0;
        this.emptyString = true;
    }

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param cpf
     * @param dateOfBirth
     * @param dateOfAdmission
     * @param active
     * @param notes
     * @param debt
     * @param totalAmountOfMoviesRented
     */
    public ClientTable(
            int id,
            String firstName,
            String lastName,
            String cpf,
            long dateOfBirth,
            long dateOfAdmission,
            int active,
            String notes,
            long debt,
            int totalAmountOfMoviesRented
    ) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.dateOfAdmission = dateOfAdmission;
        this.active = active;
        this.notes = notes;
        this.debt = debt;
        this.totalAmountOfMoviesRented = totalAmountOfMoviesRented;
        this.emptyString = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public long getDateOfAdmission() {
        return dateOfAdmission;
    }

    public int getActive() {
        return active;
    }

    public String getNotes() {
        return notes;
    }

    public long getDebt() {
        return debt;
    }

    public int getTotalAmountOfMoviesRented() {
        return totalAmountOfMoviesRented;
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
        String values = firstName
                + " "
                + lastName
                + Separator.STANDARD.getValue()
                + "CPF: "
                + cpf
                + Separator.STANDARD.getValue()
                + (active == 1 ? "ATIVO" : "INATIVO")
                + Separator.STANDARD.getValue()
                + "Quantidade total de filmes emprestados: "
                + totalAmountOfMoviesRented
                + " filme(s)"
                + Separator.STANDARD.getValue()
                + "Devendo: R$ "
                + new DecimalFormat("#0.00").format(
                        BigDecimal.valueOf(debt)
                                .divide(new BigDecimal(100)));

        return emptyString ? "" : values;
    }
}
