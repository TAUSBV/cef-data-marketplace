package net.taus.oss.gradle.querydsl;

import org.flywaydb.core.api.ResourceProvider;
import org.flywaydb.core.api.resource.LoadableResource;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SkippableMigrationResolver implements ResourceProvider {

  private final String skipPattern;
  private final String migrationsDir;
  private Map<String, Resource> resources;

  public SkippableMigrationResolver(final String skipPattern, final String migrationsDir) {
    this.skipPattern = skipPattern;
    this.migrationsDir = migrationsDir;
    resources = new HashMap<>();
  }

  @Override
  public LoadableResource getResource(final String name) {
    return resources.get(name);
  }

  @Override
  public Collection<LoadableResource> getResources(final String prefix, final String[] suffixes) {
    Collection<LoadableResource> resourcesList = new ArrayList<>();
    try {
      final DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get(migrationsDir),
        entry -> entry.toFile().isFile()
          && !entry.getFileName().toString().contains(skipPattern)
//          && (!"".isBlank() && entry.startsWith("") || true)
          && Arrays.stream(suffixes).anyMatch(s -> entry.getFileName().toString().endsWith(s))
      );
      resources = StreamSupport.stream(paths.spliterator(), false).collect(Collectors.toMap(p -> p.getFileName().toString(), Resource::new));
      resourcesList.addAll(resources.values());
    }
    catch (IOException ignored) {
    }
    return resourcesList;
  }

  public static class Resource extends LoadableResource {

    private final Path path;

    public Resource(final Path path) {
      this.path = path;
    }

    @Override
    public Reader read() {
      try {
        return Files.newBufferedReader(this.path);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public String getAbsolutePath() {
      return this.path.toAbsolutePath().toString();
    }

    @Override
    public String getAbsolutePathOnDisk() {
      return this.path.toAbsolutePath().toString();
    }

    @Override
    public String getFilename() {
      return this.path.getFileName().toString();
    }

    @Override
    public String getRelativePath() {
      return this.path.toString();
    }
  }
}
