/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Wicket Map2
 *
 * @author Martin Funk
 */
// Wicket Namespace
var Wicket;
if (!Wicket) {
    Wicket = {}
}
else 
    if (typeof Wicket != "object") {
        throw new Error("Wicket already exists and is not an object");
    }

Wicket.geocoder = new WicketClientGeocoder();

function WicketClientGeocoder(){
    this.coder = new google.maps.ClientGeocoder();
    
    this.getLatLng = function(callBackUrl, addressId){
    
        var address = Wicket.$(addressId).value;
        
        this.coder.getLocations(address, function(response){
        
            var address;
            var coordinates;
            var status = response.Status.code;
            if (status == 200) {
                address = response.Placemark[0].address;
                coordinates = response.Placemark[0].Point.coordinates;
            }
            
            wicketAjaxGet(callBackUrl +
            '&status=' +
            status +
            '&address=' +
            address +
            '&point=(' +
            coordinates[1] +
            ',' +
            coordinates[0] +
            ')', function(){
            }, function(){
            });
        });
    }
}

Wicket.maps = {}

function WicketMap2(id){
    Wicket.maps[id] = this;
    
    this.map = new google.maps.Map2(document.getElementById(id));
    this.controls = {};
    this.overlays = {};
    
    this.onEvent = function(callBack, params){
        params['center'] = this.map.getCenter();
        params['bounds'] = this.map.getBounds();
        params['zoom'] = this.map.getZoom();
        params['infoWindow.hidden'] = this.map.getInfoWindow().isHidden();
        
        for (var key in params) {
            callBack = callBack + '&' + key + '=' + params[key];
        }
        
        wicketAjaxGet(callBack, function() {}, function() {});
    }
    
    this.init = function(callBack){
        this.onEvent(callBack, {});
    }

    this.addListener = function(event, callBack) {
        var self = this;
       
        GEvent.addListener(this.map, event, function() {
            var params = {};
            for (var p = 0; p < arguments.length; p++) {
                if (arguments[p] != null) {
                    params['argument' + p] = arguments[p];
                }
            }

            self.onEvent(callBack, params);
        });
    } 
    
    this.addOverlayListener = function(event, overlayID, callBack){
        var self = this;
        var overlay = this.overlays[overlayID];

        GEvent.addListener(overlay, event, function() {
            var params = {};
            for (var p = 0; p < arguments.length; p++) {
                if (arguments[p] != null) {
                    params['argument' + p] = arguments[p];
                }
            }

            params['overlay.latLng'] = overlay.getLatLng();

            self.onEvent(callBack, params);
        });
    }
    
    this.setDraggingEnabled = function(enabled){
        if (enabled) {
            this.map.enableDragging(true);
        }
        else {
            this.map.disableDragging(false);
        }
    }
    
    this.setDoubleClickZoomEnabled = function(enabled){
        if (enabled) {
            this.map.enableDoubleClickZoom(true);
        }
        else {
            this.map.disableDoubleClickZoom(false);
        }
    }
    
    this.setScrollWheelZoomEnabled = function(enabled){
        if (enabled) {
            this.map.enableScrollWheelZoom(true);
        }
        else {
            this.map.disableScrollWheelZoom(false);
        }
    }
    
    this.setMapType = function(mapType){
        this.map.setMapType(mapType);
    }
    
    this.setZoom = function(level){
        this.map.setZoom(level);
    }
    
    this.setCenter = function(center){
        this.map.setCenter(center);
    }
    
    this.panTo = function(center){
        this.map.panTo(center);
    }
    
    this.panDirection = function(dx, dy){
        this.map.panDirection(dx, dy);
    }
    
    this.zoomOut = function(){
        this.map.zoomOut();
    }
    
    this.zoomIn = function(){
        this.map.zoomIn();
    }
    
    this.addControl = function(controlId, control){
        this.controls[controlId] = control;
        
        this.map.addControl(control);
    }
    
    this.removeControl = function(controlId){
        if (this.controls[controlId] != null) {
            this.map.removeControl(this.controls[controlId]);
            
            this.controls[controlId] = null;
        }
    }
    
    this.addOverlay = function(overlayId, overlay){
        this.overlays[overlayId] = overlay;
        overlay.overlayId = overlayId;
        overlay.toString = function() {
            return overlayId;
        };
        
        this.map.addOverlay(overlay);
    }
    
    this.removeOverlay = function(overlayId){
        if (this.overlays[overlayId] != null) {
            this.map.removeOverlay(this.overlays[overlayId]);
            
            this.overlays[overlayId] = null;
        }
    }
    
    this.clearOverlays = function(){
        this.overlays = {};
        this.map.clearOverlays();
    }
    
    this.openInfoWindowTabs = function(latLng, tabs){
        this.map.openInfoWindowTabs(latLng, tabs);
    }
    
    this.openMarkerInfoWindowTabs = function(markerId, tabs){
        this.overlays[markerId].openInfoWindowTabs(tabs);
    }
    
    this.closeInfoWindow = function(){
        this.map.closeInfoWindow();
    }
}
