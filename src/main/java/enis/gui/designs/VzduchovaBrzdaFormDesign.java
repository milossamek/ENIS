package enis.gui.designs;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class VzduchovaBrzdaFormDesign extends FormLayout {
    protected ComboBox<enis.backend.model.CiselnikVzduchovaBrzda> comboDruhVzduch;
    protected TextField brzdiciHmotnostVzduchBrzdyTF;

    public VzduchovaBrzdaFormDesign() {
		Design.read(this);
	}
}
