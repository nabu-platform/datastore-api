package be.nabu.libs.datastore;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import be.nabu.libs.datastore.api.DataProperties;
import be.nabu.libs.datastore.api.DeletableDatastore;
import be.nabu.libs.datastore.api.UpdatableDatastore;
import be.nabu.libs.datastore.api.WritableDatastore;

public class MemoryDatastore implements WritableDatastore, UpdatableDatastore, DeletableDatastore {

	Map<URI, byte[]> data = new HashMap<URI, byte[]>();
	private long counter;
	
	@Override
	public InputStream retrieve(URI uri) {
		return data.containsKey(uri) ? new ByteArrayInputStream(data.get(uri)) : null;
	}

	@Override
	public DataProperties getProperties(URI uri) {
		return data.containsKey(uri) ? new VolatileProperties(this, uri) : null;
	}

	@Override
	public URI store(InputStream input, String name, String contentType) throws IOException {
		URI uri = createURI(name, contentType);
		byte [] bytes = toBytes(input);
		synchronized(bytes) {
			data.put(uri, bytes);
		}
		return uri;
	}

	private byte [] toBytes(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int read = 0;
		byte [] buffer = new byte[4096];
		input = new BufferedInputStream(input);
		while ((read = input.read(buffer)) != -1) {
			output.write(buffer, 0, read);
		}
		return output.toByteArray();
	}
	
	synchronized private URI createURI(String name, String contentType) {
		try {
			return new URI("memory:/" + counter++ + "/" + name + "?" + contentType);
		}
		catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(URI uri) throws IOException {
		if (data.containsKey(uri)) {
			synchronized(data) {
				data.remove(uri);
			}
		}
	}

	@Override
	public void update(URI uri, InputStream input) throws IOException {
		if (!data.containsKey(uri)) {
			throw new FileNotFoundException(uri.getPath());
		}
		byte [] bytes = toBytes(input);
		synchronized(data) {
			if (!data.containsKey(uri)) {
				throw new FileNotFoundException(uri.getPath());
			}
			data.put(uri, bytes);
		}
	}
}
