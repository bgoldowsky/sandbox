/* Licensed under the Apache License, Version 2.0 (the "License"); you may not
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
package org.wicketstuff.persistence;
import java.io.Serializable;
/**
 * Describes what to do if an object can't be retrieved from the database
 * when PersistenceFacade.get() is called.
 *
 * @author Tim Boudreau
 */
public enum LoadFailurePolicy implements Serializable {
    /**
     * Failure should result in null being returned, no exception thrown.
     */
    RETURN_NULL_ON_FAILURE, 
    /**
     * Failure should trigger a LookupFailedException
     */
    THROW_EXCEPTION_ON_FAILURE, 
    /**
     * If the query fails, create a new instance of the requested type
     */
    CREATE_NEW_OBJECT_ON_FAILURE
}
