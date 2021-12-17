package net.taus.oss.gradle.querydsl;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.TaskProvider;

import java.nio.file.Path;
import java.nio.file.Paths;

public class QuerydslPlugin implements Plugin<Project> {

  public void apply(Project project) {
    project.getExtensions().create("querydsl", QuerydslPluginExtension.class);
    final TaskProvider<QuerydslSQLTask> querydslSql = project.getTasks().register("querydslSql", QuerydslSQLTask.class);
    if (project.getPluginManager().hasPlugin("java")) {
      // configure source sets
      JavaPluginConvention plugin = project.getConvention().getPlugin(JavaPluginConvention.class);
      final Path path = Paths.get(project.getBuildDir().getAbsolutePath(), "generated", "sources", "querydsl", "java", "main");
      final ConfigurableFileCollection files = project.files(path);
      final SourceSet main = plugin.getSourceSets().getByName("main");
      final SourceSet querydsl = plugin.getSourceSets().create("querydsl");
      querydsl.getJava().setSrcDirs(files);
      querydsl.getJava().setOutputDir(main.getJava().getOutputDir());
      querydsl.setCompileClasspath(querydsl.getCompileClasspath().plus(main.getCompileClasspath()));
      querydsl.setRuntimeClasspath(querydsl.getRuntimeClasspath().plus(main.getRuntimeClasspath()));
      main.setCompileClasspath(main.getCompileClasspath().plus(querydsl.getOutput()));
      main.setRuntimeClasspath(main.getRuntimeClasspath().plus(querydsl.getOutput()));
      project.getTasks().getByName("compileQuerydslJava").dependsOn("querydslSql");
    }
  }
}
