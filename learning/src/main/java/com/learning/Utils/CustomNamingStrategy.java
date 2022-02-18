package com.learning.Utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl {
	private final static String POSTFIX = "_table";
	// by default all tables should be ended with name _table;
	
	
	@Override
	public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment context) {
		// TODO Auto-generated method stub
		if(identifier == null) {
			return null;
		}
		final String newName = identifier.getText()+POSTFIX;
		
		return identifier.toIdentifier(newName);
	}
	@Override
	public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment context) {
		// TODO Auto-generated method stub
		if(identifier==null) {
			return null;
		}
		return identifier.toIdentifier(identifier.getText().toLowerCase());
	}
}
