package iopiotrsukiennik.whowhen.shared.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Main
 */
public abstract class FileUtil {

    public static final FilenameFilter CSV_FILE_FILTER = new FilenameFilter() {
        public boolean accept(File dir, String name) {
            return name.endsWith(".csv");
        }
    };

    public static final FileFilter DIRECTORY_FILTER = new FileFilter() {
        public boolean accept(File pathname) {
            return pathname.isDirectory();
        }
    };



    public static FileFilter getFileFilterForExtensions(String extension, String... extensions){
      final StringBuilder regex = new StringBuilder("(.+)").append(extension);
       for (String ex: extensions){
           regex.append("|").append(ex);
       }
       // regex.append(")");
       System.out.println("REGEX = "+regex.toString());
        return new FileFilter() {
            private Pattern pattern = Pattern.compile(regex.toString(),Pattern.DOTALL);
            public boolean accept(File pathname) {
                return pattern.matcher(pathname.getName()).matches();
            }
        };
    }

    public static List<File> recursivelyListFiles(File rootDir,FileFilter fileFilter){
        List<File> files = new ArrayList<File>();
        if (rootDir!=null && rootDir.exists()){
            if (rootDir.isFile()&&fileFilter.accept(rootDir)){
                files.add(rootDir);
                return files;
            }   else if (rootDir.isDirectory()){
                for (File file: rootDir.listFiles()){
                    files.addAll(recursivelyListFiles(file,fileFilter));
                }
            }
        }
        return files;
    }

    public  static void removeDirectory(File dir) {
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                removeDirectory(f);
            } else {
                f.delete();
            }
        }
        dir.delete();
    }

    public static void copyFile(File source, File dest) throws IOException {

        if (!dest.exists()) {
            dest.mkdirs();
            dest.createNewFile();
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(source);
            out = new FileOutputStream(dest);

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } finally {
            in.close();
            out.flush();
            out.close();
        }

    }

    public static void copyDirectory(File sourceDir, File destDir) throws IOException {

        if (!destDir.exists()) {
            destDir.mkdir();
        }

        File[] children = sourceDir.listFiles();

        for (File sourceChild : children) {
            String name = sourceChild.getName();
            File destChild = new File(destDir, name);
            if (sourceChild.isDirectory()) {
                copyDirectory(sourceChild, destChild);
            } else {
                copyFile(sourceChild, destChild);
            }
        }
    }

}
