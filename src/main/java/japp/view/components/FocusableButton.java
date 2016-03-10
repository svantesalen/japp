package japp.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import japp.view.look.JappTheme;



@SuppressWarnings("serial")
public abstract class FocusableButton extends JButton { // NOSONAR

	public FocusableButton(String txt) {
		super(txt);
		addFocusListener();
		addKeyListener();
		addActListener();
	}

	protected abstract void handleKeyEvent(KeyEvent e);
	protected abstract void handleActionEvent(ActionEvent e);

	private void addFocusListener() {
		addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				setFocusGainedStyle();
			}

			@Override
			public void focusLost(FocusEvent e) {
				setFocusLostStyle();
			}
		});
	}

	private void addKeyListener() {
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				handleKeyEvent(e);
			}

			@Override
			public void keyPressed(KeyEvent e)  {/* intentionally empty*/}

			@Override
			public void keyTyped(KeyEvent e) {/* intentionally empty*/}

		});
	}

	private void addActListener() {
		addActionListener(e -> handleActionEvent(e));
	
	}

	protected void setFocusGainedStyle() {
		setBackground(JappTheme.buttonGainedFocusBgColor);
		setForeground(JappTheme.buttonGainedFocusFgColor);
	}
	
	protected void setFocusLostStyle() {
		setBackground(JappTheme.buttonLostFocusBgColor);
		setForeground(JappTheme.buttonLostFocusFgColor);
	}
	
}

