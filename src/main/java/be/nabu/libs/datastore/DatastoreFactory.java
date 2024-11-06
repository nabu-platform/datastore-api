/*
* Copyright (C) 2014 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package be.nabu.libs.datastore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import be.nabu.libs.datastore.api.Datastore;

public class DatastoreFactory {

	private static DatastoreFactory instance;
	
	public static DatastoreFactory getInstance() {
		if (instance == null)
			instance = new DatastoreFactory();
		return instance;
	}
	
	private Datastore datastore;
	
	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}
	
	public void unsetDatastore(Datastore datastore) {
		this.datastore = null;
	}
	
	@SuppressWarnings("unchecked")
	public Datastore getDatastore() {
		if (datastore == null) {
			try {
				// let's try this with custom service loading based on a configuration
				Class<?> clazz = getClass().getClassLoader().loadClass("be.nabu.utils.services.ServiceLoader");
				Method declaredMethod = clazz.getDeclaredMethod("load", Class.class);
				List<Datastore> datastores = ((List<Datastore>) declaredMethod.invoke(null, Datastore.class));
				if (!datastores.isEmpty()) {
					datastore = datastores.get(0);
				}
			}
			catch (ClassNotFoundException e) {
				// ignore, the framework is not present
			}
			catch (NoSuchMethodException e) {
				// corrupt framework?
				throw new RuntimeException(e);
			}
			catch (SecurityException e) {
				throw new RuntimeException(e);
			}
			catch (IllegalAccessException e) {
				// ignore
			}
			catch (InvocationTargetException e) {
				// ignore
			}
			if (datastore == null) {
				ServiceLoader<Datastore> serviceLoader = ServiceLoader.load(Datastore.class);
				Iterator<Datastore> iterator = serviceLoader.iterator();
				if (iterator.hasNext()) {
					datastore = iterator.next();
				}
				else {
					datastore = new MemoryDatastore();
				}
			}
		}
		return datastore;
	}
	
	@SuppressWarnings("unused")
	private void activate() {
		instance = this;
	}
	@SuppressWarnings("unused")
	private void deactivate() {
		instance = null;
	}
}
