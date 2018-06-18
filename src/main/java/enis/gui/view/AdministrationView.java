package enis.gui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;

import enis.gui.MainUI;
import enis.gui.designs.AdministrationDesign;

@SpringComponent
@UIScope
public class AdministrationView extends AdministrationDesign implements View {

	private static final long serialVersionUID = -2659971412566116229L;

	public AdministrationView() {
        btnHistorie.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = -8816741921249459096L;

			@Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MainUI.getCurrent().getNavigator().navigateTo(MainUI.HISTORIEVIEW);
            }
        });

        btnUzivatele.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -4954407250160330764L;

			@Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MainUI.getCurrent().getNavigator().navigateTo(MainUI.GESTORVIEW);
            }
        });

        btnBack.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 4999123585906536550L;

			@Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MainUI.getCurrent().getNavigator().navigateTo(MainUI.GRIDVIEW);
            }
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
