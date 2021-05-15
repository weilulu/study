import base.BaseTestNG;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:weilu
 * @Date: 2019/3/18 18:21
 */
public class MethodMonitorTest extends BaseTestNG{

    @Test
    public void testMethodMonitor(){

    }


    public static void main(String[] args) {
        SimpleDateFormat sfYYYYMMDDHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        try {
            System.out.println(sf.format(sfYYYYMMDDHHmmss.parse("2019-06-23 00:00:00")));
            System.out.println(sf.format(sfYYYYMMDDHHmmss.parse("2040-06-23 23:59:59")));
            System.out.println(sf.format(new Date()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
