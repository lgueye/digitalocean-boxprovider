package org.diveintojee.poc.digitaloceanboxprovider.service;

import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author louis.gueye@gmail.com
 */
@Component
public class DigitalOceanProviderDropLineCommand {

    private DigitalOceanClient digitalOceanClient;
    private String inventoryName;

    @Autowired
    public void setDigitalOceanClient(DigitalOceanClient digitalOceanClient) {
        this.digitalOceanClient = digitalOceanClient;
    }

    @Value("${inventory}")
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public void run() throws RequestUnsuccessfulException, DigitalOceanException {
        digitalOceanClient.deleteDropletByTagName(inventoryName);
    }
}
