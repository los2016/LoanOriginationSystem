package com.myorg.document.upload;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.myorg.document.config.BoxConnection;

@Component
public class BoxUpload {

	@Autowired
	private BoxConnection _boxConnection;
	
	public void upload(long mortgageApplicationID, byte[] documentStream, String documentName) {
		BoxAPIConnection apiConnection = _boxConnection.getAPIConnectionByDeveloperToken();
		BoxFolder mortgageFolder = BoxConnection.getFolder(BoxFolder.getRootFolder(apiConnection), BoxConnection.BASE_MAX_DEPTH, _boxConnection.getBoxMortgageBasePath());
		System.out.println("mortgageFolder info="+mortgageFolder);
		if (mortgageFolder  != null) {
			BoxFolder loanFolder = BoxConnection.getFolder(mortgageFolder , BoxConnection.BASE_MAX_DEPTH, mortgageApplicationID+"");
			if(loanFolder == null) {
				loanFolder = mortgageFolder.createFolder(mortgageApplicationID+"").getResource();
			}
			BoxFile.Info newFileInfo = loanFolder.uploadFile(new ByteArrayInputStream(documentStream), documentName);
			System.out.println("Uploaded file :"+newFileInfo.getName()+", against loanId:"+loanFolder.getInfo().getName());
		}
	}
}
