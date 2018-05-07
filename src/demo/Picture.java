package demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

import sun.misc.BASE64Decoder;

/**
 * @date 2018年5月7日 下午12:31:33
 * Description: Picture类
 */
public class Picture {
	
	/**
	 * 
	 * @date 2018年5月7日 下午12:32:14
	 * Description: 通过base64转化为图片
	 */
	public static boolean getPicture(String str) {
		boolean flag = true;
		if(str == null || str == "") flag = false;
		//使用 BASE64Decoder对象报错，查看该博客：https://blog.csdn.net/u011514810/article/details/72725398
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] bytes = decoder.decodeBuffer(str);
			for(int i=0; i<bytes.length; i++) {
				if(bytes[i] < 0) bytes[i] += 256;
			}
			String imgFilePath = "C:\\apache-tomcat-6.0.30\\webapps\\picture-text-demo\\images\\new.jpg";
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @date 2018年5月7日 下午12:34:46
	 * Description: 获取图片中的文字
	 */
	public static String getText() {

		String APP_ID = "";
	   String API_KEY = "";
	   String SECRET_KEY = "";
	   
		// 初始化一个AipOcr
      AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

      // 传入可选参数调用接口
      HashMap<String, String> options = new HashMap<String, String>();
      options.put("language_type", "CHN_ENG");
      options.put("detect_direction", "true");
      options.put("detect_language", "true");
      options.put("probability", "true");
      
      String image = "C:\\apache-tomcat-6.0.30\\webapps\\picture-text-demo\\images\\new.jpg";
      JSONObject res = client.basicGeneral(image, options);
      System.out.println(res);
      JSONArray words = res.getJSONArray("words_result");
      Iterator<Object> it = words.iterator();
      String result = "";
      while(it.hasNext()) {
      	JSONObject ob = (JSONObject) it.next();
      	System.out.println(ob.toString());
      	result += ob.getString("words");
      }
      return result;
	}
}
