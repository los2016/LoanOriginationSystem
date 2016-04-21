package com.myorg.document.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.myorg.document.models.Document;
import com.myorg.document.models.DocumentDao;
import com.myorg.document.models.DocumentPayLoad;
import com.myorg.document.models.LoanDocument;
import com.myorg.document.models.LoanDocumentDao;
import com.myorg.document.models.LoanDocumentPK;

@Component
@Path("/document")
public class DocumentService {

	@Autowired
	private Environment env;

	@Autowired
	private DocumentDao _documentDao;
	
	@Autowired
	private LoanDocumentDao _loanDocumentDao;

	@GET
	@Path("/documentlist")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocumentList() {
		GenericEntity<List<Document>> entity;
		List<Document> documents = _documentDao.getAll();
		System.out.println("List of documents :" + documents);
		entity = new GenericEntity<List<Document>>(documents) {};
		return Response.ok(entity).build();
	}
	
	@GET
	@Path("/retrieveDocumentList/{mortgageApplicationID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocumentList(@PathVariable("mortgageApplicationID") long mortgageApplicationID) {
		GenericEntity<List<DocumentPayLoad>> entity;
		List<LoanDocument> documents = _loanDocumentDao.getByLoanId(mortgageApplicationID);
		System.out.println("For loanId ["+mortgageApplicationID+"] List of documents by loan Id:" + documents);
		List<DocumentPayLoad> uploadedDocuments = new ArrayList<DocumentPayLoad>();
		if (documents != null && documents.size() > 0) {
			for (Iterator<LoanDocument> iterator = documents.iterator(); iterator.hasNext();) {
				LoanDocument document = (LoanDocument) iterator.next();
				DocumentPayLoad payLoad = new DocumentPayLoad();
				payLoad.setMortgageApplicationID(document.getLoanDocumentComposite().getMortgageApplicationID());
				payLoad.setDocumentTypeId(document.getLoanDocumentComposite().getDocumentTypeId());
				payLoad.setDocumentDescription(document.getDocumentMetadata().getDocumentDescription());
				payLoad.setDocumentDownloadLink("/downloadDocument/"+mortgageApplicationID+"/"+document.getLoanDocumentComposite().getDocumentTypeId());
			}
		}
		entity = new GenericEntity<List<DocumentPayLoad>>(uploadedDocuments) {};
		return Response.ok(entity).build();
	}
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("documentPayload") InputStream uploadedInputStream,
			@FormDataParam("documentPayload") FormDataContentDisposition fileDetail,
			@FormDataParam("mortgageApplicationID") Long mortgageApplicationID,
			@FormDataParam("documentTypeId") Long documentTypeId) throws Throwable {
		
		System.out.println("mortgageApplicationID="+mortgageApplicationID+", documentTypeId="+documentTypeId);
		LoanDocumentPK loanDocumentComposite = new LoanDocumentPK();
		loanDocumentComposite.setMortgageApplicationID(mortgageApplicationID);
		loanDocumentComposite.setDocumentTypeId(documentTypeId);
		loanDocumentComposite.setSequenceNumber(1);
		
		LoanDocument loanDocument = new LoanDocument();
		loanDocument.setLoanDocumentComposite(loanDocumentComposite);
		loanDocument.setDocumentPayload(IOUtils.toByteArray(uploadedInputStream));
		_loanDocumentDao.saveDocument(loanDocument);
		
		// Get the filename and build the local file path
		String filename = fileDetail.getFileName();
		String directory = env.getProperty("mortgage.paths.uploadedFiles");
		String filepath = Paths.get(directory, filename).toString();

		// save it
		upload(uploadedInputStream, filepath);
		String output = "File uploaded to : " + filepath;
		return Response.status(200).entity(output).build();
	}
	
	/*@GET
	@Path("/downloadDocument/{mortgageApplicationID}/{documentTypeId}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadDocument(@PathVariable("mortgageApplicationID") final long mortgageApplicationID, final @PathVariable("documentTypeId") long documentTypeId) {
		 StreamingOutput stream =  new StreamingOutput() {
			@Override
			public void write(OutputStream arg0) throws IOException{
				BufferedOutputStream bus = new BufferedOutputStream(arg0);
				try {
					LoanDocument loanDocument = _loanDocumentDao.getDocument(mortgageApplicationID, documentTypeId, 1);
					byte[] bufferPayLoad = loanDocument.getDocumentPayload();
					bus.write(bufferPayLoad);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		return Response.ok(stream).build();
	}*/
	

	// save uploaded file to new location
	private void upload(InputStream uploadedInputStream, String uploadedFileLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}