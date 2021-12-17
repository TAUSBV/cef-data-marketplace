package net.taus.oss.gradle.querydsl;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class QuerydslPluginTest {
  @Test
  public void pluginRegistersATask() {
    Project project = ProjectBuilder.builder().build();
    project.getPlugins().apply("net.taus.oss.querydsl");
    assertNotNull(project.getTasks().findByName("querydslSql"));
  }
}
