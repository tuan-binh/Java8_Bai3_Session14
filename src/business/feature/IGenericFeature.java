package business.feature;

/**
 * @param <T> type object handle
 * @param <E> type id in object
 */
public interface IGenericFeature<T, E>
{
	void addOrUpdate(T t);
	
	void delete(E id);
	
	int findIndexById(E id);
}
