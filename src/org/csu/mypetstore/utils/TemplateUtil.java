package org.csu.mypetstore.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.csu.mypetstore.constant.LocationEnum;
import org.csu.mypetstore.constant.TemplateVault;
import org.csu.mypetstore.repository.RedisCache;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class TemplateUtil {
    private static Configuration configuration;
    
    static{
        //1.创建配置类
        configuration=new Configuration(freemarker.template.Configuration.getVersion());
        //3.设置字符集
        configuration.setDefaultEncoding("utf-8");
        String tplPath= LocationEnum.CLASSPATH+TemplateVault.FOlDER.getLocation();
        File pathfile=new File(tplPath);
        try {
            configuration.setDirectoryForTemplateLoading(pathfile);
        } catch (IOException e) {
            System.out.println("TemplateUtil: TemplateUtil() failed");
            throw new RuntimeException(e);
        }
    }
    public static String MakeProduct(Map<String,String> map, TemplateVault kind) throws IOException, TemplateException {
        //4.加载模板
        String redisKey=RedisUtil.MakeTemplateKeyByName(kind);
        String templateValue= RedisCache.getPlainString(redisKey);
        if(templateValue!=null){
            Template template=new Template(kind.getLocation(),templateValue,configuration);
            Writer out=new StringWriter();
            template.process(map,out);
            out.close();
            return out.toString();
        }
        templateValue=FileUtil.FileToString(kind.getAbsolutePath());
        RedisCache.setPlainValue(redisKey,templateValue);
        //注意，这里configuration已经设置过查找模板的路径了，所以这里的kind.getLocation()是相对路径于模板文件夹的路径
        Template template = configuration.getTemplate(kind.getLocation());
        //5.创建模板
        //6.创建数据模型
        //7.创建Writer对象
        Writer out=new StringWriter();
        //8.输出
        template.process(map,out);
        //9.关闭Writer对象
        out.close();
        return out.toString();
    }

}
