package org.functions;

import java.util.*;
import java.util.function.Function;

import static org.functions.collections.reduce;

public class maps {
	private maps() {
		throw new IllegalStateException("utility class");
	}

	public static <T> Function<T, Collection<T>> add_if_exists(Collection<T> collection) {
		return value -> {
			if (value != null) {
				collection.add(value);
			}
			return collection;
		};
	}

	public static Function<Object, Function<List<Map<Object, Object>>, HashSet<Object>>>
	get_unique_values_for_column() {
		return key -> map_collection -> reduce((set, map) -> {
			var value = map.get(key);
			set.add(value);
			return set;
		}, new HashSet<>(), map_collection);
	}

	public static Function<Map<Object, Map<Object, Object>>,
					Function<Object,
									Function<Object,
													Map<Object, Map<Object, Object>>>>> index_on() {
		return old_relational_map -> key_name_for_old_key -> new_key -> {
			var new_data_map = new HashMap<Object, Map<Object, Object>>();
			var reduce_map_into_new_key =
							reindex_map().apply(old_relational_map).apply(key_name_for_old_key).apply(new_key);
			var re_indexed_data =
							reduce((data_map, old_key) -> (HashMap<Object, Map<Object, Object>>) reduce_map_into_new_key.apply(old_key).apply(data_map),
											new_data_map,
											old_relational_map.keySet());
			return re_indexed_data;
		};
	}

	public static <O, K, V>
	Function<Map<O, Map<K, V>>,
					Function<K,
									Function<K,
													Function<O,
																	Function<Map<V, Map<K, O>>,
																					Map<V, Map<K, O>>>>>>>
	reindex_map() {
		return data_to_remap -> initial_key_name -> new_key_name -> initial_key_value -> new_map -> {
			var existing_info = data_to_remap.get(initial_key_value);
			var reordered_map = new HashMap<K, O>();
			reordered_map.put(initial_key_name, initial_key_value);
			var new_key_from_value = existing_info.get(new_key_name);
			existing_info.remove(new_key_name);
			reordered_map.putAll((Map<K, O>) existing_info);
			new_map.put(new_key_from_value, reordered_map);
			return new_map;
		};
	}

	public static Function<Object,
					Function<Map<Object, Map<Object, Object>>,
									Function<Map<Object, Object>,
													Map<Object, Map<Object, Object>>>>>
	create_relational_map() {
		return relational_key_name -> relational_map -> map -> {
			var relational_key = map.get(relational_key_name);
			map.remove(relational_key_name);
			relational_map.put(relational_key, map);
			return relational_map;
		};
	}
}
