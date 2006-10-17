/*
 * $Id: WicketExampleHeader.java 627 2006-03-20 07:12:13 +0000 (Mon, 20 Mar
 * 2006) eelco12 $ $Revision$ $Date: 2006-03-20 07:12:13 +0000 (Mon, 20
 * Mar 2006) $
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
package wicket.contrib.examples;

import wicket.MarkupContainer;
import wicket.markup.html.basic.Label;
import wicket.markup.html.panel.Panel;

/**
 * Navigation panel for the examples project.
 *
 * @author Eelco Hillenius
 */
public final class WicketExampleHeader extends Panel {
    /**
     * Construct.
     *
     * @param parent The parent
     * @param id name of the component
     * @param exampleTitle title of the example
     */
    public WicketExampleHeader(MarkupContainer parent, String id, String exampleTitle) {
        super(parent, id);
        new Label(this, "exampleTitle", exampleTitle);
    }
}
