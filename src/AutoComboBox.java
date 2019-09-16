
/*
 * From http://java.sun.com/docs/books/tutorial/index.html
 */

 /*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class AutoComboBox extends JComboBox {

    private class AutoTextFieldEditor extends BasicComboBoxEditor {

        private AutoTextField getAutoTextFieldEditor() {
            return (AutoTextField) editor;
        }

        AutoTextFieldEditor(java.util.List list) {
            editor = new AutoTextField(list, AutoComboBox.this);
        }
    }

    public AutoComboBox(java.util.List list) {
        isFired = false;
        autoTextFieldEditor = new AutoTextFieldEditor(list);
        setEditable(true);
        setModel(new DefaultComboBoxModel(list.toArray()) {

            @Override
            protected void fireContentsChanged(Object obj, int i, int j) {
                if (!isFired) {
                    super.fireContentsChanged(obj, i, j);
                }
            }

        });
        setEditor(autoTextFieldEditor);
    }

    public boolean isCaseSensitive() {
        return autoTextFieldEditor.getAutoTextFieldEditor().isCaseSensitive();
    }

    public void setCaseSensitive(boolean flag) {
        autoTextFieldEditor.getAutoTextFieldEditor().setCaseSensitive(flag);
    }

    public boolean isStrict() {
        return autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
    }

    public void setStrict(boolean flag) {
        autoTextFieldEditor.getAutoTextFieldEditor().setStrict(flag);
    }

    public java.util.List getDataList() {
        return autoTextFieldEditor.getAutoTextFieldEditor().getDataList();
    }

    public void setDataList(java.util.List list) {
        autoTextFieldEditor.getAutoTextFieldEditor().setDataList(list);
        setModel(new DefaultComboBoxModel(list.toArray()));
    }

    /**
     * this method works only for objects with different toString()s, as it uses it to find a match.
     *
     * i changed this method to work with custom objects, if you are going to work only with
     * Strings, reverse this implementation because it will be quicker. this implementation is
     * iterating through the list to find the object that has a toString that equals this str taken
     * as input, and then sets such found object as the selected object in the model. if your
     * objects are just strings, then it wont need to iterate to find an object, it just sets the
     * str from input as the selected object, thus saving time.
     *
     * more comments inside the method.
     *
     * @param str receives an String from the AutoTextField.java implementation for an autocomplete
     * comboBox, but it could be easily changed to receive and declare objects. previously this
     * param obj was of type Object, but it was being called only from two places inside the
     * AutoTextField.java code and it was only passing Strings, so i've made a change in this method
     * to work with custom objects, and not only with strings, but to find the custom object it uses
     * the .toString() of the object, therefore i've made it to work considering a String as input,
     * because even if it was an Object obj, it would still be a String being passed to it, so this
     * way is more clear straight away the type of the input.
     */
    public void setSelectedValue(Object str) {
        if (isFired) {
            return;
        } else {
            isFired = true;
//            setSelectedItem(obj); //previous 'original' implementation
//            System.out.println("inside setSelectedValue");
//            setSelectedItem(str);

            /**
             * this iterarion seems costly to do it everytime, but it needs to set the object that
             * contains such string, and not just make the string be the selected object. a better
             * implementation could be to use the list that's passed here through the constructor
             * and use a indexOf inside such list, maybe make another list so that i can
             * indexOf(str) and get the inner values of the custom object from the other list by
             * accessing the same index, in this scenario i could make two lists, one having the
             * strings, or the toString()s defined in the customObject, and another list having the
             * complete custom object, so that way i can indexOf(str) and get the complete object
             * from the second list using the result of the indexOf(str), the string list would have
             * only the strings of the compete customObjects present in the second list precisely in
             * the same index position. but...such 'better implementation' would use the indexOf
             * everytime as well, and the implementation of indexOf is an iteration through the
             * whole list until the target object, therefore it's the same as what's being done
             * here, so doing the iteration here is better because it wouldnt need to have the
             * second list of complete objects, and overall it's better for maintenance because it's
             * more clar of what's happening everytime.
             *
             * the iteration is happening inside the setSelectedItem using sequencialSearch as well,
             * is quite slow and i've thought about some algorithms to search an alphabetically
             * sorted list, but considering that this is just some homework for a teacher that maybe
             * wont even read the code, as he apparently doesnt care much about it, then i'll
             * put my time into AI algorithms atm. Another option is to carry the id in the string
             * inside the comboBox and make a search in the db using such id, as the id is the
             * primary key, the db itself will be implementing indexing and such algorithm is
             * already faster then this sequencial search. But apparently the anomaly is the tens of
             * thousands of rows inside the comboBox, because considering that the JComboBox uses
             * sequencial search inside, then maybe the JComboBox was not made to handle tens of
             * thousands of rows. if this were a product for a client, i would maybe make a db
             * design more focused on performance, as i have already done but for a homework for
             * that teacher, maybe he wouldnt like it, and learn more about this swing API and make
             * some customizations if needed.
             */
            for (int i = 0; i < super.getItemCount(); i++) {
                if (super.getItemAt(i).toString().equals(str)) {
//                    System.out.println(getItemAt(i) + " found. i: " + i);
                    super.setSelectedItem(super.getItemAt(i));
//                    super.setSelectedIndex(i);
                    break;
                }
            }
            fireItemStateChanged(new ItemEvent(this, 701, selectedItemReminder, 1));
            isFired = false;
            return;
        }
    }

    @Override
    protected void fireActionEvent() {
        if (!isFired) {
            super.fireActionEvent();
        }
    }

    private AutoTextFieldEditor autoTextFieldEditor;

    private boolean isFired;

}
