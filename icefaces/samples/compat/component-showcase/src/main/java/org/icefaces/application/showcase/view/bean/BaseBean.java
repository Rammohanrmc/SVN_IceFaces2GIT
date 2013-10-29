/*
 * Copyright 2004-2013 ICEsoft Technologies Canada Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.icefaces.application.showcase.view.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.icefaces.application.showcase.util.MessageBundleLoader;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.context.effects.Effect;
import com.icesoft.faces.context.effects.Highlight;

import java.io.Serializable;

/**
 * <p>The BaseBean is a nice little helper class for common functionality
 * accross the component examples.  The BaseBean or the notion of a base
 * bean is handy in most application as it can provice commonality for logging,
 * init and dispose methods as well as references to Service lookup
 * mechanism. </p>
 *
 * <p>The valueChangeEffect is used by most example beans to highlight
 * changes in backing bean values that are reflected on the client side.</p>
 *
 * @since 1.7
 */
public class BaseBean implements Serializable {
    //the logger for this class
	protected final Log logger = LogFactory.getLog(this.getClass());

    // effect that shows a value binding chance on there server
    protected Effect valueChangeEffect;

    public BaseBean() {
        valueChangeEffect = new Highlight("#fda505");
        valueChangeEffect.setFired(true);
    }

    /**
     * Resets the valueChange effect to fire when the current response
     * is completed.
     *
     * @param event jsf action event
     */
    public void effectChangeListener(ValueChangeEvent event){
        valueChangeEffect.setFired(false);
    }

    /**
	 * Used to initialize the managed bean.
	 */
	protected void init() {

    }

    public Effect getValueChangeEffect() {
        return valueChangeEffect;
    }

    public void setValueChangeEffect(Effect valueChangeEffect) {
        this.valueChangeEffect = valueChangeEffect;
    }

    /**
     * Utility method for building SelectItem[] from a series of
     * localised entries in a MessageBundle. The SelectItem value
     * is the MessageBundle key, and the label is the localised
     * MessageBundle value.
     *  
     * @param prefix Beginning part of the MessageBundle key
     * @param suffix Ending part of the MessageBundle key
     * @param first First number of the series
     * @param last Lat number of the series
     */
    protected static SelectItem[] buildSelectItemArray(
            String prefix, String suffix, int first, int last) {
        int num = last - first + 1;
        SelectItem[] ret = new SelectItem[num];
        for(int i = 0; i < num; i++) {
            String key = prefix + Integer.toString(first+i) + suffix;
            ret[i] = buildSelectItem(key);
        }
        return ret;
    }
    
    protected static SelectItem buildSelectItem(String key) {
        return new SelectItem(
                key, MessageBundleLoader.getMessage(key));
    }
    
    /**
     * Converts string arrays for displays.
     *
     * @param stringArray string array to convert
     * @return a string concatenating the elements of the string array
     */
    protected static String convertToString(String[] stringArray) {
        if (stringArray == null) {
            return "";
        }
        StringBuffer itemBuffer = new StringBuffer();
        for (int i = 0, max = stringArray.length; i < max; i++) {
            if (i > 0) {
                itemBuffer.append(" , ");
            }
            itemBuffer.append(MessageBundleLoader.getMessage(stringArray[i]));
        }
        return itemBuffer.toString();
    }
}
