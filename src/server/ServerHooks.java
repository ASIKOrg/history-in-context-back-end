/*
 This file is part of theunibot.

 theunibot is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 theunibot is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with theunibot.  If not, see <http://www.gnu.org/licenses/>.

 Copyright (c) 2014 Unidesk Corporation
 */
package server;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class ServerHooks {

	//Objects
	private static ServerHooks s = null;

	//used in get/set vars calls
	private static Map<String, String> vars = Collections.synchronizedMap(new LinkedHashMap<String, String>());


	/**
	 * Singleton instance of ServerHooks 
	 * @return
	 */
	public static ServerHooks getInstance() {
		if (s == null)
			s = new ServerHooks();
		return s;
	}	
}
