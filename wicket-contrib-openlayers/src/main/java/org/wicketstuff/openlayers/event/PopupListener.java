/*
 * $Revision: 5004 $
 * $Date: 2006-03-17 20:47:08 -0800 (Fri, 17 Mar 2006) $
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
package org.wicketstuff.openlayers.event;

import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wicketstuff.openlayers.OpenLayersMap;
import org.wicketstuff.openlayers.api.LonLat;
import org.wicketstuff.openlayers.api.Marker;
import org.wicketstuff.openlayers.api.Overlay;

/**
 * See the event section of <a
 * href="http://www.google.com/apis/maps/documentation/reference.html#GMap2">GMap2</a>.
 * TODO should we put 'click' and 'dblclkick' together in this listener?
 */
public abstract class PopupListener extends AbstractDefaultAjaxBehavior {

	@Override
	protected void onBind() {
		if (!(getComponent() instanceof OpenLayersMap)) {
			throw new IllegalArgumentException("must be bound to OpenlayersMap");
		}
	}

	@Override
	protected final void respond(AjaxRequestTarget target) {

		onEvent(target);
	}

	protected final OpenLayersMap getOpenLayerMap() {
		return (OpenLayersMap) getComponent();
	}

	protected void onEvent(AjaxRequestTarget target) {
		Request request = RequestCycle.get().getRequest();

		Overlay overlay = null;
		LonLat latLng = null;

		String markerParameter = request.getParameter("marker");
		if (markerParameter != null) {
			for (Overlay ovl : getOpenLayerMap().getOverlays()) {
				if (ovl.getId().equals(markerParameter)) {
					overlay = ovl;
					break;
				}
			}
		}

		onClick(target, overlay);
	}
public String getCallBackForMarker(Marker marker)
{
	return getCallbackUrl() + "&marker="+ marker.getId();	

}

	/**
	 * Override this method to provide handling of a click on the map. See the
	 * event section of <a
	 * href="http://www.google.com/apis/maps/documentation/reference.html#GMap2">GMap2</a>.
	 * 
	 * @param latLng
	 *            The clicked GLatLng. Might be null if a Marker was clicked.
	 * @param overlay
	 *            The clicked overlay. Might be null.
	 * @param target
	 *            The target that initiated the click.
	 */
	protected abstract void onClick(AjaxRequestTarget target, Overlay overlay);
}