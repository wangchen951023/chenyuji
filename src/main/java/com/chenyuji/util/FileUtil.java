package com.chenyuji.util;

import java.io.File;

public class FileUtil {
    public static void makeDir(File dir) {
        if (!dir.exists()) {
            makeDir(dir.getParentFile());
            dir.mkdir();
        }
    }
}
