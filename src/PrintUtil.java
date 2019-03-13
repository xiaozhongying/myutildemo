package common.utl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import dswork.core.util.TimeUtil;

public class PrintUtil
{
	/**
	 * 写入日志 filePath 日志文件的路径 code 要写入日志文件的内容
	 */
	public static  void print(String filePath, String code) {
		try {
			File tofile = new File(filePath);
			FileWriter fw = new FileWriter(tofile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(TimeUtil.getCurrentTime()+":"+code);
			pw.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
