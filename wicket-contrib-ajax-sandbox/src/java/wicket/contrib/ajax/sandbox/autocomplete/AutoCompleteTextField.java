/*
 * $Id$
 * $Revision$
 * $Date$
 * 
 * ==============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.contrib.ajax.sandbox.autocomplete;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import wicket.AjaxHandler;
import wicket.Application;
import wicket.AttributeModifier;
import wicket.IInitializer;
import wicket.contrib.ajax.sandbox.autocomplete.misc.IdPathAttributeModifier;
import wicket.contrib.ajax.sandbox.autocomplete.misc.PrependAttributeModifier;
import wicket.markup.ComponentTag;
import wicket.markup.html.HtmlHeaderContainer;
import wicket.markup.html.PackageResourceReference;
import wicket.markup.html.ajax.scriptaculous.ScriptaculousAjaxHandler;
import wicket.markup.html.form.FormComponent;
import wicket.markup.html.form.TextField;
import wicket.model.Model;
import wicket.util.resource.IResourceStream;
import wicket.util.resource.StringBufferResourceStream;

public abstract class AutoCompleteTextField extends TextField {
	
	public static class Initializer implements IInitializer {
		public void init(Application application) {

			// TODO do I have to use the PackageResource.bind static method, or can I bind the static instances themselves ?
			//PackageResource.bind(application, AutoCompleteTextFieldBorder.class, "indicator.gif");
			
			DEFAULT_INDICATOR.bind(application);
			PROTOTYPE.bind(application);
			SCRIPTACULOUS_UTIL.bind(application);
			SCRIPTACULOUS_CONTROLS.bind(application);
			SCRIPTACULOUS_EFFECTS.bind(application);
		}
	}
	
	private static final PackageResourceReference DEFAULT_INDICATOR = new PackageResourceReference(AutoCompleteTextField.class, "indicator.gif");
	
	private static final PackageResourceReference PROTOTYPE = new PackageResourceReference(AutoCompleteTextField.class, "prototype-1.4.0_pre2.js");
	private static final PackageResourceReference SCRIPTACULOUS_UTIL = new PackageResourceReference(AutoCompleteTextField.class, "scriptaculous/util.js");
	private static final PackageResourceReference SCRIPTACULOUS_CONTROLS = new PackageResourceReference(AutoCompleteTextField.class, "scriptaculous/controls.js");
	private static final PackageResourceReference SCRIPTACULOUS_EFFECTS = new PackageResourceReference(AutoCompleteTextField.class, "scriptaculous/effects.js");
	
	private final AutoCompleteEventRequestHandler handler;
	protected abstract String[] getResults(String input);
	 
	public AutoCompleteTextField(String id) {
		super(id);

		// turn the border off for this input, since the surrounding table will draw the border
		add(new PrependAttributeModifier("style", true, new Model("border:0px;")));
		
		// prevent the normal html autocomplete popup from appearing
		add(new AttributeModifier("autocomplete", true, new Model("false")));
		
		// TODO : what happens if the user specifies their own ID attribute for CSS / JavaScript access - may need to use a custom wicket:path attribute, and provide some JS to locate the control
		IdPathAttributeModifier.bind(this);
		
        handler = new AutoCompleteEventRequestHandler();
        add(handler);

	}
	
	private void write(String s) {
		getResponse().write(s);
	}
	
	protected void onRender() {

		write("<table class='wicketAutoComplete' style='padding: 1px; border: 1px solid #7F9DB9;' cellpadding='0' cellspacing='0'>\n");
		write("	<tr>\n");
		write("		<td>\n");
		
		super.onRender();

		write("		</td>");
		write("		<td width='14' align='right'>\n");
		write("			<!-- @TODO resize the indicator so that its actually 12 pixels !! -->\n");
//		write("			<img style='display:none;' wicket:id='indicator' height='12' src='indicator.gif'></img>\n");
		write("		</td>\n");
		write("	</tr>\n");
		write("</table>\n");

		
//		// TODO : make the indicator configurable
//		Image image = new Image("indicator", DEFAULT_INDICATOR);
//		IdPathAttributeModifier.bind(image);
//		add(image);
//		this.indicator = image;
//
		Properties options = new Properties();
//		if (indicator != null) {
//			// TODO escape quoted value
//			options.put("indicator", "'" + indicator.getPath() + "'");
//		}
//		if (paramName != null) {
//			// TODO escape quoted value
//			options.put("paramName", "'" + paramName + "'");
//		}
		
		String updateId = getPath() + "_update";

		// the actual autocomplete dropdown div container
        write("<div class='auto_complete' id='" + updateId + "'></div>\n");
		// call the Ajax.Autocomplete javascript control 
        write("<script type='text/javascript'>");
        write("new Ajax.Autocompleter('" + getPath() + "', '" + updateId + "', '" + handler.getCallbackUrl() + "', " + jsHash(options) + ");");
        write("</script>\n");
    }

	/**
	 * TODO candidate for a JavaScript util class - requires a JavaScriptEncode function
	 */
	private String jsHash(Properties options) {
		StringBuffer hash = new StringBuffer(10);
		hash.append("{");
		for (Iterator iter = options.entrySet().iterator(); iter.hasNext(); ) {
			Map.Entry entry = (Map.Entry) iter.next();
			
			// TODO jsEncode key
			hash.append(entry.getKey());

			hash.append(":");

			hash.append(entry.getValue());
			
			if (iter.hasNext()) {
				hash.append(",");
			}
			
		}
		hash.append("}");
		return hash.toString();
	}
	
	private class AutoCompleteEventRequestHandler extends ScriptaculousAjaxHandler {

		FormComponent formComponent;
	
	     public void onBind() {
            this.formComponent = (FormComponent) getComponent();
        }
	
        public void onComponentTag(ComponentTag tag) {
        }

        protected IResourceStream getResponse() {
            String value = formComponent.getInput();
        	
            StringBufferResourceStream s = new StringBufferResourceStream();
            s.append("<ul class='auto_complete'>\n");
            String[] results = getResults(value);
            for (int x = 0; x < results.length; x++) {
                String result = results[x];
                s.append("<li class='auto_complete'>" + result + "</li>\n");
            }
            s.append("</ul>\n");

            return s;
        }
	}

}
