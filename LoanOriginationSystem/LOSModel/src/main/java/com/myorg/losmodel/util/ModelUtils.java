package com.myorg.losmodel.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.myorg.losmodel.model.DBEntityMapping;
import com.myorg.losmodel.model.ValidateMesg;
import com.myorg.losmodel.model.client.MortgageApplication;

public class ModelUtils {

	private static List<Integer> enableqnsIdList = new ArrayList<Integer>();
	private static List<Integer> disableqnsIdList = new ArrayList<Integer>();

	private static List<ValidateMesg> validationMesgList = new ArrayList<ValidateMesg>();

	private static List<String> qnsIdList = new ArrayList<String>();
	private static Map<String, String> dbAttributeToObjectNamesMap;

	public static void addNewQuestionId(String qnsId) {

		// if(!StringUtils.isEmpty(qnsId)) {
		if (qnsId != null) {

			qnsIdList.add(qnsId);
		}

	}

	public static void addQnsIdToEnableList(Integer qnsId) {

		// if(!StringUtils.isEmpty(qnsId)) {
		if (enableqnsIdList != null) {

			enableqnsIdList.add(qnsId);
		}

	}

	public static void addQnsIdToDisableList(Integer qnsId) {

		// if(!StringUtils.isEmpty(qnsId)) {
		if (disableqnsIdList != null) {

			disableqnsIdList.add(qnsId);
		}

	}

	public static void addMesgToValidationMesgList(ValidateMesg mesg) {

		if (validationMesgList != null) {

			validationMesgList.add(mesg);
		}
	}

	public static void addMesgToValidationMesgList(String type, String mesg) {

		ValidateMesg vMesg = new ValidateMesg();
		vMesg.setType(type);
		vMesg.setMesg(mesg);

		addMesgToValidationMesgList(vMesg);

	}

	public static void cleanQnsIdList() {

		// if(!CollectionUtils.isEmpty(qnsIdList)) {
		if (qnsIdList != null) {

			qnsIdList.clear();

		}

	}

	public static void cleanEnableqnsIdList() {

		// if(!CollectionUtils.isEmpty(qnsIdList)) {
		if (enableqnsIdList != null) {

			enableqnsIdList.clear();

		}

	}

	public static void cleanDisableqnsIdList() {

		// if(!CollectionUtils.isEmpty(qnsIdList)) {
		if (disableqnsIdList != null) {

			disableqnsIdList.clear();

		}

	}

	public static void cleanValidationMesgList() {

		if (validationMesgList != null) {

			validationMesgList.clear();
		}

	}

	public static List<String> getQnsListId() {
		return qnsIdList;
	}

	public static List<Integer> getEnableqnsIdList() {
		return enableqnsIdList;
	}

	public static List<Integer> getDisableqnsIdList() {
		return disableqnsIdList;
	}

	/*
	 * Initialize only at the startup
	 */
	public static void initializeDBtoObjectModelMapping(MortgageApplication ma) {

		setDbAttributeToObjectNamesMap(new HashMap<String, String>());

		readAllFields(Arrays.asList(ma.getClass().getDeclaredFields()), ma, null, null,
				getDbAttributeToObjectNamesMap(), getClassName(ma.getClass().getName()));
	}

	public static List<ValidateMesg> getValidationMesgList() {
		return validationMesgList;
	}

	/*
	 * Call in DAO layer to save the object in the DB in attribute = vlaue
	 * format
	 */
	public static Map<String, Object> getObjectToDbAttributeMapping(MortgageApplication ma) {

		Map<String, Object> dbAttributeValuesMap = new HashMap<String, Object>();

		readAllFields(Arrays.asList(ma.getClass().getDeclaredFields()), ma, dbAttributeValuesMap, null, null,
				getClassName(ma.getClass().getName()));

		return dbAttributeValuesMap;
	}

	static void readAllFields(List<Field> fields, Object obj, Map<String, Object> dbFieldValues,
			Annotation parentAnnotation, Map<String, String> dbFieldToObjectModelMappings, String parentClassName) {
		try {

			for (Field field : fields) {

				if (field.getName().equalsIgnoreCase("serialVersionUID"))
					continue;

				// if (field.getType().getName().equalsIgnoreCase("[C"))
				// continue;

				if (java.lang.reflect.Modifier.isStatic(field.getModifiers()))
					continue;

				field.setAccessible(true);

				if (field.getType().isPrimitive() || field.getType().getName().equals("java.lang.String")) {

					// System.out.println("Field Name ->" + field.getName() + "
					// Primitive Value ->" + field.get(obj));
					addDbFieldVaulePrimitive(field, field.get(obj), dbFieldValues, parentAnnotation,
							dbFieldToObjectModelMappings, parentClassName);
				} else if (field.getType().getName().equals("java.util.List")) {

					List<?> listValue;
					if (field.get(obj) != null)
						listValue = (List<?>) field.get(obj);
					else
						listValue = new ArrayList<Object>();
					//Shubhrajit Changed - 25th April
					addDbFieldVaulePrimitive(field, listValue, dbFieldValues, null,
							dbFieldToObjectModelMappings, parentClassName);
				} else if (!field.getType().isArray()) {

					Object childobj = field.get(obj);

					if (childobj != null) {
						List<Field> fieldList = new ArrayList<Field>();
						String className = parentClassName + "@" + getClassName(field.getType().getName());
						readAllFields(getAllFields(fieldList, childobj.getClass()), childobj, dbFieldValues,
								field.getAnnotation(DBEntityMapping.class), dbFieldToObjectModelMappings, className);
					}

				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		if (!type.getSuperclass().getName().equals("java.lang.Object")) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	static void addDbFieldVaulePrimitive(Field field, Object value, Map<String, Object> dbFieldValues,
			Annotation parentAnnotation, Map<String, String> dbFieldToObjectModelMappings, String parentClassName) {

		if (parentAnnotation != null) {
			String entityAnnotation = ((DBEntityMapping) parentAnnotation).attributeName();

			StringTokenizer st = new StringTokenizer(entityAnnotation, "|");

			while (st.hasMoreTokens()) {
				String attrToDbField = st.nextToken();

				StringTokenizer st1 = new StringTokenizer(attrToDbField, "=");

				while (st1.hasMoreTokens()) {
					String attrName = st1.nextToken();
					String dbAttrName = st1.nextToken();
					if (attrName.equals(field.getName())) {

						if (dbFieldValues != null)
							dbFieldValues.put(dbAttrName, value);

						if (dbFieldToObjectModelMappings != null) {
							dbFieldToObjectModelMappings.put(dbAttrName, parentClassName + "@" + field.getName());
							// System.out.println(parentClassName + "@" +
							// field.getName());
						}
					}
				}
			}
		} else if (field.getAnnotation(DBEntityMapping.class) != null) {

			String dbFieldName = field.getAnnotation(DBEntityMapping.class).attributeName();

			if (dbFieldName.indexOf("|") == -1) {

				if (dbFieldValues != null)
					dbFieldValues.put(dbFieldName, value);

				if (dbFieldToObjectModelMappings != null) {
					dbFieldToObjectModelMappings.put(dbFieldName, parentClassName + "@" + field.getName());
					// System.out.println(parentClassName + "@" +
					// field.getName());
				}
			}
		}
	}

	static String getClassName(String fqClassName) {
		return fqClassName.substring(fqClassName.lastIndexOf(".") + 1);
	}

	public static Map<String, String> getDbAttributeToObjectNamesMap() {
		return dbAttributeToObjectNamesMap;
	}

	protected static void setDbAttributeToObjectNamesMap(Map<String, String> dbAttributeToObjectNamesMap) {
		ModelUtils.dbAttributeToObjectNamesMap = dbAttributeToObjectNamesMap;
	}
}