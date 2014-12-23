package be.nabu.libs.datastore;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import be.nabu.libs.datastore.api.ContextualWritableDatastore;
import be.nabu.libs.datastore.api.DataProperties;
import be.nabu.libs.datastore.api.WritableDatastore;

public class ScopedWritableDatastore implements WritableDatastore {

	@SuppressWarnings("rawtypes")
	private ContextualWritableDatastore datastore;
	private Object context;
	
	public <T> ScopedWritableDatastore(ContextualWritableDatastore<T> datastore, T context) {
		this.datastore = datastore;
		this.context = context;
	}
	
	@Override
	public InputStream retrieve(URI uri) throws IOException {
		return datastore.retrieve(uri);
	}

	@Override
	public DataProperties getProperties(URI uri) throws IOException {
		return datastore.getProperties(uri);
	}

	@SuppressWarnings("unchecked")
	@Override
	public URI store(InputStream input, String name, String contentType) throws IOException {
		return datastore.store(context, input, name, contentType);
	}
	
	Object getContext() {
		return context;
	}
	
	ContextualWritableDatastore<?> getDatastore() {
		return datastore;
	}
}
