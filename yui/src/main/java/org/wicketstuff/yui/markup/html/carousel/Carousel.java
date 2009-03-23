package org.wicketstuff.yui.markup.html.carousel;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.yui.YuiHeaderContributor;
import org.wicketstuff.yui.behavior.Attributes;

public class Carousel<T> extends Panel implements IHeaderContributor
{
	private static final long serialVersionUID = 1L;

	private static final String[] DEPENDENCIES = { "animation" };

	private static final boolean DEBUG = false;

	private static final String BUILD = "2.6.0";

	private static final String MODULE = "carousel";

	public static final ResourceReference SAM_SKIN = new ResourceReference(
			YuiHeaderContributor.class, "inc/" + BUILD + "/assets/skins/sam/" + MODULE + ".css");

	public static final ResourceReference NO_SKIN_CORE_CSS = new ResourceReference(
			YuiHeaderContributor.class, "inc/" + BUILD + "/" + MODULE + "/assets/" + MODULE
					+ "-core.css");

	private WebMarkupContainer carousel;

	public Carousel(String id, IModel<List<T>> list, ResourceReference css)
	{
		super(id);

		add(YuiHeaderContributor.forModule(MODULE, DEPENDENCIES, DEBUG, BUILD));
		if (css != null)
			add(CSSPackageResource.getHeaderContribution(css));

		WebMarkupContainer skinContainer;
		add(skinContainer = new WebMarkupContainer("skin_container"));

		skinContainer.add(carousel = new WebMarkupContainer("carousel_container"));
		carousel.setOutputMarkupId(true);

		carousel.add(new ListView<T>("list", list)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<T> item)
			{
				item.add(newPanel("item", item.getModelObject()).setRenderBodyOnly(true));
			}
		});

		skinContainer.add(new AttributeModifier("class", true, new Model<String>(getCssClass())));
	}

	/**
	 * default carousel with Sam Skin
	 * 
	 * @param id
	 * @param list
	 */
	public Carousel(String id, final List<T> list)
	{
		this(id, new LoadableDetachableModel<List<T>>()
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected List<T> load()
			{
				return list;
			}

		}, SAM_SKIN);

	}

	/**
	 * 
	 * @param id
	 * @param list
	 * @param css
	 */
	public Carousel(String id, final List<T> list, ResourceReference css)
	{
		this(id, new LoadableDetachableModel<List<T>>()
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected List<T> load()
			{
				return list;
			}

		}, css);
	}

	protected String getCssClass()
	{
		return "yui-skin-sam";
	}

	protected Component newPanel(String id, T object)
	{
		return new EmptyPanel(id);
	}

	/**
	 * YAHOO.util.Event.onContentReady("container", function (ev) { var carousel
	 * = new YAHOO.widget.Carousel("container"); carousel.show(); // rest of the
	 * code });
	 */
	public void renderHead(IHeaderResponse response)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("var ").append(getCarouselVar()).append(" = new YAHOO.widget.Carousel(")
				.append("\"").append(getCarouselId()).append("\",").append(getOpts()).append(");");

		buffer.append(getCarouselVar()).append(".render();");
		buffer.append(getCarouselVar()).append(".show()");
		response.renderOnDomReadyJavascript(buffer.toString());

	}

	/**
	 * Override this method to provde own customised options...use these
	 * Options... { animation {} , }
	 * 
	 * isCircular: true, numVisible: 1
	 * 
	 * @see <a href="http://developer.yahoo.com/yui/carousel/#apiref">YUI
	 *      developer Carousel Reference Table</a>
	 * @return
	 */
	protected String getOpts()
	{
		// or simply {}
		Attributes attributes = new Attributes();
		// animation - animation : {speed : 0.5}
		attributes.add(new Attributes("animation", new Attributes("speed", 0.5F).toString()));
		// circular
		attributes.add(new Attributes("isCircular", "true"));
		// num Visible
		attributes.add(new Attributes("numVisible", 1));
		return attributes.toString();
	}

	private String getCarouselVar()
	{
		return "var_" + getCarouselId();
	}

	private String getCarouselId()
	{
		return carousel.getMarkupId();
	}
}
