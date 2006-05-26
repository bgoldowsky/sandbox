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
package wicket.contrib.phonebook.web.page;

import wicket.Page;
import wicket.contrib.phonebook.Contact;
import wicket.markup.html.form.Button;
import wicket.markup.html.form.Form;
import wicket.markup.html.form.RequiredTextField;
import wicket.markup.html.form.TextField;
import wicket.markup.html.form.validation.EmailAddressPatternValidator;
import wicket.markup.html.form.validation.StringValidator;
import wicket.markup.html.panel.FeedbackPanel;
import wicket.model.CompoundPropertyModel;
import wicket.util.collections.MicroMap;
import wicket.util.string.interpolator.MapVariableInterpolator;

/**
 * Edit the Contact. Display details if an existing contact, then persist them
 * if saved.
 * 
 * @author igor
 * 
 */
public class EditContactPage extends BasePage {
	private Page backPage;

	/**
	 * Constructor. Create or edit the contact. Note that if you don't need the
	 * page to be bookmarkable, you can use whatever constructor you need, such
	 * as is done here.
	 * 
	 * @param backPage
	 *            The page that the user was on before coming here
	 * @param contactId
	 *            The id of the Contact to edit, or 0 for a new contact.
	 */
	public EditContactPage(Page backPage, final long contactId) {
		this.backPage = backPage;
		Contact contact = (contactId == 0) ? new Contact() : getDao().load(
				contactId);

		 new FeedbackPanel(this,"feedback");

		Form form = new Form(this,"contactForm", new CompoundPropertyModel(contact));

		new RequiredTextField(form,"firstname").add(StringValidator
				.maximumLength(32));

		new RequiredTextField(form,"lastname").add(StringValidator
				.maximumLength(32));

		new RequiredTextField(form,"phone").add(StringValidator
				.maximumLength(16));

		new TextField(form,"email").add(StringValidator.maximumLength(128))
				.add(EmailAddressPatternValidator.getInstance());

		new Button(form,"cancel") {
			protected void onSubmit() {
				String msg=getLocalizer().getString("status.cancel", this);
				getSession().info(msg);
				setResponsePage(EditContactPage.this.backPage);
			}
		}.setDefaultFormProcessing(false);

		new Button(form,"form,save") {
			protected void onSubmit() {
				Contact contact = (Contact) getForm().getModelObject();
				getDao().save(contact);

				String msg=MapVariableInterpolator.interpolate(getLocalizer().getString("status.save", this),
						new MicroMap<String,String>("name", contact.getFullName()));

				getSession().info(msg);
				
				setResponsePage(EditContactPage.this.backPage);
			}
		};
	}
}
