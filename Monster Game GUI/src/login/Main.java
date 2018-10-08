package login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Main extends Thread {

	public static Thread thread = new Thread();

	public Accounts accounts = new Accounts();
	public static Account loggedIn = null;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Monster Game");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		render();
	}

	private void render() {
		frame.getContentPane().removeAll();

		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (loggedIn != null) {
					Game.Main.main(null);
				} else {
					JOptionPane.showMessageDialog(null, "You must login to do that.");
				}

			}
		});

		frame.getContentPane().add(btnPlay, "6, 6");

		if (loggedIn == null) {
			JButton btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					frame.getContentPane().removeAll();

					JButton btnBack = new JButton("Back");
					btnBack.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							render();
						}
					});

					frame.getContentPane().add(btnBack, "2, 2");

					JLabel lblUsername = new JLabel("Username:");
					frame.getContentPane().add(lblUsername, "4, 6");

					JTextField txtUsername = new JTextField();
					frame.getContentPane().add(txtUsername, "8, 6, fill, default");
					txtUsername.setColumns(10);

					JLabel lblPassword = new JLabel("Password:");
					frame.getContentPane().add(lblPassword, "4, 10");

					JPasswordField txtPassword = new JPasswordField();
					frame.getContentPane().add(txtPassword, "8, 10, fill, default");
					txtPassword.setColumns(10);

					JButton btnSubmit = new JButton("Submit");
					btnSubmit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							loggedIn = accounts.logIn(txtUsername.getText(), new String(txtPassword.getPassword()));

							if (loggedIn != null) {
								render();
							}
						}
					});

					frame.getContentPane().add(btnSubmit, "4, 14");

					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
				}
			});

			frame.getContentPane().add(btnLogin, "6, 12");
		} else {
			JButton btnLogout = new JButton("Logout");
			btnLogout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					loggedIn = null;
					JOptionPane.showMessageDialog(null, "Successfully logged out.");
					render();
				}
			});

			frame.getContentPane().add(btnLogout, "6, 12");
		}

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();

				JButton btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						render();
					}
				});

				frame.getContentPane().add(btnBack, "2, 2");

				JLabel lblUsername = new JLabel("Username:");
				frame.getContentPane().add(lblUsername, "4, 6");

				JTextField txtUsername = new JTextField();
				frame.getContentPane().add(txtUsername, "8, 6, fill, default");
				txtUsername.setColumns(10);

				JLabel lblPassword = new JLabel("Password:");
				frame.getContentPane().add(lblPassword, "4, 10");

				JPasswordField txtPassword = new JPasswordField();
				frame.getContentPane().add(txtPassword, "8, 10, fill, default");
				txtPassword.setColumns(10);

				JButton btnSubmit = new JButton("Submit");
				btnSubmit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Boolean test = accounts.registerAccount(txtUsername.getText(),
								new String(txtPassword.getPassword()));

						if (test) {
							render();
						}
					}
				});

				frame.getContentPane().add(btnSubmit, "4, 14");

				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
			}
		});

		frame.getContentPane().add(btnRegister, "6, 18");

		JButton btnLeaderboard = new JButton("Leaderboard");
		btnLeaderboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();

				JButton btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						render();
					}
				});

				frame.getContentPane().add(btnBack, "2, 2");

				accounts.printLeaderboard(frame);

				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
			}
		});

		frame.getContentPane().add(btnLeaderboard, "6, 24");

		if (loggedIn != null) {
			if (loggedIn.getType() == AccountType.ADMIN) {
				JButton btnRemoveAccount = new JButton("Remove Account");
				btnRemoveAccount.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						frame.getContentPane().removeAll();

						JButton btnBack = new JButton("Back");
						btnBack.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								render();
							}
						});

						frame.getContentPane().add(btnBack, "2, 2");

						JLabel lblUsername = new JLabel("Username:");
						frame.getContentPane().add(lblUsername, "4, 6");

						JTextField txtUsername = new JTextField();
						frame.getContentPane().add(txtUsername, "8, 6, fill, default");
						txtUsername.setColumns(10);

						JButton btnSubmit = new JButton("Submit");
						btnSubmit.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								Boolean test = accounts.removeAccount(txtUsername.getText());

								if (test) {
									render();
								}
							}
						});

						frame.getContentPane().add(btnSubmit, "4, 14");

						frame.getContentPane().revalidate();
						frame.getContentPane().repaint();
					}
				});

				frame.getContentPane().add(btnRemoveAccount, "6, 30");

				JButton btnChangeWinTimer = new JButton("Change Win Timer");
				btnChangeWinTimer.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						frame.getContentPane().removeAll();

						JButton btnBack = new JButton("Back");
						btnBack.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								render();
							}
						});

						frame.getContentPane().add(btnBack, "2, 2");

						JLabel lblTimer = new JLabel("Time in seconds:");
						frame.getContentPane().add(lblTimer, "4, 6");

						JTextField txtTimer = new JTextField();
						frame.getContentPane().add(txtTimer, "8, 6, fill, default");
						txtTimer.setColumns(10);

						JButton btnSubmit = new JButton("Submit");
						btnSubmit.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								Boolean test = isInteger(txtTimer.getText());

								if (test) {
									Game.Main.gameTimerTarget = 60 * Integer.parseInt(txtTimer.getText());
									JOptionPane.showMessageDialog(null, "Successfully set timer.");
									render();
								} else {
									JOptionPane.showMessageDialog(null, "Must be an integer.");
								}
							}
						});

						frame.getContentPane().add(btnSubmit, "4, 14");

						frame.getContentPane().revalidate();
						frame.getContentPane().repaint();
					}
				});

				frame.getContentPane().add(btnChangeWinTimer, "6, 36");
			}
		}

		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}

		return true;
	}
}
