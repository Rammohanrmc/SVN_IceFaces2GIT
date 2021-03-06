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

package org.icefaces.samples.showcase.example.ace.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;

import org.icefaces.ace.model.chart.BubbleSeries;

@ManagedBean(name= ChartBubbleBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class ChartBubbleBean implements Serializable {
    public static final String BEAN_NAME = "chartBubbleBean";
	public String getBeanName() { return BEAN_NAME; }

    public List<BubbleSeries> bubbleData = new ArrayList<BubbleSeries>() {{
        add(new BubbleSeries() {{
            setBubbleAlpha(30);
            setHighlightAlpha(30);
            setVaryBubbleColors(false);
            add(11,123,1236, "Acura");
            add(45,92,1076, "Alfa Romeo");
            add(24,104,1176, "AM General");
            add(50,24,610, "Aston Martin Lagonda");
            add(18,17,539, "Audi");
            add(7,89,864, "BMW");
            add(2,13,1026, "Bugatti");
        }});

        add(new BubbleSeries() {{
            setBubbleAlpha(30);
            setHighlightAlpha(30);
            setVaryBubbleColors(false);
            add(11,213,756, "Boeing");
            add(54,60,376, "Airbus");
            add(42,140,676, "GE");
            add(25,42,310, "Rolls Royce");
            add(65,142,239, "Avro");
            add(5,200,164, "Fairchild");
            add(32,231,1026, "Lockheed");
        }});
    }};

    public List<BubbleSeries> getBubbleData() {
        return bubbleData;
    }

    public void setBubbleData(List<BubbleSeries> bubbleData) {
        this.bubbleData = bubbleData;
    }
}
