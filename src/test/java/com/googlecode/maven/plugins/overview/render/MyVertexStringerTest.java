package com.googlecode.maven.plugins.overview.render;

import com.googlecode.maven.plugins.overview.vo.ArtifactVertex;
import edu.uci.ics.jung.graph.impl.SimpleDirectedSparseVertex;
import static junit.framework.Assert.assertEquals;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;
import org.apache.maven.artifact.versioning.VersionRange;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * MyVertexStringer tests.
 *
 * @author Hubert Iwaniuk
 * @see com.googlecode.maven.plugins.overview.render.MyVertexStringer
 * @since 1.2
 */
public class MyVertexStringerTest {

  /**
   * getLabel: null parameter.
   */
  @Test public void getLabelNullArtifact() {
    MyVertexStringer stringer = new MyVertexStringer(false, false);
    assertNull(stringer.getLabel(null));
  }

  /**
   * getLabel: not
   * {@link com.googlecode.maven.plugins.overview.vo.ArtifactVertex} parameter.
   */
  @Test public void getLabelNotArtifactVertex() {
    MyVertexStringer stringer = new MyVertexStringer(false, false);
    assertNull(stringer.getLabel(new SimpleDirectedSparseVertex()));
  }

  /**
   * getLabel: short label.
   */
  @Test public void getLabelShort() {
    MyVertexStringer stringer = new MyVertexStringer(false, false);
    assertEquals(
        "a", stringer.getLabel(
        new ArtifactVertex(
            new DefaultArtifact(
                "g", "a", VersionRange.createFromVersion("1.0"), "test", "jar",
                "", new DefaultArtifactHandler()),
            1
        )));
  }

  /**
   * getLabel: long label.
   */
  @Test public void getLabelLong() {
    MyVertexStringer stringer = new MyVertexStringer(true, false);
    assertEquals(
        "g:a:jar:1.0",
        stringer.getLabel(
            new ArtifactVertex(
                new DefaultArtifact(
                    "g", "a", VersionRange.createFromVersion("1.0"), "test",
                    "jar",
                    "", new DefaultArtifactHandler()),
                1
            )));
  }

    /**
     * getLabel: Contains version
     */
    @Test public void getVersionedLabel() {
        MyVertexStringer stringer = new MyVertexStringer(false, true);
        assertEquals(
            "a:1.0",
            stringer.getLabel(
                new ArtifactVertex(
                    new DefaultArtifact(
                        "g", "a", VersionRange.createFromVersion("1.0"), "test",
                        "jar",
                        "", new DefaultArtifactHandler()),
                    1
                )));
    }

    /**
     * getLabel: make sure that if fullLabel and showVersion provided, fullLabel behavior is choosen.
     */
    @Test public void makeSureLongLabelOverridesVersioned() {
        MyVertexStringer stringer = new MyVertexStringer(true, true);
        assertEquals(
            "g:a:jar:1.0",
            stringer.getLabel(
                new ArtifactVertex(
                    new DefaultArtifact(
                        "g", "a", VersionRange.createFromVersion("1.0"), "test",
                        "jar",
                        "", new DefaultArtifactHandler()),
                    1
                )));
    }
}
