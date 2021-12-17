package net.taus.oss.gradle.querydsl;

import com.querydsl.sql.codegen.MetaDataExporter;
import org.flywaydb.core.Flyway;
import org.gradle.api.DefaultTask;
import org.gradle.api.artifacts.ResolvedArtifact;
import org.gradle.api.artifacts.ResolvedConfiguration;
import org.gradle.api.file.FileCollection;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import javax.inject.Inject;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class QuerydslSQLTask extends DefaultTask {

//  private QuerydslPluginExtension extension;

  @Inject
  public QuerydslSQLTask() {
  }

//  public QuerydslPluginExtension getExtension() {
//    if (Objects.isNull(extension)) {
//      extension = getProject().getExtensions().getByType(QuerydslPluginExtension.class);
//    }
//    return extension;
//  }


  // FIXME move to a extension
  @OutputDirectory
  public File getOutput() {
    return Paths.get(getProject().getBuildDir().getAbsolutePath(), "generated/sources/querydsl/java/main").toFile();
  }

  // FIXME move to a extension
  @InputDirectory
  public File getInput() {
    return Paths.get(getProject().getProjectDir().getAbsolutePath() , "src/main/resources/db/migration").toFile();
  }

  @TaskAction
  public void run() throws SQLException, MalformedURLException {
    getLogger().info("Starting MySQL");
    final QuerydslPluginExtension extension = getProject().getExtensions().getByType(QuerydslPluginExtension.class);
    final MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql/mysql-server:8.0.23").asCompatibleSubstituteFor("mysql"));
    mysql.setTmpFsMapping(Map.of("/var/lib/mysql", "rw"));
    Startables.deepStart(Stream.of(mysql)).join();
    getLogger().info("Started MySQL");
    final Connection connection = DriverManager.getConnection(mysql.getJdbcUrl(), mysql.getUsername(), mysql.getPassword());
    getLogger().info("Migrations dir {}", getInput());
    ClassLoader classLoader = getClassLoader();
    getLogger().info("Migrating");
    var flyway = Flyway.configure(classLoader)
//      .locations(String.format("filesystem:%s", migrationsDir), "classpath:/db/migration")
      .dataSource(mysql.getJdbcUrl(), mysql.getUsername(), mysql.getPassword())
      .resourceProvider(new SkippableMigrationResolver("populate", getInput().getAbsolutePath()))
//      .skipDefaultResolvers(true)
      .load();
    getLogger().info("Migrations info {}", flyway.info());
    flyway.migrate();
    getLogger().info("Migrated");
    MetaDataExporter exporter = new MetaDataExporter();
    exporter.setPackageName("net.taus.data.language.model.sql");
    getLogger().info("Saving exported classes to: {}", getOutput().getAbsolutePath());
    exporter.setTargetFolder(getOutput());
    exporter.setNamePrefix("QSQL");
    getLogger().info("Exporting classes");
    exporter.export(connection.getMetaData());
    getLogger().info("Exported classes");
    getLogger().info("Stopping MySQL");
    mysql.stop();
    getLogger().info("Stopped MySQL");
  }

  private ClassLoader getClassLoader() throws MalformedURLException {
    Set<URL> extraURLs = new HashSet<>();
    JavaPluginConvention plugin = getProject().getConvention().getPlugin(JavaPluginConvention.class);
    for (SourceSet sourceSet : plugin.getSourceSets()) {
      FileCollection classesDirs = sourceSet.getOutput().getClassesDirs();
      for (File directory : classesDirs.getFiles()) {
        URL classesUrl = directory.toURI().toURL();
        getLogger().info("Adding directory to Classpath: {}", classesUrl);
        extraURLs.add(classesUrl);
      }
      URL resourcesUrl = sourceSet.getOutput().getResourcesDir().toURI().toURL();
      getLogger().debug("Adding directory to Classpath: " + resourcesUrl);
      extraURLs.add(resourcesUrl);
    }
    ResolvedConfiguration resolvedConfiguration = getProject().getConfigurations().getByName("compileClasspath").getResolvedConfiguration();
    for (ResolvedArtifact artifact : resolvedConfiguration.getResolvedArtifacts()) {
      URL artifactUrl = artifact.getFile().toURI().toURL();
      if (!artifact.getFile().getAbsolutePath().contains("flyway")) {
//      getLogger().info("Adding artifact to classpath: " + artifactUrl);
        extraURLs.add(artifactUrl);
      }
    }
    return new URLClassLoader(extraURLs.toArray(new URL[0]), getProject().getBuildscript().getClassLoader());
  }
}

