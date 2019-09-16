/*
 * using dbDesign9
 */

/**
 * contains data from db tables to avoid searching the db again
 */
public class DBData { //SHOULD THIS BE ABSTRACT?

    private final int id;

    /**
     * check if an object is real (if it's not the first empty string in the comboBox) by checking
     * if some of its fields are null, fields that are 'not null' in the DB because they would need
     * be filled in these objects, therefore if the object is real the field will need to have some
     * value stored in it. some field like "description" or "name" or something like that.
     * i'm saying this because all these objects will have an id, even 'empty string' ones because
     * this constructor sets the default id to 7 if the object doesnt have one.
     */
    public DBData() {
        this.id = 7;
    }

    /**
     *
     * @param id
     */
    public DBData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        /*
         * when over 1k elements, selecting the 1k+ will be a bit slow also because of the printing
         * of the printings happening in the lines below. they are here just to debug.
         */
//        System.out.println("\ntesting here.");
//        System.out.println("this: " + this + "equals: " + obj);
////        return obj != null
////                && obj instanceof DBData
////                && this.toString().equals(obj.toString());

//        boolean result = obj != null
//                && obj instanceof DBData
//                && this.toString().equals(obj.toString());
//        System.out.println("result: " + result);
        return obj != null
                && obj instanceof DBData
                && this.toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        int hash = 5;

        hash = 43 * hash + this.id;

        return hash;
    }

//    @Override
//    public abstract String toString();
}
