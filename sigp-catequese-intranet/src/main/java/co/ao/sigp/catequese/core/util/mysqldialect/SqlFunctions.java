package co.ao.sigp.catequese.core.util.mysqldialect;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.type.StandardBasicTypes;

public class SqlFunctions implements MetadataBuilderContributor {

	@Override
	public void contribute(MetadataBuilder metadataBuilder) {
		
		metadataBuilder.applySqlFunction("group_concat", new CustomStandardSQLFunction("group_concat", StandardBasicTypes.STRING));
	}
}