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

package com.icesoft.faces.component.gmap;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.icesoft.faces.component.ext.UIColumns;

public class GMapLatLngs extends UIPanel{
	public static final String COMPONENT_TYPE = "com.icesoft.faces.GMapLatLngs";
    public static final String COMPONENT_FAMILY = "com.icesoft.faces.GMapLatLngs";

	private List value;
	public GMapLatLngs() {
		setRendererType(null);
	}

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public String getComponentType() {
        return COMPONENT_TYPE;
    }
    
    public void encodeBegin(FacesContext context) throws IOException {
    	setRendererType(null);
        super.encodeBegin(context);    	
    	Iterator it = getValue().iterator();
    	StringBuffer latLngScript = new StringBuffer();
    	while(it.hasNext()) {
	    	UIComponent kid = (UIComponent)it.next();
            if (!kid.isRendered()) continue;	    	
	    	kid.encodeBegin(context);
		    if (kid.getRendersChildren()) {
		    	kid.encodeChildren(context);
		    }
		    kid.encodeEnd(context);
		    latLngScript.append(kid.getAttributes().get("latLngScript") + 
		    		"kid-id"+ kid.getClientId(context) + ";");
    	}
	    this.getAttributes().put("latLngsScript", latLngScript);
    }

	public List getValue() {
		if (value != null) {
			return value;
	    }
	    ValueBinding vb = getValueBinding("value");
	    return vb != null ? (List) vb.getValue(getFacesContext()) : null;
	}

	public void setValue(List value) {
		this.value = value;
	}

    private transient Object values[];
    public void restoreState(FacesContext context, Object state) {
        values = (Object[])state;        
        super.restoreState(context, values[0]);
        value = (List)values[1];
    }


    public Object saveState(FacesContext context) {
        if(values == null){
            values = new Object[2];
        }
        values[0] = super.saveState(context);
        values[1] = value;
        return values;
    }

}
