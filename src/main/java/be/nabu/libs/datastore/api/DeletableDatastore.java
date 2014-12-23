package be.nabu.libs.datastore.api;

import java.io.IOException;
import java.net.URI;

public interface DeletableDatastore extends Datastore {
	public void delete(URI uri) throws IOException;
}
