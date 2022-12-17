package com.billing_soft.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.billing_soft.dtos.Cataloguedto;
import com.billing_soft.entities.Catalogue;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {
	
	Catalogue cataloguedtotoentity(Cataloguedto cataloguedto);
	Cataloguedto cataloguedentitytodto(Catalogue catalogue);
	
	
	List<Cataloguedto> cataloguedtolist(List<Catalogue> product);

}
