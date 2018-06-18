package enis.gui.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.VerticalLayout;

@SpringComponent
@UIScope
@Scope("prototype")
public class CustomLoginForm extends VerticalLayout implements View {
    
	private static final long serialVersionUID = -3226731400623947499L;

	@Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;
    
    private LoginForm login;
    private Label loginFailLabel;

    public CustomLoginForm() {
        super();
        buildForm();              
        addListeners();
    }
    
    private void addListeners() {
    	login.addLoginListener(new LoginForm.LoginListener() {
			private static final long serialVersionUID = -468298182751117007L;

			@Override
            public void onLogin(LoginForm.LoginEvent loginEvent) {
            	try {
            		Authentication auth = new UsernamePasswordAuthenticationToken(loginEvent.getLoginParameter("username"), loginEvent.getLoginParameter("password"));
            		Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
            		SecurityContextHolder.getContext().setAuthentication(authenticated);
            		
            		if(authenticated.isAuthenticated()){
            			Page.getCurrent().setLocation("/app");
            		}					
				} catch (AuthenticationException e) {
					loginFailLabel.setVisible(true);
				}
            }
        });
	}

	private void buildForm(){
    	VerticalLayout layout = new VerticalLayout();

        login = new LoginForm();
        login.setUsernameCaption("Uživatelské jméno");
        login.setPasswordCaption("Heslo");
        login.setLoginButtonCaption("Přihlásit se");
        
        loginFailLabel = new Label("Nesprávné uživatelské jméno nebo heslo");
        loginFailLabel.setVisible(false);
        loginFailLabel.addStyleName("failure");
        layout.addComponent(loginFailLabel);
        layout.addComponent(login);
        layout.setSizeFull();
        layout.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(loginFailLabel, Alignment.MIDDLE_CENTER);
        login.setWidth(null);

        addComponent(layout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
