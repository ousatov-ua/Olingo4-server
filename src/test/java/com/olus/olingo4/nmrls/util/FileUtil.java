package com.olus.olingo4.nmrls.util;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    private FileUtil() {

        // Empty
    }

    /**
     * Load content for specified file path
     *
     * @param filePath file path
     * @return content
     * @throws IOException exception
     */
    public static String getFileContent(String filePath) throws IOException {
        return Resources.toString(Resources.getResource(filePath), StandardCharsets.UTF_8).trim();
    }
}
