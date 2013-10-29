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

package org.icefaces.samples.showcase.example.compat.menuPopup;

import java.io.Serializable;

public class FormattedWord implements Serializable {
    private String text;
    private String style;
    
    public FormattedWord(String text) {
        this(text, null);
    }
    
    public FormattedWord(String text, String style) {
        this.text = text;
        this.style = style;
    }
    
    public String getText() { return text; }
    public String getStyle() { return style; }
    
    public void setText(String text) { this.text = text; }
    public void setStyle(String style) { this.style = style; }
    
    public String toString() {
        return text;
    }
}
