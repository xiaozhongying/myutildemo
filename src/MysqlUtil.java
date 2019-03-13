package util.mysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * mysql数据库备份
 * @author 钟颖
 *
 */
public class MysqlUtil
{
	/**
	 * 
	 * @param root mysql登录名
	 * @param rootPass 登录密码
	 * @param dbName 需要备份的数据库名称
	 * @param backupsPath 备份的路径
	 * @param backupsSqlFileName 备份文件的名字
	 * @return
	 */
	public static String dbBackUp(String root,String rootPass,String dbName,String backupsPath,String backupsSqlFileName)  
    {  
        String pathSql = backupsPath+backupsSqlFileName;  
        try {  
            File fileSql = new File(pathSql);  
            if(!fileSql.exists()){  
                fileSql.createNewFile();  
            }  
            StringBuffer sbs = new StringBuffer();  
            sbs.append("mysqldump ");  
            sbs.append(" -h 127.0.0.1 ");  
            sbs.append(" -u ");  
            sbs.append(root+" ");  
            sbs.append("-p"+rootPass+" ");  
            sbs.append(dbName);  
            sbs.append(" --default-character-set=utf8 ");  
            sbs.append(" --result-file="+pathSql);  
            System.out.println("cmd命令为：——>>>"+sbs.toString());  
            Runtime runtime = Runtime.getRuntime();  
            Process child = runtime.exec(sbs.toString());  
              
            //读取备份数据并生成临时文件  
            InputStream in = child.getInputStream();  
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(pathSql), "utf8");  
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf8"));  
            String line=reader.readLine();  
            while (line != null) {  
                writer.write(line+"\n");  
                line=reader.readLine();  
             }  
             writer.flush();  
             System.out.println("数据库已备份到——>>"+pathSql);  
        } catch (Exception e) {  
              
        }  
        return pathSql;  
    } 
	/** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
    	//mysqldump -h 192.168.110.10 --user=root --password=fortune123 --lock-all-tables=true --result-file=D:tt.sql\ --default-character-set=utf8 ERP  
        SimpleDateFormat sd=new SimpleDateFormat("yyyyMMddHHmmss");  
        String root = "root";  
        String rootPass = "root";  
        String dbName = "bs";  
        String backupsPath = "E:";  
        String backupsSqlFileName = dbName+sd.format(new Date())+".sql";  
        dbBackUp(root, rootPass, dbName, backupsPath, backupsSqlFileName);  
    }  
  
   
}
