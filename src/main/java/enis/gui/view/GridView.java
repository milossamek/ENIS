package enis.gui.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.renderers.HtmlRenderer;

import enis.backend.export.PDFExport;
import enis.backend.model.Historie;
import enis.backend.model.Vuz;
import enis.backend.service.HistorieService;
import enis.backend.service.UzivatelService;
import enis.backend.service.VuzService;
import enis.backend.utils.DialogHandler;
import enis.backend.utils.OperationType;
import enis.gui.MainUI;
import enis.gui.designs.GridViewDesign;
import enis.gui.dialog.ConfirmDialog;
import enis.gui.form.MainWinForm;
import enis.security.utils.SecurityUtils;


@SpringComponent
@UIScope
public class GridView extends GridViewDesign implements View {

	private static final long serialVersionUID = -1447097347698572728L;
	
	@Autowired
    private VuzService vuzService;

	@Autowired
	private HistorieService historie;

	@Autowired
	private UzivatelService uzivatel;
    
    @Autowired
    private BeanFactory beanFactory;

	private PDFExport export;

	private Window win;
    private Window dialogWin;	
	private DialogHandler dialogHandler;



	private ListDataProvider<Vuz> dataProvider;
	
	private TextField vuzCisTF = new TextField();
	
	public GridView() {
		super();
		setupPage();
		addListeners();
		preparePDFExport();
	}

	private void preparePDFExport() {
		export = new PDFExport();
		StreamResource myResource = export.getPDFStream();
		FileDownloader fileDownloader = new FileDownloader(myResource);
		fileDownloader.extend(btnExport);
	}

	private void exportPDF(List<Vuz> vozy) {
		export.setVozy(vozy);
	}
	
	private void addListeners() {		
		addButton.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 6755292723127732895L;

			@Override
			public void buttonClick(ClickEvent event) {		
				showVuzAddWindow();
			}
		});
		
		win.addCloseListener(new CloseListener() {

			private static final long serialVersionUID = -707710393099478431L;

			@Override
			public void windowClose(CloseEvent e) {
				fillGrid();
			}
		});
		
		dialogWin.addCloseListener(new CloseListener() {
			
			private static final long serialVersionUID = -6042490708383133523L;

			@Override
			public void windowClose(CloseEvent e) {
				if(dialogHandler.isConfirm()){
					String loggedUser = SecurityUtils.getLoggedUser();
					historie.save(new Historie(uzivatel.findUserByUsername(loggedUser),OperationType.DELETE.getName() + " " + ((Vuz) dialogHandler.getObject()).getVuzCis(), new Date(),uzivatel.findUserByUsername(loggedUser).toString()));
					vuzService.delete((Vuz) dialogHandler.getObject());
					fillGrid();
				}
			}
		});

		grid.addItemClickListener(new ItemClickListener<Vuz>() {

			private static final long serialVersionUID = 9017288249375793184L;

			@Override
			public void itemClick(ItemClick<Vuz> event) {
				ArrayList<Vuz> vuzs = new ArrayList<>();
				vuzs.add(event.getItem());
				exportPDF(vuzs);

				if(event.getColumn().getId().equals("edit")){
					showVuzEditWindow(event.getItem());
				} else if(event.getColumn().getId().equals("delete")){
					showConfirmDialog(event.getItem());
				} else {
					showVuzDetailWindow(event.getItem());
				}
			}
		});

		btnAdmin.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 6755767411477077542L;

			@Override
			public void buttonClick(ClickEvent clickEvent) {
				MainUI.getCurrent().getNavigator().navigateTo(MainUI.ADMINVIEW);
			}
		});
	}

	private void setupPage() {
		btnAdmin.setVisible(SecurityUtils.checkRole("GESTOR"));
		btnAdmin.setCaption("Administrace");
		addButton.setVisible(SecurityUtils.checkRole("GESTOR"));
		
		win = new Window();
		dialogWin = new Window();
		
		addButton.setCaption("Přidej nový vůz");
		
		grid.setSelectionMode(SelectionMode.NONE);
		
		grid.removeColumn("id");
		grid.removeColumn("podvozek");
		grid.removeColumn("revize");
		grid.removeColumn("rozmer");
		grid.removeColumn("rucniBrzda");
		grid.removeColumn("vzduchovaBrzda");

		if (SecurityUtils.checkRole("GESTOR")) {
			grid.addColumn(a -> {
				return VaadinIcons.PENCIL.getHtml();
			}, new HtmlRenderer()).setCaption("Uprav").setSortable(false).setId("edit");
			grid.addColumn(a -> {
				return VaadinIcons.TRASH.getHtml();
			}, new HtmlRenderer()).setCaption("Smaž").setSortable(false).setId("delete");
		}

		grid.getColumn("vuzCis").setCaption("Číslo vozu");
		grid.getColumn("stavVozu").setCaption("Stav vozu");
		grid.getColumn("vlastniHmotnost").setCaption("Vlastní hmotnost");
		grid.getColumn("maxRychlost").setCaption("Maximální rychlost");
		
		grid.appendHeaderRow();	
		
		vuzCisTF.addValueChangeListener(this::onVuzCisFilterTextChange);
		vuzCisTF.setPlaceholder("Vyhledat dle čísla vozu");
		vuzCisTF.addStyleName("small");
		
		HeaderRow hr = grid.getHeaderRow(1);
		hr.getCell("vuzCis").setComponent(vuzCisTF);
	}
	
	private void onVuzCisFilterTextChange(HasValue.ValueChangeEvent<String> event){
		ListDataProvider<Vuz> dp = (ListDataProvider<Vuz>) grid.getDataProvider();
		dp.setFilter(Vuz::getVuzCis, s -> caseInsensitiveContains(String.valueOf(s), event.getValue()));
	}
	
    private Boolean caseInsensitiveContains(String where, String what) {
        return where.toLowerCase().contains(what.toLowerCase());
    }
	
	@PostConstruct
	private void fillGrid() {
		dataProvider = DataProvider.ofCollection(vuzService.findAll());
		grid.setDataProvider(dataProvider);			
	}	
	
	private void showVuzDetailWindow(Vuz vuz){
		MainWinForm mainWinForm = beanFactory.getBean(MainWinForm.class);
		mainWinForm.setReadOnly();
		mainWinForm.setVuz(vuz);
		showWindow("Detail vozu", mainWinForm);
	}
	
	private void showVuzAddWindow(){
		MainWinForm mainWinForm = beanFactory.getBean(MainWinForm.class);
		mainWinForm.setButtonCaption("Přidej");
		mainWinForm.setCloseFunction(() -> {win.close();});
		mainWinForm.setType(OperationType.ADD);
		showWindow("Přidání vozu", mainWinForm);
	}
	
	private void showVuzEditWindow(Vuz vuz){
		MainWinForm mainWinForm = beanFactory.getBean(MainWinForm.class);
		mainWinForm.setButtonCaption("Uprav");
		mainWinForm.setCloseFunction(() -> {win.close();});
		mainWinForm.setVuz(vuz);
		mainWinForm.setType(OperationType.EDIT);
		showWindow("Úprava vozu", mainWinForm);
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
	
	private void showConfirmDialog(Vuz vuz){
		dialogHandler = new DialogHandler(vuz, dialogWin);
		ConfirmDialog cd = new ConfirmDialog("Opravdu chcete smazat tento Vůz?", "Ano", "Ne", dialogHandler);
		dialogWin.setContent(cd);
		dialogWin.setCaption("Smazání");
		dialogWin.setModal(true);
		dialogWin.center();
		dialogWin.setResizable(false);
		UI.getCurrent().addWindow(dialogWin);
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
