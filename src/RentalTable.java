/*
 * using dbDesign9
 */

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * movies to be returned in different days, make different rentals.
 *
 * this object doesn't have a toInsert flag because this object is not used in a circumstance where
 * there's a list of this object in and some of them are to be inserted in the DB, and some are not,
 * therefore there's no use for the flag.
 */
public class RentalTable extends DBData {

    private final long dateBegin;
    private final long dateEndPredicted;

    /**
     * actual date of return, it may have been a late return.
     */
    private final long dateEnd;

    private final int active;
    private final int amountOfMovies;
    private final int amountOfDaysPredicted;

    /**
     * total amount, it may be have been a late return.
     */
    private final int amountOfDays;

    private final long totalPricePredicted;

    /**
     * total price, it may have been a late return so it will have the fines included.
     */
    private long totalPrice;

    private final int tbClientId;

    /**
     * flag to know which object is the first empty string in the comboBox, there will be only one
     * emptyString object in each comboBox.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs.
     */
    private final boolean emptyString;

    /**
     * Using this just to create the first empty string in the comboBox, this will be used only once
     * for each comboBox of rentals.
     * the object that is the emptyString will have empty\default values in their attributes, just
     * because final variables need to be initialized otherwise a compile-time error occurs. this
     * constructor is initializing the final attributes to empty\default values.
     */
    public RentalTable() {
        this.dateBegin = 0L;
        this.dateEndPredicted = 0L;
        this.dateEnd = 0L;
        this.active = 0;
        this.amountOfMovies = 0;
        this.amountOfDaysPredicted = 0;
        this.amountOfDays = 0;
        this.totalPricePredicted = 0L;
        this.totalPrice = 0L;
        this.tbClientId = 0;
        this.emptyString = true;
    }

    public RentalTable(
            int id,
            long dateBegin,
            long dateEndPredicted,
            long dateEnd,
            int active,
            int amountOfMovies,
            int amountOfDaysPredicted,
            int amountOfDays,
            long totalPricePredicted,
            int tbClientId
    ) {
        super(id);
        this.dateBegin = dateBegin;
        this.dateEndPredicted = dateEndPredicted;
        this.dateEnd = dateEnd;
        this.active = active;
        this.amountOfMovies = amountOfMovies;
        this.amountOfDaysPredicted = amountOfDaysPredicted;
        this.amountOfDays = amountOfDays;
        this.totalPricePredicted = totalPricePredicted;
        this.tbClientId = tbClientId;
        this.emptyString = false;
    }

    public long getDateBegin() {
        return dateBegin;
    }

    public long getDateEndPredicted() {
        return dateEndPredicted;
    }

    public long getDateEnd() {
        return dateEnd;
    }

    public int getActive() {
        return active;
    }

    public int getAmountOfMovies() {
        return amountOfMovies;
    }

    public int getAmountOfDaysPredicted() {
        return amountOfDaysPredicted;
    }

    public int getAmountOfDays() {
        return amountOfDays;
    }

    public long getTotalPricePredicted() {
        return totalPricePredicted;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public int getTbClientId() {
        return tbClientId;
    }

    public boolean isEmptyString() {
        return emptyString;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    /*
     * First line of the comboBox needs to have an empty string, therefore if the flag emptyString
     * is true this custom toString() will return an empty string, otherwise will show the values.
     *
     * @return
     */
    @Override
    public String toString() {
        String values = "Início do Empréstimo: "
                + String.format("%1$td / %1$tm / %1$tY", new Date(dateBegin))
                + Separator.STANDARD.getValue()
                + "Devolução prevista para: "
                + String.format("%1$td / %1$tm / %1$tY", new Date(dateEndPredicted))
                + Separator.STANDARD.getValue()
                + "Quantidade de filmes: "
                + amountOfMovies
                + " filme(s)"
                + Separator.STANDARD.getValue()
                + "Valor previsto: R$ "
                + new DecimalFormat("#0.00").format(
                        BigDecimal.valueOf(totalPricePredicted)
                                .divide(new BigDecimal(100)))
                + Separator.STANDARD.getValue()
                + "Quantidade de dias previstos: "
                + amountOfDaysPredicted
                + " dia(s)";

        return emptyString ? "" : values;
    }
}
