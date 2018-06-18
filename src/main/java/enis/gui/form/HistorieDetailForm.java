package enis.gui.form;

import org.springframework.context.annotation.Scope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import enis.backend.model.Historie;
import enis.gui.designs.HistorieDetailDesign;

@SpringComponent
@UIScope
@Scope("prototype")
public class HistorieDetailForm extends HistorieDetailDesign {

	private static final long serialVersionUID = -7558330699136775078L;


    private Historie historie;

    public HistorieDetailForm() {
        setReadOnly();
    }

    public void setHistorie(Historie historie) {
        this.historie=historie;
        setForm();
    }

    private void setForm() {
       lblId.setValue(Long.toString(historie.getId()));
       lblDatumZmeny.setValue(historie.getDatumZmeny().toString());
       lblOperace.setValue(historie.getOperace().toString());
       lblUzivatel.setValue(historie.getUzivatel().toString());
       if (historie.getDetail() != null) {
           textDetail.setValue(historie.getDetail());
       }
    }

    public void setReadOnly() {
        textDetail.setReadOnly(true);
    }
}
