package tk.json.simple;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JSONObject
  extends HashMap
  implements Map, JSONAware, JSONStreamAware
{
  private static final long serialVersionUID = -503443796854799292L;
  
  public static void writeJSONString(Map paramMap, Writer paramWriter)
    throws IOException
  {
    if (paramMap == null)
    {
      paramWriter.write("null");
      return;
    }
    int i = 1;
    Iterator localIterator = paramMap.entrySet().iterator();
    
    paramWriter.write(123);
    while (localIterator.hasNext())
    {
      if (i != 0) {
        i = 0;
      } else {
        paramWriter.write(44);
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramWriter.write(34);
      paramWriter.write(escape(String.valueOf(localEntry.getKey())));
      paramWriter.write(34);
      paramWriter.write(58);
      JSONValue.writeJSONString(localEntry.getValue(), paramWriter);
    }
    paramWriter.write(125);
  }
  
  public void writeJSONString(Writer paramWriter)
    throws IOException
  {
    writeJSONString(this, paramWriter);
  }
  
  public static String toJSONString(Map paramMap)
  {
    if (paramMap == null) {
      return "null";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 1;
    Iterator localIterator = paramMap.entrySet().iterator();
    
    localStringBuffer.append('{');
    while (localIterator.hasNext())
    {
      if (i != 0) {
        i = 0;
      } else {
        localStringBuffer.append(',');
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      toJSONString(String.valueOf(localEntry.getKey()), localEntry.getValue(), localStringBuffer);
    }
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
  
  public String toJSONString()
  {
    return toJSONString(this);
  }
  
  private static String toJSONString(String paramString, Object paramObject, StringBuffer paramStringBuffer)
  {
    paramStringBuffer.append('"');
    if (paramString == null) {
      paramStringBuffer.append("null");
    } else {
      JSONValue.escape(paramString, paramStringBuffer);
    }
    paramStringBuffer.append('"').append(':');
    
    paramStringBuffer.append(JSONValue.toJSONString(paramObject));
    
    return paramStringBuffer.toString();
  }
  
  public String toString()
  {
    return toJSONString();
  }
  
  public static String toString(String paramString, Object paramObject)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    toJSONString(paramString, paramObject, localStringBuffer);
    return localStringBuffer.toString();
  }
  
  public static String escape(String paramString)
  {
    return JSONValue.escape(paramString);
  }
}
