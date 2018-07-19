package com.labwinner.vo;

import java.util.List;

import com.labwinner.domain.ChemicalParameter;

public class UserChemicalVo {
	
	private ChemicalParameter chemicalParameter;
	
	private List<String> molecularImgs;
	
	private List<String> representationMapImgs;
	
	private List<String> molecularImgUrls;
	
	private List<String> representationMapImgUrls;
	
	public UserChemicalVo(){}

	public ChemicalParameter getChemicalParameter() {
		return chemicalParameter;
	}

	public void setChemicalParameter(ChemicalParameter chemicalParameter) {
		this.chemicalParameter = chemicalParameter;
	}

	public List<String> getMolecularImgs() {
		return molecularImgs;
	}

	public void setMolecularImgs(List<String> molecularImgs) {
		this.molecularImgs = molecularImgs;
	}

	public List<String> getRepresentationMapImgs() {
		return representationMapImgs;
	}

	public void setRepresentationMapImgs(List<String> representationMapImgs) {
		this.representationMapImgs = representationMapImgs;
	}

	public List<String> getMolecularImgUrls() {
		return molecularImgUrls;
	}

	public void setMolecularImgUrls(List<String> molecularImgUrls) {
		this.molecularImgUrls = molecularImgUrls;
	}

	public List<String> getRepresentationMapImgUrls() {
		return representationMapImgUrls;
	}

	public void setRepresentationMapImgUrls(List<String> representationMapImgUrls) {
		this.representationMapImgUrls = representationMapImgUrls;
	}
	
}
