/* ==============================================================================
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

/*
 * @author Karri-Pekka Laakso (kplaakso)
 */


function setUp() {
}

function testConstruction() {
	new Wicket.DateLocale();
}

function testMonths() {
	var months = ["January","February","March","April","May","June","July","August","September","October","November","December"];
	var locale = new Wicket.DateLocale();
	for (var i=0; i<12; i++) {
		assertEquals("Month", months[i], locale.getMonth(i));
	}

	var finnishMonths = ["Tammikuu","Helmikuu","Maaliskuu","Huhtikuu","Toukokuu","Kes\u00E4kuu",
		"Hein\u00E4kuu","Elokuu","Syyskuu","Lokakuu","Marraskuu","Joulukuu"];
	var fiMonthString = finnishMonths.join(",");
	
	locale.setMonths(fiMonthString);
	for (i=0; i<12; i++) {
		assertEquals("Finnish months", finnishMonths[i], locale.getMonth(i));
	}
}

function testGetWeekdays() {
	var weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
	var mondayStartingWeek = ["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"];
	var shortWeekdays = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];
	var mondayStartingShort  = ["Mon","Tue","Wed","Thu","Fri","Sat","Sun"];
	
	var locale = new Wicket.DateLocale();

	var i = locale.getWeekdayIterator();
	checkWeekdays(i, weekdays);

	i = locale.getShortWeekdayIterator();
	checkWeekdays(i, shortWeekdays);

	locale.setFirstDayOfWeek(1);
	i = locale.getWeekdayIterator();
	checkWeekdays(i, mondayStartingWeek);
	
	i = locale.getShortWeekdayIterator();
	checkWeekdays(i, mondayStartingShort);
}

function checkWeekdays(i, weekdays) {
	var j = 0;
	while (i.hasNext()) {
		var weekday = i.next();
		assertEquals("Weekday iterating", weekdays[j], weekday);
		j++;
	} 	
}

function tearDown() {

}
