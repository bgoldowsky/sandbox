/*
 * $Id$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wicket.addons;

import java.util.Map;

import wicket.RequestCycle;
import wicket.addons.dao.Addon;
import wicket.addons.dao.Comment;
import wicket.addons.dao.User;
import wicket.markup.html.basic.Label;
import wicket.markup.html.form.Form;
import wicket.markup.html.panel.FeedbackPanel;

/**
 * @author Juergen Donnerstag
 */
public final class AddComment extends BaseHtmlPage /* AuthenticateHtmlPage */
{
    /**
     * Constructor
     * @param parameters
      */
    public AddComment(final Addon addon)
    {
        super(null, "Wicket-Addons: Add comment");
        
        add(new Label("addonName", addon.getName()));
        
        // Create and add feedback panel to page
        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        //add(feedback);
        
        add(new AddCommentForm("form", feedback, addon));
    }

    public final class AddCommentForm extends Form
    {
        private final Addon addon;
        
        /**
         * Constructor
         * @param componentName Name of form
         * @param book Book model
         * @param feedback Feedback component that shows errors
         */
        public AddCommentForm(final String componentName, final FeedbackPanel feedback, final Addon addon)
        {
            super(componentName, feedback);
            
            this.addon = addon;
        }
        
        /**
         * Show the resulting valid edit
         * @param cycle The request cycle
         */
        public final void onSubmit()
        {
            final Comment comment = new Comment();
            
            final RequestCycle cycle = getRequestCycle();
            final Map params = cycle.getRequest().getParameterMap();
            
            comment.setComment((String)params.get("comment"));
            comment.setAddon(addon);
            comment.setUser((User)getUserDao().getUsers().get(0));
            
            getAddonDao().saveOrUpdate(comment);
            cycle.setResponsePage(newPage(getApplication().getPages().getHomePage()));
        }
    }
}
