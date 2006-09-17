package wicket.examples.cdapp;

import wicket.contrib.data.model.PersistentObjectModel;
import wicket.examples.cdapp.model.CD;
import wicket.model.AbstractDetachableModel;
import wicket.model.AbstractReadOnlyDetachableModel;

/**
 * Special model for the title header. It returns the CD title if there's a
 * loaded object (when the id != null) or it returns a special string in case
 * there is no loaded object (if id == null).
 */
public class TitleModel extends AbstractReadOnlyDetachableModel<String>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** decorated model; provides the current id. */
	private final PersistentObjectModel<CD, Long> cdModel;

	/**
	 * Construct.
	 * 
	 * @param cdModel
	 *            the model to decorate
	 */
	public TitleModel(PersistentObjectModel<CD, Long> cdModel)
	{
		this.cdModel = cdModel;
	}

	/**
	 * @see wicket.model.AbstractDetachableModel#onAttach()
	 */
	@Override
	protected void onAttach()
	{
		cdModel.attach();
	}

	/**
	 * @see AbstractDetachableModel#onDetach()
	 */
	@Override
	protected void onDetach()
	{
		cdModel.detach();
	}

	/**
	 * @see AbstractDetachableModel#onGetObject()
	 */
	@Override
	protected String onGetObject()
	{
		if (cdModel.getId() != null) // it is allready persistent
		{
			CD cd = cdModel.getObject();
			return cd.getTitle();
		}
		else
		// it is a new cd
		{
			return "<NEW CD>";
		}
	}
}