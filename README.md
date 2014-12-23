# Datastore

A datastore offers an interface to store and retrieve data. The developer using the datastore does not know where the data is stored, the datastore simply allows him to persist his bytes and retrieve them again later.

```java
// You have data that you want to persist
InputStream input = ...
// When you store it, you get a URI that uniquely references your data
URI uri = datastore.store(input, "mydata", "text/plain");

// You can use the URI to access the data
InputStream input = datastore.retrieve(uri);

// And the properties
String name = datastore.getProperties(uri).getName();
```

Where it is actually stored is a matter of configuration.

## Context

There are several degrees of functionality that a datastore can offer, for example you can have a simple `WritableDatastore` that simply allows you to store data but you can also have a more advanced `ContextualWritableDatastore` that allows you to route data to different backends depending on context. It is up to you to define what a good context is.

This would allow you for example to store data for project A in a database, for project B on a file system and for project C on a remote drive somewhere.

### Scoping

In a number of cases it is interesting to have your low level code be unaware of the context but to have one at the high level. At that high level you can `scope` a datastore to pass to the lower level:

```java
ContextualWritableDatastore<String> contextualDatastore = ...
WritableDatastore scopedDatastore = DatastoreUtils.scope(contextualDatastore, "myscope");
``` 

Anything that is written to the `scopedDatastore` is actually persisted using the fixed scope `myscope`.

## URL <> URN

In the datastore there is an interface called `URNManager` which allows the datastore to map a URL to a URN (and back). The difference between a URL ([locator](http://en.wikipedia.org/wiki/Uniform_resource_locator)) and a URN ([name](http://en.wikipedia.org/wiki/Uniform_resource_name)) is that the latter is globally unique and immutable whereas a URL is only locally unique and can change: you might for example archive the data by zipping it on the file system.

Both URL and URN fit inside the URI specification and the datastore can handle both. If you set no URN manager, it works only with URLs but if you set one, it will use that to create and resolve URNs.

We strongly advise the use of URNs as they allow for the data to be moved once it is stored. This could be due to archival, infrastructural changes,...

## Caching

A datastore can optimally cache data you write to it. For instance while you are storing data to a file backend, it might also create a copy in memory for rapid access upon retrieval. This will minimize I/O in a transparent manner. Whether the cache is clustered or not depends on your usecase but in our experience the clustered cache is generally overkill because in many cases the processing of a single data artifact happens on a single server, it is only cross-data that you start clustered parallelization.

## External Access

Once you centralize all your data into the datastore, it becomes trivial to access any data in a uniform manner. This includes allowing for external access through a simple REST API or the likes. This can make it easier to integrate systems with one another.

## Immutability

Currently the datastore offers interfaces for Updatable and Deletable but we advise strongly **against** using these. They are there to complete the data management for those few usecases where you might need it, but in almost all cases it is infinitely better to have the data in datastore be immutable. This is important for caching, cluster-based processing etc.

## Internalized vs Externalized Writing

The default datastore approach is to use _internalized_ writing which means you give it an InputStream that it has to store somewhere. The datastore itself is responsible of pushing the data from the inputstream to the backend. This is generally easier to use as developer and is easier to implement in the backend (e.g. when writing to databases).

However in some cases you really want an OutputStream that you can write to instead of an InputStream (e.g. you are generating chunks of data). You can mimic this of course with two threads and pipes but in general this is a cumbersome approach. If you **really** need this, there are interfaces for it: StreamableDatastore and ContextualStreamableDatastore.

Note though that this might not be supported by all datastore implementations.