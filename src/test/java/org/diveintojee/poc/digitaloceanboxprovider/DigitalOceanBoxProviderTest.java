package org.diveintojee.poc.digitaloceanboxprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import org.diveintojee.poc.digitaloceanboxprovider.domain.Intent;
import org.diveintojee.poc.digitaloceanboxprovider.domain.Request;
import org.diveintojee.poc.digitaloceanboxprovider.service.DigitalOceanProviderCreateLineCommand;
import org.diveintojee.poc.digitaloceanboxprovider.service.DigitalOceanProviderDropLineCommand;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * @author louis.gueye@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class DigitalOceanBoxProviderTest {

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private DigitalOceanProviderCreateLineCommand digitalOceanProviderCreateLineCommand;
    @Mock
    private DigitalOceanProviderDropLineCommand digitalOceanProviderDropLineCommand;
    @InjectMocks
    private DigitalOceanBoxProvider underTest;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void runShouldCreateBoxes() throws Exception {
        // Given
        String requestAsString = "{bla}";
        underTest.setRequestAsString(requestAsString);
        Request request = mock(Request.class);
        when(request.getProvider()).thenReturn(SupportedProvider.digitalocean);
        when(request.getIntent()).thenReturn(Intent.create);
        when(objectMapper.readValue(requestAsString, Request.class)).thenReturn(request);

        // When
        underTest.run();

        // Then
        verify(digitalOceanProviderCreateLineCommand).setRequest(request);
        verify(digitalOceanProviderCreateLineCommand).run();

    }

    @Test
    public void runShouldDropBoxes() throws Exception {
        // Given
        String requestAsString = "{bla}";
        underTest.setRequestAsString(requestAsString);
        Request request = mock(Request.class);
        when(request.getProvider()).thenReturn(SupportedProvider.digitalocean);
        when(request.getIntent()).thenReturn(Intent.drop);
        when(objectMapper.readValue(requestAsString, Request.class)).thenReturn(request);

        // When
        underTest.run();

        // Then
        verify(digitalOceanProviderDropLineCommand).run();

    }

    @Test
    public void runShouldThrowUnsupportedOperationExceptionWithUnsupportedProvider() throws IOException, RequestUnsuccessfulException, DigitalOceanException {
        // Given
        String requestAsString = "{bla}";
        underTest.setRequestAsString(requestAsString);
        Request request = mock(Request.class);
        final SupportedProvider provider = SupportedProvider.unsupported;
        when(request.getProvider()).thenReturn(provider);
        final Intent intent = Intent.create;
        when(request.getIntent()).thenReturn(intent);
        when(objectMapper.readValue(requestAsString, Request.class)).thenReturn(request);
        exception.expect(UnsupportedOperationException.class);
        exception.expectMessage("Supported providers are " + Lists.newArrayList(SupportedProvider.values()) + ", got " + provider);
        // When
        underTest.run();
    }

    @Test
    public void runShouldThrowUnsupportedOperationExceptionWithUnsupportedIntent() throws IOException, RequestUnsuccessfulException, DigitalOceanException {
        // Given
        String requestAsString = "{bla}";
        underTest.setRequestAsString(requestAsString);
        Request request = mock(Request.class);
        final SupportedProvider provider = SupportedProvider.digitalocean;
        when(request.getProvider()).thenReturn(provider);
        final Intent intent = Intent.unsupported;
        when(request.getIntent()).thenReturn(intent);
        when(objectMapper.readValue(requestAsString, Request.class)).thenReturn(request);
        exception.expect(UnsupportedOperationException.class);
        exception.expectMessage("Supported operations are " + Lists.newArrayList(Intent.values()) + ", got " + intent);
        // When
        underTest.run();
    }

}
