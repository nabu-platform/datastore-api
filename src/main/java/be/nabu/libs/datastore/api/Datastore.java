package be.nabu.libs.datastore.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.jws.WebParam;
import javax.jws.WebResult;

public interface Datastore {
	@WebResult(name = "stream")
	public InputStream retrieve(@WebParam(name = "uri") URI uri) throws IOException;
	
	@WebResult(name = "properties")
	public DataProperties getProperties(@WebParam(name = "uri") URI uri) throws IOException;
}
