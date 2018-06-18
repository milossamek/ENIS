package enis.backend.utils;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

import enis.backend.model.CiselnikVzduchovaBrzda;
import enis.backend.service.CiselnikVzduchovaBrzdaService;


public class VzduchConverter implements Converter<CiselnikVzduchovaBrzda, Long> {
    CiselnikVzduchovaBrzdaService service;

    private static final long serialVersionUID = -8547561912101668309L;

    public VzduchConverter(CiselnikVzduchovaBrzdaService service) {
        this.service = service;
    }

    @Override
    public Result<Long> convertToModel(CiselnikVzduchovaBrzda ciselnikVzduchovaBrzda, ValueContext valueContext) {
        if (ciselnikVzduchovaBrzda.getId() == null) {
            return Result.ok((long) 0);
        }

        return Result.ok(ciselnikVzduchovaBrzda.getId());
    }

    @Override
    public CiselnikVzduchovaBrzda convertToPresentation(Long aLong, ValueContext valueContext) {
        if (service != null) {
            return service.findById(aLong);
        }
        return null;
    }
}
