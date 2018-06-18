package enis.gui.designs;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

import enis.gui.form.LogoutForm;

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
public class GridViewDesign extends VerticalLayout {
    protected LogoutForm lognoutForm;
    protected Button btnAdmin;
    protected Button btnExport;
    protected Button addButton;
    protected Grid<enis.backend.model.Vuz> grid;

    public GridViewDesign() {
		Design.read(this);
	}
}
