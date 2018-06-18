package enis.backend.utils;

import com.vaadin.ui.Window;

public class DialogHandler {

	private Object obj;
	private boolean confirm;
	private Window win;
	
	public DialogHandler(Object obj, Window win) {
		this.obj = obj;
		this.win = win;
	}

	public Object getObject() {
		return obj;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Window getWin() {
		return win;
	}
}
