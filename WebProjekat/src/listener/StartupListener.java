package listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.DataContext;
import model.User;
import security.AuthRole;

// Web resource: http://www.java2s.com/Tutorial/Java/0400__Servlet/SetServletContextListenerinwebXML.htm

public class StartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("About to destroy webapp...");
		
		ServletContext ctx = arg0.getServletContext();
		DataContext dctx = (DataContext) ctx.getAttribute("data");
		String path = ctx.getRealPath("") + "/data/dataContext.ser";
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(dctx);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in /data/dataContext.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("About to start webapp...");
		System.out.println("Loading DataContext...");

		ServletContext ctx = arg0.getServletContext();
		DataContext dctx = new DataContext();		
		String path = ctx.getRealPath("") + "/data/dataContext.ser";
		if (fileExists(path)) {
			try {
				FileInputStream fileIn = new FileInputStream(path);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				dctx = (DataContext) in.readObject();
				in.close();
				fileIn.close();
				System.out.println("Loading done.");
			} catch (Exception ex) {
				System.out.println("Error while loading .ser file, new DataContext will be created.");
				dctx.init();
			}
		} else {
			System.out.println("No .ser file found, new DataContext created.");
			dctx.init();
		}

		ctx.setAttribute("data", dctx);
		
		path = ctx.getRealPath("") + File.separator + "data" + File.separator + "admins.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				User u = new User(tokens[0], tokens[1], tokens[2], tokens[3], ctx.getRealPath("") + tokens[4], tokens[5], tokens[6]);
				u.setRole(AuthRole.ADMIN);
				dctx.getUsers().put(u.getUsername(), u);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	private boolean fileExists(String path) {
		File f = new File(path);
		if (!f.exists() || f.isDirectory())
			return false;
		return true;
	}
	
	
}
