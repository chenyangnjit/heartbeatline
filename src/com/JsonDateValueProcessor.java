package com;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * 
 * Json与Java Bean互相转换时,Bean中的Timestamp字段是无法直接处理的，需要实现两个转换器。
 * DateJsonValueProcessor的作用是Bean转换为Json时将Timepstamp转换为指定的时间格式。
 * @author chenyang
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
	private String format ="HH:mm:ss";
	
	public JsonDateValueProcessor() {
		super();
	}
	
	public JsonDateValueProcessor(String format) {
		super();
		this.format = format;
	}

	@Override
	public Object processArrayValue(Object paramObject,
			JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	@Override
	public Object processObjectValue(String paramString, Object paramObject,
			JsonConfig paramJsonConfig) {
		return process(paramObject);
	}
	
	
	private Object process(Object value){
        if(value instanceof Date){  
            SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.CHINA);  
            return sdf.format(value);
        }  
        return value == null ? "" : value.toString();  
    }

}
