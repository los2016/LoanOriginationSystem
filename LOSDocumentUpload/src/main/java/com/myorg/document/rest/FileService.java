package com.myorg.document.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.myorg.document.models.Document;
import com.myorg.document.models.DocumentDao;

@Component
@Path("/file")
public class FileService {

	@Autowired
	private Environment env;
	
	@Autowired
	private DocumentDao _documentDao;

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition uploadfile) {
		// Get the filename and build the local file path
		String filename = uploadfile.getFileName();
		String directory = env.getProperty("mortgage.paths.uploadedFiles");
		String filepath = Paths.get(directory, filename).toString();

		// save it
		upload(uploadedInputStream, filepath);
		String output = "File uploaded to : " + filepath;
		return Response.status(200).entity(output).build();
	}

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
	
	@GET
	@Path("/documentlist")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocumentList() {
		GenericEntity< List< Document > > entity;
		List<Document> documents = _documentDao.getAll();
		System.out.println("List of documents :"+documents);
		entity  = new GenericEntity< List< Document > >( documents ) { };
		return Response.ok(entity).build();
	}
	
	
}