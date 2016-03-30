package com.clean.example.businessrequirements.broadbandAccessDevice;

import com.clean.example.businessrequirements.YatspecTest;
import com.clean.example.endtoend.broadbandAccessDevice.ReconcileBroadbandAccessDeviceEndToEndTest;
import com.googlecode.yatspec.junit.LinkingNote;
import com.googlecode.yatspec.junit.Notes;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("TODO")
@Notes("Reconciles information about Broadband Access Devices between what is stored in our model and the data that comes back from the actual device")
@LinkingNote(message = "End to End test: %s", links = {ReconcileBroadbandAccessDeviceEndToEndTest.class})
public class ReconcileBroadbandAccessDeviceAcceptanceTest extends YatspecTest {

    @Test
    public void nothingToReconcileWhenModelAndRealityAreTheSame() throws Exception {

    }

    @Test
    public void updatesModelWhenRealityHasChanged() throws Exception {

    }

    @Test
    public void recordsAnErrorWhenRealityInformationIsInvalid() throws Exception {

    }

    @Test
    public void recordsAnErrorWhenTheCommunicationWithTheDeviceHasProblems() throws Exception {

    }

}
