package br.com.kontrola.application.persistence;

import static br.com.kontrola.application.persistence.EncapsulatedObjectifyService.ofy;

public abstract class BaseEntityRepository {

	protected <T extends BaseEntity> T persist(T entity) {
		if (!entity.isPersisted()) {
			entity.defineKey();
		}

		ofy().save().entity(entity).now();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> T loadByKey(Class<T> clazz, String key) {
		return (T) ofy().load().type(clazz).filterKey(key).first().now();
	}

}
