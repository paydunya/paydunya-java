package tk.json.simple.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tk.json.simple.JSONArray;
import tk.json.simple.JSONObject;

public class JSONParser
{
  public static final int S_INIT = 0;
  public static final int S_IN_FINISHED_VALUE = 1;
  public static final int S_IN_OBJECT = 2;
  public static final int S_IN_ARRAY = 3;
  public static final int S_PASSED_PAIR_KEY = 4;
  public static final int S_IN_PAIR_VALUE = 5;
  public static final int S_END = 6;
  public static final int S_IN_ERROR = -1;
  private LinkedList handlerStatusStack;
  private Yylex lexer = new Yylex((Reader)null);
  private Yytoken token = null;
  private int status = 0;
  
  private int peekStatus(LinkedList paramLinkedList)
  {
    if (paramLinkedList.size() == 0) {
      return -1;
    }
    Integer localInteger = (Integer)paramLinkedList.getFirst();
    return localInteger.intValue();
  }
  
  public void reset()
  {
    this.token = null;
    this.status = 0;
    this.handlerStatusStack = null;
  }
  
  public void reset(Reader paramReader)
  {
    this.lexer.yyreset(paramReader);
    reset();
  }
  
  public int getPosition()
  {
    return this.lexer.getPosition();
  }
  
  public Object parse(String paramString)
    throws ParseException
  {
    return parse(paramString, (ContainerFactory)null);
  }
  
  public Object parse(String paramString, ContainerFactory paramContainerFactory)
    throws ParseException
  {
    StringReader localStringReader = new StringReader(paramString);
    try
    {
      return parse(localStringReader, paramContainerFactory);
    }
    catch (IOException localIOException)
    {
      throw new ParseException(-1, 2, localIOException);
    }
  }
  
  public Object parse(Reader paramReader)
    throws IOException, ParseException
  {
    return parse(paramReader, (ContainerFactory)null);
  }
  
  public Object parse(Reader paramReader, ContainerFactory paramContainerFactory)
    throws IOException, ParseException
  {
    reset(paramReader);
    LinkedList localLinkedList1 = new LinkedList();
    LinkedList localLinkedList2 = new LinkedList();
    try
    {
      do
      {
        nextToken();
        Object localObject;
        Map localMap1;
        List localList;
        switch (this.status)
        {
        case 0: 
          switch (this.token.type)
          {
          case 0: 
            this.status = 1;
            localLinkedList1.addFirst(new Integer(this.status));
            localLinkedList2.addFirst(this.token.value);
            break;
          case 1: 
            this.status = 2;
            localLinkedList1.addFirst(new Integer(this.status));
            localLinkedList2.addFirst(createObjectContainer(paramContainerFactory));
            break;
          case 3: 
            this.status = 3;
            localLinkedList1.addFirst(new Integer(this.status));
            localLinkedList2.addFirst(createArrayContainer(paramContainerFactory));
            break;
          case 2: 
          default: 
            this.status = -1;
          }
          break;
        case 1: 
          if (this.token.type == -1) {
            return localLinkedList2.removeFirst();
          }
          throw new ParseException(getPosition(), 1, this.token);
        case 2: 
          switch (this.token.type)
          {
          case 5: 
            break;
          case 0: 
            if ((this.token.value instanceof String))
            {
              localObject = (String)this.token.value;
              localLinkedList2.addFirst(localObject);
              this.status = 4;
              localLinkedList1.addFirst(new Integer(this.status));
            }
            else
            {
              this.status = -1;
            }
            break;
          case 2: 
            if (localLinkedList2.size() > 1)
            {
              localLinkedList1.removeFirst();
              localLinkedList2.removeFirst();
              this.status = peekStatus(localLinkedList1);
            }
            else
            {
              this.status = 1;
            }
            break;
          default: 
            this.status = -1;
          }
          break;
        case 4: 
          switch (this.token.type)
          {
          case 6: 
            break;
          case 0: 
            localLinkedList1.removeFirst();
            localObject = (String)localLinkedList2.removeFirst();
            localMap1 = (Map)localLinkedList2.getFirst();
            localMap1.put(localObject, this.token.value);
            this.status = peekStatus(localLinkedList1);
            break;
          case 3: 
            localLinkedList1.removeFirst();
            localObject = (String)localLinkedList2.removeFirst();
            localMap1 = (Map)localLinkedList2.getFirst();
            localList = createArrayContainer(paramContainerFactory);
            localMap1.put(localObject, localList);
            this.status = 3;
            localLinkedList1.addFirst(new Integer(this.status));
            localLinkedList2.addFirst(localList);
            break;
          case 1: 
            localLinkedList1.removeFirst();
            localObject = (String)localLinkedList2.removeFirst();
            localMap1 = (Map)localLinkedList2.getFirst();
            Map localMap2 = createObjectContainer(paramContainerFactory);
            localMap1.put(localObject, localMap2);
            this.status = 2;
            localLinkedList1.addFirst(new Integer(this.status));
            localLinkedList2.addFirst(localMap2);
            break;
          case 2: 
          case 4: 
          case 5: 
          default: 
            this.status = -1;
          }
          break;
        case 3: 
          switch (this.token.type)
          {
          case 5: 
            break;
          case 0: 
            localObject = (List)localLinkedList2.getFirst();
            ((List)localObject).add(this.token.value);
            break;
          case 4: 
            if (localLinkedList2.size() > 1)
            {
              localLinkedList1.removeFirst();
              localLinkedList2.removeFirst();
              this.status = peekStatus(localLinkedList1);
            }
            else
            {
              this.status = 1;
            }
            break;
          case 1: 
            localObject = (List)localLinkedList2.getFirst();
            localMap1 = createObjectContainer(paramContainerFactory);
            ((List)localObject).add(localMap1);
            this.status = 2;
            localLinkedList1.addFirst(new Integer(this.status));
            localLinkedList2.addFirst(localMap1);
            break;
          case 3: 
            localObject = (List)localLinkedList2.getFirst();
            localList = createArrayContainer(paramContainerFactory);
            ((List)localObject).add(localList);
            this.status = 3;
            localLinkedList1.addFirst(new Integer(this.status));
            localLinkedList2.addFirst(localList);
            break;
          case 2: 
          default: 
            this.status = -1;
          }
          break;
        case -1: 
          throw new ParseException(getPosition(), 1, this.token);
        }
        if (this.status == -1) {
          throw new ParseException(getPosition(), 1, this.token);
        }
      } while (this.token.type != -1);
    }
    catch (IOException localIOException)
    {
      throw localIOException;
    }
    throw new ParseException(getPosition(), 1, this.token);
  }
  
  private void nextToken()
    throws ParseException, IOException
  {
    this.token = this.lexer.yylex();
    if (this.token == null) {
      this.token = new Yytoken(-1, null);
    }
  }
  
  private Map createObjectContainer(ContainerFactory paramContainerFactory)
  {
    if (paramContainerFactory == null) {
      return new JSONObject();
    }
    Map localMap = paramContainerFactory.createObjectContainer();
    if (localMap == null) {
      return new JSONObject();
    }
    return localMap;
  }
  
  private List createArrayContainer(ContainerFactory paramContainerFactory)
  {
    if (paramContainerFactory == null) {
      return new JSONArray();
    }
    List localList = paramContainerFactory.creatArrayContainer();
    if (localList == null) {
      return new JSONArray();
    }
    return localList;
  }
  
  public void parse(String paramString, ContentHandler paramContentHandler)
    throws ParseException
  {
    parse(paramString, paramContentHandler, false);
  }
  
  public void parse(String paramString, ContentHandler paramContentHandler, boolean paramBoolean)
    throws ParseException
  {
    StringReader localStringReader = new StringReader(paramString);
    try
    {
      parse(localStringReader, paramContentHandler, paramBoolean);
    }
    catch (IOException localIOException)
    {
      throw new ParseException(-1, 2, localIOException);
    }
  }
  
  public void parse(Reader paramReader, ContentHandler paramContentHandler)
    throws IOException, ParseException
  {
    parse(paramReader, paramContentHandler, false);
  }
  
  public void parse(Reader paramReader, ContentHandler paramContentHandler, boolean paramBoolean)
    throws IOException, ParseException
  {
    if (!paramBoolean)
    {
      reset(paramReader);
      this.handlerStatusStack = new LinkedList();
    }
    else if (this.handlerStatusStack == null)
    {
      paramBoolean = false;
      reset(paramReader);
      this.handlerStatusStack = new LinkedList();
    }
    LinkedList localLinkedList = this.handlerStatusStack;
    try
    {
      do
      {
        switch (this.status)
        {
        case 0: 
          paramContentHandler.startJSON();
          nextToken();
          switch (this.token.type)
          {
          case 0: 
            this.status = 1;
            localLinkedList.addFirst(new Integer(this.status));
            if (!paramContentHandler.primitive(this.token.value)) {
              return;
            }
            break;
          case 1: 
            this.status = 2;
            localLinkedList.addFirst(new Integer(this.status));
            if (!paramContentHandler.startObject()) {
              return;
            }
            break;
          case 3: 
            this.status = 3;
            localLinkedList.addFirst(new Integer(this.status));
            if (!paramContentHandler.startArray()) {
              return;
            }
            break;
          case 2: 
          default: 
            this.status = -1;
          }
          break;
        case 1: 
          nextToken();
          if (this.token.type == -1)
          {
            paramContentHandler.endJSON();
            this.status = 6;
            return;
          }
          this.status = -1;
          throw new ParseException(getPosition(), 1, this.token);
        case 2: 
          nextToken();
          switch (this.token.type)
          {
          case 5: 
            break;
          case 0: 
            if ((this.token.value instanceof String))
            {
              String str = (String)this.token.value;
              this.status = 4;
              localLinkedList.addFirst(new Integer(this.status));
              if (!paramContentHandler.startObjectEntry(str)) {
                return;
              }
            }
            else
            {
              this.status = -1;
            }
            break;
          case 2: 
            if (localLinkedList.size() > 1)
            {
              localLinkedList.removeFirst();
              this.status = peekStatus(localLinkedList);
            }
            else
            {
              this.status = 1;
            }
            if (!paramContentHandler.endObject()) {
              return;
            }
            break;
          default: 
            this.status = -1;
          }
          break;
        case 4: 
          nextToken();
          switch (this.token.type)
          {
          case 6: 
            break;
          case 0: 
            localLinkedList.removeFirst();
            this.status = peekStatus(localLinkedList);
            if (!paramContentHandler.primitive(this.token.value)) {
              return;
            }
            if (!paramContentHandler.endObjectEntry()) {
              return;
            }
            break;
          case 3: 
            localLinkedList.removeFirst();
            localLinkedList.addFirst(new Integer(5));
            this.status = 3;
            localLinkedList.addFirst(new Integer(this.status));
            if (!paramContentHandler.startArray()) {
              return;
            }
            break;
          case 1: 
            localLinkedList.removeFirst();
            localLinkedList.addFirst(new Integer(5));
            this.status = 2;
            localLinkedList.addFirst(new Integer(this.status));
            if (!paramContentHandler.startObject()) {
              return;
            }
            break;
          case 2: 
          case 4: 
          case 5: 
          default: 
            this.status = -1;
          }
          break;
        case 5: 
          localLinkedList.removeFirst();
          this.status = peekStatus(localLinkedList);
          if (!paramContentHandler.endObjectEntry()) {
            return;
          }
          break;
        case 3: 
          nextToken();
          switch (this.token.type)
          {
          case 5: 
            break;
          case 0: 
            if (!paramContentHandler.primitive(this.token.value)) {
              return;
            }
            break;
          case 4: 
            if (localLinkedList.size() > 1)
            {
              localLinkedList.removeFirst();
              this.status = peekStatus(localLinkedList);
            }
            else
            {
              this.status = 1;
            }
            if (!paramContentHandler.endArray()) {
              return;
            }
            break;
          case 1: 
            this.status = 2;
            localLinkedList.addFirst(new Integer(this.status));
            if (!paramContentHandler.startObject()) {
              return;
            }
            break;
          case 3: 
            this.status = 3;
            localLinkedList.addFirst(new Integer(this.status));
            if (!paramContentHandler.startArray()) {
              return;
            }
            break;
          case 2: 
          default: 
            this.status = -1;
          }
          break;
        case 6: 
          return;
        case -1: 
          throw new ParseException(getPosition(), 1, this.token);
        }
        if (this.status == -1) {
          throw new ParseException(getPosition(), 1, this.token);
        }
      } while (this.token.type != -1);
    }
    catch (IOException localIOException)
    {
      this.status = -1;
      throw localIOException;
    }
    catch (ParseException localParseException)
    {
      this.status = -1;
      throw localParseException;
    }
    catch (RuntimeException localRuntimeException)
    {
      this.status = -1;
      throw localRuntimeException;
    }
    catch (Error localError)
    {
      this.status = -1;
      throw localError;
    }
    this.status = -1;
    throw new ParseException(getPosition(), 1, this.token);
  }
}
