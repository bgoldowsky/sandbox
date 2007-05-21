/*
 * $Id: JaasStrategyFactory.java,v 1.1 2006/06/28 10:00:16 Marrink Exp $ $Revision: 1.1 $ $Date: 2006/06/28 10:00:16 $
 * ============================================================================== Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.wicket.security.strategies;



/**
 * Factory to provide a strategy for each wicket session.
 * 
 * @author marrink
 */
public interface StrategyFactory
{
	/**
	 * Create a new Strategy.
	 * @return
	 */
	public WaspAuthorizationStrategy newStrategy();
	/**
	 * Called at the end of the applications lifecycle to clean up resources
	 *
	 */
	public void destroy();

}
