package enis.gui.dialog;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import enis.backend.utils.DialogHandler;
import enis.gui.designs.ConfirmDialogDesign;

public class ConfirmDialog extends ConfirmDialogDesign {

	private static final long serialVersionUID = -8396410180320822405L;
	
	private DialogHandler handler;

	
	public ConfirmDialog(String label, String ok, String storno, DialogHandler handler) {
		super();		
		this.handler = handler;
		descLabel.setValue(label);
		okButton.setCaption(ok);
		stornoButton.setCaption(storno);
		addListeners();
	}	

	private void addListeners() {
		okButton.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = -4707786355267960690L;

			@Override
			public void buttonClick(ClickEvent event) {
				handler.setConfirm(true);
				handler.getWin().close();
			}
		});
		
		stornoButton.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 343556892649759048L;

			@Override
			public void buttonClick(ClickEvent event) {
				handler.setConfirm(false);
				handler.getWin().close();				
			}
		});
	}

}
