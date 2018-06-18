package enis.gui;


import enis.backend.importdata.ImportData;
import enis.backend.service.VuzService;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import enis.gui.view.AdministrationView;
import enis.gui.view.CustomLoginForm;
import enis.gui.view.GestorView;
import enis.gui.view.GridView;
import enis.gui.view.HistoryView;

@Title("ENIS Data")
@Theme("valo")
@SpringUI(path = "/app")
public class MainUI extends UI {

    private static final long serialVersionUID = 1L;
    Navigator navigator;
    @Autowired
    private VuzService vuzService;


    @Autowired
    CustomLoginForm customLoginForm;
    @Autowired
    GridView gridView;
    @Autowired
    GestorView gestorView;
    @Autowired
    AdministrationView adminView;
    @Autowired
    HistoryView histView;

    public static final String LOGINVIEW = "login";    
    public static final String GRIDVIEW = "gridview";
    public static final String GESTORVIEW = "gestorview";
    public static final String ADMINVIEW = "adminview";
    public static final String HISTORIEVIEW = "historieview";


    public MainUI() {
    }

    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this,this);
        navigator.addView(LOGINVIEW,customLoginForm);
        navigator.addView(GRIDVIEW, gridView);
        navigator.addView(GESTORVIEW, gestorView);
        navigator.addView(ADMINVIEW, adminView);
        navigator.addView(HISTORIEVIEW, histView);

        navigator.navigateTo(MainUI.GRIDVIEW);
        
    /*   ImportData data = new ImportData("D:\\škola\\IntellJ\\ENIS\\export_RP.xlsx",vuzService);
        try {
            data.convertExcelToObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

}
