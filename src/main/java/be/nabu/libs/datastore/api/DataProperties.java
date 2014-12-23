package be.nabu.libs.datastore.api;

import java.util.Date;

public interface DataProperties {
	public long getSize();
	public String getName();
	public String getContentType();
	public Date getLastModified();
}
