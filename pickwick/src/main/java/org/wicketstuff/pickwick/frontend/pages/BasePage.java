package org.wicketstuff.pickwick.frontend.pages;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.resources.StyleSheetReference;
import org.apache.wicket.model.Model;
import org.wicketstuff.pickwick.PickwickApplication;
import org.wicketstuff.pickwick.auth.PickwickLoginPage;
import org.wicketstuff.pickwick.auth.PickwickLogoutPage;
import org.wicketstuff.pickwick.auth.PickwickSession;
import org.wicketstuff.pickwick.backend.pages.BackendLandingPage;

public class BasePage extends WebPage {
	
	public BasePage() {
		super();
		add(new StyleSheetReference("pickwickCss", BasePage.class, "css/pickwick.css"));
		addHeader();
	}
	
	public void addHeader(){

		BookmarkablePageLink backend = new BookmarkablePageLink("back", BackendLandingPage.class);
		add(backend);
		Image bckImg = new Image("bckImage", new ResourceReference(BasePage.class, "images/backend.png"));
		backend.add(bckImg);
		String name = PickwickSession.get().getUserName();
		if(PickwickSession.get().getUser().isAdmin()){
			backend.setVisible(true);
		}else{
			backend.setVisible(false);
		}
		
		Label userName = new Label("userName", new Model(name));
		PageLink auth = new PageLink("auth", PickwickLoginPage.class);
		PageLink logout = new PageLink("logout", PickwickLogoutPage.class);
		PageLink home = new PageLink("home", getApplication().getHomePage());
		add(home);
		home.add(new Image("homeImage", new ResourceReference(BasePage.class, "images/home.png")));
		add(userName);
		add(auth);
		add(logout);
		
		Image userImg = new Image("userImage", new ResourceReference(BasePage.class, "images/users.png"));
		add(userImg);
		Image logInImg = new Image("logInImage", new ResourceReference(BasePage.class, "images/log-in.png"));
		auth.add(logInImg);
		Image logOutImg = new Image("logOutImage", new ResourceReference(BasePage.class, "images/log-out.png"));
		logout.add(logOutImg);
		
		if (PickwickApplication.get().getPickwickSession().getDefaultUser().getName().equals(name)){
			//anonymous
			userName.setVisible(false);
			userImg.setVisible(false);
			logout.setVisible(false);
		}else{
			auth.setVisible(false);
		}
		
		
	}

}
