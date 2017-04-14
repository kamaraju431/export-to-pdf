package com.aizant.model;

import java.io.Serializable;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

public class DoubleArrayType implements UserType {

	private final int[] arrayTypes = new int[] { Types.ARRAY };

	public int[] sqlTypes() {
		return arrayTypes;
	}

	public Class<Double[]> returnedClass() {
		return Double[].class;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return x == null ? y == null : x.equals(y);
	}

	public int hashCode(Object x) throws HibernateException {
		return x == null ? 0 : x.hashCode();
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value == null ? null : ((Double[]) value).clone();
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		// get the first column names
		if (names != null && names.length > 0 && rs != null && rs.getArray(names[0]) != null) {
			Double[] results = (Double[]) rs.getArray(names[0]).getArray();
			return results;
		}
		return null;
	}

	// @Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		// setting the column with string array
//		oracle.jdbc.OracleDriver ora = new oracle.jdbc.OracleDriver();
//		java.sql.Connection conn = ora.defaultConnection();
//		OracleConnection oraConn = conn.unwrap(OracleConnection.class);
//		oracle.sql.ARRAY widgets = oraConn.createARRAY("widgets_t", elements);
		
		if (value != null && st != null) {
			Double[] castObject = (Double[]) value;
			Array array = session.connection().createArrayOf("double", castObject);
			st.setArray(index, array);
		} else {
			st.setNull(index, arrayTypes[0]);
		}
		
	}
}
