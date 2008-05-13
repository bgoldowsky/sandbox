package wicket.contrib.examples.gmap.many;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.RepeatingView;

import wicket.contrib.examples.GMapExampleApplication;
import wicket.contrib.examples.WicketExamplePage;
import wicket.contrib.gmap.GMapHeaderContributor;

/**
 * SimplePage for the wicket-contrib-gmap2 project
 */
public class ManyPage extends WicketExamplePage<Void>
{

	private static final long serialVersionUID = 1L;

	private final WebMarkupContainer<Object> container;

	private final RepeatingView<Object> repeating;

	public ManyPage()
	{
		AjaxFallbackLink<Object> create = new AjaxFallbackLink<Object>("create")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				ManyPage.this.addPanel();

				if (target != null)
				{
					target.addComponent(container);
				}
			}
		};
		add(create);

		container = new WebMarkupContainer<Object>("container");
		container.setOutputMarkupId(true);
		// optional: do this if no GMap2 is added initially
		container
				.add(new GMapHeaderContributor(GMapExampleApplication.get().getGoogleMapsAPIkey()));
		add(container);

		repeating = new RepeatingView<Object>("repeating");
		container.add(repeating);

		// addPanel();
	}

	protected void addPanel()
	{
		ManyPanel newPanel = new ManyPanel(repeating.newChildId(), GMapExampleApplication.get()
				.getGoogleMapsAPIkey())
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void closing(AjaxRequestTarget target)
			{
				repeating.remove(this);

				if (target != null)
				{
					target.addComponent(container);
				}
			}
		};
		repeating.add(newPanel);
	}
}