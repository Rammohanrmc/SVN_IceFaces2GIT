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

package org.icefaces.samples.showcase.example.ace.autocompleteentry;

import org.icefaces.samples.showcase.metadata.annotation.*;
import org.icefaces.samples.showcase.metadata.context.ComponentExampleImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ComponentExample(
        parent = AutoCompleteEntryBean.BEAN_NAME,
        title = "example.ace.autocompleteentry.rows.title",
        description = "example.ace.autocompleteentry.rows.description",
        example = "/resources/examples/ace/autocompleteentry/autoCompleteEntryRows.xhtml"
)
@ExampleResources(
        resources ={
            // xhtml
            @ExampleResource(type = ResourceType.xhtml,
                    title="autoCompleteEntryRows.xhtml",
                    resource = "/resources/examples/ace/autocompleteentry/autoCompleteEntryRows.xhtml"),
            // Java Source
            @ExampleResource(type = ResourceType.java,
                    title="...RowsBean.java",
                    resource = "/WEB-INF/classes/org/icefaces/samples/showcase"+
                    "/example/ace/autocompleteentry/AutoCompleteEntryRowsBean.java"),
            @ExampleResource(type = ResourceType.java,
                    title="AutoCompleteEntryBean.java",
                    resource = "/WEB-INF/classes/org/icefaces/samples/showcase"+
                    "/example/ace/autocompleteentry/AutoCompleteEntryBean.java")
        }
)
@ManagedBean(name= AutoCompleteEntryRowsBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class AutoCompleteEntryRowsBean extends ComponentExampleImpl<AutoCompleteEntryRowsBean> implements Serializable
{
    public static final String BEAN_NAME = "autoCompleteEntryRowsBean";
	public String getBeanName() { return BEAN_NAME; }
    
    private String selectedText;
    private int rows = 6;
    
    public AutoCompleteEntryRowsBean() { 
        super(AutoCompleteEntryRowsBean.class);
    }
    
    public String getSelectedText() {
        return selectedText;
    }
    
    public int getRows() {
        return rows;
    }
    
    public void setSelectedText(String selectedText) {
        this.selectedText = selectedText;
    }
    
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    @PostConstruct
    public void initMetaData() {
        super.initMetaData();
    }
}
