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
	public long getSize() {
		byte [] data = memoryDatastore.data.get(uri);
		return data != null ? data.length : 0;
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