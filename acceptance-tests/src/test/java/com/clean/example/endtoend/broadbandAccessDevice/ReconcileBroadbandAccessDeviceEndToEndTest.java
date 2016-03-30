package com.clean.example.endtoend.broadbandAccessDevice;

import com.clean.example.businessrequirements.broadbandAccessDevice.ReconcileBroadbandAccessDeviceAcceptanceTest;
import com.clean.example.endtoend.EndToEndYatspecTest;
import com.googlecode.yatspec.junit.LinkingNote;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("TODO")
@LinkingNote(message = "Business Requirements: %s", links = {ReconcileBroadbandAccessDeviceAcceptanceTest.class})
public class ReconcileBroadbandAccessDeviceEndToEndTest extends EndToEndYatspecTest {

    // fire job, check database for model, check audit (in log file?); snmp is mocked

    @Test
    public void jobAuditsSuccessesAndFailures() throws Exception {

    }

}
