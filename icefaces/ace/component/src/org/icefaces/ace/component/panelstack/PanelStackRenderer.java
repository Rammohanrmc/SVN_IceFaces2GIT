/*
 * Copyright 2004-2014 ICEsoft Technologies Canada Corp.
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
package org.icefaces.ace.component.panelstack;


import org.icefaces.ace.renderkit.CoreRenderer;
import org.icefaces.ace.component.stackpane.StackPane;
import org.icefaces.ace.util.HTML;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

public class PanelStackRenderer extends CoreRenderer {

   private static final Logger logger = Logger.getLogger(PanelStackRenderer.class.getName());


    public void encodeBegin(FacesContext facesContext, UIComponent uiComponent)throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();
         String clientId = uiComponent.getClientId(facesContext);
         PanelStack container = (PanelStack) uiComponent;


            /* write out root tag.   */
         writer.startElement(HTML.DIV_ELEM, uiComponent);
         writer.writeAttribute(HTML.ID_ATTR, clientId, HTML.ID_ATTR);
         writer.writeAttribute(HTML.CLASS_ATTR, "ace-panelstack", null);

    }

    public boolean getRendersChildren() {
        return true;
    }

    public void encodeChildren(FacesContext facesContext, UIComponent uiComponent) throws IOException{
        for (UIComponent child : uiComponent.getChildren()) {
             if (!(child instanceof StackPane) && logger.isLoggable(Level.FINER)){
                 logger.finer("all children must be of type stackPane");
                 return;
             }else {
             Map attributes = child.getAttributes();
             boolean notSet = false;
             StackPane pane = (StackPane) child;
             if (attributes.get("selFlag")!=null){
                pane.setSelected(Boolean.TRUE);
                notSet = true;
             } else {
                 pane.setSelected(Boolean.FALSE);
             }
             PanelStack container = (PanelStack) uiComponent;
             if (child.getId().endsWith(container.getSelectedId()) && notSet==false){
                    //check to see if handler set the value!!
               //      logger.info(" HANDLER unable to set selected value!!!");
                     pane.setSelected(Boolean.TRUE);
                 }
             }
        }
        super.renderChildren(facesContext, uiComponent);
    }

    public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException {
         ResponseWriter writer = facesContext.getResponseWriter();
         PanelStack stack = (PanelStack) uiComponent;
         String clientId = uiComponent.getClientId(facesContext);
         writer.startElement("span", uiComponent);
         writer.startElement("input", uiComponent);
         writer.writeAttribute("type", "hidden", null);
         writer.writeAttribute("id", clientId+"_hidden", null);
         writer.writeAttribute("name", clientId+"_hidden", null);
         String selectedId = stack.getSelectedId();
         if( selectedId != null && selectedId.length() > 0 ) {
             writer.writeAttribute("value", selectedId, null);
         }
         writer.endElement("input");
         writer.endElement("span");
         writer.endElement(HTML.DIV_ELEM);

    }

}
