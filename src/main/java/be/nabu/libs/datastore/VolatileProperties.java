/*
* Copyright (C) 2014 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package be.nabu.libs.datastore;

import java.net.URI;
import java.util.Date;

import be.nabu.libs.datastore.api.DataProperties;

public class VolatileProperties implements DataProperties {

	private URI uri;
	private MemoryDatastore memoryDatastore;
	private Date lastModified = new Date();
	
	VolatileProperties(MemoryDatastore memoryDatastore, URI uri) {
		this.memoryDatastore = memoryDatastore;
		this.uri = uri;
	}
	
	@Override
	public Long getSize() {
		byte [] data = memoryDatastore.data.get(uri);
		return data != null ? data.length : 0l;
	}

	@Override
	public String getName() {
		return uri.getPath().replaceFirst("/[0-9]+/", "");
	}

	@Override
	public String getContentType() {
		return uri.getQuery();
	}

	@Override
	public Date getLastModified() {
		return lastModified;
	}
}