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

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import be.nabu.libs.datastore.api.URIProvider;

public class DatastoreOutputStream extends OutputStream {
	
	private OutputStream parent;
	private URIProvider uriProvider;

	public DatastoreOutputStream(URIProvider uriProvider, OutputStream parent) {
		this.uriProvider = uriProvider;
		this.parent = new BufferedOutputStream(parent);
	}
	public DatastoreOutputStream(final URI uri, OutputStream parent) {
		this.parent = parent;
		this.uriProvider = new URIProvider() {
			@Override
			public URI getURI() {
				return uri;
			}
		};
	}
	
	@Override
	public void close() throws IOException {
		parent.close();
	}
	
	@Override
	public void flush() throws IOException {
		parent.flush();
	}
	
	@Override
	public void write(byte[] arg0, int arg1, int arg2) throws IOException {
		parent.write(arg0, arg1, arg2);
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		parent.write(b);
	}
	
	@Override
	public void write(int arg0) throws IOException {
		parent.write(arg0);
	}
	
	public URI getURI() {
		return uriProvider.getURI();
	}
}
