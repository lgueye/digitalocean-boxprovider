package org.diveintojee.poc.digitaloceanboxprovider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by <a href="mailto:louis.gueye@domo-safety.com">Louis Gueye</a>.
 */
@Component
public class AnsibleInventoryWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnsibleInventoryWriter.class);

	private String inventoryName;

	@Value("${inventory}")
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	void write(String inventoryAsString) throws IOException {
		// Write content
		String inventoryPath = Paths.get(System.getProperty("user.home"), "inventories", inventoryName).toString();
		Files.deleteIfExists(Paths.get(inventoryPath));
		File file = new File(inventoryPath);
		final boolean parentsCreated = file.getParentFile().mkdirs();
		if (!parentsCreated) {
			LOGGER.debug("Parents for file " + file + " were already created");
		}
		Writer w = new OutputStreamWriter(new FileOutputStream(inventoryPath), "UTF-8");
		w.append(inventoryAsString);
		w.close();
	}
}
