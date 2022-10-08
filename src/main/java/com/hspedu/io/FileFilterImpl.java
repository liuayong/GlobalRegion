package com.hspedu.io;

import java.io.File;
import java.io.FileFilter;

public class FileFilterImpl implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        //System.err.println(pathname);
        //return false;
        if(pathname.isDirectory()) {
            return true;
        }
        
        return pathname.getName().toLowerCase().endsWith(".java");
    }
}
