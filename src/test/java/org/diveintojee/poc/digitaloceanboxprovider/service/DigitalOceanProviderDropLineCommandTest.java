package org.diveintojee.poc.digitaloceanboxprovider.service;

import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * @author louis.gueye@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class DigitalOceanProviderDropLineCommandTest {

    @Mock
    private DigitalOceanClient digitalOceanClient;
    @InjectMocks
    private DigitalOceanProviderDropLineCommand underTest;

    @Test
    public void run() throws Exception {
        // Given
        final String inventoryName = "vintagezerodowntime";
        underTest.setInventoryName(inventoryName);

        // When
        underTest.run();

        // Then
        verify(digitalOceanClient).deleteDropletByTagName(inventoryName);
    }

}
