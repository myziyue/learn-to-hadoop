//package com.myziyue.hadoop.ch03.ex05;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.OutputStream;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileStatus;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hdfs.MiniDFSCluster;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static com.sun.javafx.fxml.expression.Expression.lessThanOrEqualTo;
//import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
//import static org.junit.Assert.assertThat;
//
//public class ShowFileStatusTest {
//    private MiniDFSCluster cluster; // use an in-process HDFS cluster for testing
//    private FileSystem fs;
//
//    @Before
//    public void setUp() throws IOException {
//        Configuration conf = new Configuration();
//        if(null == System.getProperties("test.build.data")) {
//            System.setProperties("test.build.data", "/tmp");
//        }
//
//        cluster = new MiniDFSCluster(conf, 1, true, null);
//        fs = cluster.getFileSystem();
//        OutputStream out = fs.create(new Path("/dir/file"));
//        out.write("content" . getBytes("UTF-8"));
//        out.close();
//    }
//
//    @After
//    public void tearDown() throws IOException {
//        if (fs != null){
//            fs.close();
//        }
//
//        if(cluster != null){
//            cluster.shutdown();
//        }
//    }
//
//    @Test(expected = FileNotFoundException.class)
//    public void throwsFileNoteFoundForNonExistentFile() throws IOException {
//        fs.getFileStatus(new Path("no-such-file"));
//    }
//
//    @Test
//    public void fileStatusForFile() throws IOException {
//        Path file = new Path("/dir/file");
//        FileStatus stat = fs.getFileStatus(file);
//        assertThat(stat.getPath().toUri().getPath(), is("/dir/file"));
//        assertThat(stat.isDirectory(), is(false));
//        assertThat(stat.getLen(), is(7L));
//        assertThat(stat.getModificationTime(), is(lessThanOrEqualTo(System.currentTimeMillis())));
//        assertThat(stat.getReplication(), is((short) 1));
//        assertThat(stat.getBlockSize(), is(64 * 1024 * 1024L));
//        assertThat(stat.getOwner(), is("hadoop"));
//        assertThat(stat.getGroup(), is("supergroup"));
//        assertThat(stat.getPermission().toString(), is("rw-r--r--"));
//    }
//
//    @Test
//    public void fileStatusForDirectory() throws IOException {
//        Path file = new Path("/dir");
//        FileStatus stat = fs.getFileStatus(file);
//        assertThat(stat.getPath().toUri().getPath(), is("/dir"));
//        assertThat(stat.isDirectory(), is(true));
//        assertThat(stat.getLen(), is(0L));
//        assertThat(stat.getModificationTime(), is(lessThanOrEqualTo(System.currentTimeMillis())));
//        assertThat(stat.getReplication(), is((short) 0 ));
//        assertThat(stat.getBlockSize(), is(0L));
//        assertThat(stat.getOwner(), is("hadoop"));
//        assertThat(stat.getGroup(), is("supergroup"));
//        assertThat(stat.getPermission().toString(), is("rwxr-xr-x"));
//    }
//}
