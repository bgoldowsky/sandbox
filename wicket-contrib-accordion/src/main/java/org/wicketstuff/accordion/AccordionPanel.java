package org.wicketstuff.accordion;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;

/**
 * 
 * @author Nino Martinez Wael (nino.martinez.wael @ gmail.com)
 * Component that wraps this : http://www.hedgerwow.com/360/mwd/accordion/demo.php 
 */
public class AccordionPanel extends Panel implements IHeaderContributor {

	protected List<AccordionPanelItem> accordionMenu = new ArrayList<AccordionPanelItem>();

	private ResourceReference JAVASCRIPT = new CompressedResourceReference(
			AccordionPanel.class, "accordion-menu-v2.js");
	private ResourceReference STYLE = new CompressedResourceReference(
			AccordionPanel.class, "accordion-menu-v2.css");

	public AccordionPanel(String id) {
		super(id);
		add(new ListView("accordionMenu", accordionMenu) {
			@Override
			protected void populateItem(ListItem item) {
				AccordionPanelItem accordionPanelItem = (AccordionPanelItem) item
						.getModelObject();
				item.add(accordionPanelItem);

			}
		});

	}

	public void addMenu(AccordionPanelItem accordionPanelItem) {
		accordionMenu.add(accordionPanelItem);
	}

	public void renderHead(IHeaderResponse response) {

		response
				.renderJavascriptReference("http://us.js2.yimg.com/us.js.yimg.com/lib/common/utils/2/yahoo_2.0.0-b2.js");
		response
				.renderJavascriptReference("http://us.js2.yimg.com/us.js.yimg.com/lib/common/utils/2/event_2.0.0-b2.js");
		response
				.renderJavascriptReference("http://us.js2.yimg.com/us.js.yimg.com/lib/common/utils/2/dom_2.0.2-b3.js");
		response
				.renderJavascriptReference("http://us.js2.yimg.com/us.js.yimg.com/lib/common/utils/2/animation_2.0.0-b3.js");
		response.renderJavascriptReference(JAVASCRIPT);
		response.renderCSSReference(STYLE);
	};

}
