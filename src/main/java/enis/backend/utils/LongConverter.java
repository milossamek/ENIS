package enis.backend.utils;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

public class LongConverter implements Converter<String, Long> {

	private static final long serialVersionUID = -8547561912101668309L;

	@Override
	public Result<Long> convertToModel(String value, ValueContext context) {
		try {
			Long val = Long.valueOf(value);
			return Result.ok(val);
		} catch (Exception e) {
			if(value == null || value.equals("")){
				if(context.getComponent().get().getId() != null && context.getComponent().get().getId().equals("vuzCis")){
					return Result.error("Musí být číslo");
				}
				return Result.ok(null);
			} else {
				return Result.error("Musí být číslo");
			}
		}
	}

	@Override
	public String convertToPresentation(Long value, ValueContext context) {
		return String.valueOf(value == null ? "" : value);
	}

}
