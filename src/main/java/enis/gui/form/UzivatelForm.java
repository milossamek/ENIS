package enis.gui.form;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

import enis.backend.model.Uzivatel;
import enis.backend.service.RoleService;
import enis.backend.service.UzivatelService;
import enis.backend.utils.IForm;
import enis.gui.designs.UzivatelFormDesign;

@SpringComponent
@UIScope
@Scope("prototype")
public class UzivatelForm extends UzivatelFormDesign implements IForm {
    
	private static final long serialVersionUID = 3160127677852297769L;

	@Autowired
    private UzivatelService uzivatelService;
    
    @Autowired
    private RoleService roleService;

    private Uzivatel uzivatel;

    private BeanValidationBinder<Uzivatel> binder;

    public UzivatelForm() {
        super();
        binder = new BeanValidationBinder<>(Uzivatel.class);
        uzivatel = new Uzivatel();
        bindFields();
        addListeners();
    }

    @PostConstruct
    private void setupPage() {
    	comboRole.setEmptySelectionAllowed(false);
        comboRole.setDataProvider(DataProvider.ofCollection(roleService.findAll()));
    }

    private void bindFields() {
        binder.setBean(uzivatel);

        binder.forField(textJmeno).bind(Uzivatel::getJmeno, Uzivatel::setJmeno);
        binder.forField(textHeslo).bind(Uzivatel::getHeslo, Uzivatel::setHeslo);
        binder.forField(comboRole).bind(Uzivatel::getRole,Uzivatel::setRole);
    }

    private void addListeners() {
        btnConfirm.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 9022838750258897230L;

			@Override
            public void buttonClick(Button.ClickEvent event) {
                if(uzivatel != null && validateForm()){
                    uzivatelService.save(uzivatel);
                } else {
                	Notification.show("Nektere zadane udaje jsou nevalidni");
                }
            }
        });
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
        binder.setBean(uzivatel);
    }

    @Override
    public boolean validateForm() {
        return binder.validate().isOk();
    }

    @Override
    public void setReadOnly() {
        textHeslo.setReadOnly(true);
        textJmeno.setReadOnly(true);
        comboRole.setReadOnly(true);
    }
}
