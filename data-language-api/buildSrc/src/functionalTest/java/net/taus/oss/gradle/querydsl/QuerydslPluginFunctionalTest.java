package net.taus.oss.gradle.querydsl;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;

public class QuerydslPluginFunctionalTest {
  @Test
  public void canRunTask() throws IOException {
//    File projectDir = new File("build/functionalTest");
//    Files.createDirectories(projectDir.toPath());
//    writeString(new File(projectDir, "settings.gradle"), "");
//    writeString(new File(projectDir, "build.gradle"),
//      "plugins {" +
//        "  id('net.taus.oss.querydsl')" +
//        "}");
//
//    // Run the build
//    GradleRunner runner = GradleRunner.create();
//    runner.forwardOutput();
//    runner.withPluginClasspath();
//    runner.withArguments("querydslSql", "--warning-mode", "all");
//    runner.withProjectDir(projectDir);
//    BuildResult result = runner.build();
//
//    // Verify the result
//    System.out.println(result.getOutput());
//    assertTrue(result.getOutput().contains("Hi there"));
  }

  private void writeString(File file, String string) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      writer.write(string);
    }
  }
}
