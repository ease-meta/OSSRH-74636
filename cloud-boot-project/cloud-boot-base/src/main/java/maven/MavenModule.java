package maven;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Leijian
 * @date 2020/3/29
 */
public class MavenModule {

	public static void main(String[] args) throws IOException, XmlPullParserException {
		FileInputStream fis = new FileInputStream(new File("D:\\IdeaProjects\\open-cloud-platform\\cloud-base-java\\pom.xml"));
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader.read(fis);
	}
}
