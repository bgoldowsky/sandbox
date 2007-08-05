package org.wicketstuff.pickwick.backend.panel;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.pickwick.backend.Settings;
import org.wicketstuff.pickwick.bean.User;

import com.google.inject.Inject;

/**
 * a panel to add or edit a user.
 * @author Vincent Demay
 *
 */
public abstract class UserPanel extends Panel {

	@Inject
	Settings settings;
	
	private Form form;
	private TextField name;
	
	public UserPanel(String id, CompoundPropertyModel userModel) {
		super(id, new Model());
		setOutputMarkupId(true);
		
		form = new Form("userForm", userModel);
		
		if (userModel == null){
			form.setModel(new CompoundPropertyModel(new User()));
		}
		add(form);
		
		name = new TextField("name");
		name.setOutputMarkupId(true);
		form.add(name);

		TextField role = new TextField("role");
		form.add(role);
		
		CheckBox admin = new CheckBox("admin");
		form.add(admin);
		
		form.add(new AjaxButton("save",form){

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				User user = (User)form.getModelObject();
				settings.getUserManagement().addUser(user);
				onSave(target);
			}

		});
	}
	
	public abstract void onSave(AjaxRequestTarget target);
	
	@Override
	public Component setModel(IModel model) {
		if (!(model instanceof CompoundPropertyModel)){
			throw new WicketRuntimeException("model should be an instnceof CompoundPropertyModel");
		}
		super.setModel(new Model());
		form.setModel(model);
		return this;
	}
	
	public void disableName(AjaxRequestTarget target){
		target.appendJavascript("document.getElementById('" + name.getMarkupId() + "').disabled='disabled'");
	}
	
	public void enableName(AjaxRequestTarget target){
		target.appendJavascript("document.getElementById('" + name.getMarkupId() + "').disabled='false'");
	}
	

}
