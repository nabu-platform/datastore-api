package be.nabu.libs.datastore.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.jws.WebParam;

public interface UpdatableDatastore extends Datastore {
	public void update(@WebParam(name = "uri") URI uri, @WebParam(name = "stream") InputStream input) throws IOException;
}
