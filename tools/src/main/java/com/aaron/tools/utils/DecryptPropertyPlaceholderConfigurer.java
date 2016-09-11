package com.aaron.tools.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.*;
import java.util.Properties;

/**
 * 读取配置文件属性相关操作
 * 
 * @author aaron.qiu
 * @since 2015-2016
 */
public class DecryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	private Resource[] locations;
	private String fileEncoding;
	private String linuxSystePath;
	private String windowsSystemPath;
	private Properties props;

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	public void setLocations(Resource[] locations) {
		this.locations = locations;
	}

	public void setLinuxSystePath(String linuxSystePath) {
		this.linuxSystePath = linuxSystePath;
	}

	public void setWindowsSystemPath(String windowsSystemPath) {
		this.windowsSystemPath = windowsSystemPath;
	}

	public void loadProperties(Properties props) throws IOException {
		if (this.locations != null) {
			PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
			for (int i = 0; i < this.locations.length; i++) {
				InputStream is = null;
				Resource location = this.locations[i];
				File configDir = new File(this.linuxSystePath);
				if (!configDir.exists()) {
					is = getClass().getResourceAsStream(
							this.windowsSystemPath + "/"
									+ location.getFilename());
				} else {
					String filePath = this.linuxSystePath + File.separator
							+ location.getFilename();

					is = new FileInputStream(filePath);
				}
				try {
					if (this.fileEncoding != null) {
						propertiesPersister.load(props, new InputStreamReader(
								is, this.fileEncoding));
					} else {
						propertiesPersister.load(props, is);
					}
					this.props = props;
				} finally {
					if (is != null)
						is.close();
				}
			}
		}
	}

	public String getValue(String key) {
		return this.props.getProperty(key);
	}
}
