package es.arf.mca.tfm.apimanager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static String convertToJSON(Object o) throws JsonProcessingException {
		return mapper.writeValueAsString(o);
	}

	public static <T> T convertToClass(Object fromValue, Class<T> toValueType) {
		return mapper.convertValue(fromValue, toValueType);
	}
}
