/*
 * $Id$ $Revision:
 * 1.6 $ $Date$
 * 
 * ==================================================================== Licensed
 * under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.addons;

import java.util.Locale;

import wicket.Application;
import wicket.addons.dao.User;
import wicket.addons.utils.UserCount;
import wicket.protocol.http.WebSession;

/**
 * @author Jonathan Locke
 */
public final class AddonSession extends WebSession
{
    // > 0, than logged in
    private int userId;
    
    private String nickname;

    // If true, the sidebar "top rated" will be shown, else "top clicks"
    private boolean topRated = true;
    
    
	/**
	 * Constructor
	 * 
	 * @param application
	 *            The application
	 */
	protected AddonSession(Application application)
	{
		super(application);
	}

	/**
	 * Checks the given username and password, returning a User object if if the
	 * username and password identify a valid user.
	 * 
	 * @param username
	 *            The username
	 * @param password
	 *            The password
	 * @return True if the user was authenticated
	 */
	public final boolean authenticate(final String username, final String password)
	{
        if (userId == 0)
        {
            final User user = ((AddonApplication)getApplication()).authenticate(username, password);
            
            if (user != null)
            {
    		    UserCount.addUser(user.getId());

                if (user.getLocale() != null)
                {
                    setLocale(new Locale(user.getLocale()));
                }
            }

            this.userId = user.getId();
            this.nickname = user.getNickname();
        }

        return isSignedIn();
	}

	/**
	 * @return True if user is signed in
	 */
	public boolean isSignedIn()
	{
		return userId > 0;
	}

	/**
	 * @return User
	 */
	public int getUserId()
	{
		return this.userId;
	}
	
	public String getUserLogonName()
	{
	    return this.nickname;
	}
	
	public boolean isTopRated()
	{
	    return this.topRated;
	}
	
	public void toggleTopRated()
	{
	    this.topRated = !this.topRated;
	}
}
