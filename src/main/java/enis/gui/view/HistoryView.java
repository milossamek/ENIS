package enis.gui.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.ItemClickListener;

import enis.backend.model.Historie;
import enis.backend.service.HistorieService;
import enis.gui.MainUI;
import enis.gui.designs.HistoryDesign;
import enis.gui.form.HistorieDetailForm;

@SpringComponent
@UIScope
public class HistoryView extends HistoryDesign implements View {
    
	private static final long serialVersionUID = -6892530358179870754L;

	@Autowired
    private HistorieService history;

    @Autowired
    private BeanFactory beanFactory;

    private Window win;

    private ListDataProvider<Historie> dataProvider;

    public HistoryView() {
        super();

        win = new Window();


        btnList.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -6423064547813416389L;

			@Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MainUI.getCurrent().getNavigator().navigateTo(MainUI.ADMINVIEW);
            }
        });

        grid.addItemClickListener(new ItemClickListener<Historie>() {

			private static final long serialVersionUID = -2453315101574039650L;

			@Override
            public void itemClick(Grid.ItemClick<Historie> event) {
                showDetailHistorie(event.getItem());
            }
        });


        grid.removeColumn("detail");
    }

    private void showWindow(String caption, Component context){
        win.setContent(context);
        win.setCaption(caption);
        win.setModal(true);
        win.center();
        win.setResizable(true);
        if (!UI.getCurrent().getWindows().contains(win)) { //hazelo to except že okno už je přidané
            UI.getCurrent().addWindow(win);
        }
    }

    private void showDetailHistorie(Historie historie){
        HistorieDetailForm historieDetailForm = beanFactory.getBean(HistorieDetailForm.class);
        historieDetailForm.setReadOnly();
        historieDetailForm.setHistorie(historie);
        showWindow("Detail historie", historieDetailForm);
    }




    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
    @PostConstruct
    private void fillGrid() {
        dataProvider = DataProvider.ofCollection(history.findAll());
        grid.setDataProvider(dataProvider);
    }
}
