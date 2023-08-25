package be.nabu.libs.datastore.api;

import java.net.URI;

import javax.jws.WebParam;

public interface UpdatableURNManager extends URNManager {
	public void update(@WebParam(name = "urn") URI urn, @WebParam(name = "url") URI url);
}
