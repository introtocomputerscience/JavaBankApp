/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Carl
 */
class BofFilter extends FileFilter {

    public BofFilter() {
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory() || f.getName().toLowerCase().endsWith(".bof")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Bank Object File";
    }

}
