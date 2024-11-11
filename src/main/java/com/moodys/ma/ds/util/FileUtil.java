package com.moodys.ma.ds.util;

import com.moodys.ma.ds.exceptions.FileReadException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtil {



    public static String getFileContentsAsString(String fileName) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(String.format("classpath:%s", fileName));
        try {
            return new String(resource.getInputStream().readAllBytes());
        } catch (IOException e) {
            throw new FileReadException(
                    String.format("Could not read the file %s. Reason : %s", fileName, e.getMessage()), e);
        }
    }

    public static List<String> getContentsAsStringFromDir(String dirName, String fileExtension) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] fileResources = resolver.getResources(dirName+"/*."+fileExtension);
            return Arrays.stream(fileResources).map(resource -> {
                try {
                    return new String(resource.getInputStream().readAllBytes());
                } catch (IOException e) {
                    throw new FileReadException(
                            String.format("Could not read the file %s. Reason : %s", resource.getFilename(), e.getMessage()), e);
                }
            }).toList();
        } catch (IOException e) {
            throw new FileReadException(
                    String.format("Could not read from the directory %s. Reason : %s", dirName, e.getMessage()), e);
        }
    }
}
