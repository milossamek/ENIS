package enis.gui.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.renderers.HtmlRenderer;

import enis.backend.model.Uzivatel;
import enis.backend.service.UzivatelService;
import enis.backend.utils.DialogHandler;
import enis.gui.MainUI;
import enis.gui.designs.GestorFormDesign;
import enis.gui.dialog.ConfirmDialog;
import enis.gui.form.UzivatelForm;

@SpringComponent
@UIScope
public class GestorView extends GestorFormDesign implements View {
    
	private static final long serialVersionUID = -553575145448585212L;

	@Autowired
    private UzivatelService uzivatelService;

    @Autowired
    private BeanFactory beanFactory;

    private Window win;
    private Window dialogWin;	
	private DialogHandler dialogHandler;
    
	private ListDataProvider<Uzivatel> dataProvider;
	
	private TextField jmenoTF = new TextField();

    public GestorView() {
        super();
        setupPage();
        addListeners();
    }

    private void addListeners() {
        addButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 8218983710578110631L;

			@Override
            public void buttonClick(Button.ClickEvent event) {
                showAddUzivatelWindow();
            }
        });

        win.addCloseListener(new Window.CloseListener() {

			private static final long serialVersionUID = 8777117354751081327L;

			@Override
            public void windowClose(Window.CloseEvent e) {
                fillGrid();
            }
        });
        
		dialogWin.addCloseListener(new CloseListener() {
			
			private static final long serialVersionUID = 8369037861177884334L;

			@Override
			public void windowClose(CloseEvent e) {
				if(dialogHandler.isConfirm()){
					uzivatelService.delete((Uzivatel) dialogHandler.getObject());
					fillGrid();
				}
			}
		});

        grid.addItemClickListener(new ItemClickListener<Uzivatel>() {

			private static final long serialVersionUID = 2810761908952975959L;

			@Override
            public void itemClick(Grid.ItemClick<Uzivatel> event) {
                if(event.getColumn().getId().equals("edit")){
                    showEditUzivatelWindow(event.getItem());
                } else if(event.getColumn().getId().equals("delete")) {
                    showConfirmDialog(event.getItem());
                }
            }
        });

        btnList.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 2279530440214287374L;

			@Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MainUI.getCurrent().getNavigator().navigateTo(MainUI.ADMINVIEW);
            }
        });
    }

    private void showAddUzivatelWindow(){
        UzivatelForm uzivatelForm = beanFactory.getBean(UzivatelForm.class);
        showWindow("Přidej uživatele", uzivatelForm);

    }

    private void showEditUzivatelWindow(Uzivatel uzivatel){
        UzivatelForm uzivatelForm = beanFactory.getBean(UzivatelForm.class);
        uzivatelForm.setUzivatel(uzivatel);
        showWindow("Uprav uživatele", uzivatelForm);
    }

    private void showWindow(String caption, Component context){
        win.setContent(context);
        win.setCaption(caption);
        win.setModal(true);
        win.center();
        win.setResizable(false);
        if (!UI.getCurrent().getWindows().contains(win)) { //hazelo to except že okno už je přidané
            UI.getCurrent().addWindow(win);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    private void setupPage() {
        win = new Window();
		dialogWin = new Window();

        addButton.setCaption("Přidej nového uživatele");

        grid.setSelectionMode(Grid.SelectionMode.NONE);
        
        grid.removeColumn("id");
        grid.removeColumn("heslo");

        grid.addColumn(a -> {return VaadinIcons.PENCIL.getHtml();}, new HtmlRenderer()).setCaption("Uprav").setSortable(false).setId("edit");
        grid.addColumn(a -> {return VaadinIcons.TRASH.getHtml();}, new HtmlRenderer()).setCaption("Smaž").setSortable(false).setId("delete");

        grid.getColumn("jmeno").setCaption("Jméno");
        grid.getColumn("role").setCaption("Role");
        
		grid.appendHeaderRow();	
		
		jmenoTF.addValueChangeListener(this::onJmenoCisFilterTextChange);
		jmenoTF.setPlaceholder("Vyhledat dle jména");
		jmenoTF.addStyleName("small");
		
		HeaderRow hr = grid.getHeaderRow(1);
		hr.getCell("jmeno").setComponent(jmenoTF);
    }
    
	private void onJmenoCisFilterTextChange(HasValue.ValueChangeEvent<String> event){
		ListDataProvider<Uzivatel> dp = (ListDataProvider<Uzivatel>) grid.getDataProvider();
		dp.setFilter(Uzivatel::getJmeno, s -> caseInsensitiveContains(String.valueOf(s), event.getValue()));
	}
	
    private Boolean caseInsensitiveContains(String where, String what) {
        return where.toLowerCase().contains(what.toLowerCase());
    }

    @PostConstruct
    private void fillGrid() {
    	dataProvider = DataProvider.ofCollection(uzivatelService.findAll());
        grid.setDataProvider(dataProvider);
    }
    
	private void showConfirmDialog(Uzivatel uzivatel){
		dialogHandler = new DialogHandler(uzivatel, dialogWin);
		ConfirmDialog cd = new ConfirmDialog("Opravdu chcete smazat tohoto uživatele?", "Ano", "Ne", dialogHandler);
		dialogWin.setContent(cd);
		dialogWin.setCaption("Smazání");
		dialogWin.setModal(true);
		dialogWin.center();
		dialogWin.setResizable(false);
		UI.getCurrent().addWindow(dialogWin);
	}
}
