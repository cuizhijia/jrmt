package com.swift.jrmt.testcodeutil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * 
 *	日期		:	2015年11月9日<br>
 *	项目		:	epark<br>
 *	功能		:	<br>
 */


public class TestCodeUtil {

	/**
	 ***********************************************************
	 **
	 ** 使用前请修改数据库连接参数，以及包路径等项
	 **
	 ***********************************************************
	 */
	//====需修改项==========================================================================================================
	private final static String openUrl="G:\\createJAVACode";//生成文件的目录
	private final String bean_path = openUrl+"/entity_bean"; //存放bean的路径
	private final String mapper_path = openUrl+"/entity_mapper";//存放mapper的路径
	private final String xml_path = openUrl+"/entity_mapper/xml";//存放xml的路径
	private final String moduleName = "new_mordencoal"; // 数据库名
	private final String bean_package = "com.swift.jrmt.information.model";//bean顶部的包路径
	private final String mapper_package = "com.swift.jrmt.information.dao";//mapper顶部的包路径
	private final String driverName = "com.mysql.jdbc.Driver";//mysql驱动名
	private final String user = "java";//数据库登录名
	private final String password = "1605sfxd";//数据库登录密码
	private final String url = "jdbc:mysql://dev.mysqldb.56kuaiche.com/" + moduleName + "?characterEncoding=utf8";

	private final String classAuthor="YJB";//类注释里的作者
	private final String projectName="information.tag";//类注释里的 项目名称
	//====需修改项END==========================================================================================================


	/*  暂未生成  controller,service,serviceImpl*/
	private final String controller_path = openUrl+"/controller"; //存放controller的路径
	private final String service_path = openUrl+"/service"; //存放service的路径
	private final String serviceImpl_path = openUrl+"/serviceImpl"; //存放serviceImpl的路径
	private final String controller_package = "--";//controller顶部的包路径
	private final String service_package = "--";//service顶部的包路径
	private final String serviceImpl_package = "--";//serviceImpl顶部的包路径


	private String tableName = null;

	private String beanName = null;

	private String mapperName = null;

	private Connection conn = null;

	private final String type_char = "char";

	private final String type_date = "date";

	private final String type_timestamp = "timestamp";

	private final String type_int = "int";

	private final String type_bigint = "bigint";

	private final String type_text = "text";

	private final String type_bit = "bit";

	private final String type_decimal = "decimal";

	private final String type_blob = "blob";



	/**
	 * 
	 * Description :连接数据库 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void init() throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
	}


	/**
	 *  获取所有的表
	 *
	 * @return
	 * @throws SQLException 
	 */
	private List<String> getTables() throws SQLException {
		List<String> tables = new ArrayList<String>();
		PreparedStatement pstate = conn.prepareStatement("show tables");
		ResultSet results = pstate.executeQuery();
		while ( results.next() ) {
			String tableName = results.getString(1);
			tables.add(tableName);
		}
		return tables;
	}


	/**
	 * 
	 * Description :转换数据库表名为类名,干掉下划线
	 * @param table 表名
	 */
	private void processTable( String table ) {
		StringBuffer sb = new StringBuffer(table.length());
		String tableNew = table.toLowerCase();
		String[] tables = tableNew.split("_");
		String temp = null;
		for ( int i = 0 ; i < tables.length ; i++ ) {
			temp = tables[i].trim();
			sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
		}
		beanName = sb.toString();

		mapperName = beanName + "Mapper";
	}


	/**
	 * 
	 * Description :转换数据类型为java数据类型 
	 * @param type 数据库数据类型
	 * @return
	 */
	private String processType( String type ) {
		if ( type.indexOf(type_char) > -1 ) {
			return "String";
		} else if ( type.indexOf(type_bigint) > -1 ) {
			return "Long";
		} else if ( type.indexOf(type_int) > -1 ) {
			return "Integer";
		} else if ( type.indexOf(type_date) > -1 ) {
			return "java.util.Date";
		} else if ( type.indexOf(type_text) > -1 ) {
			return "String";
		} else if ( type.indexOf(type_timestamp) > -1 ) {
			return "java.util.Date";
		} else if ( type.indexOf(type_bit) > -1 ) {
			return "Boolean";
		} else if ( type.indexOf(type_decimal) > -1 ) {
			return "java.math.BigDecimal";
		} else if ( type.indexOf(type_blob) > -1 ) {
			return "byte[]";
		}
		return null;
	}

	/**
	 * 
	 * Description :获取mybatis数据类型  jdbcType=VARCHAR   
	 * @param type
	 * @return
	 */
	private String getJDBCType( String type ) {
		String typeHead="jdbcType=";
		if ( type.indexOf(type_char) > -1 ) {
			return typeHead+"VARCHAR";
		} else if ( type.indexOf(type_bigint) > -1 ) {
			return "BIGINT";
		} else if ( type.indexOf(type_int) > -1 ) {
			return "INTEGER";
		} else if ( type.indexOf(type_date) > -1 ) {
			return "TIMESTAMP";
		} else if ( type.indexOf(type_text) > -1 ) {
			return "VARCHAR";
		} else if ( type.indexOf(type_timestamp) > -1 ) {
			return "TIMESTAMP";
		} else if ( type.indexOf(type_bit) > -1 ) {
			return "BIT";
		} else if ( type.indexOf(type_decimal) > -1 ) {
			return "DECIMAL";
		} else if ( type.indexOf(type_blob) > -1 ) {
			return "BLOB";
		}
		return null;
	}



	/**
	 * 
	 * Description :转换数据库字段名为javabean属性名 
	 * @param field 数据库字段名
	 * @return
	 */
	private String processField( String field ) {
		StringBuffer sb = new StringBuffer(field.length());
		String[] fields = field.split("_");
		String temp = null;
		sb.append(fields[0]);
		for ( int i = 1 ; i < fields.length ; i++ ) {
			temp = fields[i].trim();
			sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
		}
		return sb.toString();
	}


	/**
	 *  将实体类名首字母改为小写
	 *
	 * @param beanName
	 * @return 
	 */
	private String processResultMapId( String beanName ) {
		return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
	}


	/**
	 *  构建类上面的注释
	 *
	 * @param bw
	 * @param text
	 * @return
	 * @throws IOException 
	 */
	private BufferedWriter buildClassComment( BufferedWriter bw, String text ) throws IOException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		bw.newLine();
		bw.newLine();
		bw.write("/**");
		bw.newLine();
		bw.write(" * 日期  : "+sdf.format(new Date()));
		bw.newLine();
		bw.write(" * 作者  : "+classAuthor);
		bw.newLine();
		bw.write(" * 项目  : "+projectName);
		bw.newLine();
		bw.write(" * 功能  :  "+text);
		bw.newLine();
		bw.write(" * ");
		bw.newLine();
		bw.write(" **/");
		return bw;
	}



	/**
	 * 
	 * Description :构建方法上面的注释 
	 * @param bw
	 * @param text 功能说明
	 * @param params 参数集，多个用逗号隔开
	 * @param paramsComment 参数说明，多个用逗号隔开
	 * @return
	 * @throws IOException
	 */
	private BufferedWriter buildMethodComment( BufferedWriter bw, String text,String params,String paramsComment ) throws IOException {
		int size=0;
		String[] paramsList=null;
		String[] paramsCommentList=null;
		if(null!=params){
			paramsList = params.split(",");
			paramsCommentList = paramsComment.split(",");
			size=paramsList.length;
		}
		bw.newLine();
		bw.write("\t/**");
		bw.newLine();
		bw.write("\t * ");
		bw.newLine();
		bw.write("\t * Description : " + text);
		bw.newLine();
		if(size>0){
			for(int i=0;i<size;i++){
				bw.write("\t * @param "+paramsList[i]+" : "+paramsCommentList[i]);
				bw.newLine();
			}
		}
		bw.write("\t * ");
		bw.newLine();
		bw.write("\t **/");
		return bw;
	}




	/**
	 * 
	 * Description :生成实体类 
	 * @param columns 列名
	 * @param types 类型
	 * @param comments 描述
	 * @param tableComment 表描述
	 * @throws IOException
	 */
	private void buildEntityBean( List<String> columns, List<String> types, List<String> comments, String tableComment )
			throws IOException {
		File folderRoot = new File(openUrl);
		if ( !folderRoot.exists() ) {
			folderRoot.mkdir();
		}

		File folder = new File(bean_path);
		if ( !folder.exists() ) {
			folder.mkdir();
		}

		File beanFile = new File(bean_path, beanName + ".java");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
		bw.write("package " + bean_package + ";");
		bw.newLine();
		bw.write("import java.io.Serializable;");
		bw.newLine();
		bw = buildClassComment(bw, tableComment);
		bw.newLine();
		bw.write("@SuppressWarnings(\"serial\")");
		bw.newLine();
		bw.write("public class " + beanName + " implements Serializable {");
		bw.newLine();
		bw.newLine();
		int size = columns.size();
		for ( int i = 0 ; i < size ; i++ ) {
			bw.write("\t/**" + comments.get(i) + "**/");
			bw.newLine();
			bw.write("\tprivate " + processType(types.get(i)) + " " + processField(columns.get(i)) + ";");
			bw.newLine();
			bw.newLine();
		}
		bw.newLine();
		// 生成get 和 set方法
		String tempField = null;
		String _tempField = null;
		String tempType = null;
		for ( int i = 0 ; i < size ; i++ ) {
			tempType = processType(types.get(i));
			_tempField = processField(columns.get(i));
			tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
			bw.newLine();
			bw.write("\tpublic void set" + tempField + "(" + tempType + " " + _tempField + "){");
			bw.newLine();
			bw.write("\t\tthis." + _tempField + " = " + _tempField + ";");
			bw.newLine();
			bw.write("\t}");
			bw.newLine();
			bw.newLine();
			bw.write("\tpublic " + tempType + " get" + tempField + "(){");
			bw.newLine();
			bw.write("\t\treturn this." + _tempField + ";");
			bw.newLine();
			bw.write("\t}");
			bw.newLine();
		}
		bw.newLine();
		bw.write("}");
		bw.newLine();
		bw.flush();
		bw.close();
	}


	/**
	 * 
	 * Description :构建Mapper文件 
	 * @throws IOException
	 */
	private void buildMapper() throws IOException {
		File folder = new File(mapper_path);
		if ( !folder.exists() ) {
			folder.mkdirs();
		}

		File mapperFile = new File(mapper_path, mapperName + ".java");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
		bw.write("package " + mapper_package + ";");
		bw.newLine();
		bw.newLine();
		bw.write("import " + bean_package + "." + beanName + ";");
		bw.newLine();
		bw.write("import org.apache.ibatis.annotations.Param;");
		bw = buildClassComment(bw, mapperName + "数据库操作接口类");
		bw.newLine();
		bw.newLine();
		bw.write("public interface " + mapperName + "{");
		bw.newLine();
		bw.newLine();
		// ----------定义Mapper中的方法Begin----------
		bw.newLine();
		bw = buildMethodComment(bw, "查询 （获取 "+beanName+" 对象）","queryEntity","查询条件封装");
		bw.newLine();
		bw.write("\t" + beanName+" queryObj( " + beanName + " queryEntity );");
		bw.newLine();
		bw = buildMethodComment(bw, "添加 （匹配有值的字段）","entity","bean");
		bw.newLine();
		bw.write("\t" + "int insert"+beanName+"( " + beanName + " entity );");
		bw.newLine();
		bw = buildMethodComment(bw, "修改 （匹配有值的字段）","entity","bean");
		bw.newLine();
		bw.write("\t" + "int update"+beanName+"( " + beanName + " entity );");
		bw.newLine();
		bw = buildMethodComment(bw, "删除(传入列名，以及要删除的值)","delCloumn,delValue","列名,要删除的值");
		bw.newLine();
		bw.write("\t" + "void deleteByCloumn(@Param(\"delCloumn\")String delCloumn,@Param(\"delValue\")String delValue);");
		bw.newLine();



		bw.newLine();

		// ----------定义Mapper中的方法End----------
		bw.newLine();
		bw.write("}");
		bw.flush();
		bw.close();
	}


	/**
	 *  构建实体类映射XML文件
	 *
	 * @param columns
	 * @param types
	 * @param comments
	 * @throws IOException 
	 */
	private void buildMapperXml( List<String> columns, List<String> types, List<String> comments ) throws IOException {
		File folder = new File(xml_path);
		if ( !folder.exists() ) {
			folder.mkdirs();
		}

		File mapperXmlFile = new File(xml_path, mapperName + ".xml");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();
		bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
		bw.newLine();
		bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
		bw.newLine();
		bw.write("<mapper namespace=\"" + mapper_package + "." + mapperName + "\">");
		bw.newLine();
		bw.newLine();


		buildSQL(bw, columns, types);

		bw.write("</mapper>");
		bw.flush();
		bw.close();
	}


	/**
	 * 
	 * Description :构建xml中的方法 
	 * @param bw
	 * @param columns
	 * @param types
	 * @throws IOException
	 */
	private void buildSQL( BufferedWriter bw, List<String> columns, List<String> types ) throws IOException {
		int size = columns.size();
		String tempField = null;
		// 通用结果列
		bw.write("\t<!-- 通用查询结果列-->");
		bw.newLine();
		bw.write("\t<sql id=\"Base_Column_List\">");
		bw.newLine();

		//bw.write("\t\t id,");
		for ( int i = 0 ; i < size ; i++ ) {
			bw.write("\t\t\t" + columns.get(i));
			if ( i != size - 1 ) {
				bw.write(",");
			}
			bw.newLine();
		}

		bw.newLine();
		bw.write("\t</sql>");
		bw.newLine();
		bw.newLine();

		// 通用and条件列
		bw.write("\t<!-- 通用条件列-->");
		bw.newLine();
		bw.write("\t<sql id=\"Base_And\">");
		bw.newLine();

		for ( int i = 0 ; i < size ; i++ ) {
			tempField = processField(columns.get(i));
			bw.write("\t\t<if test=\"" + tempField + " != null\">");
			bw.newLine();
			bw.write("\t\t  and " + columns.get(i) + "=#{"+tempField+","+getJDBCType(types.get(i))+"}  ");
			bw.newLine();
			bw.write("\t\t</if>");
			bw.newLine();
		}

		bw.newLine();
		bw.write("\t</sql>");
		bw.newLine();
		bw.newLine();

		//---------------  查询单个对象方法queryObj
		bw.write("\t<!-- 查询单个对象方法-->");
		bw.newLine();
		bw.write("\t<select id=\"queryObj\" parameterType=\"" + bean_package+"."+beanName + "\" resultType=\""+bean_package+"."+beanName +" \"  >");
		bw.newLine();
		bw.write("\t\t select <include refid=\"Base_Column_List\"/> ");
		bw.newLine();
		bw.write("\t\t  from "+tableName);
		bw.newLine();
		bw.write("\t\t  where 1=1 <include refid=\"Base_And\"/> ");
		bw.newLine();
		bw.write("\t</select>");
		bw.newLine();
		bw.newLine();
		//---------------  insert方法（匹配有值的字段）
		bw.write("\t<!-- 添加 （匹配有值的字段）-->");
		bw.newLine();
		bw.write("\t<insert id=\"insert"+beanName+"\" parameterType=\"" + bean_package+"."+beanName + "\">");
		bw.newLine();
		bw.write("\t\t INSERT INTO " + tableName);
		bw.newLine();
		bw.write("\t\t <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
		bw.newLine();

		tempField = null;
		for ( int i = 0 ; i < size ; i++ ) {
			tempField = processField(columns.get(i));
			bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
			bw.newLine();
			bw.write("\t\t\t\t " + columns.get(i) + ",");
			bw.newLine();
			bw.write("\t\t\t</if>");
			bw.newLine();
		}

		bw.newLine();
		bw.write("\t\t </trim>");
		bw.newLine();

		bw.write("\t\t <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
		bw.newLine();

		tempField = null;
		for ( int i = 0 ; i < size ; i++ ) {
			tempField = processField(columns.get(i));
			bw.write("\t\t\t<if test=\"" + tempField + "!=null\">");
			bw.newLine();
			bw.write("\t\t\t\t #{" + tempField + ", "+getJDBCType(types.get(i))+"},");
			bw.newLine();
			bw.write("\t\t\t</if>");
			bw.newLine();
		}

		bw.write("\t\t </trim>");
		bw.newLine();
		bw.write("\t</insert>");
		bw.newLine();
		bw.newLine();
		//---------------  完毕


		// 修改update方法
		bw.write("\t<!-- 修 改-->");
		bw.newLine();
		bw.write("\t<update id=\"update"+beanName+"\" parameterType=\"" + bean_package+"."+beanName  + "\">");
		bw.newLine();
		bw.write("\t\t UPDATE " + tableName);
		bw.newLine();
		bw.write(" \t\t <set> ");
		bw.newLine();

		tempField = null;
		for ( int i = 1 ; i < size ; i++ ) {
			tempField = processField(columns.get(i));
			bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
			bw.newLine();
			bw.write("\t\t\t\t " + columns.get(i) + " = #{" + tempField + ", "+getJDBCType(types.get(i))+" },");
			bw.newLine();
			bw.write("\t\t\t</if>");
			bw.newLine();
		}

		bw.newLine();
		bw.write(" \t\t </set>");
		bw.newLine();
		bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + ", "+getJDBCType(types.get(0))+"}");
		bw.newLine();
		bw.write("\t</update>");
		bw.newLine();
		bw.newLine();
		// update方法完毕

		//删除 deleteByCloumn 
		bw.write("\t<!-- 删除-->");
		bw.newLine();
		bw.write("\t<delete id=\"deleteByCloumn\">");
		bw.newLine();
		bw.write("\t\t delete from "+tableName +" where ${delCloumn}=${delValue} ");
		bw.newLine();
		bw.write("\t</delete>");

		bw.newLine();







		bw.newLine();
		bw.newLine();
	}


	/**
	 *  获取所有的数据库表注释
	 *
	 * @return
	 * @throws SQLException 
	 */
	private Map<String, String> getTableComment() throws SQLException {
		Map<String, String> maps = new HashMap<String, String>();
		PreparedStatement pstate = conn.prepareStatement("show table status");
		ResultSet results = pstate.executeQuery();
		while ( results.next() ) {
			String tableName = results.getString("NAME");
			String comment = results.getString("COMMENT");
			maps.put(tableName, comment);
		}
		return maps;
	}


	/**
	 * 
	 * Description : 生成代码
	 * @param creatTable 指定生成代码的表名 ，值为null时则生成全部表的相关代码
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void generate(String creatTable) throws ClassNotFoundException, SQLException, IOException {
		init();
		String prefix = "show full fields from ";
		List<String> columns = null;
		List<String> types = null;
		List<String> comments = null;
		PreparedStatement pstate = null;
		List<String> tables = getTables();
		Map<String, String> tableComments = getTableComment();
		if(null==creatTable){//不指定表名，生成全部
			for ( String table : tables ) {
				columns = new ArrayList<String>();
				types = new ArrayList<String>();
				comments = new ArrayList<String>();
				pstate = conn.prepareStatement(prefix + table);
				ResultSet results = pstate.executeQuery();
				while ( results.next() ) {
					columns.add(results.getString("FIELD"));
					types.add(results.getString("TYPE"));
					comments.add(results.getString("COMMENT"));
				}
				tableName = table;
				processTable(table);
				//          this.outputBaseBean();
				String tableComment = tableComments.get(tableName);
				buildEntityBean(columns, types, comments, tableComment);
				buildMapper();
				buildMapperXml(columns, types, comments);
				System.out.println("====表"+tableName+"==相关代码生成完毕");
			}

		}else{//指定表名

			for ( String table : tables ) {

				if(table.equals(creatTable)){
					columns = new ArrayList<String>();
					types = new ArrayList<String>();
					comments = new ArrayList<String>();
					pstate = conn.prepareStatement(prefix + table);
					ResultSet results = pstate.executeQuery();
					while ( results.next() ) {
						columns.add(results.getString("FIELD"));
						types.add(results.getString("TYPE"));
						comments.add(results.getString("COMMENT"));
					}
					tableName = table;
					processTable(table);
					//          this.outputBaseBean();
					String tableComment = tableComments.get(tableName);
					buildEntityBean(columns, types, comments, tableComment);
					buildMapper();
					buildMapperXml(columns, types, comments);
					System.out.println("====表"+tableName+"==相关代码生成完毕");
				}

			}


		}

		conn.close();
	}




	public static void main( String[] args ) {

		try {
			String creatTable="bid_quote";//数据库表明
			//传入表名，生成制定表的代码，传入null,生成数据库全部表的代码
			new TestCodeUtil().generate(creatTable);
			System.out.println("==============================================================");
			System.out.println("==============================================================");
			System.out.println("====生成代码完毕===请查看："+openUrl);

			// 自动打开生成文件的目录
			Runtime.getRuntime().exec("cmd /c start explorer "+openUrl);



		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch ( IOException e ) {
			e.printStackTrace();
		}


	}





}