package be.nabu.libs.datastore.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.jws.WebParam;
import javax.jws.WebResult;

public interface WritableDatastore extends Datastore {
	@WebResult(name = "uri")
	public URI store(@WebParam(name = "stream") InputStream input, @WebParam(name = "name") String name, @WebParam(name = "contentType") String contentType) throws IOException;
}
