package enis.gui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import enis.gui.view.CustomLoginForm;

@StyleSheet("/css/styles.css")
@Title("ENIS login form")
@Theme("valo")
@SpringUI(path = "/login")
public class LoginUI extends UI {
	
	private static final long serialVersionUID = -4088949130907606626L;
	@Autowired
    CustomLoginForm customLoginForm;

	@Override
	protected void init(VaadinRequest request) {
		setContent(customLoginForm);
	}

}
