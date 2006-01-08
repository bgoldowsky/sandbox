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
package wicket.contrib.examples.velocity;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.app.Velocity;

import wicket.WicketRuntimeException;
import wicket.protocol.http.WebApplication;
import wicket.util.time.Duration;

/**
 * Application class for velocity template example.
 *
 * @author Eelco Hillenius
 */
public class VelocityTemplateApplication extends WebApplication
{
	/** simple persons db. */
	private static List persons = new ArrayList();
	static
	{
		persons.add(new Person("Joe", "Down"));
		persons.add(new Person("Fritz", "Frizel"));
		persons.add(new Person("Flip", "Vlieger"));
		persons.add(new Person("George", "Forrest"));
		persons.add(new Person("Sue", "Hazel"));
		persons.add(new Person("Bush", "Gump"));
	}

	/**
	 * Gets the dummy persons database.
	 * @return the dummy persons database
	 */
	public static List getPersons()
	{
		return persons;
	}

    /**
     * Constructor.
     */
    public VelocityTemplateApplication()
    {
        getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);

		// initialize velocity
		try
		{
			Velocity.init();
		}
		catch (Exception e)
		{
			throw new WicketRuntimeException(e);
		}
    }
    
    /**
     * @return class
     */
    public Class getHomePage()
    {
    	return TemplatePage.class;
    }
}
