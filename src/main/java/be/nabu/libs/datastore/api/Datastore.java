package be.nabu.libs.datastore.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public interface Datastore {
	public InputStream retrieve(URI uri) throws IOException;
	public DataProperties getProperties(URI uri) throws IOException;
}
