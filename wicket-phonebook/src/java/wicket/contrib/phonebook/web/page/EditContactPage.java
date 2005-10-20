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
import wicket.markup.html.form.validation.LengthValidator;
import wicket.markup.html.panel.FeedbackPanel;
import wicket.model.CompoundPropertyModel;

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
		Contact contact = (contactId == 0) ? new Contact() : getContactDao()
				.load(contactId);

		add(new FeedbackPanel("feedback"));

		Form form = new Form("contactForm", new CompoundPropertyModel(contact));
		add(form);

		form.add(new RequiredTextField("firstname")
				.add(LengthValidator.max(32)));

		form
				.add(new RequiredTextField("lastname").add(LengthValidator
						.max(32)));

		form.add(new RequiredTextField("phone").add(LengthValidator.max(16)));

		form.add(new TextField("email").add(LengthValidator.max(128)).add(
				EmailAddressPatternValidator.getInstance()));

		form.add(new Button("cancel") {
			protected void onSubmit() {
				setResponsePage(EditContactPage.this.backPage);
			}
		}.setDefaultFormProcessing(false));

		form.add(new Button("save") {
			protected void onSubmit() {
				Contact contact = (Contact) getForm().getModelObject();
				getContactDao().save(contact);
				setResponsePage(EditContactPage.this.backPage);
			}
		});
	}
}
