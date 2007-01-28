package wicket.contrib.dojo.examples;

import wicket.PageParameters;
import wicket.ajax.AjaxRequestTarget;
import wicket.ajax.markup.html.AjaxLink;
import wicket.contrib.dojo.markup.html.toaster.DojoToaster;
import wicket.markup.html.WebPage;
import wicket.model.Model;
import wicket.util.time.Duration;

public class ToasterSample extends WebPage {
	
	public ToasterSample(PageParameters parameters){
		final DojoToaster toaster1 = new DojoToaster("toaster1",new Model("Some messages here. Funy isn\'t it ;)"));
		AjaxLink link1 = new AjaxLink( "link1"){
			public void onClick(AjaxRequestTarget target) {
				toaster1.publishMessage(target);
				
			}
		};
		
		final DojoToaster toaster2 = new DojoToaster("toaster2",new Model("Some messages here. Funy isn\'t it ;)"));
		AjaxLink link2 = new AjaxLink("link2"){
			public void onClick(AjaxRequestTarget target) {
				toaster2.publishMessage(target,DojoToaster.ERROR);
				
			}
		};

		
		final DojoToaster toaster4 = new DojoToaster("toaster4",new Model("Some messages here. Funy isn\'t it ;)"));
		toaster4.setPosition(DojoToaster.BOTTOM_LEFT_UP);
		toaster4.setDuration(Duration.seconds(10));
		AjaxLink link4 = new AjaxLink("link4"){
			public void onClick(AjaxRequestTarget target) {
				toaster4.publishMessage(target,DojoToaster.WARNING);
				
			}
		};
		
		add(toaster1);
		add(link1);
		add(toaster2);
		add(link2);
		add(toaster4);
		add(link4);
	}
}
