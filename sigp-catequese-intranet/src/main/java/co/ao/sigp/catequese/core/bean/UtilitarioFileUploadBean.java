package co.ao.sigp.catequese.core.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.ao.sigp.catequese.core.util.jsf.JSFUtil;
import lombok.Getter;
import lombok.Setter;

@Controller("utilitarioFileUploadBean")
@Scope("view")
public class UtilitarioFileUploadBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private CroppedImage croppedImage;

	@Getter
	private UploadedFile originalImageFile;

	public void handleFileUpload(FileUploadEvent event) {

		this.originalImageFile = null;

		this.croppedImage = null;

		UploadedFile file = event.getFile();

		if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {

			this.originalImageFile = file;
		}
	}

	public void crop() {
		
		if (this.croppedImage == null || this.croppedImage.getBytes() == null || this.croppedImage.getBytes().length == 0) {
			
			JSFUtil.mensagemErro("Cropping failed.");
		}
	}

	public StreamedContent getImage() {
		
		return DefaultStreamedContent.builder().contentType(originalImageFile == null ? null : originalImageFile.getContentType()).stream(() -> {
					
				if (originalImageFile == null || originalImageFile.getContent() == null || originalImageFile.getContent().length == 0) {
						
					return null;
				}

				try {
						
					return new ByteArrayInputStream(originalImageFile.getContent());
					
				} catch (Exception e) {
						
					e.printStackTrace();
						
					return null;
				}
			}).build();
	}

	public StreamedContent getCropped() {
		
		return DefaultStreamedContent.builder().contentType(originalImageFile == null ? null : originalImageFile.getContentType()).stream(() -> {
					
				if (croppedImage == null || croppedImage.getBytes() == null || croppedImage.getBytes().length == 0) {
						
					return null;
				}

				try {
						
					return new ByteArrayInputStream(this.croppedImage.getBytes());
				
				} catch (Exception e) {
				
					e.printStackTrace();
					
					return null;
				}
			}).build();
	}
}
