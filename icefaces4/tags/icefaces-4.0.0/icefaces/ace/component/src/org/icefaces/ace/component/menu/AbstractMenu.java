/*
 * Original Code Copyright Prime Technology.
 * Subsequent Code Modifications Copyright 2011-2014 ICEsoft Technologies Canada Corp. (c)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * NOTE THIS CODE HAS BEEN MODIFIED FROM ORIGINAL FORM
 *
 * Subsequent Code Modifications have been made and contributed by ICEsoft Technologies Canada Corp. (c).
 *
 * Code Modification 1: Integrated with ICEfaces Advanced Component Environment.
 * Contributors: ICEsoft Technologies Canada Corp. (c)
 *
 * Code Modification 2: [ADD BRIEF DESCRIPTION HERE]
 * Contributors: ______________________
 * Contributors: ______________________
 */
package org.icefaces.ace.component.menu;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import org.icefaces.ace.model.MenuModel;

import java.util.Iterator;

public abstract class AbstractMenu extends UIComponentBase {
	
	private final static String BUILT_FROM_MODEL_PARAM = "_builtFromModel";
	
	public void buildMenuFromModel() {
		MenuModel model = (MenuModel) getModel();

		if(model != null) {
            setInView(false);
			getChildren().clear();
			for(UIComponent kid : model.getMenus()) {
                setTransientRecursive(kid);
                UIComponent p = kid.getParent();
                if (p != null) {
                    p.setInView(false);
                    kid.setId(kid.getId());
                    kid.setParent(null);
                }
                getChildren().add(kid);
            }
			markAsBuiltFromModel();
            setInView(true);
		}
	}

    public void processDecodes(FacesContext context) {
        if(shouldBuildFromModel()) {
            buildMenuFromModel();
        }
        super.processDecodes(context);
    }

    protected void setTransientRecursive(UIComponent comp) {
        comp.setTransient(true);
        Iterator<UIComponent> it = comp.getFacetsAndChildren();
        while (it.hasNext()) {
            setTransientRecursive(it.next());
        }
    }
	
	public abstract MenuModel getModel();
	
	public boolean isDynamic() {
		return this.getValueExpression("model") != null;
	}
	
	public boolean isBuiltFromModel() {
		return getFacesContext().getViewRoot().getViewMap().containsKey(this.getClientId() + BUILT_FROM_MODEL_PARAM);
	}
	
	public void markAsBuiltFromModel() {
		getFacesContext().getViewRoot().getViewMap().put(this.getClientId() + BUILT_FROM_MODEL_PARAM, true);
	}
	
	public boolean shouldBuildFromModel() {
		return this.isDynamic();
	}
}