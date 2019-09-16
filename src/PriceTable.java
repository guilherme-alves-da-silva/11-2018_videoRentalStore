/*
 * using dbDesign9
 */

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * this class has 1 more field than the DB table, it has the "multiplyBy" just to show the prices
 * already multiplied in the list. the "multiplyBy" value is only used in the toString() method.
 *
 * this object doesn't have the emptyString flag because this object is not used in a comboBox,
 * which is the only place where the emptyString flag is used.
 */
public class PriceTable extends DBData {

    private final int amountOfDays;
    private final long price;
    
    /**
     * if it's a new priceCategory, then this price wont have a tbPriceCategoryId until the
     * priceCategory have been inserted in the DB.
     */
    private int tbPriceCategoryId;

    /**
     * this attribute is not present in the TB_PRICE table, is here just to be used in the
     * toString() method and show the prices multiplied in the case of a late return of a rental.
     *
     * this variable is not final because it may be changed by the user through the GUI, and this
     * will only be used to show the multiplied price value in the toString().
     */
    private int multiplyBy;

    /**
     * flag to know if a given price, inside the list of prices, is to be inserted in the DB (is a
     * new price), or is not to be inserted in the DB (is not a new price therefore it already was
     * in the DB).
     */
    private final boolean toInsert;

    /**
     * doesn't have an id because it will be used in the list of prices as being a new price,
     * therefore it doesn't have a DB id yet.
     *
     * doesn't have a tbPriceCategoryId because it's a new price for a new priceCategory, so the
     * priceCategory doesn't have an ID yet.
     *
     * @param amountOfDays
     * @param price
     * @param multiplyBy
     */
    public PriceTable(
            int amountOfDays,
            long price,
            int multiplyBy
    ) {
        this.amountOfDays = amountOfDays;
        this.price = price;
        this.multiplyBy = multiplyBy;
        this.toInsert = true; //is to insert in the DB because it's a new price.
    }

    /**
     * doesn't have an id because it will be used in the list of prices as being a new price,
     * therefore it doesn't have a DB id yet.
     *
     * @param amountOfDays
     * @param price
     * @param tbPriceCategoryId
     * @param multiplyBy
     */
    public PriceTable(
            int amountOfDays,
            long price,
            int tbPriceCategoryId,
            int multiplyBy
    ) {
        this.amountOfDays = amountOfDays;
        this.price = price;
        this.tbPriceCategoryId = tbPriceCategoryId;
        this.multiplyBy = multiplyBy;
        this.toInsert = true; //is to insert in the DB because it's a new price.
    }

    /**
     * id is from the DB, therefore this constructor is used only for prices that came from the DB.
     *
     * @param id
     * @param amountOfDays
     * @param price
     * @param tbPriceCategoryId
     * @param multiplyBy
     */
    public PriceTable(
            int id,
            int amountOfDays,
            long price,
            int tbPriceCategoryId,
            int multiplyBy
    ) {
        super(id);
        this.amountOfDays = amountOfDays;
        this.price = price;
        this.tbPriceCategoryId = tbPriceCategoryId;
        this.multiplyBy = multiplyBy;
        this.toInsert = false; //is not to insert in the DB because it's already in the DB.
    }

    public int getAmountOfDays() {
        return amountOfDays;
    }

    public long getPrice() {
        return price;
    }

    public int getTbPriceCategoryId() {
        return tbPriceCategoryId;
    }

    public boolean isToInsert() {
        return toInsert;
    }

    public int getMultiplyBy() {
        return multiplyBy;
    }

    public void setMultiplyBy(int multiplyBy) {
        this.multiplyBy = multiplyBy;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-35s %-25s %-35s",
                ("Dias: "
                + amountOfDays),
                ("Preço: R$ "
                + new DecimalFormat("#0.00").format(
                        BigDecimal.valueOf(price)
                                .divide(new BigDecimal(100)))),
                ("Dias atrasados: "
                + amountOfDays),
                ("Preço atrasado: R$ "
                + new DecimalFormat("#0.00").format(
                        BigDecimal.valueOf(
                                BigDecimal.valueOf(price)
                                        .divide(new BigDecimal(100))
                                        .multiply(BigDecimal.valueOf(multiplyBy)) //forward from here is just to round to 2 decimal places
                                        .multiply(new BigDecimal(100)) //go two places forward
                                        .longValue() //truncate
                        ).divide(new BigDecimal(100))) //get the right 2 decimal places
                ));
    }
}
