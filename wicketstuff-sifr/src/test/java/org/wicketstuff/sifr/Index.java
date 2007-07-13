/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wicketstuff.sifr;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class Index extends WebPage
{

	private static final long serialVersionUID = 1L;

	public Index()
	{
		Label header = new Label("header", "Hello world!");
		header.add(new FlashTextBehavior(FlashTextBehavior.TRADEGOTHIC_FONT, "#334455"));
		add(header);

		Label paragraph = new Label("paragraph", "Lorem ipsum dolor sit amet!");
		paragraph.add(new FlashTextBehavior(FlashTextBehavior.VANDENKEERE_FONT, "#445566"));
		add(paragraph);
	}
}
