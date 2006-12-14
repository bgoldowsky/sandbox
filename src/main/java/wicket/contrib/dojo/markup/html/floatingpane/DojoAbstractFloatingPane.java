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
package wicket.contrib.dojo.markup.html.floatingpane;

import wicket.MarkupContainer;
import wicket.ResourceReference;
import wicket.ajax.AjaxRequestTarget;
import wicket.contrib.dojo.widgets.StylingWebMarkupContainer;
import wicket.markup.ComponentTag;

/**
 * Dojo Abstract floating pane
 * @author doume
 *
 */
public abstract class DojoAbstractFloatingPane extends StylingWebMarkupContainer
{
	
	private String title;
	private boolean rezisable;
	private boolean displayMinimizeAction;
	private boolean displayMaximizeAction;
	private boolean displayCloseAction;
	private boolean hasShadow;

	/**
	 * Constructor
	 * @param parent prent where the widget will be added
	 * @param id widget id
	 */
	public DojoAbstractFloatingPane(MarkupContainer parent, String id)
	{
		super(parent, id);
		title = "";
		rezisable = true;
		displayCloseAction = true;
		displayMaximizeAction = true;
		displayCloseAction = true;
		hasShadow = false;
	}
	
	
	@Override
	protected void onComponentTag(ComponentTag tag)
	{
		super.onComponentTag(tag);
		tag.put("title", title);
		tag.put("templatePath", urlFor(new ResourceReference(DojoModalFloatingPane.class, "FloatingPane.htm")));
		
		if (rezisable){
			tag.put("rezisable", "true");
		}
		else{
			tag.put("rezisable", "false");
		}
		
		if (displayMinimizeAction){
			tag.put("displayMinimizeAction", "true");
		}
		else{
			tag.put("displayMinimizeAction", "false");
		}
		
		if (displayMaximizeAction){
			tag.put("displayMaximizeAction", "true");
		}
		else{
			tag.put("displayMaximizeAction", "false");
		}
		
		if (displayCloseAction){
			tag.put("displayCloseAction", "true");
		}
		else{
			tag.put("displayCloseAction", "false");
		}
		
		if (hasShadow){
			tag.put("hasShadow", "true");
		}
		else{
			tag.put("hasShadow", "false");
		}
	}
	
	
	/**
	 * Show the modal pane
	 * @param target
	 */
	public void show(AjaxRequestTarget target){
		target.appendJavascript("dojo.widget.byId('" + getMarkupId() + "').show()");
	}
	
	/**
	 * Hide the modal pane
	 * @param target
	 */
	public void close(AjaxRequestTarget target){
		target.appendJavascript("dojo.widget.byId('" + getMarkupId() + "').hide()");
	}


	public boolean isDisplayCloseAction()
	{
		return displayCloseAction;
	}


	public void setDisplayCloseAction(boolean displayCloseAction)
	{
		this.displayCloseAction = displayCloseAction;
	}


	public boolean isDisplayMaximizeAction()
	{
		return displayMaximizeAction;
	}


	public void setDisplayMaximizeAction(boolean displayMaximizeAction)
	{
		this.displayMaximizeAction = displayMaximizeAction;
	}


	public boolean isDisplayMinimizeAction()
	{
		return displayMinimizeAction;
	}


	public void setDisplayMinimizeAction(boolean displayMinimizeAction)
	{
		this.displayMinimizeAction = displayMinimizeAction;
	}


	public boolean isHasShadow()
	{
		return hasShadow;
	}


	public void setHasShadow(boolean hasShadow)
	{
		this.hasShadow = hasShadow;
	}


	public boolean isRezisable()
	{
		return rezisable;
	}


	public void setRezisable(boolean rezisable)
	{
		this.rezisable = rezisable;
	}


	public String getTitle()
	{
		return title;
	}


	public void setTitle(String title)
	{
		this.title = title;
	}

}
