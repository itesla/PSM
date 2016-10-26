package org.power_systems_modelica.psm.dymola.integration.proxy.service.utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class Utils {

    public static void deleteDirectoryRecursively(Path aPath) throws IOException {
        Files.walkFileTree(aPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
    
    /**
     * Transpose a 2D array
     * @return
     */
    public static UnaryOperator<double[][]> transpose() {
        return m -> {
            return IntStream.range(0, m[0].length).mapToObj(r ->
            IntStream.range(0, m.length).mapToDouble(c -> m[c][r]).toArray()
            ).toArray(double[][]::new);
        };
    }
}
