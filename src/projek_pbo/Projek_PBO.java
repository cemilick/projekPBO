/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek_pbo;

import com.controllers.UserController;
import com.models.DBConectivity;
import com.models.User;
import com.views.ViewHome;
import com.views.admin.ViewRead;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author cemil
 */
public class Projek_PBO {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        DBConectivity DB = new DBConectivity();
        JFrame viewhome = new ViewHome(DB);
        viewhome.setVisible(true);
        
        
    }
    
}
