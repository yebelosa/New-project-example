package com.clean.example.endtoend.exchange.capacity;

import com.clean.example.businessrequirements.exchange.getcapacity.GetCapacityForExchangeAcceptanceTest;
import com.clean.example.endtoend.EndToEndYatspecTest;
import com.googlecode.yatspec.junit.LinkingNote;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;

@Ignore("TODO enable when production code is ready")
@LinkingNote(message = "Business Requirements: %s", links = {GetCapacityForExchangeAcceptanceTest.class})
public class GetCapacityForExchangeEndToEndTest extends EndToEndYatspecTest {

    @Test
    public void name() throws Exception {
        fail("TODO");
    }
}
