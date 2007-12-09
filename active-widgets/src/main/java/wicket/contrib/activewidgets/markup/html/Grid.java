package wicket.contrib.activewidgets.markup.html;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.template.PackagedTextTemplate;

import wicket.contrib.activewidgets.AWHeaderContributor;

public class Grid extends Panel  implements IHeaderContributor {

	interface IToken {
		String getToken();
		String getTokenName();
	}
	
	abstract class Token implements IToken {

		/*** serialization 	 */
		private static final long serialVersionUID = 1L;
		protected String value;
		
	}
	
	abstract class JavascriptToken extends Token {

		/*** serialization 	 */
		private static final long serialVersionUID = 1L;
		
	}
	
	abstract class StyleTokenPx extends Token {

		/*** serialization 	 */
		private static final long serialVersionUID = 1L;

		public String getToken() {
			return 	"\n" + "#" + activeWidgetsId + " {" + getTokenName() + ":" + value + "px}";
		}
		void setPxValue(int pxValue) {
			this.value = new Integer(pxValue).toString();
		}
		
	}
	
	class Width extends StyleTokenPx{

		public Width(int width) {
			this.setPxValue(width);
		}

		public void setWidth(int width) {
			this.setPxValue(width);
		}

		public String getTokenName() {
			return "width";
		}

	}


	public class Height extends StyleTokenPx {

		public Height(int height) {
			this.setPxValue(height);
		}


		public void setHeight(int height) {
			this.setPxValue(height);
		}


		public String getTokenName() {
			return "height";
		}

	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The DOM id of the element that hosts the javascript component.
	 */
	private String domId;

	/**
	 * The JavaScript variable name of the grid component.
	 */
	private String varId;

	

	/**
	 * active widgets grid ID
	 */
	private String activeWidgetsId;
	
	@SuppressWarnings("unused")
	private GirdElement gridElement;
	
	private Width width;

	private Height height;

	/**
	 * The container/ receiver of the javascript component.
	 */
	final class GirdElement extends FormComponent {

		private static final long serialVersionUID = 1L;

		public GirdElement(String id, IModel model) {
			super(id, model);
		}

		public GirdElement(String id) {
			super(id);
			add(new AttributeModifier("id", true, new AbstractReadOnlyModel()
			{
				private static final long serialVersionUID = 1L;

				@Override
				public Object getObject()
				{
					return domId;
				}
			}));
		}

	}


	private void constructorInit(String id) {
		
		add(new AWHeaderContributor());
		add(gridElement = new GirdElement("gridContainer"));
		
		Label style = new Label("style", new AbstractReadOnlyModel()
		{
			private static final long serialVersionUID = 1L;

			/**
			 * @see wicket.model.IModel#getObject(wicket.Component)
			 */
			@Override
			public Object getObject()
			{
				StringBuffer result = new StringBuffer();
				result.append(styleInit());
				return result.toString();
			}
		});
		style.setEscapeModelStrings(false);
		gridElement.add(style);

		Label javascript = new Label("javascript", new AbstractReadOnlyModel()
		{
			private static final long serialVersionUID = 1L;

			/**
			 * @see wicket.model.IModel#getObject(wicket.Component)
			 */
			@Override
			public Object getObject()
			{
				StringBuffer result = new StringBuffer();
				result.append(javascriptInit());
				return result.toString();
			}
		});
		javascript.setEscapeModelStrings(false);
		gridElement.add(javascript);
		
	}

	
	public Grid(String id, IModel model) {
		super(id, model);
		constructorInit(id);
	}

	public Grid(String id) {
		super(id);
		constructorInit(id);
	}

	/**
	 * 
	 * @return the initilization javascript
	 */
	private String javascriptInit()
	{
		Map<String, Object> variables = new HashMap<String, Object>(4);
		variables.put("javaScriptId", varId);
		variables.put("elementId", domId);
		variables.put("activeWidgetsId", activeWidgetsId);

		PackagedTextTemplate template = new PackagedTextTemplate(Grid.class, "init.js");
		template.interpolate(variables);

		return template.getString();
	}

	/**
	 * 
	 * @return the initilization javascript
	 */
	private String styleInit()
	{
		StringBuffer result = new StringBuffer();
		if (width != null) {
			result.append(width.getToken());
		}
		if (height != null) {
			result.append(height.getToken());
		}
		return result.toString();
	}

	/**
	 * @see wicket.Component#onAttach()
	 */
	@Override
	protected void onBeforeRender()
	{
		super.onBeforeRender();

		// initialize lazily
		if (domId == null) {
			// assign the markup id
			String id = getMarkupId();
			domId = "AW" + id;
			varId = "var" + domId;
			activeWidgetsId = "_" + domId;
		}
	}


	public void renderHead(IHeaderResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	
	public Grid width(int width) {
		if (this.width == null) {
			this.width = new Width(width);
		} else {
			this.width.setWidth(width);
		}
		
		return this;
	}


	public Grid height(int height) {
		if (this.height == null) {
			this.height = new Height(height);
		} else {
			this.height.setHeight(height);
		}
		return this;
	}



}
