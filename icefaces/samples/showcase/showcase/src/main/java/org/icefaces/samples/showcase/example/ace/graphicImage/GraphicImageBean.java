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

package org.icefaces.samples.showcase.example.ace.graphicImage;

import org.icefaces.samples.showcase.metadata.annotation.*;
import org.icefaces.samples.showcase.metadata.context.ComponentExampleImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;

@ComponentExample(
        title = "example.ace.graphicImage.title",
        description = "example.ace.graphicImage.description",
        example = "/resources/examples/ace/graphicImage/graphicImage.xhtml"
)
@ExampleResources(
        resources ={
            // xhtml
            @ExampleResource(type = ResourceType.xhtml,
                    title="graphicImage.xhtml",
                    resource = "/resources/examples/ace/graphicImage/graphicImage.xhtml"),
            // Java Source
            @ExampleResource(type = ResourceType.java,
                    title="GraphicImageBean.java",
                    resource = "/WEB-INF/classes/org/icefaces/samples/showcase"+
                    "/example/ace/graphicImage/GraphicImageBean.java")
        }
)
@Menu(
    title = "menu.ace.graphicImage.subMenu.title", 
    menuLinks = {
        @MenuLink(title = "menu.ace.graphicImage.subMenu.main", isDefault = true, exampleBeanName = GraphicImageBean.BEAN_NAME)
    }
)

@ManagedBean(name = GraphicImageBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class GraphicImageBean extends ComponentExampleImpl<GraphicImageBean> implements Serializable {
    public static final String BEAN_NAME = "graphicImageBean";
	public String getBeanName() { return BEAN_NAME; }
    
    public GraphicImageBean() {
        super(GraphicImageBean.class);
    }
    
    @PostConstruct
    public void initMetaData() {
        super.initMetaData();
    }
}
