package enis.backend.utils;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

import enis.backend.model.CiselnikRucniBrzda;
import enis.backend.service.CiselnikRucniBrzdaService;

public class RucniConverter implements Converter<CiselnikRucniBrzda, Long> {
    
	private static final long serialVersionUID = -2392579436038709527L;
	CiselnikRucniBrzdaService service;

    public RucniConverter(CiselnikRucniBrzdaService service) {
        this.service = service;
    }

    @Override
    public Result<Long> convertToModel(CiselnikRucniBrzda ciselnikRucniBrzda, ValueContext valueContext) {
        if (ciselnikRucniBrzda.getId() == null) {
            return Result.ok((long) 0);
        }

        return Result.ok(ciselnikRucniBrzda.getId());
    }

    @Override
    public CiselnikRucniBrzda convertToPresentation(Long aLong, ValueContext valueContext) {
      if (service != null) {
          return service.findById(aLong);
      }
      return null;
    }
}
