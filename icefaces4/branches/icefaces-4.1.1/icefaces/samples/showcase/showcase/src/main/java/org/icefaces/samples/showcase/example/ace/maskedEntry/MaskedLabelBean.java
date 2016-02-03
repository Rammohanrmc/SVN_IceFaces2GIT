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

package org.icefaces.samples.showcase.example.ace.maskedEntry;

import java.io.Serializable;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name= MaskedLabelBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class MaskedLabelBean implements Serializable
{
    public static final String BEAN_NAME = "maskedLabelBean";
	public String getBeanName() { return BEAN_NAME; }
    
    private String labelText = "Masked DOB:";
    private String labelPosition = "left";
    private String dob;
    private String workPhone;
    private String mobilePhone;
    private String sin;
    
    public String getLabelText() {
        return labelText;
    }
    
    public String getLabelPosition() {
        return labelPosition;
    }

    public String getSin() {
        return sin;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getDob() {
        return dob;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }
    
    public void setLabelPosition(String labelPosition) {
        this.labelPosition = labelPosition;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }
}
